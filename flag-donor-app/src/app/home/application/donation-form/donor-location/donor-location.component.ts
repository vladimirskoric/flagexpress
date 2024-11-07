import * as _ from 'lodash';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '@shared/services/api.service';
import { DonationFormService } from '@shared/services/donation-form.service';
import { MatSelectChange } from '@angular/material/select';
import { WebStorageService } from '@shared/services/web-storage.service';
import { FormHandlerService } from '@shared/services/form-handler.service';
import { UtilityService } from '@shared/services/utility.service';

@Component({
  selector: 'app-donor-location',
  templateUrl: './donor-location.component.html',
  styleUrls: ['./donor-location.component.scss']
})
export class DonorLocationComponent implements OnInit {

  public isDomestic: boolean;
  public donorLocationForm: FormGroup;
  public domesticLocations: any;
  public mntnc: any = {
    barangays: [],
    cities: [],
    provinces: [],
    countries: [],
  };

  constructor(
    private formBuilder: FormBuilder,
    public formHandlerService: FormHandlerService,
    private router: Router,
    private route: ActivatedRoute,
    private donationService: DonationFormService,
    private webStorageService: WebStorageService,
    private apiService: ApiService,
    private utilityService: UtilityService,
  ) {
  }

  ngOnInit(): void {
    this.isDomestic = this.webStorageService.getDonationOrigin() === 'Domestic';
    if (this.isDomestic) {
      this.domesticForm();
    } else {
      this.internationalForm();
    }
    this.donationService.stepReady(this.donorLocationForm, 'donorLocation');
    this.getMaintenance();
  }

  private async getMaintenance() {
    if (this.isDomestic) {
      this.domesticLocations = await this.apiService.GetProvinces();
      this.setProvince(this.domesticLocations);
    } else {
      this.mntnc.countries = await this.apiService.GetCountries();
    }
  }
  public provinceChange(event: MatSelectChange) {
    this.setCity(event.value);
  }

  // private getMaintenance() {
  //   this.mntnc.barangays = this.apiService.GetBarangays();
  //   this.mntnc.cities = this.apiService.GetCities();
  //   this.mntnc.provinces = this.apiService.GetProvinces();
  //   this.mntnc.countries = this.apiService.GetCountries();
  // }

  public cityChange(event: MatSelectChange) {
    this.setBarangay(event.value);
  }

  private setProvince(locations: any) {
    this.mntnc.provinces = [];
    Object.keys(locations).map((region) => {
      Object.keys(locations[region].province_list).map((province) => {
        this.mntnc.provinces.push({ region_name: region, province_name: province });
      });
    });
    this.mntnc.provinces = _.orderBy(this.mntnc.provinces, ['province_name'], ['asc']);
  }

  private setCity(selectedProvince: any) {
    this.mntnc.cities = [];
    Object.keys(this.domesticLocations[selectedProvince.region_name].province_list[selectedProvince.province_name].municipality_list).map((city) => {
      this.mntnc.cities.push({ region_name: selectedProvince.region_name, province_name: selectedProvince.province_name, city_name: city });
    });
    this.mntnc.cities = _.orderBy(this.mntnc.cities, ['city_name'], ['asc']);
    this.utilityService.enableFormControls(this.donorLocationForm, ['city']);
  }

  private setBarangay(selectedCity: any) {
    this.mntnc.barangays = [];
    this.domesticLocations[selectedCity.region_name].province_list[selectedCity.province_name].municipality_list[selectedCity.city_name].barangay_list.forEach((barangay: string) => {
      this.mntnc.barangays.push({ province_name: selectedCity.province_name, city_name: selectedCity.city_name, barangay_name: barangay });
    });
    this.mntnc.barangays = _.orderBy(this.mntnc.barangays, ['barangay_name'], ['asc']);
    this.utilityService.enableFormControls(this.donorLocationForm, ['barangay']);
  }

  private domesticForm() {
    this.donorLocationForm = this.formBuilder.group({
      address: [null, Validators.required],
      barangay: [null, Validators.required],
      city: [null, Validators.required],
      province: [null, Validators.required],
    });
    this.utilityService.disableFormControls(this.donorLocationForm, ['city', 'barangay']);
  }

  private internationalForm() {
    this.donorLocationForm = this.formBuilder.group({
      country: [null, Validators.required],
      city: [null, Validators.required],
    });
  }

}

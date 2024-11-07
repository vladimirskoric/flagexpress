import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSelectChange } from '@angular/material/select';
import * as _ from 'lodash';
import { ApiService } from '@shared/services/api.service';
import { DonationFormService } from '@shared/services/donation-form.service';
import { UtilityService } from '@shared/services/utility.service';
import { WebStorageService } from '@shared/services/web-storage.service';

@Component({
  selector: 'app-recipient-located-at',
  templateUrl: './recipient-located-at.component.html',
  styleUrls: ['./recipient-located-at.component.scss']
})
export class RecipientLocatedAtComponent implements OnInit {

  public donorRecipientForm: FormGroup;
  public mntnc: any = {
    locations: [],
    barangays: [],
    cities: [],
    provinces: [],
    regions: [],
  };

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private donationService: DonationFormService,
    private apiService: ApiService,
    private utilityService: UtilityService,
    private webStorageService: WebStorageService
  ) {
  }

  ngOnInit(): void {
    this.initForm();
    this.getMaintenance();
  }

  private async getMaintenance() {
    this.mntnc.locations = await this.apiService.GetLocations();
    this.setRegion(this.mntnc.locations);
  }
  public regionChange(event: MatSelectChange) {
    this.setProvince(event.value);
  }
  public provinceChange(event: MatSelectChange) {
    this.setCity(event.value);
  }
  public cityChange(event: MatSelectChange) {
    this.setBarangay(event.value);
  }
  private setRegion(locations: any) {
    this.mntnc.regions = [];
    Object.keys(locations).map((regionKey: string) => {
      let region = this.mntnc.locations[regionKey];
      this.mntnc.regions.push({ name: region.region_name, code: regionKey });
    });
    this.mntnc.regions = _.orderBy(this.mntnc.regions, ['code'], ['asc']);
  }
  private setProvince(selectedRegion: any) {
    this.mntnc.provinces = [];
    Object.keys(this.mntnc.locations[selectedRegion.code].province_list).map((province_name: string) => {
      this.mntnc.provinces.push({
        name: province_name,
      });
    });
    this.mntnc.provinces = _.orderBy(this.mntnc.provinces, ['name'], ['asc']);
    this.utilityService.enableFormControls(this.donorRecipientForm, ['province']);
  }
  private setCity(selectedProvince: any) {
    this.mntnc.cities = [];
    let form = this.donorRecipientForm.value;
    Object.keys(this.mntnc.locations[form.region.code].province_list[form.province.name].municipality_list).map((city_name: string) => {
      this.mntnc.cities.push({ name: city_name });
    });
    this.mntnc.cities = _.orderBy(this.mntnc.cities, ['name'], ['asc']);
    this.utilityService.enableFormControls(this.donorRecipientForm, ['city']);
  }
  private setBarangay(selectedCity: any) {
    this.mntnc.barangays = [];
    let form = this.donorRecipientForm.value;
    let barangay_list = this.mntnc.locations[form.region.code].province_list[form.province.name].municipality_list[selectedCity.name].barangay_list;
    barangay_list.forEach((barangay_name: string) => {
      this.mntnc.barangays.push({ name: barangay_name });
    });
    this.mntnc.barangays = _.orderBy(this.mntnc.barangays, ['name'], ['asc']);
    this.utilityService.enableFormControls(this.donorRecipientForm, ['barangay']);
  }

  private initForm() {
    this.donorRecipientForm = this.formBuilder.group({
      region: [null, Validators.required],
      province: [null, Validators.required],
      city: [null, Validators.required],
      barangay: [null, Validators.required],
      address_line_one: [null, Validators.required],
    });
    this.utilityService.disableFormControls(this.donorRecipientForm, ['province', 'city', 'barangay']);
    this.donationService.stepReady(this.donorRecipientForm, 'donorRecipient');
  }

  submitForm(): void{
    this.donationService.submitDonationForm().subscribe((result) => {
      const ref_code = result.ref_code;
      this.webStorageService.setReferenceCode(ref_code);
      this.webStorageService.setDonationEditing(false);
      this.router.navigate(['../complete'], {relativeTo: this.route})
    });
  }

}

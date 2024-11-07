import { Component, OnInit, Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { NavigationService } from '@shared/services/navigation.service';
import { DonationFormService } from '@shared/services/donation-form.service';
import { ApiService } from 'src/app/_shared/services/api.service';
import { FormHandlerService } from '@shared/services/form-handler.service';
import { UtilityService } from "@shared/services/utility.service";
import { phoneNumberValidator } from '@shared/validators/phone.validator';

@Component({
  selector: 'app-donor-details',
  templateUrl: './donor-details.component.html',
  styleUrls: ['./donor-details.component.scss']
})

@Injectable()
export class DonorDetailsComponent implements OnInit {



  public donorDetailsForm: FormGroup;
  public mntnc: any = {
    sectors: []
  };

  constructor(
    private formBuilder: FormBuilder,
    public formHandlerService: FormHandlerService,
    private donationService: DonationFormService,
    private navigationService: NavigationService,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService,
    private utilityService: UtilityService,
  ) {

  }

  ngOnInit(): void {
    this.navigationService.setTitle({
      title: 'Donation Form',
      toolbar: true,
      isShowAppName: true
    });

    this.donorDetailsForm = this.formBuilder.group({
      representative: [null, Validators.required],
      organization: [null, Validators.required],
      sector: [null, Validators.required],
      mobile: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(64),
      ]],
      landline: [null, [
        Validators.maxLength(7)
      ]],
      isAnonymous: [false],
    });

    this.donationService.stepReady(this.donorDetailsForm, 'donorDetails');
    this.apiService.GetSectors().subscribe((result) => {
      this.mntnc.sectors = result;
    });
  }
}

import { Component, OnInit, Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { NavigationService } from '@shared/services/navigation.service';
import { DonationFormService } from '@shared/services/donation-form.service';
import { ApiService } from '@shared/services/api.service';
import { UtilityService } from "@shared/services/utility.service";
import { Recipient } from "@shared/models/recipient";

@Component({
  selector: 'app-recipient-sent-to',
  templateUrl: './recipient-sent-to.component.html',
  styleUrls: ['./recipient-sent-to.component.scss']
})

@Injectable()
export class RecipientSentToComponent implements OnInit {
  
  public donorRecipientForm: FormGroup;
  sectors: any = [];

  constructor(
    private formBuilder: FormBuilder,
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

    this.donorRecipientForm = this.formBuilder.group({
      affiliation_org: [null, Validators.required],
      contact_person: [null, Validators.required],
      sector_type: [null, Validators.required],
      mobile_number: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(64),
      ]],
      email_address: [null, [
        Validators.required,
        Validators.email,
      ]],
    });

    this.apiService.GetSectors().subscribe((result) => {
      this.sectors = result;
    });

    this.donationService.stepReady(this.donorRecipientForm, 'donorRecipient');
  }

  public getError(controlName: string): string {
    let errorMessage = this.utilityService.formError(this.donorRecipientForm, controlName);
    return errorMessage;
  }

}

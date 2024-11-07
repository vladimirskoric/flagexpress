import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '@shared/services/api.service';
import { DonationFormService } from '@shared/services/donation-form.service';

@Component({
  selector: 'app-recipient-donated-on',
  templateUrl: './recipient-donated-on.component.html',
  styleUrls: ['./recipient-donated-on.component.scss']
})
export class RecipientDonatedOnComponent implements OnInit {

  public donorRecipientForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private donationService: DonationFormService,
    private apiService: ApiService,
  ) {

  }

  ngOnInit(): void {
    this.initForm();
  }
  
  private initForm() {
    this.donorRecipientForm = this.formBuilder.group({
      donated_on: [null, Validators.required],
    });
    this.donationService.stepReady(this.donorRecipientForm, 'donorRecipient');
  }

}

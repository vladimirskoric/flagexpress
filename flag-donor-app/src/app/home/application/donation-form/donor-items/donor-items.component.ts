import * as _ from 'lodash';
import { Component, OnInit } from '@angular/core';
import { DonationFormService } from '@shared/services/donation-form.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/_shared/services/api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {StepperService} from '@shared/services/stepper.service';

@Component({
	selector: 'app-donor-items',
	templateUrl: './donor-items.component.html',
	styleUrls: ['./donor-items.component.scss']
})
export class DonorItemsComponent implements OnInit {

  public showError: boolean;
	public donorItemTypes: any;
	public donorItemTypesForm!: FormGroup;
	public mntnc = {
		itemTypes: []
	};

	constructor(
		private formBuilder: FormBuilder,
  public stepperService: StepperService,
		private donationService: DonationFormService,
		private router: Router,
		private route: ActivatedRoute,
		private apiService: ApiService,
	) {

	}

	ngOnInit(): void {
		this.mntnc.itemTypes = this.apiService.GetItemTypes();
		this.initForm();
	}
	private initForm() {
		let formControls = {};

		if (this.donationService.donorItemTypes) {
			formControls = this.donationService.donorItemTypes;
		} else {
			this.mntnc.itemTypes.forEach((itemTypes: any) => {
				formControls[itemTypes.code] = [0];
			});
		}

		this.donorItemTypesForm = this.formBuilder.group(formControls);
		this.donationService.stepReady(this.donorItemTypesForm, 'donorItemTypes');
	}

  public btnAddQuantity(code: string, index: number) {
    const donorItems = this.donorItemTypesForm.value;
    const newQuantity = donorItems[code] + 1;
    const maxQuantity = this.mntnc.itemTypes[index].maxQuantity;
    if (newQuantity <= maxQuantity) {
      this.fnSetQuantityValue(code, newQuantity);
    }
  }

  public btnMinusQuantity(code: string, index: number) {
    const donorItems = this.donorItemTypesForm.value;
    const newQuantity = donorItems[code] - 1;
    if (newQuantity >= 0) {
      this.fnSetQuantityValue(code, newQuantity);
    }
  }

	private fnSetQuantityValue(code: string, newQuantity: number) {
		const value = {
			[code]: newQuantity
		};
		this.donorItemTypesForm.patchValue(value);
	}

	public next() {
	  if (this.totalDonation > 0) {
      this.stepperService.setProgressBarValue(20, true);
      this.stepperService.matStepper.next();
      this.showError = false;
    } else {
      this.showError = true;
    }
  }

  get totalDonation() {
    return _.sum(_.values(this.donorItemTypesForm.value));
  }
}

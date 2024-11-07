import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {DonationFormService} from '@shared/services/donation-form.service';
import {ApiService} from '@shared/services/api.service';
import {UtilityService} from '@shared/services/utility.service';
import {DonationItem} from '@shared/models/donation-item';
import {CashDonation} from '@shared/models/cash-donation';
import {Service} from '@shared/models/service';
import {FormHandlerService} from '@shared/services/form-handler.service';
import {StepperService} from "@shared/services/stepper.service";

@Component({
  selector: 'app-donor-items-details',
  templateUrl: './donor-items-details.component.html',
  styleUrls: ['./donor-items-details.component.scss']
})
export class DonorItemsDetailsComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    public formHandlerService: FormHandlerService,
    private stepperService: StepperService,
    private donationService: DonationFormService,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService,
    private util: UtilityService,
  ) {

  }

  public donorItemTypes: any;
  public donorItemDetails: any;
  public donorItemDetailsForm!: FormGroup;
  public isEmpty = true;

  /** Form Control Formats */
  private static DonationItemFormat(donationItem: DonationItem): any {
    return {
      donation_type: [donationItem.donation_type || null],
      description: [donationItem.description || null, [Validators.required]],
      unit: [donationItem.unit || "box", [Validators.required]],
      currency: [donationItem.currency || 'PHP', [Validators.required]],
      estimated_cost: [donationItem.estimated_cost || null, [Validators.required]],
      total_unit_value: [donationItem.total_unit_value || null],
      weight: [donationItem.weight || null, [Validators.required]],
      dimension: [donationItem.dimension || null, [Validators.required]],
    };
  }
  private static CashDonationFormat(cashDonation: CashDonation): any {
    return {
      currency: [cashDonation.currency || 'PHP', [Validators.required]],
      amount: [cashDonation.amount, [Validators.required]],
      description: [cashDonation.description],
    };
  }
  private static ServiceFormat(service: Service): any {
    return {
      description: [service.description || null, [Validators.required]],
      start_date: [service.start_date || null, [Validators.required]],
      end_date: [service.end_date || null, [Validators.required]],
    };
  }
  private static RentalFormat(rental: any): any {
    return {
      currency: [rental.currency || "PHP", [Validators.required]],
      description: [rental.description || null, [Validators.required]],
      quantity: [rental.quantity || null, [Validators.required]],
      weight: [rental.weight || null, [Validators.required]],
      total_unit_value: [rental.total_unit_value || null],
      dimension: [rental.dimension || null, [Validators.required]],
      start_date: [rental.start_date || null, [Validators.required]],
      end_date: [rental.end_date || null, [Validators.required]],
    };
  }

  ngOnInit(): void {
    this.initForm();
  }

  /** Commented */
  public btnRemove(key: string, item: any, i: any) {
    console.log('item: ', item);
    console.log('i: ', i);

    const itemDetail = this.donorItemDetailsForm.get(key) as FormArray;
    itemDetail.removeAt(i);
  }

  public applyNewItemTypes(newDonorItemTypes: any) {
    if (newDonorItemTypes) {
      this.donorItemTypes = newDonorItemTypes;
      this.initForm();
    }
  }

  private initForm() {
    if (!this.donorItemTypes) {
      this.isEmpty = true;
      return;
    }
    this.isEmpty = false;
    const formControls = {
      CASH: this.formBuilder.array([]),
      MDS: this.formBuilder.array([]),
      NF: this.formBuilder.array([]),
      Others: this.formBuilder.array([]),
      RENT: this.formBuilder.array([]),
      SV: this.formBuilder.array([]),
      WF: this.formBuilder.array([]),
    };

    this.util.forEach(this.donorItemTypes, (key: string, value: any, index: number) => {
      for (let index = 0; index < value; index++) {
        // const element = array[index];

        const formArrayKey = formControls[key] as FormArray;
        let value: any = {};
        let format: any = {};

        if (this.donationService.donationForm.value.donorItemDetails) {
          value = this.donationService.donationForm.value.donorItemDetails[key][index] || {};
        }

        switch (key) {
          case 'CASH':
            format = DonorItemsDetailsComponent.CashDonationFormat(value);
            formArrayKey.push(this.formBuilder.group(format));
            break;
          case 'SV':
            format = DonorItemsDetailsComponent.ServiceFormat(value);
            formArrayKey.push(this.formBuilder.group(format));
            break;
          case 'RENT':
            format = DonorItemsDetailsComponent.RentalFormat(value);
            formArrayKey.push(this.formBuilder.group(format));
            break;
          default:
            value.donation_type = key;
            format = DonorItemsDetailsComponent.DonationItemFormat(value);
            formArrayKey.push(this.formBuilder.group(format));
        }
      }
    });

    this.donorItemDetailsForm = this.formBuilder.group(formControls);
    this.donationService.stepReady(this.donorItemDetailsForm, 'donorItemDetails');
  }

  public next() {
    this.stepperService.setProgressBarValue(20, true);
    this.stepperService.matStepper.next();
  }

}

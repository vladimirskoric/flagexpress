import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '@shared/services/api.service';
import { UtilityService } from '@shared/services/utility.service';
import { DonorStatus } from 'src/app/_shared/models/donor-status.model';
import { StatusDeliveryDialogComponent } from 'src/app/_shared/components/status-delivery-dialog/status-delivery-dialog.component';

@Component({
  selector: 'app-check-input',
  templateUrl: './check-input.component.html',
  styleUrls: ['./check-input.component.scss']
})
export class CheckInputComponent implements OnInit {
  public checkStatusForm: FormGroup;
  public donationForm: DonorStatus = null;
  public processing: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private apiService: ApiService,
    private utilityService: UtilityService,
  ) {

  }

  ngOnInit(): void {
    this.checkStatusForm = this.formBuilder.group({
      referenceCode: ['', Validators.required]
    });
  }

  async btnSubmit(referenceCode: string | number = null): Promise<void> {
    this.processing = true;
    try {
      let referenceCode = this.checkStatusForm.value.referenceCode;
      this.donationForm = await this.apiService.checkDonorStatus(referenceCode);

      /** TEST: condition subject to change */
      switch (this.donationForm.referenceCode) {
        case "1":   // "Processing Request"
        case "2":   // "Request Approved"
        case "3":   // "Arrived at Warehouse"
        case "4":   // "Request Cancelled"
        case "5":   // "Dispatched"
        case "6":   // "Completed"
          break;
        case "7":
          throw "No Reference Code found!";
        default:
          throw "Something went wrong";
      };

      /** #1 Approach Using Modal */
      // const fullScreenConfig = {
      //   minWidth: '100%',
      //   height: '100%',
      //   panelClass: 'full-screen-modal',
      //   data: this.donationForm,
      // };
      // this.dialog.open(StatusDeliveryDialogComponent, fullScreenConfig);

      /** #2 Re-routing to Child Component */
      let extras = {
        relativeTo: this.route,
        state: {
          data: this.donationForm
        }
      };
      this.router.navigate(['./result-status'], extras);
    } catch (errorMsg) {
      this.utilityService.snackbar(errorMsg);
    }
    this.processing = false;
  }

  get referenceCodeErrorMsg() {
    const referenceCode = this.checkStatusForm.controls.referenceCode;
    if (!referenceCode.touched) {
      return '';
    }

    return referenceCode.hasError('required') ? 'Required' : '';
  }

}

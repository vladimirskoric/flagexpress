import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';

@Component({
  selector: 'app-status-delivery-dialog',
  templateUrl: './status-delivery-dialog.component.html',
  styleUrls: ['./status-delivery-dialog.component.scss']
})
export class StatusDeliveryDialogComponent implements OnInit {
  public donationForm: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) private dialogData: any,
  ) {
    this.donationForm = this.dialogData;
  }

  ngOnInit(): void {
  }

  // private fnCreateDonationForm(response: DonorStatus) {
  //   this.donationForm = this.formBuilder.group({
  //     referenceCode: [response.referenceCode],
  //     locationDonor: [response.locationDonor],
  //     locationRecipient: [response.locationRecipient],
  //     donationDetails: [response.donationDetails],
  //     remarks: [response.remarks],
  //     pickupDate: [response.pickupDate],
  //     pickupTimeFrom: [response.pickupTimeFrom],
  //     pickupTimeTo: [response.pickupTimeTo],
  //   });
  // }

}

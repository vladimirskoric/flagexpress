import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.scss']
})
export class DeliveryComponent implements OnInit {

  deliveryForm: FormGroup;
  minDate = new Date();
  processing: boolean;
  delivered: boolean;

  private errorMessages = {
    required: 'Required',
    maxlength: 'Maximum length exceeded',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<DeliveryComponent>,
    public formBuilder: FormBuilder,
  ) {
    dialogRef.disableClose = true;
    console.log(data);
  }

  ngOnInit(): void {
    this.deliveryForm = this.formBuilder.group({
      dateOfDelivery: ['', [
        Validators.required
      ]],
    });
  }

  async submit(): Promise<void> {
    if (!this.deliveryForm.invalid){
      this.processing = true;
      setTimeout(() => {
        this.processing = false;
        this.delivered = true;
        setTimeout(() => {
          this.dialogRef.close(true);
        }, 3000);
      }, 1000);
    }
  }

  public getError(controlName: string): string {
    const field = this.deliveryForm.get(controlName);
    const errorKeys = Object.keys(field.errors || {});
    if (!errorKeys.length) {
      return null;
    }
    return field.errors[errorKeys[0]].message || this.errorMessages[errorKeys[0]];
  }
}

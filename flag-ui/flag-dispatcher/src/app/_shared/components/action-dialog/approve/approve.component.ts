import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-approve',
  templateUrl: './approve.component.html',
  styleUrls: ['./approve.component.scss']
})
export class ApproveComponent implements OnInit {

  approveForm: FormGroup;
  minDate = new Date();
  processing: boolean;
  approved: boolean;

  private errorMessages = {
    required: 'Required',
    maxlength: 'Maximum length exceeded',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<ApproveComponent>,
    public formBuilder: FormBuilder,
  ) {
    dialogRef.disableClose = true;
    console.log(data);
  }

  ngOnInit(): void {
    this.approveForm = this.formBuilder.group({
      fleet: ['', [
        Validators.required,
        Validators.maxLength(20)
      ]],
      dateOfPickup:['',[
        Validators.required
      ]],
    });
  }

  async submit(): Promise<void> {
    if (!this.approveForm.invalid){
      this.processing = true;
      setTimeout(() => {
        this.processing = false;
        this.approved = true;
        setTimeout(() => {
          this.dialogRef.close(true);
        }, 3000);
      }, 1000);
    }
  }

  public getError(controlName: string): string {
    const field = this.approveForm.get(controlName);
    const errorKeys = Object.keys(field.errors || {});
    if (!errorKeys.length) {
      return null;
    }
    return field.errors[errorKeys[0]].message || this.errorMessages[errorKeys[0]];
  }

}

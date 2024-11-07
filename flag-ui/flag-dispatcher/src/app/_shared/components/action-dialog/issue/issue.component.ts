import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {

  issueForm: FormGroup;
  processing: boolean;
  issue: boolean;

  private errorMessages = {
    required: 'Required',
    maxlength: 'Maximum length exceeded',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<IssueComponent>,
    public formBuilder: FormBuilder,
  ) {
    dialogRef.disableClose = true;
    console.log(data);
  }

  ngOnInit(): void {
    this.issueForm = this.formBuilder.group({
      remarks: ['', [
        Validators.required,
        Validators.maxLength(150)
      ]],
    });
  }

  async submit(): Promise<void> {
    if (!this.issueForm.invalid){
      this.processing = true;
      setTimeout(() => {
        this.processing = false;
        this.issue = true;
        setTimeout(() => {
          this.dialogRef.close(true);
        }, 3000);
      }, 1000);
    }
  }

  public getError(controlName: string): string {
    const field = this.issueForm.get(controlName);
    const errorKeys = Object.keys(field.errors || {});
    if (!errorKeys.length) {
      return null;
    }
    return field.errors[errorKeys[0]].message || this.errorMessages[errorKeys[0]];
  }

}

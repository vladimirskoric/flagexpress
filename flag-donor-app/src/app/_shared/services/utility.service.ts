import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  private errorMessages = {
    required: 'Required',
    minlength: 'Must be at least 8 characters',
    maxlength: 'Maximum length exceeded',
    phoneNumber: 'Not a valid mobile number',
    nonNumeric: 'Numbers are not allowed',
    alphaNumeric: 'Only letters and numbers are allowed',
    nonEmpty: 'Value should not be empty',
    email: 'Input proper email',
  };

  constructor(
    private _snackBar: MatSnackBar
  ) {

  }

  public snackbar(message: string, timerInSeconds: number = 2, action: string = "Close") {
    let timerInMilli = timerInSeconds * 1000;
    this._snackBar.open(message, action, {
      duration: timerInMilli,
    });
  }

  public forEach(object: object, fn: Function) {
    Object.keys(object).forEach(function (key: string, index: any) {
      let value = object[key];
      fn(key, value, index);
    });
  }

  public formError(formInstance: FormGroup, controlName: string): string {
    const field = formInstance.get(controlName);
    if (!field.touched) {
      return null;
    }
    const errorKeys = Object.keys(field.errors || {});
    if (!errorKeys.length) {
      return null;
    }
    return field.errors[errorKeys[0]].message || this.errorMessages[errorKeys[0]];
  }

  public enableFormControls(formInstance: FormGroup, formControlNames: string[]) {
		formControlNames.forEach((controlName: string) => {
			formInstance.controls[controlName].enable();
		});
	}
	public disableFormControls(formInstance: FormGroup, formControlNames: string[]) {
		formControlNames.forEach((controlName: string) => {
			formInstance.controls[controlName].disable();
			formInstance.patchValue({
				[controlName]: null
			});
		});
	}

}
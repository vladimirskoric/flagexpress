import { Injectable } from '@angular/core';
import {FormGroup} from '@angular/forms';
import {StepperService} from '@shared/services/stepper.service';

@Injectable({
  providedIn: 'root'
})
export class FormHandlerService {
  private errorMessages = {
    required: 'Required',
    minlength: 'Must be at least 8 characters',
    maxlength: 'Maximum length exceeded',
    phoneNumber: 'Not a valid mobile number',
    nonNumeric: 'Numbers are not allowed',
    alphaNumeric: 'Only letters and numbers are allowed',
    nonEmpty: 'Value should not be empty',
  };

  constructor(private stepperService: StepperService) { }

  public async next(form: FormGroup): Promise<void> {
    if (form.valid) {
      this.stepperService.setProgressBarValue(20, true);
      this.stepperService.matStepper.next();
    } else {
      this.showFieldErrors(form);
    }
  }
  private showFieldErrors(form: FormGroup) {
    Object.values(form.controls).forEach(control => {
      control.markAsTouched();

      if ((control as any).controls) {
        this.showFieldErrors(control as FormGroup);
      }
    });
  }

  public getError(form: FormGroup, controlName: string): string {
    const field = form.get(controlName);

    if (!field?.touched) {
      return null;
    }

    const errorKeys = Object.keys(field.errors || {});
    if (!errorKeys.length) {
      return null;
    }

    return field.errors[errorKeys[0]].message || this.errorMessages[errorKeys[0]];
  }
}

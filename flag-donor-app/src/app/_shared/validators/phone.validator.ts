import { AbstractControl, ValidationErrors } from '@angular/forms';

export const PHONE_NUMBER_REGEX = /^09[0-9]{9}$/;

export function phoneNumberValidator() {
  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value && control.value.search(PHONE_NUMBER_REGEX) < 0) {
      return { phoneNumber: true };
    }
    return null;
  };
}

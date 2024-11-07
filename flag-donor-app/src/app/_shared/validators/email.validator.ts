import { AbstractControl, ValidationErrors } from '@angular/forms';

export const EMAIL_REGEX = /^[a-zA-Z]+[a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

export function emailValidator() {
  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value && control.value.search(EMAIL_REGEX) < 0) {
      return { email: true };
    }
    return null;
  };
}

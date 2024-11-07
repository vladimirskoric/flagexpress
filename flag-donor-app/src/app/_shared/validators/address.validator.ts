import { AbstractControl, ValidationErrors } from '@angular/forms';

export const ADDRESS_REGEX = /^[a-zA-Z\u00f1\u00d1\d .,'/#-]*$/;

export function addressValidator(fieldName: string = 'input') {
  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value && control.value.search(ADDRESS_REGEX) < 0) {
      return { name: { message: `Invalid ${fieldName} format` } };
    }
    return null;
  };
}

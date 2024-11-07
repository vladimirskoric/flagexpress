import { AbstractControl, ValidationErrors } from '@angular/forms';

export function nonNumericValidator() {
  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value && control.value.search(/[\d]/) > -1) {
      return { nonNumeric : true };
    }
    return null;
  };
}

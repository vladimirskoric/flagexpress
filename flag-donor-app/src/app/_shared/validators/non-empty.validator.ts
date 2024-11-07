import { AbstractControl, ValidationErrors } from '@angular/forms';

export function nonEmptyValidator() {
  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value && control.value.trim().length === 0) {
      return { nonEmpty: true };
    }
    return null;
  };
}

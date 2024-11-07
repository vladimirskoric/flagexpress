import { AbstractControl, ValidationErrors } from '@angular/forms';

export const ALPHA_NUMERIC_REGEX = /^[a-zA-Z0-9]*$/;
export const ALPHA_NUMERIC_HYPHEN_REGEX = /^[a-zA-Z0-9-]*$/;

/**
 * Added optional parameter alphaNumericOnly
 * if set to false
 * validator will allow hyphen(-) as a valid character
 * example: CRN-2131-123123-9 (checks as valid)
 */
export function alphaNumericValidator(fieldName: string = 'input', alphaNumericOnly: boolean = true) {
  return (control: AbstractControl): ValidationErrors | null => {

    const regex = alphaNumericOnly ? ALPHA_NUMERIC_REGEX : ALPHA_NUMERIC_HYPHEN_REGEX;

    if (control.value && control.value.search(regex) < 0) {
      return { alphaNumeric: alphaNumericOnly ? true : { message: `Only letters, numbers, and dash(-) are allowed` } };
    }
    return null;
  };
}

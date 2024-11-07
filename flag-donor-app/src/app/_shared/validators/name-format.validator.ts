import { AbstractControl, ValidationErrors } from '@angular/forms';

/**
 * 1. Names should start with a letter.
 *    Then followed by either a letter, number, space, apostrophe, hyphen, period, or comma
 * 2. Names should only end in either a letter, number or period
 */

export const NAME_REGEX = /^[a-zA-Z\u00f1\u00d1\d][ '-.,a-zA-Z\u00f1\u00d1\d]*$/;
export const ALPHA_PERIOD_REGEX = /^[a-zA-Z\u00f1\u00d1][a-zA-Z\u00f1\u00d1.]*$/;

export function nameFormatValidator(name: string = 'name', alphaPeriodOnly?: boolean) {
  return (control: AbstractControl): ValidationErrors | null => {

    const regex = alphaPeriodOnly ? ALPHA_PERIOD_REGEX : NAME_REGEX;

    if (control.value && control.value.search(regex) < 0) {
      return { name: { message: `Invalid ${ name } format` } };
    } else if (control.value && control.value.slice(-1).search(/[a-zA-Z\u00f1\u00d1\d.]/) < 0) {
      return { name: { message: `Invalid ${ name } format` } };
    }
    return null;
  };
}

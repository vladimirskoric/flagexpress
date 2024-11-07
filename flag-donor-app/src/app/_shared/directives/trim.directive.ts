import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

/**
 * Attribute directive that ensures spaces between words are
 * at most one (or none, if [noSpace]="true" and removes
 * leading and trailing spaces.
 *
 * Apply to `input` and `textarea` as follows:
 * <input formControlName="name" appTrim [noSpace]="true">
 *
 * Required attribute: formControlName
 */
@Directive({
  selector: '[appTrim]'
})
export class TrimDirective {

  @Input() noSpace: boolean;

  constructor(private ctrl: NgControl) {}

  @HostListener('blur') onBlur() {
    const spaceReplacement = this.noSpace === true ? '' : ' ';
    const currentTextValue = this.ctrl.control.value;
    const newTextValue = currentTextValue.trim().replace(/\s+/g, spaceReplacement);

    // Ensure we won't unnecessarily trigger validation
    // Important for fields that trigger async validators
    if (currentTextValue !== newTextValue) {
      this.ctrl.control.setValue(newTextValue);
    }
  }
}

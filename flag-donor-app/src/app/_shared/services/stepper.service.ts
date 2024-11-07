import { Injectable } from '@angular/core';
import {MatStepper} from '@angular/material/stepper';
import {Location} from '@angular/common';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StepperService {

  matStepper: MatStepper;
  currentProgressBarValue: number;
  private progressBarSource = new BehaviorSubject(20);
  progressBarValue = this.progressBarSource.asObservable();

  constructor() {
    this.progressBarValue.subscribe( data => {
      this.currentProgressBarValue = data;
    });
  }

  setProgressBarValue(value: number, isAddValue: boolean) {
    let currentVal = this.currentProgressBarValue;
    if (isAddValue) {
      currentVal += value;
    } else {
      currentVal -= value;
    }
    this.progressBarSource.next(currentVal);
  }


  stepperReady(stepper: MatStepper) {
    this.matStepper = stepper;
  }

}

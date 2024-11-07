import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  private pageTitle = new BehaviorSubject(null);
  currentTitle = this.pageTitle.asObservable();

  constructor() { }

  setTitle(pageTitle: object) {
    this.pageTitle.next(pageTitle);
  }
}

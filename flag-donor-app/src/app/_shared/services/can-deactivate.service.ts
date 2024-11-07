import { Injectable } from '@angular/core';
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Location } from '@angular/common';

export interface CanComponentDeactivate {
  canDeactivate: () => Promise<boolean>;
}

@Injectable()
export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate> {
  constructor(private location: Location, private router: Router) {
  }

  canDeactivate(
    component: CanComponentDeactivate,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState: RouterStateSnapshot) {
    return component.canDeactivate ? component.canDeactivate().then(canDeactivate => {
      if (!canDeactivate && this.router.getCurrentNavigation().trigger === 'popstate') {
        this.location.go(currentState.url);
      }

      return canDeactivate;
    }) : true;
  }
}

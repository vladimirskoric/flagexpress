import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';
import { ApiService } from '@shared/services/api.service';
import { Observable, of, timer } from 'rxjs';
import { fromPromise } from 'rxjs/internal-compatibility';
import { catchError, map, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AsyncValidators {

  constructor(private apiService: ApiService) {
  }

}

import {Inject, Injectable, InjectionToken} from '@angular/core';
import {SESSION_STORAGE, StorageService} from 'ngx-webstorage-service';
import {Observable, Subject} from "rxjs";
import {FormGroup} from "@angular/forms";

export const DONATION_SERVICE_STORAGE = new InjectionToken<StorageService>('DONATION_SERVICE_STORAGE');
const DONATION_OPTION = 'donation_option';
const ORIGIN_KEY = 'donation_origin';
const DONATION_KEY = 'donation_is_editing';
@Injectable()
export class WebStorageService {

  constructor(@Inject(SESSION_STORAGE) private storage: StorageService) {}

  public setDonationOption(data: string) {
    this.storage.set(DONATION_OPTION, data);
  }

  public getDonationOption() {
    return this.storage.get(DONATION_OPTION);
  }

  public setDonationOrigin(data: string) {
    this.storage.set(ORIGIN_KEY, data);
  }

  public getDonationOrigin() {
    return this.storage.get(ORIGIN_KEY);
  }

  public setDonationEditing(data: boolean) {
    this.storage.set(DONATION_KEY, data);
  }

  public getDonationEditing() {
    return this.storage.get(DONATION_KEY);
  }

  public setReferenceCode(refCode: string){
    return this.storage.set('refCode', refCode);
  }

  public getReferenceCode(){
    return this.storage.get('refCode');
  }
}



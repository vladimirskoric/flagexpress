import { Injectable } from '@angular/core';
import {WebStorageService} from '@shared/services/web-storage.service';
import * as moment from 'moment';
import { DonorDetails, DonorLocation } from '../../home/application/donation-form/donation-form.interface';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { DonationRequest } from '@shared/models/donation-request';
import { Beneficiary } from '@shared/models/beneficiary';
import { Address } from '@shared/models/address';

@Injectable({
  providedIn: 'root'
})
export class DonationFormService {

  donorDetails: DonorDetails;
  donorLocation: DonorLocation;
  donorItemTypes: any;
  donorItemDetails: any;
  donorRemark: string = null;
  donorRecipient: string;
  refCode: string;

  donationForm: FormGroup;

  private stepDonorDetailsSource: Subject<FormGroup> = new Subject();
  stepDonorDetails: Observable<FormGroup> = this.stepDonorDetailsSource.asObservable();

  private stepDonorLocationSource: Subject<FormGroup> = new Subject();
  stepDonorLocation: Observable<FormGroup> = this.stepDonorLocationSource.asObservable();

  private stepDonorItemTypesSource: Subject<FormGroup> = new Subject();
  stepDonorItemTypes: Observable<FormGroup> = this.stepDonorItemTypesSource.asObservable();

  private stepDonorItemDetailsSource: Subject<FormGroup> = new Subject();
  stepDonorItemDetails: Observable<FormGroup> = this.stepDonorItemDetailsSource.asObservable();

  private stepDonorRemarkSource: Subject<FormGroup> = new Subject();
  stepDonorRemarks: Observable<FormGroup> = this.stepDonorRemarkSource.asObservable();

  private stepDonorRecipientSource: Subject<FormGroup> = new Subject();
  stepDonorRecipient: Observable<FormGroup> = this.stepDonorRecipientSource.asObservable();

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private webStorageService: WebStorageService) {

    this.donationForm = this.formBuilder.group({
      donorDetails: this.donorDetails,
      donorLocation: this.donorLocation,
      donorItemTypes: this.donorItemTypes,
      donorItemDetails: this.donorItemDetails,
      donorRemark: this.donorRemark,
      donorRecipient: this.donorRecipient,
      donorTcAccepted: false,
    });

    this.stepDonorDetails.subscribe(form =>
      form.valueChanges.subscribe(value => {
        this.donationForm.patchValue({
          donorDetails: value,
        });
      })
    );

    this.stepDonorLocation.subscribe(form =>
      form.valueChanges.subscribe(value => {
        this.donationForm.patchValue({
          donorLocation: value,
        });
      })
    );

    this.stepDonorItemTypes.subscribe(form =>
      form.valueChanges.subscribe(value => {
        this.donationForm.patchValue({
          donorItemTypes: value,
        });
      })
    );

    this.stepDonorItemDetails.subscribe(form =>
      form.valueChanges.subscribe(value => {
        this.donationForm.patchValue({
          donorItemDetails: value,
        });
      })
    );

    this.stepDonorRemarks.subscribe(form =>
      form.valueChanges.subscribe(value => {
        this.donationForm.patchValue({
          donorRemark: value,
        });
      })
    );

    this.stepDonorRecipient.subscribe(form =>
      form.valueChanges.subscribe(value => {
        const mergedValue = this.value.donorRecipient ? Object.assign(this.value.donorRecipient, value) : value;
        this.donationForm.patchValue({
          donorRecipient: mergedValue,
        });
      })
    );
  }

  public stepReady(form: FormGroup, part: string) {
    switch (part) {
      case 'donorDetails': { this.stepDonorDetailsSource.next(form); break; }
      case 'donorLocation': { this.stepDonorLocationSource.next(form); break; }
      case 'donorItemTypes': { this.stepDonorItemTypesSource.next(form); break; }
      case 'donorItemDetails': { this.stepDonorItemDetailsSource.next(form); break; }
      case 'donorRemark': { this.stepDonorRemarkSource.next(form); break; }
      case 'donorRecipient': { this.stepDonorRecipientSource.next(form); break; }
    }
  }

  submitDonationForm(): Observable<any> {
    const endpoint = environment.endpoints.submitDonationForm;
    const { representative,
            organization,
            sector,
            mobile,
            landline,
            isAnonymous } = this.donationForm.get('donorDetails').value;     
    const isInternational = this.webStorageService.getDonationOrigin() === 'International'; 
    const donorLocation  = this.donationForm.get('donorLocation').value;
    const recipient = this.donationForm.get('donorRecipient').value;
    const beneficiary = recipient ? this.getBeneficiary(recipient) : null;
    const request_type = this.webStorageService.getDonationOption();

    const { CASH: cash_donations,
            RENT: rentals,
            SV: services,
            MDS, NF, WF, Others } = this.donationForm.get('donorItemDetails').value;
      
    const data: DonationRequest = {
      donor: {
        contact_person: representative,
        affiliation_org: organization,
        sector_type: sector.code,
        mobile_number: mobile,
        landline_number: landline,
        isAnonymous,
        isInternational,
        email_address: '',
        address: this.getAddress(donorLocation),
      },
      beneficiary,
      donations:
      [
        {
          currency: 'PHP',
          remarks: this.donationForm.get('donorRemark').value.describe,
          donation_items: [],
          cash_donations,
          services,
          rentals,
          donation_date: moment().format('YYYY-MM-DD hh:mm:ss')
        }
      ],
      request_type
    }
    data.donations[0].donation_items = MDS.concat(Others,NF,WF)
      
    return this.http.post(endpoint.url, data);
  }

  getAddress(object): Address{
    const { address: donorAddress, address_line_one, barangay, city, country, province, region } = object;
    const address: Address = {
      address_line_one: address_line_one || donorAddress,
      barangay: barangay ? barangay.name || barangay.barangay_name : null,
      city: typeof city === 'string' ? city : city.name || city.city_name,
      country: country ? country.name : 'Philippines', 
      province: province ? province.name || province.province_name : '',
      region: region ? region.name || region.region_name : null
    }

    return address
  }

  getBeneficiary(recpObject): Beneficiary{
    const { affiliation_org, 
      contact_person, 
      mobile_number, 
      email_address, 
      landline_number, 
      sector_type, ...recp } = recpObject;
    const beneficiary: Beneficiary = { 
      affiliation_org, 
      contact_person, 
      mobile_number, 
      email_address, 
      landline_number, 
      sector_type: sector_type.code, 
      address: this.getAddress(recp) 
    }

    return beneficiary;
  }

  setDonorLocation(location: DonorLocation): void {
    this.donorLocation = location;
  }

  public get value() {
    return this.donationForm.value;
  }

  setDonorRemark(donorRemark: string) {
    this.donorRemark = donorRemark;
  }
}

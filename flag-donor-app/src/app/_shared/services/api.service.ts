import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {DonorStatus} from 'src/app/_shared/models/donor-status.model';
import {Barangay, City, Region} from 'src/app/_shared/models/locations.model';
import {DonationItemTypes} from 'src/app/_shared/models/donation-item';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

  public async checkDonorStatus(referenceCode: string): Promise<any> {
    /**
     * 1 = Processing Request
     * 2 = Request Approved
     * 3 = Arrived at warehouse
     * 4 = Request Cancelled
     * 5 = Dispatched
     * 6 = Completed
     * 7 = Not Found/Invalid
     * 0 = Something went wrong
     */
    const responseDummy: DonorStatus = {
      status: null,
      referenceCode,
      locationDonor: '#140724, Street, Barangay, City, Region',
      locationRecipient: '#140724, Street, Barangay, City, Region',
      donationItems: [
        {
          itemName: 'Foods',
          itemDescription: 'Solid Food (10/box)',
          quantity: 5,
          quantityUnit: 'boxes',
          estimatedWeight: 100,
          estimatedWeightUnit: 'kg',
        }, {
          itemName: 'Non-Perished Food',
          itemDescription: 'Drinking Water (500mL, 10/box)',
          quantity: 20,
          quantityUnit: 'boxes',
          estimatedWeight: 100,
          estimatedWeightUnit: 'kg',
        }, {
          itemName: 'Masks',
          itemDescription: '95N-type (100/box)',
          quantity: 8,
          quantityUnit: 'boxes',
          estimatedWeight: 100,
          estimatedWeightUnit: 'kg',
        }
      ],
      remarks: 'Please handle with care',
      pickupDate: '2020-05-07',
      pickupTimeFrom: '08:00',
      pickupTimeTo: '12:00',
    };
    return new Promise(resolve =>
      setTimeout(() => resolve(responseDummy), 1000)
    );
  }

  /**
   * Maintenance > Donation Form
   */
  public OldSectorOptions() {
    return [
      {
        name: 'PRIVATE',
        value: 'private',
        description: 'Companies, etc.',
        style: {
          button: {
            border: '1px solid gray',
            backgroundColor: '#fff'
          },
          text: {
            color: 'gray'
          }
        }
      },
      {
        name: 'GOVERNMENT',
        value: 'government',
        description: 'Government offices, etc.',
        style: {
          button: {
            border: '1px solid gray',
            backgroundColor: '#fff'
          },
          text: {
            color: 'gray'
          }
        }
      },
      {
        name: 'CIVIL SOCIETY',
        value: 'civil_society',
        description: 'NGOs, etc.',
        style: {
          button: {
            border: '1px solid gray',
            backgroundColor: '#fff'
          },
          text: {
            color: 'gray'
          }
        }
      }
    ];
  }
  public GetSectors(): Observable<any> {
    return this.httpClient.get(environment.endpoints.getSectors.url);
  }

  public GetRecipientSectors(): Observable<any> {
    return this.httpClient.get(environment.endpoints.getRecipientSectors.url);
  }

  public GetBarangays() {
    const barangays: Barangay[] = [
      {
        name: 'Alabang',
        code: null,
      }, {
        name: 'Bayanan',
        code: null,
      }, {
        name: 'Buli',
        code: null,
      }, {
        name: 'Cupang',
        code: null,
      }, {
        name: 'Putatan',
        code: null,
      }
    ];
    return barangays;
  }
  public GetCities() {
    const cities: City[] = [
      {
        name: 'Caloocan',
        code: null,
      }, {
        name: 'Muntinlupa',
        code: null,
      }, {
        name: 'Pasig',
        code: null,
      }, {
        name: 'Quezon City',
        code: null,
      }, {
        name: 'Taguig',
        code: null,
      }
    ];
    return cities;
  }
  public GetLocations(): Promise<any> {
    return this.httpClient.get<any>(`https://raw.githubusercontent.com/flores-jacob/philippine-regions-provinces-cities-municipalities-barangays/master/philippine_provinces_cities_municipalities_and_barangays_2019v2.json`)
      .toPromise();
  }
  public GetProvinces(): Promise<any> {
    return this.httpClient.get<any>(`https://raw.githubusercontent.com/flores-jacob/philippine-regions-provinces-cities-municipalities-barangays/master/philippine_provinces_cities_municipalities_and_barangays_2019v2.json`)
      .toPromise();
  }
  
  public GetRegions() {
    let regions: Region[] = [
      {
        name: "Region I",
        code: "R1",
      }, {
        name: "Region II",
        code: "R2",
      }, {
        name: "Region III",
        code: "R3",
      }, {
        name: "Region IV",
        code: "R4",
      }, {
        name: "Region V",
        code: "R5",
      }
    ];
    return regions;
  }
  public GetCountries(): Promise<any> {
    return this.httpClient.get(environment.endpoints.getCountries.url)
      .toPromise();
  }
  public GetItemTypes() {
    const itemTypes: DonationItemTypes[] = [
      {
        name: 'Cash',
        code: 'CASH',
        maxQuantity: 1,
      }, {
        name: 'Medical Supplies',
        code: 'MDS',
        maxQuantity: 99,
      }, {
        name: 'Water and Foods',
        code: 'WF',
        maxQuantity: 99,
      }, {
        name: 'Non-Food Items',
        code: 'NF',
        maxQuantity: 99,
      }, {
        name: 'Services',
        code: 'SV',
        maxQuantity: 99,
      }, {
        name: 'Rentals',
        code: 'RENT',
        maxQuantity: 99,
      }, {
        name: 'Others',
        code: 'Others',
        maxQuantity: 99,
      }
    ];
    return itemTypes;
  }
  public GetOrigins() {
    const originType: any[] = [
      {
        name: 'International',
        code: 'INT',
      }, {
        name: 'Domestic',
        code: 'DOM',
      }
    ];
    return originType;
  }
}

import { Address } from './address';

export interface Recipient {
    facility_name: string;
    representative: string;
    mobile_number: string;
    email: string;
    address: Address;
    donated_on: Date;
}
import { Address } from './address';
import { Sectors } from 'src/app/home/application/donation-form/donation-form.interface';

export interface Beneficiary {
    contact_person?: string;
    affiliation_org?: string;
    sector_type?: string;
    mobile_number?: string;
    landline_number?: string;
    email_address?: string;
    address?: Address;
}

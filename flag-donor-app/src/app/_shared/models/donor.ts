import { Address } from './address';

export interface Donor {
    contact_person: string;
    affiliation_org: string;
    sector_type: string;
    mobile_number: string;
    landline_number: string;
    email_address?: string;
    isAnonymous: boolean;
    isInternational: boolean;
    address: Address;
}
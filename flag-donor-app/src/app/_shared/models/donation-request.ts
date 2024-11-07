import { Donor } from './donor';
import { Beneficiary } from './beneficiary';
import { Donation } from './donation';

export interface DonationRequest{
    donor: Donor;
    beneficiary?: Beneficiary;
    donations: Donation[];
    request_type: string;
}

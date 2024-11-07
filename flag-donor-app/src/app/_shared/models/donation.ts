import { DonationItem } from './donation-item';
import { CashDonation } from './cash-donation';
import { Service } from './service';
import { Rental } from './rental';

export interface Donation {
    currency?: string;
    value?: number;
    remarks: string;
    photo_reference?: string[]; //array of base64-formatted picture
    file_reference?: string;
    donation_items: DonationItem[];
    cash_donations: CashDonation[];
    services: Service[];
    rentals: Rental[];
    donation_date: string;
}
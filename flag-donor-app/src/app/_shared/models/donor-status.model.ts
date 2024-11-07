export interface DonorStatus {
  status?: string|number;
  referenceCode: string;
  locationDonor: string;
  locationRecipient: string;
  donationItems: DonationItems[];
  photo?: any;
  remarks: string;
  pickupDate: string;
  pickupTimeFrom: string;
  pickupTimeTo: string;
  notes?: string;
}

interface DonationItems {
  itemName: string;
  itemDescription: string;
  quantity: number;
  quantityUnit?: string;
  estimatedWeight: number;
  estimatedWeightUnit: string;
}
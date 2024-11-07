export interface DonationForm {
    donor: {
        donorDetails: DonorDetails
        donorLocation: DonorLocation
    };
}

export interface DonorDetails {
    organization: string;
    sector: string;
    representative: string;
    mobile: string;
    email: string;
    isAnonymous: boolean;
}

export interface DonorLocation {
    address: string;
    barangay: string;
    city: string;
    landmark?: string;
}
export interface Sectors {
    name: string;
    code?: string;
}

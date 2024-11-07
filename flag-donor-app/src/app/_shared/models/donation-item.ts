export interface DonationItem {
    donation_type: string;
    description: string;
    unit: string;
    currency: string;
    estimated_cost: number;
    total_unit_value: number;
    weight: number;
    dimension: string;
}

export interface DonationItemTypes {
    name: string;
    code: string;
    maxQuantity: number;
}
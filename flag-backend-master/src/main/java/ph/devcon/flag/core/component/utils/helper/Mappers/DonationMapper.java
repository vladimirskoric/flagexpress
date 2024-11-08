package ph.devcon.flag.core.component.utils.helper.Mappers;

import java.util.List;
import java.util.stream.Collectors;
import ph.devcon.flag.core.component.donations.application.DonationItemDTO;
import ph.devcon.flag.core.component.donations.application.RentalDTO;
import ph.devcon.flag.core.component.donations.application.ServiceDTO;
import ph.devcon.flag.core.component.donations.application.CashDonationDTO;
import ph.devcon.flag.core.component.donations.application.DonationDTO;
import ph.devcon.flag.core.component.donations.domain.DonationItem;
import ph.devcon.flag.core.component.donations.domain.Rental;
import ph.devcon.flag.core.component.donations.domain.Service;
import ph.devcon.flag.core.component.donations.domain.CashDonation;
import ph.devcon.flag.core.component.donations.domain.Donation;

public final class DonationMapper {

    public static List<DonationDTO> toDonationsDTO(final List<Donation> packages) {
        return packages.stream().map(x -> toDonationDTO(x)).collect(Collectors.toList());
    }

    public static DonationDTO toDonationDTO(final Donation donation) {
        if (donation == null)
            return new DonationDTO();

        return DonationDTO.builder()
                .id(donation.getId())
                .currency(donation.getCurrency().getCode())
                .remarks(donation.getRemarks())
                .photoReference(donation.getPhotoReference().split(","))
                .fileReference(donation.getFileReference())
                .value(donation.getValue())
                .donationDate(donation.getDonationDate())
                .items(toDonationItemsDTOs(donation.getItems()))
                .cashDonations(toCashDonationItemsDTO(donation.getCashDonations()))
                .services(toServiceDonationsDTO(donation.getServiceDonations()))
                .rentals(toRentalDonationsDTO(donation.getRentalDonations()))
                .build();

    }
    public static List<RentalDTO> toRentalDonationsDTO(List<Rental> rentalDonations) {
        return rentalDonations.stream()
                .map(x-> toRentalDonationDTO(x))
                .collect(Collectors.toList());
    }

    public static RentalDTO toRentalDonationDTO(Rental rental){
        return RentalDTO.builder()
        .id(rental.getId())
        .currency(rental.getCurrency().getCode())
        .estimatedCost(rental.getEstimatedCost())
        .description(rental.getDescription())
        .startDate(rental.getStartDate())
        .endDate(rental.getEndDate())
        .build();
    }

    public static List<ServiceDTO> toServiceDonationsDTO(final List<Service> serviceDonations) {
        return serviceDonations.stream()
                .map(x-> toServiceDonatioDTO(x))
                .collect(Collectors.toList());
    }

    public static ServiceDTO toServiceDonatioDTO(final Service service){
        return ServiceDTO.builder()
                .id(service.getId())
                .serviceType(service.getServiceType().getName())
                .currency(service.getCurrency().getCode())
                .estimatedCost(service.getEstimatedCost())
                .description(service.getDescription())
                .hours(service.getHours())
                .startDate(service.getStartDate())
                .endDate(service.getEndDate())
                .build();
    }

    public static List<CashDonationDTO> toCashDonationItemsDTO(final List<CashDonation> cashDonations) {
        return cashDonations.stream()
                .map(x->toCashDonationDTO(x))
                .collect(Collectors.toList());
    }

    public static CashDonationDTO toCashDonationDTO(final CashDonation cd){
        return CashDonationDTO.builder()
                .id(cd.getId())
                .amount(cd.getAmount())
                .currency(cd.getCurrency().getCode())
                .description(cd.getDescription())
                .build();
    }

    public static List<DonationItemDTO> toDonationItemsDTOs(final List<DonationItem> contents) {
        return contents.stream()
                .map(packageContent -> toDonationItemDTO(packageContent))
                .collect(Collectors.toList());
    }

    public static DonationItemDTO toDonationItemDTO(final DonationItem item) {
        return DonationItemDTO.builder()
                .id(item.getId())
                .donationType(item.getDonationType().getName())
                .currency(item.getCurrency().getCode())
                .description(item.getDescription())
                .estimatedCost(item.getEstimatedCost())
                .totalUnitValue(item.getTotalUnitValue())
                .unit(item.getUnit().getName())
                .weight(item.getWeight())
                .dimension(item.getDimension())
                .build();
    }
}
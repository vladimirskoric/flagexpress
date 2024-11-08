package ph.devcon.flag.core.component.reports;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.donations.domain.CashDonation;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.component.donations.domain.DonationItem;
import ph.devcon.flag.core.component.donations.domain.Rental;
import ph.devcon.flag.core.component.donations.domain.Service;
import ph.devcon.flag.core.port.persistence.DonationRequestRepository;

public class YearlyReport extends ReportBase {

    @Override
    public String getFileName() {
        return "YearlyReport.csv";
    }

    @Override
    public String getFields() {
        return "date_reported,donor,donor_sector,donation,donation_type,beneficiary,beneficiary_sector,region";

    }

    @Override
    public List<String> getContents(DonationRequestRepository repo, LocalDateTime start, LocalDateTime end) {
        List<String> data = new ArrayList<String>();

        if(repo == null) 
            return data;

        List<DonationRequest> requests = repo.getAllDonationRequestsByDate(start, end);
        for(DonationRequest item: requests){
                data = AddCashDonations(data, item);
                data = AddItemDonations(data, item);
                data = AddServiceDonations(data, item);
                data = AddRentalDonations(data, item);
        }

        return data;
    }

    private List<String> AddCashDonations(List<String> data, DonationRequest request) {
        for(Donation don: request.getDonations()){
            for(CashDonation cash: don.getCashDonations()){
                String retval = "";
                LocalDateTime dt = LocalDate.parse(request.getCreatedDate().toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
                retval = retval.concat(dt.toString()).concat(",");
                retval = retval.concat(request.getDonor().getContactPerson().concat("/").concat(request.getDonor().getAffiliation())).concat(",");
                retval = retval.concat(request.getDonor().getSectorType().getName()).concat(",");
                retval = retval.concat(cash.getAmount().toString()).concat(",");
                retval = retval.concat(cash.getDonationType().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getContactPerson().concat("/").concat(request.getBeneficiary().getAffiliation())).concat(",");
                retval = retval.concat(request.getBeneficiary().getSector().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getAddress().getRegion());
                data.add(retval);
            }
        }

        return data;
    }

    private List<String> AddItemDonations(List<String> data, DonationRequest request) {
        for(Donation don: request.getDonations()){
            for(DonationItem item: don.getItems()){
                String retval = "";
                LocalDateTime dt = LocalDate.parse(request.getCreatedDate().toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
                retval = retval.concat(dt.toString()).concat(",");
                retval = retval.concat(request.getDonor().getContactPerson().concat("/").concat(request.getDonor().getAffiliation())).concat(",");
                retval = retval.concat(request.getDonor().getSectorType().getName()).concat(",");
                retval = retval.concat(item.getDescription().toString()).concat(",");
                retval = retval.concat(item.getDonationType().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getContactPerson().concat("/").concat(request.getBeneficiary().getAffiliation())).concat(",");
                retval = retval.concat(request.getBeneficiary().getSector().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getAddress().getRegion());
                data.add(retval);
            }
        }

        return data;
    }

    private List<String> AddServiceDonations(List<String> data, DonationRequest request) {
        for(Donation don: request.getDonations()){
            for(Service item: don.getServiceDonations()){
                String retval = "";
                LocalDateTime dt = LocalDate.parse(request.getCreatedDate().toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
                retval = retval.concat(dt.toString()).concat(",");
                retval = retval.concat(request.getDonor().getContactPerson().concat("/").concat(request.getDonor().getAffiliation())).concat(",");
                retval = retval.concat(request.getDonor().getSectorType().getName()).concat(",");
                retval = retval.concat(item.getDescription().toString()).concat(",");
                retval = retval.concat(item.getDonationType().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getContactPerson().concat("/").concat(request.getBeneficiary().getAffiliation())).concat(",");
                retval = retval.concat(request.getBeneficiary().getSector().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getAddress().getRegion());
                data.add(retval);
            }
        }

        return data;
    }

    private List<String> AddRentalDonations(List<String> data, DonationRequest request) {
        for(Donation don: request.getDonations()){
            for(Rental item: don.getRentalDonations()){
                String retval = "";
                LocalDateTime dt = LocalDate.parse(request.getCreatedDate().toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
                retval = retval.concat(dt.toString()).concat(",");
                retval = retval.concat(request.getDonor().getContactPerson().concat("/").concat(request.getDonor().getAffiliation())).concat(",");
                retval = retval.concat(request.getDonor().getSectorType().getName()).concat(",");
                retval = retval.concat(item.getDescription().toString()).concat(",");
                retval = retval.concat(item.getDonationType().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getContactPerson().concat("/").concat(request.getBeneficiary().getAffiliation())).concat(",");
                retval = retval.concat(request.getBeneficiary().getSector().getName()).concat(",");
                retval = retval.concat(request.getBeneficiary().getAddress().getRegion());
                data.add(retval);
            }
        }

        return data;
    }

    @Override
    public ReportFrequency getReportFrequency() {
        return ReportFrequency.Yearly;
    }
}
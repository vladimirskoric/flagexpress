package ph.devcon.flag.core.component.reports;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.donations.domain.CashDonation;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.port.persistence.DonationRequestRepository;

public class CashDonationReport extends ReportBase {

    @Override
    public String getFileName() {
        return "CashDonation.csv";
    }

    @Override
    public String getFields() {
        return "date,donor,amount,remarks";
    }

    @Override
    public List<String> getContents(DonationRequestRepository repo, LocalDateTime start, LocalDateTime end) {
        List<String> data = new ArrayList<String>();

        if(repo == null) 
            return data;

        List<DonationRequest> requests = repo.getAllDonationRequestsByDate(start, end);
        for(DonationRequest item: requests){
            for(Donation don: item.getDonations()){
                for(CashDonation cash: don.getCashDonations()){
                    String retval = "";
                    LocalDateTime dt = LocalDate.parse(don.getDonationDate().toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
                    retval = retval.concat(dt.toString()).concat(",");
                    retval = retval.concat(item.getDonor().getContactPerson().concat("/").concat(item.getDonor().getAffiliation())).concat(",");
                    retval = retval.concat(cash.getAmount().toString()).concat(",");
                    retval = retval.concat(cash.getDescription());
                    data.add(retval);
                }
            }
        }

        return data;
    }

    @Override
    public ReportFrequency getReportFrequency() {
        return ReportFrequency.Daily;
    }
}
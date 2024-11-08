package ph.devcon.flag.core.component.reports;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.component.donations.domain.DonationItem;
import ph.devcon.flag.core.port.persistence.DonationRequestRepository;

public class DispatchReport extends ReportBase {

    @Override
    public String getFileName() {
        return "Dispatch.csv";
    }

    @Override
    public String getFields() {
        return "date,donor,beneficiary,type,warehouse,route,product,qty,uom";

    }

    @Override
    public List<String> getContents(DonationRequestRepository repo, LocalDateTime start, LocalDateTime end) {
        List<String> data = new ArrayList<String>();

        if(repo == null) 
            return data;

        List<DonationRequest> requests = repo.getAllDonationRequestsByDate(start, end);
        for(DonationRequest request: requests){
            if(request.getRequestType().getName().equalsIgnoreCase("ILL_DELIVER_TO_OCD")){
                for(Donation don: request.getDonations()){
                    for(DonationItem item: don.getItems()){
                        String retval = "";
                        LocalDateTime dt = LocalDate.parse(don.getDonationDate().toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
                        retval = retval.concat(dt.toString()).concat(",");
                        retval = retval.concat(request.getDonor().getContactPerson().concat("/").concat(request.getDonor().getAffiliation())).concat(",");
                        retval = retval.concat(request.getBeneficiary().getContactPerson().concat("/").concat(request.getBeneficiary().getAffiliation())).concat(",");
                        retval = retval.concat("Direct Delivery").concat(",");
                        retval = retval.concat("").concat(",");     
                        retval = retval.concat("").concat(","); 
                        retval = retval.concat(item.getDescription()).concat(","); 
                        retval = retval.concat(item.getTotalUnitValue().toString()).concat(",");
                        retval = retval.concat(item.getUnit().getName());
                        data.add(retval);
                    }
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
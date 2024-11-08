package ph.devcon.flag.core.component.reports;

public interface ReportService {
    public void generateCashDonationReport();
    public void generateDomesticDonationReport();
    public void generateInternationalDonationReport();
    public void generateDispatchReport();
    public String generateReportByYear(Integer year);
}
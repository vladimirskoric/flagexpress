package ph.devcon.flag.core.component.reports;

import java.time.LocalDateTime;
import java.time.Month;
import javax.enterprise.context.ApplicationScoped;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class DefaultReportService implements ReportService{

    @Override
    public void generateCashDonationReport() {
       CashDonationReport report = new CashDonationReport();
       report.createCSV(LocalDateTime.now());
    }

    @Override
    public void generateDomesticDonationReport() {
       DomesticDonationReport report = new DomesticDonationReport();
       report.createCSV(null);
    }

    @Override
    public void generateInternationalDonationReport() {
       InternationalDonationReport report = new InternationalDonationReport();
       report.createCSV(null);
    }

    @Override
    public void generateDispatchReport() {
       DispatchReport report = new DispatchReport();
       report.createCSV(null);
    }

    @Override
    public String generateReportByYear(Integer year) {
       log.info(year.toString());
       LocalDateTime date = LocalDateTime.of(year,Month.JANUARY,1,0,0);

       log.info(date.toString());
       YearlyReport report = new YearlyReport();
       String path = report.createCSV(date);

       return path;
    }
}
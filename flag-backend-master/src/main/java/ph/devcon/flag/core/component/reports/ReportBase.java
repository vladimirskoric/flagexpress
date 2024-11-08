package ph.devcon.flag.core.component.reports;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.utils.application.Emailer;
import ph.devcon.flag.core.component.utils.application.SimpleEmailer;
import ph.devcon.flag.core.port.persistence.DonationRequestRepository;
import ph.devcon.flag.infrastructure.persistence.PanacheDonationRequestRepository;

@Slf4j
public abstract class ReportBase {

    private static String reportFolder = "reports";

    public abstract String getFileName();
    public abstract String getFields();
    public abstract ReportFrequency getReportFrequency();

    public abstract List<String> getContents(DonationRequestRepository repository, LocalDateTime start, LocalDateTime end);
    
    public String createCSV(LocalDateTime date) {
        String root = System.getProperty("user.dir");
        
        File reportDirectory = new File(root, reportFolder);
        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        File sub = new File(reportDirectory, (DateTimeFormatter.ISO_DATE).format(LocalDateTime.now()));
        if (!sub.exists()) {
            sub.mkdirs();
        }

        // An output stream accepts output bytes and sends them to sink.
        try {            
            File file = new File(sub, getFileName());
            FileWriter csvWriter = new FileWriter(file);
            csvWriter.append(getFields().concat("\n"));

            date = date == null? LocalDateTime.now(): date;
            LocalDateTime start = LocalDate.parse(date.toString(), DateTimeFormatter.ISO_DATE_TIME).atStartOfDay();
            log.info(start.toString());
            start = start.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            log.info(start.toString());

            LocalDateTime end = null;
            switch(getReportFrequency()){
                case Daily: 
                    end = start.plusDays(1);
                    break;
                
                case Quarterly:
                    Month m = start.getMonth().firstMonthOfQuarter();
                    start = LocalDateTime.of(start.getYear(), m, 1, 0, 0);
                    end = start.plusMonths(3);
                    break;

                case Yearly:
                    start = date;
                    end = start.plusYears(1);
                    break;

				default:
                    end = start.plusDays(1);;
            }

            log.info(end.toString());
     
            for (String rowData : getContents(new PanacheDonationRequestRepository(), start, end)){
                csvWriter.append(rowData);
                csvWriter.append("\n");
            }

            csvWriter.close();

            String path = file.getAbsolutePath();

            Emailer emailer = new SimpleEmailer();
            emailer.sendReport(path);
  
            return path;
        } catch (Exception e) {
            
            e.printStackTrace();
        }  

        return "";
    }

}
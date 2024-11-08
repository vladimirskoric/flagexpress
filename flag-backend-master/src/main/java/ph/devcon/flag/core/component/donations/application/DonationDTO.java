package ph.devcon.flag.core.component.donations.application;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class DonationDTO {
    
    @JsonProperty("id")
    private long id;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("value")
    private double value;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("photo_reference")
    private String[] photoReference;

    @JsonProperty("file_reference")
    private String fileReference;

    @JsonProperty("donation_items")
    private List<DonationItemDTO> items;

    @JsonProperty("cash_donations")
    private List<CashDonationDTO> cashDonations;

    @JsonProperty("services")
    private List<ServiceDTO> services;

    @JsonProperty("rentals")
    private List<RentalDTO> rentals;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("donation_date")
    private LocalDateTime donationDate;
}

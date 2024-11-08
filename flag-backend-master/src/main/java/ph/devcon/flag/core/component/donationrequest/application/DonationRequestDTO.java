package ph.devcon.flag.core.component.donationrequest.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import ph.devcon.flag.core.component.beneficiary.application.BeneficiaryDTO;
import ph.devcon.flag.core.component.donations.application.DonationDTO;
import ph.devcon.flag.core.component.donor.application.DonorDTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Builder
public class DonationRequestDTO{

    @JsonProperty("id")
    private long id;

    @JsonProperty("donor")
    private DonorDTO donorDTO;

    @JsonProperty("beneficiary")
    private BeneficiaryDTO beneficiaryDTO;

    @JsonProperty("donations")
    private List<DonationDTO> donations;

    @JsonProperty("request_type")
    private String requestType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("last_modified")
    private LocalDateTime lastModified;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_time")
    private LocalDateTime createdDate;
}

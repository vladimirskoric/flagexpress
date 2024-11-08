package ph.devcon.flag.core.component.donations.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class CashDonationDTO {
        
    @JsonProperty("id")
    private long id;
  
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("description")
    private String description;
}
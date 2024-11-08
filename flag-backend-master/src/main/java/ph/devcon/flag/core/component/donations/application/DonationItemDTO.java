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
public class DonationItemDTO {
    
    @JsonProperty("id")
    private long id;

    @JsonProperty("donation_type")
    private String donationType;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("estimated_cost")
    private double estimatedCost;

    @JsonProperty("total_unit_value")
    private double totalUnitValue;

    @JsonProperty("weight")
    private double weight;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dimension")
    private String dimension;
}
package ph.devcon.flag.core.component.address.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("address_line_one")
    private String addressLineOne;

    @JsonProperty("barangay")
    private String barangay;

    @JsonProperty("city")
    private String city;

    @JsonProperty("province")
    private String province;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;
}
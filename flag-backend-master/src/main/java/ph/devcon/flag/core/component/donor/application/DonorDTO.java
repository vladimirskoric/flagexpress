package ph.devcon.flag.core.component.donor.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.devcon.flag.core.component.address.application.AddressDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonorDTO {
    
    @JsonProperty("id")
    private long id;

    @JsonProperty("contact_person")
    private String contactPerson;

    @JsonProperty("affiliation_org")
    private String affiliation;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("landline_number")
    private String landlineNumber;

    @JsonProperty("sector_type")
    private String sectorType;

    @JsonProperty("email_address")
    private String email;

    @JsonProperty("isInternational")
    private boolean isInternational;

    @JsonProperty("isAnonymous")
    private boolean isAnonymous;

    @JsonProperty("address")
    private AddressDTO address;
}
package ph.devcon.flag.core.component.beneficiary.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.devcon.flag.core.component.address.application.AddressDTO;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class BeneficiaryDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("contact_person")
    private String contactPerson;

    @JsonProperty("affiliation_org")
    private String affiliation;

    @JsonProperty("sector_type")
    private String sectorType;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("landline_number")
    private String landlineNUmber;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("address")
    private AddressDTO address;
}

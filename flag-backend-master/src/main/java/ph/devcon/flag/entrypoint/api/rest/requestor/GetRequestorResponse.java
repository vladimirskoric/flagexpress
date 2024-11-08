package ph.devcon.flag.entrypoint.api.rest.requestor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response to the get requetor endpoint. This gets serialized into JSON.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRequestorResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("group")
    private String group;

    @JsonProperty("email")
    private String email;

    @JsonProperty("status")
    private String status;

}

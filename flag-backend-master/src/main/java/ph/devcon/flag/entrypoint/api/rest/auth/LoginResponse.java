package ph.devcon.flag.entrypoint.api.rest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The response to the login endpoint. This gets serialized into JSON.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class LoginResponse {

    @JsonProperty("access-code")
    private String accessCode;

    @JsonProperty("refresh-code")
    private String refreshCode;


}

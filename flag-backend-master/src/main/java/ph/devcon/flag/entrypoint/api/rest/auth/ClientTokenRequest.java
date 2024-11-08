package ph.devcon.flag.entrypoint.api.rest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ClientTokenRequest {

    @JsonProperty("client_id")
    @NotBlank
    private String clientID;

    @JsonProperty("client_secret")
    @NotBlank
    private String clientSecret;

}

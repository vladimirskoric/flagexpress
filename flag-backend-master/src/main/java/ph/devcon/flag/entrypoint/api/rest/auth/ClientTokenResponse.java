package ph.devcon.flag.entrypoint.api.rest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ClientTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

}

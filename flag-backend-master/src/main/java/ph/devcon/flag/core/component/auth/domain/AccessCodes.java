package ph.devcon.flag.core.component.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * AccessCodes contains the JWT for both access and refresh.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AccessCodes {

    private String accessToken;
    private String refreshToken;

}

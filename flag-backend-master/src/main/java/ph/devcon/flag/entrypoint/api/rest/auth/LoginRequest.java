package ph.devcon.flag.entrypoint.api.rest.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The request body for the login endpoint. This gets deserialized from JSON.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LoginRequest {

    private String username;
    private String password;

}

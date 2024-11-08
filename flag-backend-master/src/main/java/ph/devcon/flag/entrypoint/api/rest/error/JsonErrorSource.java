package ph.devcon.flag.entrypoint.api.rest.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


/**
 * A JsonError source object. This should point to where in the Json Request the error occurred. This gets serialized
 * into JSON.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public final class JsonErrorSource {

    private String pointer;

    private String parameter;

}

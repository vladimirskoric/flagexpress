package ph.devcon.flag.entrypoint.api.rest.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


/**
 * A JsonError object describing the error in the API. This gets serialized into JSON.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public final class JsonError {

    private String id;

    private Map<String, Object> links;

    private String status;

    private String code;

    private String title;

    private String detail;

    private List<JsonErrorSource> source;

    private Map<String, Object> meta;

    public static JsonError simple(final String status, final String title, final String detail) {
        return JsonError.builder().status(status).title(title).detail(detail).build();
    }

}
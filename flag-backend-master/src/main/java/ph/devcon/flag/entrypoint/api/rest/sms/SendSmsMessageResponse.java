package ph.devcon.flag.entrypoint.api.rest.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SendSmsMessageResponse {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("status")
    private String status;

}

package ph.devcon.flag.entrypoint.api.rest.sms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SendSmsMessageRequest {

    private String number;
    private String message;

}

package ph.devcon.flag.core.component.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SmsConfirmation {

    private String flagMessageId;

    private String providerMessageId;

    private String status;

}

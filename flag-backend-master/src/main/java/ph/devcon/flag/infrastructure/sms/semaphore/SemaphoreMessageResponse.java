package ph.devcon.flag.infrastructure.sms.semaphore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SemaphoreMessageResponse {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user")
    private String user;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("account")
    private String account;

    @JsonProperty("recipient")
    private String recipient;

    @JsonProperty("message")
    private String message;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("network")
    private String network;

    @JsonProperty("status")
    private String status;

    @JsonProperty("type")
    private String type;

    @JsonProperty("source")
    private String source;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

}

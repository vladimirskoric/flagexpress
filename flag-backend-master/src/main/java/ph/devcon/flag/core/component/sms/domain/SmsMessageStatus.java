package ph.devcon.flag.core.component.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sms_message_statuses")
public class SmsMessageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "flag_message_status_id")
    private String flagMessageStatusId;

    @Column(name = "provider_message_status_id")
    private String providerMessageStatusId;

    @Column(name = "status_type")
    private String statusType;

}

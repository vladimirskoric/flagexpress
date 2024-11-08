package ph.devcon.flag.core.component.requestor.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity(name = "requestor_statuses")
public class RequestorStatus {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "key")
    private String key;

    @Column(name = "description")
    private String description;

    public RequestorStatus() {
    }
}

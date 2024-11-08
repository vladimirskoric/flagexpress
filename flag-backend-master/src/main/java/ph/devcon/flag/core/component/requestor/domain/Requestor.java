package ph.devcon.flag.core.component.requestor.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.EAGER;


@Data
@Entity(name = "requestors")
public class Requestor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @ManyToOne(targetEntity = RequestorType.class, fetch = EAGER)
    @JoinColumn(name = "requestor_type_id")
    private RequestorType requestorType;

    @Column(name = "group")
    private String group;

    @Column(name = "email")
    private String email;

    @ManyToOne(targetEntity = RequestorStatus.class, fetch = EAGER)
    @JoinColumn(name = "requestor_status_id")
    private RequestorStatus requestorStatus;

    public Requestor() {
    }

}

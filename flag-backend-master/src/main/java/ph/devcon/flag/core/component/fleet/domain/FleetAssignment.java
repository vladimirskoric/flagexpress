package ph.devcon.flag.core.component.fleet.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity(name = "fleet_assignments")
public class FleetAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(targetEntity = Fleet.class, fetch = EAGER)
    @JoinColumn(name = "fleet_id")
    private Fleet fleet;

    @Column(name = "fleet_leader")
    private String fleetLeader;

    @Column(name = "fleet_contact")
    private String fleetContact;

    @Column(name = "created_time")
    private Date createdTime;

    public FleetAssignment() {
    }

}

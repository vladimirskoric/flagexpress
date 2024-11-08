// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;
// import ph.devcon.flag.core.component.contactdetails.domain.ContactDetails;
// import ph.devcon.flag.core.component.fleet.domain.FleetAssignment;

// import javax.persistence.*;

// import java.time.LocalDateTime;
// import java.util.List;

// import static javax.persistence.FetchType.EAGER;

// @Data
// @Entity(name = "inbound_dispatches")
// public class InboundDispatch {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @ManyToOne(targetEntity = ContactDetails.class, fetch = EAGER, cascade = CascadeType.ALL)
//     @JoinColumn(name = "contact_details_id")
//     private ContactDetails contactDetails;

//     @ManyToOne(targetEntity = FleetAssignment.class, fetch = EAGER, cascade = CascadeType.ALL)
//     @JoinColumn(name = "fleet_assignment_id")
//     private FleetAssignment fleetAssignment;
    
//     @OneToMany(cascade = CascadeType.ALL,  mappedBy="inboundDispatch")
//     private List<PickupSchedule> schedules;

//     @OneToMany(cascade = CascadeType.ALL,  mappedBy="inboundDispatch")
//     private List<PickupStatusHistory> statusHistoryList;

//     @Column(name = "photo_reference")
//     private String photoReference;

//     @Column(name = "request_remark")
//     private String remark;

//     @Column(name = "created_time")
//     private LocalDateTime createdTime;

//     public InboundDispatch() {
//     }
// }

// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;
// import ph.devcon.flag.core.component.contactdetails.domain.ContactDetails;
// import ph.devcon.flag.core.component.fleet.domain.FleetAssignment;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;

// import java.time.LocalDateTime;
// import java.util.List;

// import static javax.persistence.FetchType.EAGER;

// @Data
// @Entity(name = "outbound_dispatches")
// public class OutboundDispatch {

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

//     @OneToMany(cascade = CascadeType.ALL,  mappedBy="outboundDispatch")
//     private List<DeliverySchedule> schedules;

//     @OneToMany(cascade = CascadeType.ALL,  mappedBy="outboundDispatch")
//     private List<DeliveryStatusHistory> statusHistoryList;

//     @OneToMany(cascade = CascadeType.ALL,  mappedBy="outboundDispatch")
//     private List<DispatchDetails> dispatchDetails;

//     @Column(name = "photo_reference")
//     private String photoReference;

//     @Column(name = "delivery_remark")
//     private String remark;

//     @Column(name = "created_time")
//     private LocalDateTime createdTime;

//     public OutboundDispatch() {
//     }
// }

// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// import java.time.LocalDateTime;

// import static javax.persistence.FetchType.EAGER;

// @Data
// @Entity(name = "pickup_status_history")
// public class PickupStatusHistory {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @ManyToOne(targetEntity = PickupStatus.class, fetch = EAGER)
//     @JoinColumn(name = "status_id")
//     private PickupStatus pickupStatus;

//     @ManyToOne(targetEntity = InboundDispatch.class, fetch = EAGER)
//     @JoinColumn(name = "inbound_id")
//     private InboundDispatch inboundDispatch;

//     @Column(name = "pickup_status_remark")
//     private String remark;

//     @Column(name = "is_active")
//     private Boolean isActive;

//     @Column(name = "created_time")
//     private LocalDateTime createdTime;

//     public PickupStatusHistory() {
//     }
// }

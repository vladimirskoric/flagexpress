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
// @Entity(name = "delivery_status_history")
// public class DeliveryStatusHistory {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @ManyToOne(targetEntity = DeliveryStatus.class, fetch = EAGER)
//     @JoinColumn(name = "status_id")
//     private DeliveryStatus deliveryStatus;

//     @ManyToOne(targetEntity = OutboundDispatch.class, fetch = EAGER)
//     @JoinColumn(name = "outbound_id")
//     private OutboundDispatch outboundDispatch;

//     @Column(name = "delivery_status_remark")
//     private String remark;

//     @Column(name = "is_active")
//     private Boolean isActive;

//     @Column(name = "created_time")
//     private LocalDateTime createdTime;

//     public DeliveryStatusHistory() {
//     }
// }

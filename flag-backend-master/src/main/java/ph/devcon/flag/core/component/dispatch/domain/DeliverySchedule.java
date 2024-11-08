// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// import static javax.persistence.FetchType.EAGER;

// import java.time.LocalDate;
// import java.time.LocalTime;

// @Data
// @Entity(name = "delivery_schedules")
// public class DeliverySchedule {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @ManyToOne(targetEntity = OutboundDispatch.class, fetch = EAGER)
//     @JoinColumn(name = "dispatch_id")
//     private OutboundDispatch outboundDispatch;

//     @Column(name ="description")
//     private String description;
    
//     @Column(name = "delivery_date")
//     private LocalDate deliveryDate;

//     @Column(name = "schedule_start")
//     private LocalTime scheduleStart;

//     @Column(name = "schedule_end")
//     private LocalTime scheduleEnd;

//     @Column(name = "is_active")
//     private Boolean isActive;

//     public DeliverySchedule() {
//     }
// }

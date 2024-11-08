// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;

// import javax.persistence.*;

// import static javax.persistence.FetchType.EAGER;

// import java.time.LocalDate;
// import java.time.LocalTime;

// @Data
// @Entity(name = "pickup_schedules")
// public class PickupSchedule {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @ManyToOne(targetEntity = InboundDispatch.class, fetch = EAGER, cascade = CascadeType.ALL)
//     @JoinColumn(name = "pickup_id")
//     private InboundDispatch inboundDispatch;

//     @Column(name ="description")
//     private String description;

//     @Column(name = "pickup_date")
//     private LocalDate pickupDate;

//     @Column(name = "schedule_start")
//     private LocalTime scheduleStart;

//     @Column(name = "schedule_end")
//     private LocalTime scheduleEnd;

//     @Column(name = "is_active")
//     private Boolean isActive;

//     public PickupSchedule() {
//     }
// }

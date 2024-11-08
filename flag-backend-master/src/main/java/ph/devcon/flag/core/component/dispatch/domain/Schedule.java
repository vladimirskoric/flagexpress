// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;

// import java.time.LocalTime;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

// @Data
// @Entity(name = "schedules")
// public class Schedule {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @Column(name = "description")
//     private String description;

//     @Column(name = "schedule_start")
//     private LocalTime scheduleStart;

//     @Column(name = "schedule_end")
//     private LocalTime scheduleEnd;

//     public Schedule() {
//     }
// }

// package ph.devcon.flag.core.component.dispatch.application.DTO;

// import java.time.LocalDateTime;
// import java.time.LocalTime;

// import com.fasterxml.jackson.annotation.JsonInclude;
// import com.fasterxml.jackson.annotation.JsonProperty;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import lombok.Builder;

// import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @JsonInclude(NON_NULL)
// public class DeliveryScheduleDTO{

//     @JsonProperty("id")
//     private long id;

//     @JsonProperty("dispatch_id")
//     private long dispatchId;
    
//     @JsonProperty("delivery_date")
//     private LocalDateTime deliveryDate;

//     @JsonProperty("description")
//     private String description;

//     @JsonProperty("schedule_start")
//     private LocalTime scheduleStart;

//     @JsonProperty("schedule_end")
//     private LocalTime scheduleEnd;

//     @JsonProperty("is_active")
//     private Boolean isActive;
// }

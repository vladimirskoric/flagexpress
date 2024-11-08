// package ph.devcon.flag.core.component.dispatch.application.DTO;

// import com.fasterxml.jackson.annotation.JsonProperty;

// import java.time.LocalDateTime;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import lombok.Builder;

// import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

// import com.fasterxml.jackson.annotation.JsonInclude;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @JsonInclude(NON_NULL)
// public class PickupStatusHistoryDTO {
        
//     @JsonProperty("id")
//     private long id;

//     @JsonProperty("status_id")
//     private long pickupStatus;

//     @JsonProperty("pickup_status_remark")
//     private String remark;

//     @JsonProperty("is_active")
//     private Boolean isActive;

//     @JsonProperty("inbound_id")
//     private long inBoundId;

//     @JsonProperty("created_time")
//     private LocalDateTime createdTime;
// }
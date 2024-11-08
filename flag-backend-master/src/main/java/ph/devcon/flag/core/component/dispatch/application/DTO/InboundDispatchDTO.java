// package ph.devcon.flag.core.component.dispatch.application.DTO;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonInclude;
// import com.fasterxml.jackson.annotation.JsonProperty;

// import org.springframework.format.annotation.DateTimeFormat;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;

// import ph.devcon.flag.core.component.contactdetails.application.ContactDetailsDTO;
// import ph.devcon.flag.core.component.donations.application.DonationDTO;
// import ph.devcon.flag.core.component.fleet.application.FleetAssignmentDTO;
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
// public class InboundDispatchDTO {
    
//     @JsonProperty("id")
//     private long id;

//     @JsonProperty("contact_details")
//     private ContactDetailsDTO contactDetails;

//     @JsonProperty("fleet_assignment")
//     private FleetAssignmentDTO fleetAssignmentDTO;

//     @JsonProperty("packages")
//     private List<DonationDTO> packages;

//     @JsonProperty("fleet")
//     private String fleetName;

//     @JsonProperty("photo_reference")
//     private String photoReference;

//     @JsonProperty("request_remark")
//     private String remark;

//     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//     @JsonFormat(pattern = "yyyy-MM-dd")
//     @JsonProperty("pickup_schedule")
//     private LocalDate pickupSchedule;

//     @JsonProperty("pickup_time")
//     private String pickupTime;

//     @JsonProperty("pickup_status")
//     private String pickupStatus;

//     @JsonProperty("pickup_status_remark")
//     private String pickupStatusRemark;

//     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//     @JsonFormat(pattern = "yyyy-MM-dd")
//     @JsonProperty("created_time")
//     private LocalDateTime createdTime;
// }

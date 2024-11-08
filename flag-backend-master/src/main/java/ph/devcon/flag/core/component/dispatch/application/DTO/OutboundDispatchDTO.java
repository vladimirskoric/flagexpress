// package ph.devcon.flag.core.component.dispatch.application.DTO;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonInclude;
// import com.fasterxml.jackson.annotation.JsonProperty;

// import org.springframework.format.annotation.DateTimeFormat;

// import ph.devcon.flag.core.component.contactdetails.application.ContactDetailsDTO;
// import ph.devcon.flag.core.component.donations.application.DonationDTO;
// import ph.devcon.flag.core.component.fleet.application.FleetAssignmentDTO;

// import java.time.LocalDateTime;
// import java.util.List;

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
// public class OutboundDispatchDTO {
    
//     @JsonProperty("id")
//     private long id;

//     @JsonProperty("contact_details")
//     private ContactDetailsDTO contactDetails;

//     @JsonProperty("fleet_assignment")
//     private FleetAssignmentDTO fleetAssignmentDTO;

//     @JsonProperty("packages")
//     private List<DonationDTO> packages;

//     @JsonProperty("dispatch_details")
//     private List<DispatchDetailsDTO> dispatchDetails;

//     @JsonProperty("fleet")
//     private String fleetName;

//     @JsonProperty("photo_reference")
//     private String photoReference;

//     @JsonProperty("delivery_status_remark")
//     private String remark;

//     @JsonProperty("delivery_schedule")
//     private LocalDateTime deliverySchedule;

//     @JsonProperty("delivery_time")
//     private String deliveryTime;

//     @JsonProperty("delivery_status")
//     private String deliveryStatus;

//     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//     @JsonFormat(pattern = "yyyy-MM-dd")
//     @JsonProperty("created_time")
//     private LocalDateTime createdTime;
// }

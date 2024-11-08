// package ph.devcon.flag.core.component.dispatch.application.DTO;

// import com.fasterxml.jackson.annotation.JsonProperty;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import ph.devcon.flag.core.component.donations.application.DonationItemDTO;
// import lombok.AllArgsConstructor;
// import lombok.Builder;

// import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


// import com.fasterxml.jackson.annotation.JsonInclude;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @JsonInclude(NON_NULL)
// public class DispatchDetailsDTO {
        
//     @JsonProperty("id")
//     private long id;

//     //this is for tracking purpose so we know at what package content do we deduct the quantity of the dispatch
//     @JsonProperty("item_code")
//     private String itemCode;

//     @JsonProperty("package_content")
//     private DonationItemDTO packageContent;

//     @JsonProperty("unit")
//     private String unit;

//     @JsonProperty("unit_value")
//     private double unitValue;
// }
// package ph.devcon.flag.core.component.utils.helper.Mappers;

// import java.util.List;
// import java.util.stream.Collectors;

// import ph.devcon.flag.core.component.dispatch.application.DTO.*;
// import ph.devcon.flag.core.component.dispatch.domain.*;

// public final class DispatchMapper {

//     public static List<OutboundDispatchDTO> toOBDispatches(List<OutboundDispatch> dispatches) {
//         return dispatches.stream().map(x -> toOBDispatchDTO(x)).collect(Collectors.toList());
//     }

//     public static OutboundDispatchDTO toOBDispatchDTO(OutboundDispatch dispatch) {
//         if(dispatch == null){
//             return new OutboundDispatchDTO();
//         }
   
//         DeliveryScheduleDTO deliverySchedule = ScheduleMapper.getActiveDeliverySchedule(dispatch.getSchedules());
//         OutboundDispatchDTO dto = OutboundDispatchDTO.builder()
//                                 .id(dispatch.getId())
//                                 .photoReference(dispatch.getPhotoReference()).deliverySchedule(deliverySchedule.getDeliveryDate())
//                                 .deliveryTime(ScheduleMapper.getActiveDeliverySchedule(dispatch.getSchedules()).getDescription())
//                                 .deliveryStatus(getDeliveryStatusDTO(dispatch).getName()).remark(dispatch.getRemark())
//                                 .fleetAssignmentDTO(FleetMapper.toFleetAssignmentDTO(dispatch.getFleetAssignment()))
//                                 .contactDetails(ContactMapper.toContactDetailsDTO(dispatch.getContactDetails())).build();

//         return dto;
//     }

//     private static DeliveryStatusDTO getDeliveryStatusDTO(OutboundDispatch dispatch) {
//         DeliveryStatusDTO statusDTO = new DeliveryStatusDTO();
//         statusDTO.setName("PENDING");

//         List<DeliveryStatusHistory> list = dispatch.getStatusHistoryList();
//         if(list == null|| list.size()==0)
//             return statusDTO;

//         for (DeliveryStatusHistory status : dispatch.getStatusHistoryList()) {
//             if (status.getIsActive()) {
//                 statusDTO = DeliveryStatusDTO.builder()
//                                              .name(status.getDeliveryStatus().getName())
//                                              .description(status.getRemark()).build();

//                 return statusDTO;
//             }
//         }

//         return statusDTO;
//     }

//     public static List<InboundDispatchDTO> toIBDispatches(List<InboundDispatch> dispatches) {
//         return dispatches.stream().map(x -> toIBDispatchDTO(x)).collect(Collectors.toList());
//     }

//     public static InboundDispatchDTO toIBDispatchDTO(InboundDispatch inboundDispatch) {
        
//         if(inboundDispatch == null) return new InboundDispatchDTO();
        
//         PickupScheduleDTO pickupScheduleDTO = ScheduleMapper.getActivePickupSchedule(inboundDispatch.getSchedules());
//         return InboundDispatchDTO.builder()
//                 .id(inboundDispatch.getId())
//                 .contactDetails(ContactMapper.toContactDetailsDTO(inboundDispatch.getContactDetails()))
//                 .photoReference(inboundDispatch.getPhotoReference())
//                 .pickupTime(pickupScheduleDTO.getDescription())
//                 .pickupSchedule(pickupScheduleDTO.getPickupDate())
//                 .pickupStatus(ScheduleMapper.getActivePickupStatus(inboundDispatch.getStatusHistoryList()).getName())
//                 .fleetAssignmentDTO(FleetMapper.toFleetAssignmentDTO(inboundDispatch.getFleetAssignment()))
//                 .remark(inboundDispatch.getRemark())
//                 .createdTime(inboundDispatch.getCreatedTime())
//                 .build();
//     }

// }
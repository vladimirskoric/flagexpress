// package ph.devcon.flag.core.component.utils.helper.Mappers;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// import ph.devcon.flag.core.component.dispatch.application.DTO.*;
// import ph.devcon.flag.core.component.dispatch.domain.*;

// public final class ScheduleMapper{

//     public static PickupStatusDTO toPickupStatusDTO(PickupStatus pickupStatus){ 
//         return PickupStatusDTO.builder()
//                 .name(pickupStatus.getName())
//                 .description(pickupStatus.getDescription())
//                 .order(pickupStatus.getOrder())
//                 .build();
//     }

//     public static List<PickupStatusDTO> toPickupStatusDTOs(List<PickupStatus> pickupStatus){
//         return pickupStatus.stream()
//                 .map(_pickUpStatus -> toPickupStatusDTO(_pickUpStatus))
//                 .collect(Collectors.toList());
//     }
    
//     public static PickupStatusDTO getActivePickupStatus(List<PickupStatusHistory> statusHistoryList){
//         if(statusHistoryList == null || statusHistoryList.isEmpty()){
//            return new PickupStatusDTO();
//         }

//         for(PickupStatusHistory item: statusHistoryList){
//             if(item.getIsActive()){
//                 return toPickupStatusDTO(item.getPickupStatus());
//             }
               
//         }

//         return new PickupStatusDTO();
//     }

//     public static PickupScheduleDTO toPickupScheduleDTO(PickupSchedule pickupSchedule){    
//         if(pickupSchedule == null)
//             return new PickupScheduleDTO();

//         return PickupScheduleDTO.builder()
//                 .id(pickupSchedule.getId())
//                 .dispatchId(pickupSchedule.getInboundDispatch().getId())
//                 .isActive(pickupSchedule.getIsActive())
//                 .pickupDate(pickupSchedule.getPickupDate())
//                 .description(pickupSchedule.getDescription())
//                 .scheduleStart(pickupSchedule.getScheduleStart())
//                 .scheduleEnd(pickupSchedule.getScheduleEnd())
//                 .build();
//     }

//     public static List<PickupScheduleDTO> toPickupScheduleDTOs(List<PickupSchedule> pickupSchedules){
//         if(pickupSchedules == null || pickupSchedules.isEmpty())
//             return new ArrayList<PickupScheduleDTO>();

//         return pickupSchedules.stream()
//                 .map(pickupSchedule -> toPickupScheduleDTO(pickupSchedule))
//                 .collect(Collectors.toList());
//     }

//     public static PickupScheduleDTO getActivePickupSchedule(List<PickupSchedule> pickupSchedules){
//         List<PickupScheduleDTO> dtos = toPickupScheduleDTOs(pickupSchedules);
//         for(PickupScheduleDTO sched: dtos){
//             if(sched.getIsActive()){
//                 return sched;
//             }
//         }

//         return new PickupScheduleDTO();
//     }

//     public static DeliveryScheduleDTO getActiveDeliverySchedule(List<DeliverySchedule> deliverySchedules){
//         List<DeliveryScheduleDTO> dtos = toDeliverySchedulesDTO(deliverySchedules);
//         for(DeliveryScheduleDTO sched: dtos){
//             if(sched.getIsActive()){
//                 return sched;
//             }
//         }

//         return new DeliveryScheduleDTO();
//     }

    
//     public static DeliveryScheduleDTO toDeliverycheduleDTO(DeliverySchedule schedule){
//         DeliveryScheduleDTO dto = DeliveryScheduleDTO.builder()
//                                 .id(schedule.getId())
//                                 .description(schedule.getDescription())
//                                 .scheduleStart(schedule.getScheduleStart())
//                                 .scheduleEnd(schedule.getScheduleEnd())
//                                  .build();
                                
        
//         return dto;
//     }

//     public static List<DeliveryScheduleDTO> toDeliverySchedulesDTO(List<DeliverySchedule> schedules){
//         if(schedules == null || schedules.isEmpty())
//             return new ArrayList<DeliveryScheduleDTO>();

//         return schedules.stream()
//                 .map(x-> toDeliverycheduleDTO(x))
//                 .collect(Collectors.toList());
//     }

//     public static List<PickupStatusHistoryDTO> toPickupStatusHistoryListDTOs(List<PickupStatusHistory> statusHistoryList) {
//         return statusHistoryList.stream()
//                 .map(_statusHistoryList -> toPickUpStatusHistoryDTO(_statusHistoryList))
//                 .collect(Collectors.toList());
//     }

//     public static PickupStatusHistoryDTO toPickUpStatusHistoryDTO(PickupStatusHistory statusHistoryList) {
//         return PickupStatusHistoryDTO.builder()
//                 .createdTime(statusHistoryList.getCreatedTime())
//                 .inBoundId(statusHistoryList.getInboundDispatch().getId())
//                 .isActive(statusHistoryList.getIsActive())
//                 .pickupStatus(statusHistoryList.getPickupStatus().getId())
//                 .remark(statusHistoryList.getRemark()).build();
//     }

//     public static DeliveryStatusDTO toDeliveryStatusDTO(DeliveryStatus deliveryStatus){
//         return DeliveryStatusDTO.builder()
//                 .description(deliveryStatus.getDescription())
//                 .name(deliveryStatus.getName())
//                 .order(deliveryStatus.getOrder())
//                 .id(deliveryStatus.getId())
//                 .build();
//     }

//     public static DeliveryStatusHistoryDTO toDeliveryStatusHistoryDTO(DeliveryStatusHistory deliveryStatusHistory){
//         return DeliveryStatusHistoryDTO.builder()
//                 .id(deliveryStatusHistory.getId())
//                 .deliveryStatusId(deliveryStatusHistory.getDeliveryStatus().getId())
//                 .outboundDispatchId(deliveryStatusHistory.getOutboundDispatch().getId())
//                 .isActive(deliveryStatusHistory.getIsActive())
//                 .remark(deliveryStatusHistory.getRemark())
//                 .createdTime(deliveryStatusHistory.getCreatedTime())
//                 .build();
//     }

//     public static List<DeliveryStatusHistoryDTO> toDeliveryStatusHistoryDTOs(List<DeliveryStatusHistory> deliveryStatusHistory){
//         return deliveryStatusHistory.stream()
//                 .map(deliveryStatusHist -> toDeliveryStatusHistoryDTO(deliveryStatusHist))
//                 .collect(Collectors.toList());
//     }

//     public static List<DeliveryStatusDTO> toDeliveryStatusDTOs(List<DeliveryStatus> deliveryStatus){
//         return deliveryStatus.stream()
//                 .map(_deliveryStatus -> toDeliveryStatusDTO(_deliveryStatus))
//                 .collect(Collectors.toList());
//     }

// }
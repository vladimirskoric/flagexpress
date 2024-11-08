// package ph.devcon.flag.core.component.dispatch.application;

// import java.text.ParseException;
// import java.util.List;

// import ph.devcon.flag.core.component.dispatch.application.DTO.*;

// public interface DispatchService {
//     InboundDispatchDTO getInboundDispatchById(long id);
//     List<InboundDispatchDTO> getAllInboundDispatches(String status, String startDate, String endDate);
//     InboundDispatchDTO getInboundByFleetId(long id);
//     InboundDispatchDTO createUpdateInboundDispatch(InboundDispatchDTO dispatch) throws ParseException;

//     OutboundDispatchDTO getOutboundDispatchById(long id);
//     List<OutboundDispatchDTO> getAllOutboundDispatches(String status, String startDate, String endDate);
//     OutboundDispatchDTO getOutboundByFleetId(long id);
//     OutboundDispatchDTO createOutboundDispatch(OutboundDispatchDTO dispatch);

//     List<PickupScheduleDTO> getAllPickupScheduleByDispatchId(long id);
//     PickupScheduleDTO getActivePickupScheduleByDispatchId(long id);
//     PickupScheduleDTO createUpdatePickupSchedule(PickupScheduleDTO schedule);

//     DeliveryScheduleDTO getActiveDeliveryScheduleByDispatchId(long id);
//     List<DeliveryScheduleDTO> getAllDeliveryScheduleByDispatchId(long id);
//     DeliveryScheduleDTO createDeliverySchedule(DeliveryScheduleDTO schedule);

//     List<PickupStatusHistoryDTO> getAllPickupStatusByDispatchId(long id);
//     PickupStatusHistoryDTO createPickupStatus(PickupStatusHistoryDTO pickupStatus);
//     PickupStatusHistoryDTO getActivePickupStatusByDispatchId(long id);

//     DeliveryStatusHistoryDTO getActiveDeliveryStatusByDispatchId(long id);
//     List<DeliveryStatusHistoryDTO> getAllDeliveryStatusByDispatchId(long id);
//     DeliveryStatusHistoryDTO createDeliveryStatus(DeliveryStatusHistoryDTO deliveryStatus);

//     List<DeliveryStatusDTO> getAllDeliveryStatus();
//     DeliveryStatusDTO getDeliveryStatusByName(String deliveryStatus);

//     List<PickupStatusDTO> getAllPickupStatus();
//     PickupStatusDTO getPickupStatusByName(String pickupStatus);
// }

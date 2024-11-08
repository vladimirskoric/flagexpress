// package ph.devcon.flag.core.component.dispatch.application;

// import ph.devcon.flag.core.component.contactdetails.domain.ContactDetails;
// import ph.devcon.flag.core.component.dispatch.application.DTO.*;
// import ph.devcon.flag.core.component.dispatch.domain.*;

// import ph.devcon.flag.core.component.exception.InvalidRequestException;
// import ph.devcon.flag.core.component.fleet.domain.Fleet;
// import ph.devcon.flag.core.component.fleet.domain.FleetAssignment;
// import ph.devcon.flag.core.component.utils.helper.Mappers.*;
// import ph.devcon.flag.core.port.persistence.dispatch.*;
// import ph.devcon.flag.core.port.persistence.*;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.stream.Collectors;

// import static java.lang.String.format;

// @ApplicationScoped
// public class DefaultDispatchService implements DispatchService {

//     @Inject
//     PickupScheduleRepository pickupScheduleRepository;

//     @Inject
//     PickupStatusRepository pickupStatusRepository;

//     @Inject
//     PickupStatusHistoryRepository pickupStatusHistoryRepository;

//     @Inject
//     InboundDispatchRepository ibDispatchRepository;

//     @Inject
//     DeliveryScheduleRepository delScheduleRepository;

//     @Inject
//     DeliveryStatusRepository delStatusRepository;

//     @Inject
//     DeliveryStatusHistoryRepository delStatusHistoryRepository;

//     @Inject
//     OutboundDispatchRepository obDispatchRepository;

//     @Inject
//     FleetRepository fleetRepository;

//     @Inject
//     DonationRepository packageRepository;

//     @Inject
//     ScheduleRepository scheduleRepository;

//     @Override
//     public InboundDispatchDTO getInboundDispatchById(long id) {
//         InboundDispatch ibDispatch = ibDispatchRepository.findById(id);
//         return DispatchMapper.toIBDispatchDTO(ibDispatch);
//     }

//     @Override
//     public List<InboundDispatchDTO> getAllInboundDispatches(String status, String startDate, String endDate) {
//         List<InboundDispatch> dispatches = ibDispatchRepository.findAllDispatches(status, startDate, endDate);
//         return DispatchMapper.toIBDispatches(dispatches);
//     }

//     @Override
//     public InboundDispatchDTO getInboundByFleetId(long id) {
//         InboundDispatch ibDispatch = ibDispatchRepository.findByFleetId(id);
//         return DispatchMapper.toIBDispatchDTO(ibDispatch);
//     }

//     @Override
//     public InboundDispatchDTO createUpdateInboundDispatch(InboundDispatchDTO dispatchDTO) {
//         InboundDispatch inboundDispatch = newInboundDispatch(dispatchDTO);
//         inboundDispatch = ibDispatchRepository.createUpdateInboundDispatch(inboundDispatch);

//         PickupSchedule pickupSchedule = newPickupSchedule(inboundDispatch, dispatchDTO);
//         pickupScheduleRepository.createUpdatePickupSchedule(pickupSchedule);

//         PickupStatus status = pickupStatusRepository.findByName(dispatchDTO.getPickupStatus());
//         if(status == null){
//             throw new InvalidRequestException(format("%s is an invalid pickup status.", dispatchDTO.getPickupStatus()));
//         }

//         PickupStatusHistory statusHistory = new PickupStatusHistory();
//         statusHistory.setInboundDispatch(inboundDispatch);
//         statusHistory.setPickupStatus(status);
//         statusHistory.setIsActive(true);
//         statusHistory.setCreatedTime(LocalDateTime.now());
//         pickupStatusHistoryRepository.createPickupStatus(statusHistory);

//         return DispatchMapper.toIBDispatchDTO(inboundDispatch);
//     }

//     @Override
//     public OutboundDispatchDTO getOutboundDispatchById(long id) {
//         OutboundDispatch obDispatch = obDispatchRepository.findById(id);
//         return DispatchMapper.toOBDispatchDTO(obDispatch);
//     }

//     @Override
//     public List<OutboundDispatchDTO> getAllOutboundDispatches(String status, String startDate, String endDate) {
//         List<OutboundDispatch> dispatches = obDispatchRepository.findAllDispatches(status, startDate, endDate);
//         return DispatchMapper.toOBDispatches(dispatches);
//     }


//     @Override
//     public OutboundDispatchDTO getOutboundByFleetId(long id) {
//         OutboundDispatch obDispatch =  obDispatchRepository.findByFleetId(id);
//         return DispatchMapper.toOBDispatchDTO(obDispatch);
//     }

//     @Override
//     public OutboundDispatchDTO createOutboundDispatch(OutboundDispatchDTO dispatch) {
//         OutboundDispatch obDispatch =  obDispatchRepository.createOutboundDispatch(toOutboundDispatch(dispatch));
//         return DispatchMapper.toOBDispatchDTO(obDispatch);
//     }

//     @Override
//     public List<PickupScheduleDTO> getAllPickupScheduleByDispatchId(long id) {
//         List<PickupSchedule> schedules =  pickupScheduleRepository.findAllPickupScheduleByDispatchId(id);
//         return ScheduleMapper.toPickupScheduleDTOs(schedules);
//     }

//     @Override
//     public PickupScheduleDTO getActivePickupScheduleByDispatchId(long id) {
//         PickupSchedule schedule =  pickupScheduleRepository.findActivePickupScheduleByDispatchId(id);
//         return ScheduleMapper.toPickupScheduleDTO(schedule);
//     }

//     @Override
//     public PickupScheduleDTO createUpdatePickupSchedule(PickupScheduleDTO schedule) {
//         PickupSchedule sched =  newPickupSchedule(schedule);
//         sched = pickupScheduleRepository.createUpdatePickupSchedule(sched);
//         return ScheduleMapper.toPickupScheduleDTO(sched);
//     }

//     @Override
//     public List<DeliveryScheduleDTO> getAllDeliveryScheduleByDispatchId(long id) {
//         List<DeliverySchedule> schedules = delScheduleRepository.findAllDeliveryScheduleByDispatchId(id);
//         return ScheduleMapper.toDeliverySchedulesDTO(schedules);
//     }

//     @Override
//     public DeliveryScheduleDTO getActiveDeliveryScheduleByDispatchId(long id) {
//         DeliverySchedule schedule =  delScheduleRepository.findActiveDeliveryScheduleByDispatchId(id);
//         return ScheduleMapper.toDeliverycheduleDTO(schedule);
//     }

//     @Override
//     public DeliveryScheduleDTO createDeliverySchedule(DeliveryScheduleDTO schedule) {
//         DeliverySchedule ds = newDeliverySchedule(schedule);
//         delScheduleRepository.createDeliverySchedule(ds);
//         return schedule;
//     }


//     @Override
//     public List<PickupStatusHistoryDTO> getAllPickupStatusByDispatchId(long id) {
//         List<PickupStatusHistory> history = pickupStatusHistoryRepository.findAllByDispatchId(id);
//         return history.stream()
//         .map(x->ScheduleMapper.toPickUpStatusHistoryDTO(x))
//         .collect(Collectors.toList());
//     }

//     @Override
//     public PickupStatusHistoryDTO getActivePickupStatusByDispatchId(long id) {
//         return ScheduleMapper.toPickUpStatusHistoryDTO(pickupStatusHistoryRepository.findActiveStatusByDispatchId(id));
//     }

//     @Override
//     public PickupStatusHistoryDTO createPickupStatus(PickupStatusHistoryDTO pickupStatus) {
//         pickupStatusHistoryRepository.createPickupStatus(newPickupHistory(pickupStatus));
//         return pickupStatus;
//     }

//     @Override
//     public List<DeliveryStatusHistoryDTO> getAllDeliveryStatusByDispatchId(long id) {
//         List<DeliveryStatusHistory> history = delStatusHistoryRepository.findAllByDispatchId(id);
//         return history.stream()
//         .map(x->ScheduleMapper.toDeliveryStatusHistoryDTO(x))
//         .collect(Collectors.toList());
//     }

//     @Override
//     public DeliveryStatusHistoryDTO getActiveDeliveryStatusByDispatchId(long id) {
//         return ScheduleMapper.toDeliveryStatusHistoryDTO(delStatusHistoryRepository.findActiveStatusByDispatchId(id));
//     }

//     @Override
//     public DeliveryStatusHistoryDTO createDeliveryStatus(DeliveryStatusHistoryDTO deliveryStatus) {
//         delStatusHistoryRepository.createDeliveryStatus(newDeliveryHistory(deliveryStatus));
//         return deliveryStatus;
//     }

//     @Override
//     public List<DeliveryStatusDTO> getAllDeliveryStatus() {
//         return ScheduleMapper.toDeliveryStatusDTOs(delStatusRepository.findAllDeliveryStatus());
//     }
    
//     @Override
//     public DeliveryStatusDTO getDeliveryStatusByName(String deliveryStatus) {
//         return ScheduleMapper.toDeliveryStatusDTO(delStatusRepository.findByName(deliveryStatus));
//     }
    
//     @Override
//     public List<PickupStatusDTO> getAllPickupStatus() {
//         return ScheduleMapper.toPickupStatusDTOs(pickupStatusRepository.findAllPickupStatus());
//     }
    
//     @Override
//     public PickupStatusDTO getPickupStatusByName(String pickupStatus) {
//         return ScheduleMapper.toPickupStatusDTO(pickupStatusRepository.findByName(pickupStatus));
//     }
    
//     private PickupStatusHistory newPickupHistory(PickupStatusHistoryDTO pickupStatus){
//         PickupStatusHistory history = new PickupStatusHistory();
//         InboundDispatch dispatch = ibDispatchRepository.findById(pickupStatus.getInBoundId());
//         history.setInboundDispatch(dispatch);
//         PickupStatus status = pickupStatusRepository.findById(pickupStatus.getPickupStatus());
//         history.setPickupStatus(status);
//         history.setIsActive(true);
//         return history;
//     }

//     private DeliveryStatusHistory newDeliveryHistory(DeliveryStatusHistoryDTO deliveryStatusHistoryDTO){
//         DeliveryStatus deliveryStatus = delStatusRepository.findById(deliveryStatusHistoryDTO.getDeliveryStatusId());
//         OutboundDispatch outboundDispatch = obDispatchRepository.findById(deliveryStatusHistoryDTO.getOutboundDispatchId());

//         if (deliveryStatus == null)
//             throw new InvalidRequestException(format("Delivery Status ID %d not found.",deliveryStatusHistoryDTO.getDeliveryStatusId()));

//         if(outboundDispatch == null)
//             throw new InvalidRequestException(format("Outbound Dispatch ID %d not found.",deliveryStatusHistoryDTO.getOutboundDispatchId()));

//         DeliveryStatusHistory deliveryStatusHistory = new DeliveryStatusHistory();
//         deliveryStatusHistory.setCreatedTime(LocalDateTime.now());
//         deliveryStatusHistory.setDeliveryStatus(deliveryStatus);
//         deliveryStatusHistory.setIsActive(deliveryStatusHistoryDTO.getIsActive());
//         deliveryStatusHistory.setOutboundDispatch(outboundDispatch);

//         return deliveryStatusHistory;
//     }

//     private PickupSchedule newPickupSchedule(InboundDispatch dispatch, InboundDispatchDTO dispatchDTO){
//         Schedule schedule = scheduleRepository.findByDescription(dispatchDTO.getPickupTime());
//         if(schedule == null)
//             throw new InvalidRequestException(format("Schedule %s not found.", dispatchDTO.getPickupTime()));

//         PickupSchedule pickupSchedule = new PickupSchedule();
//         pickupSchedule.setInboundDispatch(dispatch);
//         pickupSchedule.setDescription(schedule.getDescription());
//         pickupSchedule.setPickupDate(dispatchDTO.getPickupSchedule());
//         pickupSchedule.setScheduleStart(schedule.getScheduleStart());
//         pickupSchedule.setScheduleEnd(schedule.getScheduleEnd());
//         pickupSchedule.setIsActive(true);
                                
//         return pickupSchedule;
//     }

//     private DeliverySchedule newDeliverySchedule(DeliveryScheduleDTO dispatchDTO){
//         Schedule schedule = scheduleRepository.findByDescription(dispatchDTO.getDescription());
//         if(schedule == null)
//             throw new InvalidRequestException(format("Schedule %s not found.", dispatchDTO.getDescription()));

//         OutboundDispatch delivery = obDispatchRepository.findById(dispatchDTO.getDispatchId());
//         DeliverySchedule pickupSchedule = new DeliverySchedule();
//         DeliverySchedule deliverySchedule = new DeliverySchedule();
//         deliverySchedule.setOutboundDispatch(delivery);
//         deliverySchedule.setDescription(schedule.getDescription());
//         deliverySchedule.setScheduleStart(schedule.getScheduleStart());
//         deliverySchedule.setScheduleEnd(schedule.getScheduleEnd());
//         deliverySchedule.setIsActive(true);
                                
//         return pickupSchedule;
//     }

//     private PickupSchedule newPickupSchedule(PickupScheduleDTO dto){
//         PickupSchedule pickupSchedule = new PickupSchedule();
//         Schedule schedule = scheduleRepository.findByDescription(dto.getDescription());

//         if(schedule == null)
//             throw new InvalidRequestException(format("Schedule %s not found.",dto.getDescription()));
        
//         InboundDispatch pickup = ibDispatchRepository.findById(dto.getDispatchId());
//         pickupSchedule.setInboundDispatch(pickup);
//         pickupSchedule.setDescription(schedule.getDescription());
//         pickupSchedule.setScheduleStart(schedule.getScheduleStart());
//         pickupSchedule.setScheduleEnd(schedule.getScheduleEnd());
//         pickupSchedule.setIsActive(true);
                                
//         return pickupSchedule;
//     }

//     private InboundDispatch newInboundDispatch(InboundDispatchDTO inboundDispatchDTO){
//         String fleetName = inboundDispatchDTO.getFleetName();
//         Fleet fleet = fleetRepository.findByName(fleetName);

//         if(fleet == null)
//             throw new InvalidRequestException(format("Fleet %s not found.", fleetName));
            
//         FleetAssignment fleetAssignment = new FleetAssignment();
//         fleetAssignment.setFleet(fleet);

//         InboundDispatch inboundDispatch = new InboundDispatch();
//         ContactDetails contactDetails = ContactMapper.toContactDetails(inboundDispatchDTO.getContactDetails());

//         inboundDispatch.setPhotoReference(inboundDispatchDTO.getPhotoReference());
//         inboundDispatch.setFleetAssignment(fleetAssignment);
//         inboundDispatch.setContactDetails(contactDetails);
//         inboundDispatch.setCreatedTime(LocalDateTime.now());
//         return inboundDispatch;
//     }

//     private OutboundDispatch toOutboundDispatch(OutboundDispatchDTO outboundDispatchDTO){
//         OutboundDispatch outboundDispatch = new OutboundDispatch();
//         ContactDetails contactDetails = ContactMapper.toContactDetails(outboundDispatchDTO.getContactDetails());
//         outboundDispatch.setContactDetails(contactDetails);
//         return outboundDispatch;
//     }
// }

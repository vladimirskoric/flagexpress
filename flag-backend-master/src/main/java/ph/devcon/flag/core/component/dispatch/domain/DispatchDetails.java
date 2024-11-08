// package ph.devcon.flag.core.component.dispatch.domain;

// import lombok.Data;
// import ph.devcon.flag.core.component.donations.domain.DonationItem;
// import ph.devcon.flag.core.component.utils.domain.DonationUnit;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// import static javax.persistence.FetchType.EAGER;

// @Data
// @Entity(name = "dispatch_details")
// public class DispatchDetails {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private long id;

//     @ManyToOne(targetEntity = OutboundDispatch.class, fetch = EAGER)
//     @JoinColumn(name = "dispatch_id")
//     private OutboundDispatch outboundDispatch;

//     @ManyToOne(targetEntity = DonationItem.class, fetch = EAGER)
//     @JoinColumn(name = "donation_item_id")
//     private DonationItem donationItem;

//     @ManyToOne(targetEntity = DonationUnit.class, fetch = EAGER)
//     @JoinColumn(name = "unit_id")
//     private DonationUnit unit;

//     @Column(name = "unit_value")
//     private double unitValue;
// }
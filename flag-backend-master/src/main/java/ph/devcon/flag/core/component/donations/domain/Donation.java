package ph.devcon.flag.core.component.donations.domain;

import lombok.Data;
import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.utils.domain.Currency;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = DonationRequest.class, fetch = EAGER)
    @JoinColumn(name = "donation_request_id")
    private DonationRequest donationRequest;

    @ManyToOne(targetEntity = Currency.class, fetch = EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy="donation")
    private List<DonationItem> items;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy="donation")
    private List<CashDonation> cashDonations;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy="donation")
    private List<Service> serviceDonations;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy="donation")
    private List<Rental> rentalDonations;

    @Column(name = "value")
    private double value;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "photo_reference")
    private String photoReference;

    @Column(name = "file_reference")
    private String fileReference;

    @Column(name = "donation_date")
    private LocalDateTime donationDate;

    public Donation() {
    }
}

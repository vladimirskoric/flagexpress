package ph.devcon.flag.core.component.donations.domain;

import lombok.Data;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.component.utils.domain.DonationType;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

import java.time.LocalDateTime;

@Data
@Entity(name = "rentals")
public class Rental {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = Donation.class, fetch = EAGER)
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @ManyToOne(targetEntity = DonationType.class, fetch = EAGER)
    @JoinColumn(name = "donation_type_id")
    private DonationType donationType;

    @ManyToOne(targetEntity = Currency.class, fetch = EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "estimated_cost")
    private double estimatedCost;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}

package ph.devcon.flag.core.component.donations.domain;

import lombok.Data;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.component.utils.domain.DonationType;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity(name = "cash_donations")
public class CashDonation {
    
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

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;
}
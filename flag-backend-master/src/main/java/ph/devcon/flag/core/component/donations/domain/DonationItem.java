package ph.devcon.flag.core.component.donations.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.EAGER;

import lombok.Data;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.component.utils.domain.DonationType;
import ph.devcon.flag.core.component.utils.domain.DonationUnit;

@Data
@Entity(name = "donation_items")
public class DonationItem {
    
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

    @ManyToOne(targetEntity = DonationUnit.class, fetch = EAGER)
    @JoinColumn(name = "unit_id")
    private DonationUnit unit;

    @ManyToOne(targetEntity = Currency.class, fetch = EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "estimated_cost")
    private double estimatedCost;

    @Column(name = "total_unit_value")
    private Double totalUnitValue;

    @Column(name = "weight")
    private double weight;

    @Column(name = "dimension")
    private String dimension;

    @Column(name = "description")
    private String description;
}
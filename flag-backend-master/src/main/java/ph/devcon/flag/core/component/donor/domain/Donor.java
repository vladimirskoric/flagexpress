package ph.devcon.flag.core.component.donor.domain;

import lombok.Data;
import ph.devcon.flag.core.component.address.domain.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.EAGER;

import javax.persistence.CascadeType;

@Data
@Entity(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "affiliation_org")
    private String affiliation;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "landline_number")
    private String landlineNumber;

    @Column(name = "email_address")
    private String email;

    @Column(name = "is_anonymous")
    private boolean isAnonymous;

    @Column(name = "is_international")
    private boolean isInternational;

    @ManyToOne(targetEntity = SectorType.class, fetch = EAGER)
    @JoinColumn(name = "sector_type_id")
    private SectorType sectorType;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class, fetch = EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    public Donor() {
    }
}

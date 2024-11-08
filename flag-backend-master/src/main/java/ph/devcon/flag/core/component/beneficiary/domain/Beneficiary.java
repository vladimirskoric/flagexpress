package ph.devcon.flag.core.component.beneficiary.domain;

import lombok.Data;
import ph.devcon.flag.core.component.address.domain.Address;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity(name = "beneficiaries")
public class Beneficiary {

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
    private String landlineNuber;

    @Column(name = "email_address")
    private String emailAddress;
    
    @ManyToOne(targetEntity = BeneficiarySector.class, fetch = EAGER)
    @JoinColumn(name = "sector_type_id")
    private BeneficiarySector sector;

    @ManyToOne(targetEntity = Address.class, fetch = EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Beneficiary() {
    }

}

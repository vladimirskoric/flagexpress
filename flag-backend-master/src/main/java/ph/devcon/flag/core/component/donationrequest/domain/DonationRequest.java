package ph.devcon.flag.core.component.donationrequest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.component.donor.domain.Donor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "donation_requests")
public class DonationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = Donor.class, fetch = EAGER)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne(targetEntity = Beneficiary.class, fetch = EAGER)
    @JoinColumn(name = "beneficiary_id")
    private Beneficiary beneficiary;

    @ManyToOne(targetEntity = RequestType.class, fetch = EAGER)
    @JoinColumn(name = "request_type_id")
    private RequestType requestType;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy="donationRequest")
    private List<Donation> donations;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified")
    private LocalDateTime dateLastModified;

    @Column(name = "ref_code")
    private String refCode;
}

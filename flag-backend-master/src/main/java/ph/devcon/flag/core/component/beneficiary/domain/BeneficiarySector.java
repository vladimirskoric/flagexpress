package ph.devcon.flag.core.component.beneficiary.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "beneficiary_sectors")
public class BeneficiarySector {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public BeneficiarySector() {
    }
}

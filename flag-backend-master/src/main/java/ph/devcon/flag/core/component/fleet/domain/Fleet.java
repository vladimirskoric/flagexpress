package ph.devcon.flag.core.component.fleet.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "fleets")
public class Fleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Fleet() {
    }


}

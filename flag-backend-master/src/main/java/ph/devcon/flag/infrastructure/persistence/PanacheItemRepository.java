package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Item;
import ph.devcon.flag.core.port.persistence.ItemRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheItemRepository implements ItemRepository, PanacheRepository<Item> {

    @Override
    public List<Item> findAllItems() {
        return listAll(Sort.by("group"));
    }

    @Override
    public Item findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public Item findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public Item findByGroup(String group) {
        return find("group", group).firstResult();
    }


    @Override
    public Item findById(int id) {
        return find("id", id).firstResult();
    }
}

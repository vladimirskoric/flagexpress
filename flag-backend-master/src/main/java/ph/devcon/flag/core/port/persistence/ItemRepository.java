package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.Item;

public interface ItemRepository {
    List<Item> findAllItems();
    Item findByCode(String code);
    Item findByName(String name);
    Item findByGroup(String group);
    Item findById(int id);
}


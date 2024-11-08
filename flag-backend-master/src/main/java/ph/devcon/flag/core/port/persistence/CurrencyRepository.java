package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.Currency;

public interface CurrencyRepository {
    List<Currency> findAllCurrencies();
    Currency findByCode(String code);
    Currency findByName(String name);
    Currency findById(int id);
}


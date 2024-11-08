package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.Country;

public interface CountryRepository {
    List<Country> findAllCountries();
    Country findByName(String name);
    Country findById(int id);
}


package lab.dao;

import lab.dao.jdbc.CountryNotFoundException;
import lab.model.Country;
import lab.model.MutableCountry;

import java.util.List;

public interface CountryDao {

	void save(MutableCountry country);

	List<Country> getAllCountries();

	Country getCountryByName(String name) throws CountryNotFoundException;

}
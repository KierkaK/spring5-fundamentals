package orm;

import lab.dao.CountryDao;
import lab.dao.jdbc.CountryNotFoundException;
import lab.model.Country;
import lab.model.MutableCountry;
import lab.model.simple.SimpleCountry;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:orm.xml")
@Log
class CountryJpaDaoImplTest {

	private MutableCountry exampleCountry = new SimpleCountry( "Australia", "AU");

	@Autowired
    @Qualifier("countryJpaDaoImpl")
	private CountryDao countryDao;

	@Test
	void testSaveCountry() {

		countryDao.save(exampleCountry);

		List<Country> mutableCountryList = countryDao.getAllCountries();
		assertEquals(1, mutableCountryList.size());
		assertEquals(exampleCountry, mutableCountryList.get(0));
	}

	@Test
	void testGtAllCountries() {

		countryDao.save(new SimpleCountry("Canada", "CA"));

		List<Country> mutableCountryList = countryDao.getAllCountries();
		assertEquals(2, mutableCountryList.size());
	}

	@Test
	void testGetCountryByName() throws CountryNotFoundException {
		assertEquals(exampleCountry, countryDao.getCountryByName("Australia"));
	}

}

package jdbc;

import lab.dao.jdbc.CountryDao;
import lab.dao.jdbc.SimpleCountryDao;
import lab.model.Country;
import lab.model.simple.SimpleCountry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:jdbc.xml")
class JdbcTest {

    @Autowired
    private CountryDao countryDao;

    private List<Country> expectedCountryList;
    private List<Country> expectedCountryListStartsWithA;
    private Country countryWithChangedName =
            new SimpleCountry(8, "Russia", "RU");

    @BeforeEach
    void setUp() throws Exception {
        initExpectedCountryLists();
        countryDao.loadCountries();
    }

    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        IntStream.range(0, expectedCountryList.size())
                .forEach(i -> assertThat(countryList.get(i), is(expectedCountryList.get(i))));
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        assertThat(countryDao.getCountryListStartWith("A"),
                is(expectedCountryListStartsWithA));
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        countryDao.updateCountryName("RU", "Russia");
        assertThat(countryDao.getCountryByCodeName("RU"), is(countryWithChangedName));
    }

    private void initExpectedCountryLists() {
        expectedCountryList = IntStream.range(0, SimpleCountryDao.COUNTRY_INIT_DATA.length)
                .mapToObj(i -> {
                    String[] countryInitData = SimpleCountryDao.COUNTRY_INIT_DATA[i];
                    return new SimpleCountry(i + 1, countryInitData[0], countryInitData[1]);
                })
                .collect(Collectors.toList());

        expectedCountryListStartsWithA = expectedCountryList.stream()
                .filter(country -> country.getName().startsWith("A"))
                .collect(Collectors.toList());
    }
}
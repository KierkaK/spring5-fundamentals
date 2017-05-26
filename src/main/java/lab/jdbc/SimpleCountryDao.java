package lab.jdbc;

import lab.Country;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class SimpleCountryDao extends NamedParameterJdbcDaoSupport implements CountryDao {

    @Override
    public List<Country> getCountryList() {
        // TODO: implement it
        return null;
    }

    @Override
    public List<Country> getCountryListStartWith(String name) {
        return getNamedParameterJdbcTemplate()
                .query(
                        GET_COUNTRIES_BY_NAME_SQL,
                        new MapSqlParameterSource("name", name + "%"),
                        COUNTRY_ROW_MAPPER);
    }

    @Override
    public void updateCountryName(String codeName, String newCountryName) {
        // TODO: implement it
    }

    @Override
    public Country getCountryByCodeName(String codeName) {
        return getJdbcTemplate()
                .query(format(GET_COUNTRY_BY_CODE_NAME_SQL, codeName), COUNTRY_ROW_MAPPER)
                .get(0);
    }

    @Override
    public Country getCountryByName(String name) throws CountryNotFoundException {
        List<Country> countryList = getJdbcTemplate()
                .query(format(GET_COUNTRY_BY_NAME_SQL, name), COUNTRY_ROW_MAPPER);
        if (countryList.isEmpty())
            throw new CountryNotFoundException();
        return countryList.get(0);
    }

    @Override
    public void loadCountries() {
        //noinspection ConfusingArgumentToVarargsMethod
        Arrays.stream(COUNTRY_INIT_DATA)
                .map(countryData -> format(LOAD_COUNTRIES_SQL, countryData))
                .forEach(getJdbcTemplate()::execute);
    }
}

package lab.dao.jdbc;

import lab.model.Country;
import lab.model.MutableCountry;
import lab.model.simple.SimpleCountry;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class SimpleCountryJdbcDao extends NamedParameterJdbcDaoSupport implements CountryJdbcDao {

    @Override
    public void save(MutableCountry mutableCountry) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(LOAD_COUNTRIES_SQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, mutableCountry.getName());
                    ps.setString(2, mutableCountry.getCodeName());
                    return ps;
                },
                keyHolder);

        mutableCountry.setId(keyHolder.getKey().intValue());
    }

    @Override
    public List<Country> getAllCountries() {
        return getJdbcTemplate().query(
                GET_ALL_COUNTRIES_SQL,
                COUNTRY_ROW_MAPPER
        );
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
        getJdbcTemplate().update(
                        UPDATE_COUNTRY_NAME_SQL,
                        newCountryName,
                        codeName
        );
    }

    @Override
    public Country getCountryByCodeName(String codeName) {
        return getJdbcTemplate()
                .queryForObject(GET_COUNTRY_BY_CODE_NAME_SQL, COUNTRY_ROW_MAPPER, codeName);
    }

    @Override
    public Country getCountryByName(String name) throws CountryNotFoundException {
        try {
            return getJdbcTemplate()
                    .queryForObject(GET_COUNTRY_BY_NAME_SQL, COUNTRY_ROW_MAPPER, name);
        } catch (EmptyResultDataAccessException e) {
            throw new CountryNotFoundException();
        }
    }

    @Override
    public void loadCountries() {
        //noinspection ConfusingArgumentToVarargsMethod
        Arrays.stream(COUNTRY_INIT_DATA)
                .map(strings -> new SimpleCountry(strings[0], strings[1]))
                .forEach(this::save);
    }
}

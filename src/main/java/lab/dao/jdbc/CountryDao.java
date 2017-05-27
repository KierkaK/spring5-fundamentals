package lab.dao.jdbc;

import lab.model.Country;
import lab.model.simple.SimpleCountry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface CountryDao extends InitializingBean {
    String LOAD_COUNTRIES_SQL = "INSERT INTO country (name, code_name) VALUES ('%s', '%s')";
    String GET_ALL_COUNTRIES_SQL = "SELECT id, name, code_name FROM country";
    String GET_COUNTRIES_BY_NAME_SQL = "SELECT id, name, code_name FROM country WHERE name LIKE :name";
    String GET_COUNTRY_BY_NAME_SQL = "SELECT id, name, code_name FROM country WHERE name = '%s'";
    String GET_COUNTRY_BY_CODE_NAME_SQL = "SELECT id, name, code_name FROM country WHERE code_name = '%s'";
    String UPDATE_COUNTRY_NAME_SQL = "UPDATE country SET name='%s' WHERE code_name='%s'";
    String CREATE_COUNTRY_TABLE_SQL = "CREATE TABLE country(id IDENTITY, name VARCHAR (255), code_name VARCHAR (255))";
    String DROP_COUNTRY_TABLE_SQL = "DROP TABLE country";

    String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Russian Federation", "RU"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"}
    };

    RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, rowNum) ->
            new SimpleCountry(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code_name"));

    List<Country> getCountryList();

    List<Country> getCountryListStartWith(String name);

    void updateCountryName(String codeName, String newCountryName);

    void loadCountries();

    Country getCountryByCodeName(String codeName);

    Country getCountryByName(String name) throws CountryNotFoundException;
}

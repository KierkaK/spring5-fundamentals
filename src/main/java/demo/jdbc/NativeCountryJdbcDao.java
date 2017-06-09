package demo.jdbc;

import lab.dao.jdbc.CountryJdbcDao;
import lab.dao.jdbc.CountryNotFoundException;
import lab.model.Country;
import lab.model.MutableCountry;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.function.Supplier;

@FunctionalInterface
public interface NativeCountryJdbcDao extends CountryJdbcDao, Supplier<Connection> {

    @Override
    default List<Country> getCountryListStartWith(String name) {
        return null;
    }

    @Override
    default void updateCountryName(String codeName, String newCountryName) {

    }

    @Override
    default void loadCountries() {

    }

    @Override
    default Country getCountryByCodeName(String codeName) {
        return null;
    }

    @Override
    @SneakyThrows
    default void save(MutableCountry country) {
        try (Connection connection = get();
             PreparedStatement ps = connection.prepareStatement(
                     LOAD_COUNTRIES_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, country.getName());
            ps.setString(2, country.getCodeName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    country.setId(rs.getInt(1));
            }
        }
    }

    @Override
    default List<Country> getAllCountries() {
        return null;
    }

    @Override
    default Country getCountryByName(String name) throws CountryNotFoundException {
        return null;
    }

    @Override
    default void afterPropertiesSet() throws Exception {

    }
}

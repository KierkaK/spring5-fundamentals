package demo.jdbc;

import lab.model.MutableCountry;
import lab.model.simple.SimpleCountry;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class NativeCountryJdbcDaoTest {

    private static final String JDBC_PROPERTIES_FILE_NAME = "src/test/resources/jdbc.properties";
    private static final String DB_SCHEMA_FILE_NAME = "src/test/resources/db-schema.sql";
    private static final Properties jdbcProperties = new Properties();
    private static String URL;

    private static NativeCountryJdbcDao nativeCountryJdbcDao = NativeCountryJdbcDaoTest::getConnection;

    @BeforeAll
    @SneakyThrows
    static void init() {
        // init jdbcProperties
        try (FileInputStream fileInputStream = new FileInputStream(
                Paths.get(JDBC_PROPERTIES_FILE_NAME).toFile())) {
            jdbcProperties.load(fileInputStream);
        }

        assert jdbcProperties.size() == 4
                && jdbcProperties.containsKey("driverClassName")
                && jdbcProperties.containsKey("url")
                && jdbcProperties.containsKey("user")
                && jdbcProperties.containsKey("password");

        // load JDBC-driver class
        Class.forName((String) jdbcProperties.remove("driverClassName"));

        // extract JDBC-url
        URL = (String) jdbcProperties.remove("url");

        // read SQL DDL
        String schema = Files.lines(
                Paths.get(DB_SCHEMA_FILE_NAME))
                .collect(Collectors.joining());

        // execute SQL DDL
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(schema);
        }
    }

    @SneakyThrows
    private static Connection getConnection() {
        assert jdbcProperties.size() == 2
                && jdbcProperties.containsKey("user")
                && jdbcProperties.containsKey("password");
        return DriverManager.getConnection(URL, jdbcProperties);
    }

    @Test
    void save() {
        MutableCountry country = new SimpleCountry("Russia", "RU");
        nativeCountryJdbcDao.save(country);
        assertThat(country.getId(), is(1));
    }

}
package ioc;

import lab.model.simple.SimpleCountry;
import lab.model.Person;
import lab.model.simple.SimplePerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
class SpringTCFAppTest {

    @Autowired
    private Person person;

    private SimplePerson expectedPerson;


    @BeforeEach
    void setUp() throws Exception {
        expectedPerson = getExpectedPerson();
    }

    @Test
    void testInitPerson() {
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    private SimplePerson getExpectedPerson() {
        return new SimplePerson(
                0,
                "John Smith",
                new SimpleCountry(1, "Russia","RU"),
                35,
                1.78F,
                true,
                Arrays.asList("asd@asd.ru", "+7-234-456-67-89"));
    }

}

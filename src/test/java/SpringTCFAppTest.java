import lab.model.Country;
import lab.model.UsualPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
class SpringTCFAppTest {

    @Autowired
    private UsualPerson person;

    private UsualPerson expectedPerson;


    @BeforeEach
    void setUp() throws Exception {
        expectedPerson = getExpectedPerson();
    }

    @Test
    void testInitPerson() {
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    private UsualPerson getExpectedPerson() {
        UsualPerson person = new UsualPerson();
        person.setAge(35);
        person.setHeight(1.78F);
        person.setIsProgrammer(true);
        person.setName("John Smith");

        Country country = new Country();
        country.setId(1);
        country.setName("Russia");
        country.setCodeName("RU");

        person.setCountry(country);

        List<String> contacts = new ArrayList<String>();
        contacts.add("asd@asd.ru");
        contacts.add("+7-234-456-67-89");

        person.setContacts(contacts);

        return person;
    }

}

package ioc;

import lab.ioc.Country;
import lab.ioc.Person;
import lab.ioc.UsualPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

    private AbstractApplicationContext context;

    private Person expectedPerson;

    @BeforeEach
    void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPerson = getExpectedPerson();
    }

    @Test
    void testInitPerson() {
        Person person = (UsualPerson) context.getBean("person");
//		FYI: Another way to achieve the bean
//		person = context.getBean(UsualPerson.class);
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    private Person getExpectedPerson() {
        return new UsualPerson(
                0,
                "John Smith",
                new Country(1, "Russia","RU"),
                35,
                1.78F,
                true,
                Arrays.asList("asd@asd.ru", "+7-234-456-67-89"));
    }
}

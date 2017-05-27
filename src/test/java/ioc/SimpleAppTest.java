package ioc;

import lab.model.simple.SimpleCountry;
import lab.model.Person;
import lab.model.simple.SimplePerson;
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
        Person person = (SimplePerson) context.getBean("person");
//		FYI: Another way to achieve the bean
//		person = context.getBean(SimplePerson.class);
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    private Person getExpectedPerson() {
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

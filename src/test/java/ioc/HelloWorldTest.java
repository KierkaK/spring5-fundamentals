package ioc;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lab.model.simple.SimpleCountry;
import lab.model.simple.SimplePerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

	private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "ioc.xml";

	private SimplePerson expectedPerson;

	private BeanFactory context;

	@BeforeEach
	void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
		expectedPerson = getExpectedPerson();
	}

	@Test
	@Ignore
	void testInitPerson() {
		assertEquals(expectedPerson, context.getBean("person"));
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

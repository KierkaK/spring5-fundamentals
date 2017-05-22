import lab.model.Country;
import lab.model.UsualPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

	private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "application-context.xml";

	private UsualPerson expectedPerson;

	private BeanFactory context;

	@BeforeEach
	void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
		expectedPerson = getExpectedPerson();
	}

	@Test
	void testInitPerson() {
		assertEquals(expectedPerson, context.getBean("person"));
	}

	private UsualPerson getExpectedPerson() {
		UsualPerson usualPerson = new UsualPerson();
		usualPerson.setAge(35);
		usualPerson.setName("John Smith");
		Country country = new Country();
		country.setId(1);
		country.setName("Russia");
		country.setCodeName("RU");
		usualPerson.setCountry(country);
		return usualPerson;
	}
}

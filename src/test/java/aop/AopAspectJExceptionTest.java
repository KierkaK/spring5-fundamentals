package aop;

import lab.aop.AopLog;
import lab.aop.CustomerBrokenException;
import lab.model.Bar;
import lab.model.MutablePerson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:aop.xml")
class AopAspectJExceptionTest {

	@Autowired
	private Bar bar;
    
	@Autowired
    private MutablePerson person;

    @BeforeEach
    void setUp() throws Exception {
        person.setBroke(true);
    }

    @Test
    void testAfterThrowingAdvice() {
    	assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(person));
        assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
    }

    @AfterEach
    void after() {
        person.setBroke(false);
    }
}
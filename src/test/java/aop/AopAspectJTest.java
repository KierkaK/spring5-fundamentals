package aop;

import lab.aop.AopLog;
import lab.model.simple.ApuBar;
import lab.model.Bar;
import lab.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:aop.xml")
class AopAspectJTest {

	@Autowired
    private Bar bar;
    
	@Autowired
    private Person person;

    @BeforeEach
    void setUp() throws Exception {
        bar.sellSquishee(person);
    }

    @Test
    void testBeforeAdvice() {
        assertTrue("Before advice is not good enough...",
                AopLog.getStringValue().contains("Hello"));

        assertTrue("Before advice is not good enough...",
                AopLog.getStringValue().contains("How are you doing?"));
    }

    @Test
    void testAfterAdvice() {
        assertTrue("After advice is not good enough...",
                AopLog.getStringValue().contains("Good Bye!"));
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue("Customer is broken",
                AopLog.getStringValue().contains("Good Enough?"));
    }

    @Test
    void testAroundAdvice() {
        assertTrue("Around advice is not good enough...",
                AopLog.getStringValue().contains("Hi!"));
        assertTrue("Around advice is not good enough...",
                AopLog.getStringValue().contains("See you!"));
    }

    @Test
    @DisplayName("barObject instanceof ApuBar")
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar);
    }
}
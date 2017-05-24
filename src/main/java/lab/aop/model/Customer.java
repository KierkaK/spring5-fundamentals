package lab.aop.model;

import lab.ioc.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Customer implements Person {
    private String name;
    private boolean broke;

    public void sayHello(Person person) {
    }
}

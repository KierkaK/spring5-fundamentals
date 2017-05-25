package lab.aop.model;

import lab.ioc.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Accessors(chain = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Customer implements Person {
    private String name;
    private boolean broke;

    @Override
    public void sayHello(Person person) {
        throw new UnsupportedOperationException();
    }
}

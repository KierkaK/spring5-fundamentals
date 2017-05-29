package lab.aop.model;

import lab.model.Country;
import lab.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Customer implements Person {
    private int id;
    private String name;
    private boolean broke;
    private Country country;

    private int age;
    private float height;
    private boolean isProgrammer;

    private List<String> contacts;

    @Override
    public void sayHello(Person person) {
        throw new UnsupportedOperationException();
    }
}

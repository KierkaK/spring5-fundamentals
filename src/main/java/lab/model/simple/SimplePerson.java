package lab.model.simple;

import lab.model.Country;
import lab.model.Person;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Value
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SimplePerson implements Person {
    @Id
    @Column
    private int id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    private int age;
    private float height;
    private boolean isProgrammer;

    private List<String> contacts;

    @Override
    public void sayHello(Person person) {
        System.out.printf("Hello, %s! I`m %s.%n", person.getName(), getName());
    }
}
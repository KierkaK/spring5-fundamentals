package lab.ioc;

import lab.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsualPerson implements Person {
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

    public void sayHello(Person person) {
        System.out.printf("Hello, %s! I`m %s.%n", person.getName(), getName());
    }
}
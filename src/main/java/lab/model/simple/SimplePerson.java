package lab.model.simple;

import lab.model.Country;
import lab.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Entity
//@Table(name = "person")
@Data
//@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SimplePerson implements Person {
//    @Id
//    @Column
    private int id;

//    @Column
    private String name;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "country_id")
    private Country country;

//    @Column
    private int age;

//    @Column
    private float height;

//    @Column
    private boolean isProgrammer;

//    @Column
    private List<String> contacts;

    @Override
    public void sayHello(Person person) {
        System.out.printf("Hello, %s! I`m %s.%n", person.getName(), name);
    }
}
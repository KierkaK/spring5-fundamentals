package lab.model;

import java.util.List;

public interface MutablePerson extends Person {

    Person setId(int id);

    Person setName(String name);

    Person setCountry(Country country);

    Person setAge(int age);

    Person setHeight(float height);

    Person setProgrammer(boolean isProgrammer);

    Person setContacts(List<String> contacts);

    Person setBroke(boolean broke);
}

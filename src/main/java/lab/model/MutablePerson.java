package lab.model;

import java.util.List;

public interface MutablePerson<T extends MutablePerson> extends Person {

    T setId(int id);

    T setName(String name);

    T setCountry(Country country);

    T setAge(int age);

    T setHeight(float height);

    T setProgrammer(boolean isProgrammer);

    T setContacts(List<String> contacts);

    T setBroke(boolean broke);
}

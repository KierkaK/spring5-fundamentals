package lab.model;

import java.util.List;

public interface Person {

    int getId();

    String getName();

    void sayHello(Person person);

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    List<String> getContacts();
}
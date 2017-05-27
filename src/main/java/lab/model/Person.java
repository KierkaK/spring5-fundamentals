package lab.model;

public interface Person {

    String getId();

    String getName();

    void sayHello(Person person);

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    java.util.List<String> getContacts();
}
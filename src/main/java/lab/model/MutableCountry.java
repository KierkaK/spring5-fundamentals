package lab.model;

public interface MutableCountry extends Country {

    MutableCountry setId(int id);

    MutableCountry setName(String name);

    MutableCountry setCodeName(String codeName);
}

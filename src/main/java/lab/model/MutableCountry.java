package lab.model;

public interface MutableCountry<T extends MutableCountry> extends Country {

    T setId(int id);

    T setName(String name);

    T setCodeName(String codeName);
}

package lab.model;

import java.io.Serializable;

public interface Country extends Serializable {
    int getId();

    String getName();

    String getCodeName();

    void setId(int id);

    void setName(String name);

    void setCodeName(String codeName);
}

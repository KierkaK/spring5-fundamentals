package lab.model;

import java.io.Serializable;

public interface Country extends Serializable {
    int getId();

    String getName();

    String getCodeName();
}

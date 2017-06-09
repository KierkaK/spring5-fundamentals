package lab.model.simple;

import lab.model.MutableCountry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Entity
@Table(name = "country")
public class SimpleCountry implements MutableCountry<SimpleCountry> {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String codeName;

    public SimpleCountry(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}

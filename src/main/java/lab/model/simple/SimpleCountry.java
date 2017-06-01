package lab.model.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Entity
@Table(name = "country")
public class SimpleCountry implements lab.model.Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column//(name="code_name")
    private String codeName;

    public SimpleCountry(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}

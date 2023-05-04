package JPA;

import javax.persistence.*;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
@EqualsAndHashCode
@Entity
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private Integer age;
}

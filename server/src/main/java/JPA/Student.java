package JPA;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Student extends Person{

    @ManyToMany
    private Set<Subject> subjects = new HashSet<>();

}

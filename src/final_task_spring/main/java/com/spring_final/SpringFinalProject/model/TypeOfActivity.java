package final_task_spring.main.java.com.spring_final.SpringFinalProject.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "typeofactivity")
public class TypeOfActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Activity> activities = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}

package final_task_spring.main.java.com.spring_final.SpringFinalProject.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    private String status;
    private String description;
    private int duration;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private TypeOfActivity type;

    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ActivityRequest> activityRequests = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}

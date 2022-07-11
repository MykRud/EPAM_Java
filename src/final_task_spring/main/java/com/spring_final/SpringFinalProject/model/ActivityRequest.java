package final_task_spring.main.java.com.spring_final.SpringFinalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "ActivityRequest")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Activity activity;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String action;
    private String status;

    @Override
    public String toString() {
        return user.getUsername() + " - " + activity.getName() + " - "
                + activity + " - " + status;
    }
}

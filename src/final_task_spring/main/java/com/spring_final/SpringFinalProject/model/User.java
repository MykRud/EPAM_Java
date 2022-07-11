package final_task_spring.main.java.com.spring_final.SpringFinalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int age;
    private String contact;
    private String gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Activity> activities = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ActivityRequest> activityRequests = new HashSet<>();

    public User(int id, String firstname, String lastName, String username, String password, int age, String contact, String gender) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
    }

    public User(String firstname, String lastName, String username, String password, int age, String contact, String gender) {
        this(0, firstname, lastName, username, password, age, contact, gender);
    }

    public User() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

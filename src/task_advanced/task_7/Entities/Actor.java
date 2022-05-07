package task_advanced.task_7.Entities;

import java.sql.Date;

public class Actor extends Entity{
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Actor(int id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("{ ");
        sb.append(id).append(", ").append(firstName).append(", ").append(lastName)
                .append(", ").append(birthDate).append(" }");
        return sb.toString();
    }
}

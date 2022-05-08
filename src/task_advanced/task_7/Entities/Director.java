package task_advanced.task_7.Entities;

import java.sql.Date;

public class Director extends Entity{
    private static int numberOfDirectors = 0;
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Director(String firstName, String lastName, Date birthDate) {
        this.id = numberOfDirectors + 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        numberOfDirectors++;
    }

    public Director(int id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public static void setNumberOfDirectors(int nod) {
        numberOfDirectors = nod;
    }

    public static void decreaseNumberOfDirector(){
        numberOfDirectors--;
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

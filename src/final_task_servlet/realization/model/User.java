package final_task_servlet.realization.model;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity{
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int age;
    private String contact;
    private String gender;
    public List<Authority> authorities = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();
    private List<ActivityRequest> activityRequests = new ArrayList<>();
    private List<String> timesOfActivities = new ArrayList<>(); // error

    public User(String firstName, String lastName, String username, String password, int age, String gender, String contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    public User(int id, String firstName, String lastName,
                String username, String password, int age, String gender, String contact) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    public User() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean addActivity(Activity activity){
        if(!activities.contains(activity))
            return false;
        activities.add(activity);
        return true;
    }

    public boolean removeActivity(Activity activity){
        if(!activities.contains(activity))
            return false;
        activities.remove(activity);
        return true;
    }

    public void setActivities(List<Activity> listOfActivities){
        this.activities = listOfActivities;
    }

    public void markTime(String time){
        timesOfActivities.add(time);
    }

    public List<String> getTimesOfActivities() {
        return timesOfActivities;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public List<ActivityRequest> getActivityRequests() {
        return activityRequests;
    }

    public void setActivityRequests(List<ActivityRequest> activityRequests) {
        this.activityRequests = activityRequests;
    }

    public void addActivityRequest(ActivityRequest request){
        activityRequests.add(request);
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}

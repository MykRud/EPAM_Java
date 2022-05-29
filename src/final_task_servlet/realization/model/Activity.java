package final_task_servlet.realization.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Activity extends Entity {
    private int id;
    private String name;
    private String status;
    private String description;
    private int duration;
    private Date startTime;
    private Date endTime;
    private TypeOfActivity type;
    private List<User> users = new ArrayList<>(); // example

    public Activity(String name, TypeOfActivity type){
        this.name = name;
        this.type = type;
    }

    public Activity(int id, String name, TypeOfActivity type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getDaysDuration(){
        return (int)Math.floor(duration / 60 / 24);
    }

    public int getHoursDuration(){
        return (int)Math.floor(duration / 60) - getDaysDuration() * 24;
    }

    public int getMinutesDuration(){
        return duration - (getDaysDuration() * 24 * 60) - getHoursDuration() * 60;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfActivity getType() {
        return type;
    }

    public void setType(TypeOfActivity type) {
        this.type = type;
    }

    public List<User> getUsers(){
        return users;
    }

    public boolean addUser(User user){
        for(User currentUser : users)
            if(currentUser.equals(user))
                return false;
        users.add(user);
        return true;
    }

    public boolean removeUser(User user){
        if(!users.contains(user))
            return false;
        users.remove(user);
        return true;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getSqlStartTime() {
        Timestamp date = new Timestamp(startTime.getTime());
        return date;
    }

    public Date getStartTime() {
        if(startTime == null) {
            return null;
        }
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        if(startTime == null) {
            this.startTime = null;
            return;
        }
        Date date = new Date(startTime.getTime());
        this.startTime = date;
    }

    public Timestamp getSqlEndTime() {
        if(endTime == null) {
            return null;
        }
        Timestamp date = new Timestamp(endTime.getTime());
        return date;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        if(endTime == null) {
            this.endTime = null;
            return;
        }
        Date date = new Date(endTime.getTime());
        this.endTime = date;
    }
}

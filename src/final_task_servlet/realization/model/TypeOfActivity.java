package final_task_servlet.realization.model;

import java.util.ArrayList;
import java.util.List;

public class TypeOfActivity extends Entity{
    private int id;
    private String name;
    private List<Activity> activities = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public TypeOfActivity(String name){
        this.name = name;
    }

    public TypeOfActivity(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void setName(String nameForTypeOfActivity) {
        this.name = nameForTypeOfActivity;
    }

    public List<Activity> getActivities(){
        return activities;
    }

    public boolean addActivity(Activity activity){
        if(!activity.getType().equals(this))
            return false;
        for(Activity currentActivity : activities) {
            if (currentActivity.equals(activity)) {
                return false;
            }
        }
        activities.add(activity);
        return true;
    }

    public boolean removeActivity(Activity activity){
        if(!activity.getType().equals(this))
            return false;
        for(Activity currentActivity : activities) {
            if (!currentActivity.equals(activity)) {
                return false;
            }
        }
        activities.remove(activity);
        return true;
    }

    public void setUsers(List<User> listOfUsers) {
        this.users = listOfUsers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setActivities(List<Activity> listOfActivities) {
        this.activities = listOfActivities;
    }

    @Override
    public String toString(){
        return getName();
    }

    /*
    Physical: Working Out, Practicing for a Sport, Playing a Sport, Swimming Laps,
    Yoga Class, Running, Pilates, Home Renovations.

    Mental: Meditating, Studying for an Exam, Completing an Assignment, Reading a Textbook,
    Writing an Article, Editing a Video, Learning a New Skill, Journalling.

    Social: Joining a Social Group, Going to a Rally, Attending an Improv Class,
    Going to Church, Spending time with Friends.

    Leisure: Going for a Hike, Playing a Video Game, Reading a Novel, Going to a Concert,
    Painting a Picture.

    Occupation: Giving a Presentation, Attending a Networking Event, Giving a Speech,
    Travel for work.
     */
}

package final_task_servlet.main.java.com.finaltask.org.example.realization.model;

public class ActivityRequest extends Entity{
    private int id;
    private Activity activity;
    private User user;
    private String action;
    private String status;

    public ActivityRequest(){

    }

    public ActivityRequest(int id, Activity activity, User user, String action, String status) {
        this.id = id;
        this.activity = activity;
        this.user = user;
        this.action = action;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package final_task_servlet.realization.comparators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.Comparator;

public class ComparatorByUsers implements Comparator<Activity> {
    @Override
    public int compare(Activity o1, Activity o2) {
        Integer firstInteger = o1.getUsers().size();
        Integer secondInteger = o1.getUsers().size();
        return firstInteger.compareTo(secondInteger);
    }
}

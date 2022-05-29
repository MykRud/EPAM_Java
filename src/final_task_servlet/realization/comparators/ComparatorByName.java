package final_task_servlet.realization.comparators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.Comparator;

public class ComparatorByName implements Comparator<Activity> {
    @Override
    public int compare(Activity o1, Activity o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

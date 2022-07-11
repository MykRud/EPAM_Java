package final_task_servlet.main.java.com.finaltask.org.example.realization.comparators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.Comparator;

/**
 * Comparator class that compares activities
 *
 * @see com.finaltask.org.example.realization.model.Activity
 * @see Comparator
 *
 * @author Misha Rudyk
 */
public class ComparatorByName implements Comparator<Activity> {

    /**
     * Compare method
     * @param o1
     * @param o2
     * @return the result of the comparison
     */
    @Override
    public int compare(Activity o1, Activity o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

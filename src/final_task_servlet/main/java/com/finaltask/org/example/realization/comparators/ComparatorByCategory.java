package final_task_servlet.main.java.com.finaltask.org.example.realization.comparators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.Comparator;

/**
 * Comparator class that compares categories
 *
 * @see com.finaltask.org.example.realization.model.TypeOfActivity
 * @see Comparator
 *
 * @author Misha Rudyk
 */
public class ComparatorByCategory implements Comparator<Activity> {

    /**
     * Compare method
     * @param o1
     * @param o2
     * @return the result of the comparison
     */
    @Override
    public int compare(Activity o1, Activity o2) {
        return o1.getType().getName().compareTo(o2.getType().getName());
    }
}

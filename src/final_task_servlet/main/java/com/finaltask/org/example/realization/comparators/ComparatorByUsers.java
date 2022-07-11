package final_task_servlet.main.java.com.finaltask.org.example.realization.comparators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.Comparator;

/**
 * Comparator class that compares users
 *
 * @see com.finaltask.org.example.realization.model.User
 * @see Comparator
 *
 * @author Misha Rudyk
 */
public class ComparatorByUsers implements Comparator<Activity> {

    /**
     * Compare method
     * @param o1
     * @param o2
     * @return the result of the comparison
     */
    @Override
    public int compare(Activity o1, Activity o2) {
        Integer firstInteger = o1.getUsers().size();
        Integer secondInteger = o1.getUsers().size();
        return firstInteger.compareTo(secondInteger);
    }
}

package task_advanced.task_1.firstTask.main.house;

import java.util.ArrayList;
import java.util.List;

public class House<K> {

    private final List<K> residents = new ArrayList();

    public void enter(K resident) {
        residents.add(resident);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("There are following residents in the house:\n");
        for (K resident : residents) {
            builder.append(resident.toString()).append("\n");
        }
        return builder.toString();
    }
}

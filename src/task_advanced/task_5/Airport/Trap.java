package task_advanced.task_5.Airport;

import java.util.ArrayList;
import java.util.List;

public class Trap{
    private static List<Plane> planes = new ArrayList<>();
    private int id;
    private Plane plane;

    static {
        for(int i = 0; i < 10; i++){
            planes.add(new Plane(i));
        }
    }

    public Trap(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private volatile boolean busy;

    public boolean isBusy(){
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public Plane setOnPlane() {
        Plane plane = null;
        while(plane == null) {
            for (int i = 0; i < planes.size(); i++) {
                if (!planes.get(i).isInFlying()) {
                    plane = planes.get(i);
                    break;
                }
            }
        }
        return plane;
    }
}

package task_advanced.task_5.Airport;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Passenger extends Thread {

    private CountDownLatch START;

    private int index;
    private Airport airport;

    public Passenger(int index, Airport airport) {
        this.index = index;
        this.airport = airport;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void run() {
        Terminal terminal = airport.joinTerminal(this);
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            Trap trap = terminal.addPassenger(this);
            Plane plane = trap.setOnPlane();
            START = plane.increaseNumberOfPeopleOnBoard(this);
            START.await();
            if(!plane.isInFlying()){
                plane.setStatus(true);
                plane.fly();
            }
        } catch (InterruptedException | NotAvailableException e) {
            e.printStackTrace();
        }
    }
}

package task_advanced.task_5.Airport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Plane {
    private static final Random random = new Random();
    // CountDownLatch sets like the capacity of the plane
    private CountDownLatch START = new CountDownLatch(random.nextInt(20 - 5) + 5);
    private final int CAPACITY = (int)START.getCount();
    private int numberOfPeopleOnBoard;
    private int id;
    private boolean inFlying = false;
    private List<Passenger> passengers = new ArrayList<>();

    public Plane(int id) {
        this.id = id;
    }

    public void setStatus(boolean isFlying){
        this.inFlying = isFlying;
    }

    public boolean isInFlying() {
        return inFlying;
    }

    public int getId() {
        return id;
    }

    public CountDownLatch increaseNumberOfPeopleOnBoard(Passenger passenger){
        System.out.println("Пасажир " + passenger.getIndex() + " сів на літак номер " + getId());
        passengers.add(passenger);
        ++numberOfPeopleOnBoard;
        START.countDown();
        return START;
    }

    public int getNumberOfPeopleOnBoard(){
        return numberOfPeopleOnBoard;
    }

    public void fly() throws InterruptedException {
            System.out.printf("Літак %d в небі з %d пасажирами\n", id, numberOfPeopleOnBoard);
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.printf("Літак %d приземлився в назначеному пункті\n", id);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.printf("Літак %d повертається до аеропорту\n", id);
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.printf("Літак %d прибув до аеропорту\n", id);
        setStatus(false);
        START = new CountDownLatch(CAPACITY);
    }

}

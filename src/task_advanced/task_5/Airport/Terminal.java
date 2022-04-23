package task_advanced.task_5.Airport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeoutException;

public class Terminal{
    private int numberOfPeopleInQueue;
    private static Semaphore SEMAPHORE = new Semaphore(10);
    private static List<Trap> traps = new ArrayList<>();
    private int id;

    static {
        for(int i = 0; i < 10; i++){
            traps.add(new Trap(i));
        }
    }

    public Terminal(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void incrementNumberOfPeopleInQueue(){
        ++numberOfPeopleInQueue;
    }

    public int getNumberOfPeopleInQueue(){
        return numberOfPeopleInQueue;
    }

    public Terminal(){
        this.traps = traps;
    }

    public Trap addPassenger(Passenger passenger) throws NotAvailableException {
        boolean isAvailable = false;
        while(!isAvailable) {
            if (SEMAPHORE.tryAcquire()) {
                isAvailable = true;
                for (Trap trap : traps) {
                    if (!trap.isBusy()) {
                        trap.setBusy(true);
                        System.out.println("Пасажир " + passenger.getIndex() + " зайшов на трапу " + trap.getId());
                        releasePassenger(passenger, trap);
                        return trap;
                    }
                }
            }
        }
        throw new NotAvailableException();
    }

    public void releasePassenger(Passenger passenger, Trap trap){
        trap.setBusy(false);
        System.out.println("Пасажир " + passenger.getId() + " пройшов трапу " + trap.getId());
        SEMAPHORE.release();
    }
}

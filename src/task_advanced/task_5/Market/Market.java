package task_advanced.task_5.Market;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Market {
    private AtomicLong index = new AtomicLong(20);
    private AtomicBoolean isWorking = new AtomicBoolean(true);
    private List<Broker> brokerList = new ArrayList<>();
    private static List<Action> actionList = new ArrayList<>();
    public static int numberOfActions;

    static {
        Random random1 = new Random();
        for(int i = 0; i < 20; i++) {
            actionList.add(new Action(i, random1.nextInt(100 - 5) + 5));
        }
        numberOfActions = actionList.size();
    }

    private Random random = new Random();

    public Market(){

    }
    public Market(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }

    public void setBrokers(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }



    public Market(AtomicLong index){
        this.index = index;
    } // змінити умову

    public AtomicLong getIndex(){
        return index;
    }

    public static List<Action> getActionList() {
        return actionList;
    }

    public List<Broker> getBrokers() {
        return brokerList;
    }

    public void increaseIndex(){
        index.incrementAndGet();
    }

    public void decreaseIndex(){
        index.decrementAndGet();
    }

    public boolean checkForWorking(){
        if(index.get() < 10)
            isWorking = new AtomicBoolean(false);
        return isWorking.get();
    }

}
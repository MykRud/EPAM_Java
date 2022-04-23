package task_advanced.task_5.Market;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Broker extends Thread{
    private int index;
    private Market market;

    public Broker(int index, Market market) {
        this.index = index;
        this.market = market;
    }

    public synchronized void sell(){
        try {
            market.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        Action action = Market.getActionList().get(random.nextInt(Market.numberOfActions));
        synchronized (action) {
            System.out.println("Брокер " + index + " намагається продати акцію №" + action.getId() + " з ціною " + action.getPrice());
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isSold = random.nextBoolean();
            if (isSold) {
                int newPrice = action.getPrice() + random.nextInt(10 - 2) + 2;
                market.increaseIndex();
                action.setNewPrice(newPrice);
                System.out.println("Брокер " + index + " продав акцію №" + action.getId() + " з ціною " + action.getPrice() + "; " +
                        "нова ціна акції: " + newPrice + ". Індекс біржі: " + market.getIndex());
            } else {
                market.decreaseIndex();
                System.out.println("Брокер " + index + " не продав акцію №" + action.getId() + ". Індекс біржі: " + market.getIndex());
                if (!market.checkForWorking())
                    System.out.println("Біржа впала");
            }
        }
    }
    @Override
    public void run(){
        synchronized (market) {
            while (market.checkForWorking()) {
                try {
                    market.notify();
                    sell();
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

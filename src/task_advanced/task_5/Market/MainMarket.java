package task_advanced.task_5.Market;

import java.util.ArrayList;
import java.util.List;

public class MainMarket {
    public static void main(String[] args) {
        Market market = new Market();
        List<Broker> brokers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            brokers.add(new Broker(i, market));
        }
        market.setBrokers(brokers);
        for (Broker broker : market.getBrokers())
            broker.start();
        System.out.println("Біржа відчинена!");
        System.out.println("Початковий індекс біржі: " + market.getIndex());

    }
}

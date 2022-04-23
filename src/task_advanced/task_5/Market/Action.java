package task_advanced.task_5.Market;

import java.util.concurrent.atomic.AtomicInteger;

public class Action {
    private AtomicInteger price;
    private AtomicInteger id;

    public Action(int id, int price) {
        this.price = new AtomicInteger(price);
        this.id = new AtomicInteger(id);
    }

    public int getId() {
        return id.get();
    }

    public int getPrice() {
        return price.get();
    }

    public void setNewPrice(int price) {
        this.price = new AtomicInteger(price);
    }
}

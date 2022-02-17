package task_advanced.task_2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_2.businessLogic.collections.queue.QueueImpl;
import task_advanced.task_2.businessLogic.entities.City;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueueImplTest {

    static QueueImpl<City> citiesQueue;
    static City exampleCity1;
    static City exampleCity2;
    static City exampleCity3;

    @BeforeEach
    void init(){
        citiesQueue = new QueueImpl<>();
        exampleCity1 = new City(1, "Kyiv", 2800000, true, "Ukraine");
        exampleCity2 = new City(2, "Washington", 10000000, true, "USA");
        exampleCity3 = new City(3, "Lviv", 800000, false, "Ukraine");
    }

    @Test
    void enqueue() {
        init();
        citiesQueue.enqueue(exampleCity1);
        assertEquals(exampleCity1, citiesQueue.top());
    }

    @Test
    void dequeue() {
        init();
        citiesQueue.enqueue(exampleCity2);
        assertEquals(exampleCity2, citiesQueue.dequeue());
    }

    @Test
    void top() {
        init();
        citiesQueue.enqueue(exampleCity3);
        assertEquals(exampleCity3, citiesQueue.top());
    }

    @Test
    void clear() {
        init();
        citiesQueue.enqueue(exampleCity1);
        citiesQueue.clear();
        assertEquals(0, citiesQueue.size());
    }

    @Test
    void size() {
        init();
        citiesQueue.enqueue(exampleCity1);
        citiesQueue.enqueue(exampleCity2);
        citiesQueue.enqueue(exampleCity3);
        assertEquals(3, citiesQueue.size());
    }

    @Test
    void iterator() {
        init();
        citiesQueue.enqueue(exampleCity1);
        citiesQueue.enqueue(exampleCity2);
        citiesQueue.enqueue(exampleCity3);
        City currentCity;
        Iterator<City> iterator = citiesQueue.iterator();
        while(iterator.hasNext()) {
            currentCity = iterator.next();
            if(currentCity == exampleCity1)
                iterator.remove();
        }
        assertEquals(exampleCity2, citiesQueue.top());
    }
}
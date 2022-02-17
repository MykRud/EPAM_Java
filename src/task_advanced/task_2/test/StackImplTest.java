package task_advanced.task_2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_2.businessLogic.collections.stack.StackImpl;
import task_advanced.task_2.businessLogic.entities.City;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackImplTest {

    static StackImpl<City> citiesStack;
    static City exampleCity1;
    static City exampleCity2;
    static City exampleCity3;

    @BeforeEach
    void init(){
        citiesStack = new StackImpl<>();
        exampleCity1 = new City(1, "Kyiv", 2800000, true, "Ukraine");
        exampleCity2 = new City(2, "Washington", 10000000, true, "USA");
        exampleCity3 = new City(3, "Lviv", 800000, false, "Ukraine");
    }

    @Test
    void push() {
        init();
        citiesStack.push(exampleCity1);
        assertEquals(exampleCity1, citiesStack.top());
    }

    @Test
    void pop() {
        init();
        citiesStack.push(exampleCity1);
        citiesStack.push(exampleCity2);
        assertEquals(exampleCity2, citiesStack.pop());
    }

    @Test
    void top() {
        init();
        citiesStack.push(exampleCity1);
        citiesStack.push(exampleCity3);
        assertEquals(exampleCity3, citiesStack.top());
    }

    @Test
    void clear() {
        init();
        citiesStack.push(exampleCity1);
        citiesStack.clear();
        assertEquals(0, citiesStack.size());
    }

    @Test
    void size() {
        init();
        citiesStack.push(exampleCity1);
        citiesStack.push(exampleCity2);
        citiesStack.push(exampleCity3);
        assertEquals(3, citiesStack.size());
    }

    @Test
    void iterator() {
        init();
        citiesStack.push(exampleCity1);
        citiesStack.push(exampleCity2);
        citiesStack.push(exampleCity3);
        City currentCity;
        Iterator<City> iterator = citiesStack.iterator();
        while(iterator.hasNext()) {
            currentCity = iterator.next();
            if(currentCity == exampleCity3)
                iterator.remove();
        }
        assertEquals(exampleCity2, citiesStack.top());
    }
}
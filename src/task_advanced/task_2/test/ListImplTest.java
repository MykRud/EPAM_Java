package task_advanced.task_2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_2.businessLogic.collections.list.ListImpl;
import task_advanced.task_2.businessLogic.entities.City;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ListImplTest {

    static ListImpl<City> citiesList;
    static City exampleCity1;
    static City exampleCity2;
    static City exampleCity3;

    @BeforeEach
    void init(){
        citiesList = new ListImpl<>();
        exampleCity1 = new City(1, "Kyiv", 2800000, true, "Ukraine");
        exampleCity2 = new City(2, "Washington", 10000000, true, "USA");
        exampleCity3 = new City(3, "Lviv", 800000, false, "Ukraine");
    }

    @Test
    void addFirst() {
        init();
        citiesList.addFirst(exampleCity1);
        assertEquals(exampleCity1, citiesList.getFirst());
    }

    @Test
    void addLast() {
        init();
        citiesList.addLast(exampleCity2);
        assertEquals(exampleCity2, citiesList.getLast());
    }

    @Test
    void remove() {
        init();
        citiesList.addFirst(exampleCity2);
        citiesList.addFirst(exampleCity1);
        citiesList.remove(exampleCity1);
        assertEquals(exampleCity2, citiesList.getFirst());
    }

    @Test
    void removeFirst() {
        init();
        citiesList.addFirst(exampleCity2);
        citiesList.addFirst(exampleCity1);
        citiesList.removeFirst();
        assertEquals(exampleCity2, citiesList.getFirst());
    }

    @Test
    void removeLast() {
        init();
        citiesList.addLast(exampleCity2);
        citiesList.addLast(exampleCity3);
        citiesList.removeLast();
        assertEquals(exampleCity2, citiesList.getLast());
    }

    @Test
    void getFirst() {
        init();
        citiesList.addFirst(exampleCity3);
        Iterator<City> iterator = citiesList.iterator();
        assertEquals(exampleCity3, iterator.next());
    }

    @Test
    void getLast() {
        init();
        citiesList.clear();
        citiesList.addFirst(exampleCity3);
        Iterator<City> iterator = citiesList.iterator();
        assertEquals(exampleCity3, iterator.next());
    }

    @Test
    void search() {
        init();
        citiesList.addFirst(exampleCity1);
        assertEquals("Kyiv", citiesList.getFirst().getName());
    }

    @Test
    void clear() {
        init();
        citiesList.addFirst(exampleCity1);
        citiesList.clear();
        assertEquals(0, citiesList.size());
    }

    @Test
    void size() {
        init();
        citiesList.addFirst(exampleCity1);
        citiesList.addFirst(exampleCity2);
        citiesList.addFirst(exampleCity3);
        assertEquals(3, citiesList.size());
    }

    @Test
    void iterator() {
        init();
        citiesList.addFirst(exampleCity1);
        citiesList.addFirst(exampleCity2);
        citiesList.addFirst(exampleCity3);
        City currentCity;
        Iterator<City> iterator = citiesList.iterator();
        while(iterator.hasNext()) {
                currentCity = iterator.next();
                if(currentCity.getCodeOfCity() % 2 != 0)
                    iterator.remove();
        }
        assertEquals(exampleCity2, citiesList.getFirst());

    }
}
package task_advanced.task_2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_2.businessLogic.collections.list.ListImpl;
import task_advanced.task_2.businessLogic.entities.City;
import task_advanced.task_2.database.Formatter;
import task_advanced.task_2.database.JSONFormatter;

import static org.junit.jupiter.api.Assertions.*;

class JSONFormatterTest {

    static City[] cities;

    @BeforeEach
    void init(){
         cities = new City[]{
                new City(1, "Washington", 10000000, true, "USA"),
                new City(2, "Los Angeles", 6000000, false, "USA"),
                new City(3, "Leon", 2000000, false, "France"),
                new City(4, "Madrid", 5000000, true, "Spain"),
                new City(5, "Kyiv", 2800000, true, "Ukraine")
        };
    }

    @Test
    void saveAndRead() {
        init();
        ListImpl<City> list = new ListImpl<>();
        list.setList(cities);
        Formatter stream = new JSONFormatter();
        stream.save(list, JSONFormatter.DEFAULT_PATH_JSON);

        ListImpl<City> newList = new ListImpl<>();
        stream.read(newList, JSONFormatter.DEFAULT_PATH_JSON);
        assertEquals(list.getFirst(), list.getFirst());
    }
}
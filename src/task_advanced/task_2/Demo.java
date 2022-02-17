package task_advanced.task_2;

import task_advanced.task_2.businessLogic.collections.Container;
import task_advanced.task_2.businessLogic.collections.list.IList;
import task_advanced.task_2.businessLogic.collections.list.ListImpl;
import task_advanced.task_2.businessLogic.collections.queue.Queue;
import task_advanced.task_2.businessLogic.collections.queue.QueueImpl;
import task_advanced.task_2.businessLogic.collections.stack.Stack;
import task_advanced.task_2.businessLogic.collections.stack.StackImpl;
import task_advanced.task_2.businessLogic.entities.City;
import task_advanced.task_2.database.Formatter;
import task_advanced.task_2.database.JSONFormatter;
import task_advanced.task_2.database.XMLFormatter;

import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
        City[] cities = new City[]{
                new City(1, "Washington", 10000000, true, "USA"),
                new City(2, "Los Angeles", 6000000, false, "USA"),
                new City(3, "Leon", 2000000, false, "France"),
                new City(4, "Madrid", 5000000, true, "Spain"),
                new City(5, "Kyiv", 2800000, true, "Ukraine")
        };

        IList<City> list = operationsWithList(cities);

        Queue<City> queue = operationsWithQueue(cities);

        Stack<City> stack = operationsWithStack(cities);

        // Basic list operations
        {
            System.out.println("List before: " + list);

            list.addFirst(new City(6, "Lviv", 800000, false, "Ukraine"));
            City someCity = new City(7, "Berlin", 5000000, false, "Germany");
            list.addLast(someCity);
            list.addLast(new City(8, "Paris", 2200000, true, "France"));
            list.removeFirst(); // delete "Washington"
            list.removeLast();  // delete "Paris"
            list.remove(someCity); // delete "Berlin"
            list.getFirst();    // "Los Angeles"
            list.getLast();     // "Kyiv"

            list.addLast(someCity);
            list.search(someCity);  // search for "Berlin"

            System.out.println("List after: " + list);
            System.out.println("Size of list: " + list.size()); // 6

            // Iterator
            iterate(list);

            list.clear();
        }

        // Basic queue operations
        {
            System.out.println("Queue before: " + queue);

            queue.enqueue(new City(6, "Lviv", 800000, false, "Ukraine"));
            City someCity = new City(7, "Paris", 2200000, true, "France");
            queue.enqueue(someCity);
            queue.dequeue();    // delete "Washington"
            queue.top();        // return "Los Angeles"

            System.out.println("Queue after: " + queue);

            System.out.println("Size of queue: " + queue.size());   // 6

            // Iterator
            iterate(queue);

            queue.clear();
        }

        // Basic stack operations
        {
            System.out.println("Stack before: " + stack);

            stack.push(new City(6, "Lviv", 800000, false, "Ukraine"));
            City someCity = new City(7, "Paris", 2200000, true, "France");
            stack.push(someCity);
            stack.pop();        // delete "Washington"
            stack.top();        // return "Los Angeles"

            System.out.println("Stack after: " + stack);

            System.out.println("Size of stack: " + stack.size());   // 6

            // Iterator
            iterate(stack);

            stack.clear();
        }

    }

    private static <T> IList<T> operationsWithList(T[] array) {
        IList<T> citiesList = new ListImpl<>();
        citiesList.setList(array);

        saveJSON(citiesList);

        IList<T> newListOfCities = new ListImpl<>();
        Formatter stream = new XMLFormatter();
        stream.read(newListOfCities, XMLFormatter.DEFAULT_PATH_XML);

        return newListOfCities;
    }

    private static <T> Queue<T> operationsWithQueue(T[] array) {
        Queue<T> citiesQueue = new QueueImpl<>();
        citiesQueue.setList(array);

        saveJSON(citiesQueue);

        Queue<T> newQueueOfCities = new QueueImpl<>();
        Formatter stream = new XMLFormatter();
        stream.read(newQueueOfCities, XMLFormatter.DEFAULT_PATH_XML);

        return newQueueOfCities;

    }

    private static <T> Stack<T> operationsWithStack(T[] array) {
        Stack<T> citiesStack = new StackImpl<>();
        citiesStack.setList(array);

        saveJSON(citiesStack);

        Stack<T> newStackOfCities = new StackImpl<>();
        Formatter stream = new XMLFormatter();
        stream.read(newStackOfCities, XMLFormatter.DEFAULT_PATH_XML);

        return newStackOfCities;
    }

    private static <T> void saveJSON(Container<T> container) {
        Formatter stream = new JSONFormatter();
        stream.save(container, JSONFormatter.DEFAULT_PATH_JSON);
    }

    private static void iterate(Container<City> container) {
        City currentCity;
        Iterator<City> iterator = container.iterator();
        while (iterator.hasNext()) {
            currentCity = iterator.next();
            if (currentCity.getCodeOfCity() % 2 == 0)
                iterator.remove();
        }

        for (City city : container)
            System.out.println(city);

    }
}

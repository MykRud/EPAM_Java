package task_advanced.task_2.businessLogic.collections;

import java.util.Iterator;

public interface Container<T> extends Iterable<T> {
    void clear(); // видаляє всі елементи

    int size(); // Повертає кількість елементів

    String toString(); // Повертає строкове представлення контейнера

    Iterator<T> iterator(); // Повертає ітератор над елементами.
    // Ітератор повинен реалізувати метод remove.

    T[] asList();

    void setList(T[] array);

}

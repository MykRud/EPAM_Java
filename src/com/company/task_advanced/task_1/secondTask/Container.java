package com.company.task_advanced.task_1.secondTask;

import java.util.Iterator;

public interface Container extends Iterable<Object>{
    void clear(); // видаляє всі елементи

    int size(); // Повертає кількість елементів

    String toString(); // Повертає строкове представлення контейнера

    Iterator<Object> iterator(); // Повертає ітератор над елементами.
                                 // Ітератор повинен реалізувати метод remove.
}

package com.company.task_advanced.task_1.secondTask;

public interface Array extends Container{
    void add(Object element); // Додає елемент в кінець

    void set(int index, Object element); // Встановлює елемент у вказану позицію

    Object get(int index); // Повертає елемент у вказаній позиції

    int indexOf(Object element); // Повертає індекс першого входження зазначеного елемента,
                                 // або -1, якщо цей масив не містить елемента.

    void remove(int index); // Видаляє елемент у вказаній позиції.
}

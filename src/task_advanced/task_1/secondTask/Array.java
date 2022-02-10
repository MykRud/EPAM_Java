package task_advanced.task_1.secondTask;

public interface Array<T> extends Container{
    void add(T element); // Додає елемент в кінець

    void set(int index, T element); // Встановлює елемент у вказану позицію

    T get(int index); // Повертає елемент у вказаній позиції

    int indexOf(T element); // Повертає індекс першого входження зазначеного елемента,
                                 // або -1, якщо цей масив не містить елемента.

    void remove(int index); // Видаляє елемент у вказаній позиції.
}

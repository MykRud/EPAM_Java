package task_advanced.task_2.businessLogic.collections.queue;

import task_advanced.task_2.businessLogic.collections.Container;

public interface Queue<T> extends Container<T> {
    void enqueue(T element);

    T dequeue();

    T top();
}

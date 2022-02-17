package task_advanced.task_2.businessLogic.collections.list;

import task_advanced.task_2.businessLogic.collections.Container;

public interface IList<T> extends Container<T> {

    void addFirst(T element);

    void addLast(T element);

    void removeFirst();

    void removeLast();

    T getFirst();

    T getLast();

    T search(T element);

    boolean remove(T element);
}

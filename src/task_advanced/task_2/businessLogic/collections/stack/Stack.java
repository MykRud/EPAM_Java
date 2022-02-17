package task_advanced.task_2.businessLogic.collections.stack;

import task_advanced.task_2.businessLogic.collections.Container;

public interface Stack<T> extends Container<T> {
    void push(T element);

    T pop();

    T top();

}

package com.company.FinalTask.interfaces;

public interface OperationWithDataBase<T> {
    void write(T item);
    String[] read(T item);
}

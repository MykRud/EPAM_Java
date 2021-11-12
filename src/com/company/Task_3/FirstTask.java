package com.company.Task_3;

import java.io.IOException;

public class FirstTask {
    public static void main(String[] args) throws IOException{
        int[] arr = new int[]{1, 3, 2, 5};
        boolean isSort;
        isSort = Sorting.IsSorted(arr, SortOrder.Ascending);
        System.out.println("Сортування за зростанням: " + isSort);
        isSort = Sorting.IsSorted(arr, SortOrder.Descending);
        System.out.println("Сортування за спаданням: " + isSort);
    }
}
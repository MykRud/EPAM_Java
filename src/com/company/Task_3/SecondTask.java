package com.company.Task_3;

import java.io.IOException;
import java.util.Arrays;

public class SecondTask {
    public static void main(String[] args) throws IOException {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        boolean isAscending, isDescending;
        isAscending = Sorting.IsSorted(arr, SortOrder.Ascending);
        System.out.println("Сортування за зростанням: " + isAscending);
        isDescending = Sorting.IsSorted(arr, SortOrder.Descending);
        System.out.println("Сортування за спаданням: " + isDescending);
        int[] newArr;
        if(isAscending || isDescending) {
            newArr = Transform(arr);
            System.out.println("Масив змінено: \n" +
                    "Початковий масив: " + Arrays.toString(arr) + "\n" +
                    "Новий масив: " + Arrays.toString(newArr));
            System.out.println();
        } else
            System.out.println("Без змін: " + Arrays.toString(arr));
    }

    private static int[] Transform(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        for(int i = 0; i < arr.length; i++){
            newArr[i] = newArr[i] + i;
        }
        return newArr;
    }
}

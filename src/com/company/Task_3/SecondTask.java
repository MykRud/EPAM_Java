package com.company.Task_3;

import java.io.IOException;
import java.util.Arrays;

//Create  function  Transform,  replacing  the  value  of  each  element  of  an
//integer array with the sum of this element value and its index, only if the
//given array is sorted in the given order (the order is set up by enum value
//SortOrder). Array and sort order are passed by parameters. To check, if
//the array is sorted, the function IsSorted from the Task 1 is called.
//Example,
//For {5, 17, 24, 88, 33, 2} and “ascending” sort order values in the array do
//not change;
//For  {15,  10,  3}  and  “ascending”  sort  order  values  in  the  array  do  not
//change;
//For {15, 10, 3} and “descending” sort order the values in the array are
//changing to {15, 11, 5}

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

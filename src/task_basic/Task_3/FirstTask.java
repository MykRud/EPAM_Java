package task_basic.Task_3;

import java.io.IOException;

//Create function IsSorted, determining whether a given array of integer
//values of arbitrary length is sorted in a given order (the order is set up by
//enum value SortOrder). Array and sort order are passed by parameters.
//Function does not change the array.

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
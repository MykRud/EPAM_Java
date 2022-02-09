package task_basic.Task_3;

import java.io.IOException;
import java.util.Arrays;

public class Sorting {
    public static boolean IsSorted(int[] arr, SortOrder order ) throws IOException {
        int temp;
        int[] newArr = Arrays.copyOf(arr, arr.length);
        if(order == SortOrder.Ascending){
            for(int i = 0; i < newArr.length-1; i++){
                if(newArr[i] > newArr[i+1]){
                    temp = newArr[i];
                    newArr[i] = newArr[i+1];
                    newArr[i+1] = temp;
                }
            }
            return IsEquals(arr, newArr);
        } else if(order == SortOrder.Descending){
            for(int i = 0; i < newArr.length-1; i++){
                if(newArr[i] < newArr[i+1]){
                    temp = newArr[i];
                    newArr[i] = newArr[i+1];
                    newArr[i+1] = temp;
                }
            }
            return IsEquals(arr, newArr);
        } else{
            throw new IOException("Введено невірне значення");
        }

    }

    public static boolean IsEquals(int[] arr, int[] newArr) {
        boolean isSorted = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != newArr[i]) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}

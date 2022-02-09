package task_basic.Task_2;

import java.util.Arrays;
import java.util.Scanner;

public class SecondTask {
    private static int size = 0;
    private static int[] fillArray(int[] arr){
        Scanner scan = new Scanner(System.in);
        int i = 0;

        do{
            System.out.println("Додати число у масив? (y - yes, n - no): ");
            String addAnother = scan.nextLine();
            if(addAnother.charAt(0) == 'n') {
                size = i--;
                break;
            }
            System.out.println("Введіть значення: ");
            arr[i++] = Integer.parseInt(scan.nextLine());
            if(i == arr.length)
                arr = makeAnotherArray(arr);
        } while(true);
        return arr;
    }
    private static int[] makeAnotherArray(int[] arr){
        return Arrays.copyOf(arr, arr.length*2);
    }
    private static void printArray(int[] arr){
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + "\t");
        }
        System.out.println("");
    }
    private static int findNCalculateMaxValues(int[] arr){
        int firstMax = 0;
        int secondMax = 0;
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(arr[i] <= arr[j]) {
                    if(arr[secondMax] == arr[j]){
                        firstMax = j;
                    } else {
                        secondMax = i;
                        firstMax = j;
                    }
                    if(arr[i] != arr[j])
                        secondMax = firstMax;
                    i = j;
                }
            }
        }
        if(secondMax == size + 1)
            return 0;
        return (firstMax - secondMax);
    }
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr = fillArray(arr);
        System.out.println("Ваш масив:");
        printArray(arr);
        int result = findNCalculateMaxValues(arr);
        System.out.println(result);

    }
}

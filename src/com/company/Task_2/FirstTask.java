package com.company.Task_2;

import java.util.Arrays;
import java.util.Scanner;

public class FirstTask {
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
    private static int[] swapValues(int[] arr){
        for(int i = 0; i < size/2; i++){
            if( (arr[i]%2==0) && arr[size-i-1]%2==0 ){
                int tempValue = arr[size-i-1];
                arr[size-i-1] = arr[i];
                arr[i] = tempValue;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr = fillArray(arr);
        System.out.println("Ваш масив:");
        printArray(arr);
        arr = swapValues(arr);
        printArray(arr);
    }
}

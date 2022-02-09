package task_basic.Task_2;

import java.util.Random;
import java.util.Scanner;

public class ThirdTask {
    private static int[][] fillArray(int[][] arr){
        Scanner scan = new Scanner(System.in);

        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.println("Введіть " + j + " значення " + i + " рядка:");
                arr[i][j] = Integer.parseInt(scan.nextLine());
            }
        }
        return arr;
    }

    private static void printArray(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    private static int[][] makeDiagonal(int[][] arr){
        int[][] temp = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                temp[i][j] = arr[i][j];
                if(j > i )
                    temp[i][j] = 1;
                else if(j < i){
                    temp[i][j] = 0;
                }
            }
        }
        return temp;
    }
    private static int[][] makeTemplateArray(int arr[][]){
        final Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                arr[i][j] = random.nextInt(20);
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        Scanner scan = new Scanner(System.in);
        System.out.println("Використовувати шаблонний масив чи ввести власноруч? (y - шаблонний, n - власноруч):");
        String addAnother = scan.nextLine();
        if(addAnother.charAt(0) == 'y')
            arr = makeTemplateArray(arr);
        else
            arr = fillArray(arr);
        System.out.println("Ваш масив:");
        printArray(arr);
        System.out.println("");
        int[][] temp = makeDiagonal(arr);
        printArray(temp);
    }
}

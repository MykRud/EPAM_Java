package com.company;

import java.util.Scanner;

public class SecondTask {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] numbers = new int[3];
        for(int i = 0; i < 3; i++){
            numbers[i] = n%10;
            n = n/10;
        }
        for(int i = 0; i < 3; i++){
            for(int j = i + 1; j < 3; j++){
                int temp;
                if(numbers[i] < numbers[j]){
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        System.out.println(numbers[0]*100 + numbers[1]*10 + numbers[2]);
    }
}

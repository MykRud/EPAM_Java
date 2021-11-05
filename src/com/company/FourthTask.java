package com.company;

import java.util.Scanner;

public class FourthTask {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int binary = 0;
        int sum = 0;
        while(n > 0){
            binary = binary*10 + n%2;
            n = n / 2;
        }

        while(binary > 0){
            sum += binary%10;
            binary = binary/10;
        }
        System.out.println(sum);
    }
}

package com.company;

import java.util.Scanner;

public class ThirdTask {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int odd = 0;
        while(n > 0){
            if(n%10 % 2 != 0){
                odd += n%10;
            }
            n = n/10;
        }
        System.out.println(odd);
    }
}

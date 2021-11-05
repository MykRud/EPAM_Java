package com.company;

import java.util.Scanner;

public class FirstTast {

    public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int result;
        if(n > 0){
            result = (int)Math.pow(n, 2);
        } else if(n < 0){
            result = Math.abs(n);
        } else{
            result = 0;
        }
        System.out.println("Result: " + result);
    }
}

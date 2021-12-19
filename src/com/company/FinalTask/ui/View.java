package com.company.FinalTask.ui;

import java.util.Scanner;

public class View {
    private static Scanner scanner;

    public static void runConsole(){
        scanner = new Scanner(System.in);
    }

    public static void print(String message){
        System.out.println(message);
    }

    public static String read(){
        return scanner.nextLine();
    }
}

package com.company.Task_3;

public class ThirdTask {
    public static void main(String[] args) {
        int n = 4; // Кількість елементів для добутку
        int t = 3; // Крок
        int a1 = 5; // Перший елемент
        int result = MultArithmeticElements(a1, n, t);
        System.out.println("Добуток: " + result);
    }

    private static int MultArithmeticElements(int a1, int n, int t) {
        int an = a1;
        int res = 1;
        for(int i = 0; i < n; i++){
            res *= an;
            an = an + t;
        }
        return res;
    }
}

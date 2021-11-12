package com.company.Task_3;

public class FourthTask {
    public static void main(String[] args) {
        int a1 = 100; // Перший елемент прогресії
        double t = 0.5; // Крок прогресії
        int alim = 20; // Ліміт, який не можна перетинати про додаванні
        double result = SumGeometricElements(a1, t, alim);
        System.out.println(result);
    }

    private static double SumGeometricElements(int a1, double t, int alim) {
        double an = a1;
        int res = 0;
        while (!(an < alim)) {
            res += an;
            an = an * t;
        }
        return res;
    }
}

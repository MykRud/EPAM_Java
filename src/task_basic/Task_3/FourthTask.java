package task_basic.Task_3;

//Create function SumGeometricElements, determining the sum of the first
//elements of a decreasing geometric progression of real numbers with a
//given initial element of a progression a1 and a given progression step t,
//while the last element must be greater than a given alim. an is calculated
//by the formula (an+1 = an * t), 0<t<1 .
//Example,
//For a progression, where a1 = 100, and t = 0.5, the sum of the first elements,
//grater than alim = 20, equals to 100+50+25 = 175

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

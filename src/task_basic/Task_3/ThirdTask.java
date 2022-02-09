package task_basic.Task_3;

//Create  function  MultArithmeticElements,  which  determines  the
//multiplication  of  the  first  n  elements  of  arithmetic  progression  of  real
//numbers with a given initial element of progression a1 and progression step
//t. an is calculated by the formula (an+1 = an + t).
//Example,
//For a1 = 5, t = 3,  n = 4 multiplication equals to 5*8*11*14 = 6160

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

package task_basic.MVC;

import java.util.Arrays;

public class View {

    public static final String INPUT_VALUE = "Введене число: ";
    public static final String WIN = "Ви вгадали число!!!";
    public static final String SMALLER = "Введене число менше";
    public static final String BIGGER = "Введене число більше";
    public static final String REG = "Ви успішно зареєструвалися";
    public static final String ALREADY_REG = "Ви вже зареєстровані";
    public static final String WELCOME = "Вхід виконано успішно";

    public static final String FAILED_JOIN = "Такого користувача немає. Спробуйте спочатку зареєструватися";


    public void print(String str){
        System.out.println(str);
    }
    public void printStatisticTable(Player[] array, int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++)
            printStatisticOfPlayer(array[i].getName(), array[i].getShoots());
    }
    public void printDiapason(int firstDiapason, int secondDiapason){
        System.out.println("Виберіть число із діапазону: від " + firstDiapason + " до " + secondDiapason);
    }
    public void printStatisticOfPlayer(String name, int[] shoots){
        System.out.println("Гравець " + name + " вибрав такі числа: " + Arrays.toString(shoots));
    }
    public void printNumberOfAttempts(String name, int number){
        System.out.println("Користувач " + name + " зробив " + number + " спроб");
    }
}

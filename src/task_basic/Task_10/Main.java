package task_basic.Task_10;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService users = new UserService();
        users.fromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/59cb6066-1fac-41ed-a571-811db551c75b/download/zp-lupen-2019.csv");
        //users.getList();
        System.out.println(users.getMaxIncome());
        System.out.println(users.getMinIncome());
        System.out.println(users.getMiddleIncome());
    }
}

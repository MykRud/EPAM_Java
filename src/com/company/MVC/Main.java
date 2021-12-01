package com.company.MVC;

public class Main{
    public static void main(String [] args) {
        Control control = new Control();
        Player Mike = new Player("Mike");
        control.register(Mike);
        control.start(Mike);
        //control.viewStatisticOfPlayer(Mike);
        Player Nick = new Player("Nick");
        control.register(Nick);
        control.start(Nick);

        control.viewStatisticOfPlayer(Mike);

        control.viewNumberOfAttempts(Mike);

        //control.viewStatisticTable();
    }
}
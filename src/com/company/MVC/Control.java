package com.company.MVC;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Control{

    private Model model;
    private final View view;
    private Player[] players;
    private Player player;
    private int numberOfPlayers;

    public Model getModel() {
        return model;
    }

    public Control() {
        view = new View();
        players = new Player[3];
    }

    private boolean join(Player player){
        for(int i = 0; i < numberOfPlayers; i++)
            if(player.checkForName(players[i].getName())) {
                view.print(View.WELCOME);
                this.player = player;
                return true;
            }
            view.print(View.FAILED_JOIN);
            return false;
    }

    public boolean register(Player player){
        if(players.length == numberOfPlayers){
            Player[] temp = players;
            players = new Player[temp.length*2];
            for(int i = 0; i < numberOfPlayers; i++)
                players[i] = temp[i];
        }
        if(numberOfPlayers != 0) {
            for (int i = 0; i < numberOfPlayers; i++) {
                if (players[i].getId() == player.getId()) {
                    view.print(View.ALREADY_REG);
                    return false;
                }
            }
        }
            view.print(View.REG);
            players[numberOfPlayers] = player;
            player.setId(++numberOfPlayers);
            return true;
    }

    public boolean start(Player player){
        setModel();
        if(!join(player)) {
            view.print(View.FAILED_JOIN);
            return false;
        }
        Scanner scan = new Scanner(System.in);
        boolean isTrue = false;
        while(!isTrue) {
            view.printDiapason(model.getDiapason(Diapason.FIRST), model.getDiapason(Diapason.SECOND));
            try {
                isTrue = checkForDiapason(scan.nextInt());
            } catch (InputMismatchException e){
                e.printStackTrace();
            }
            model.increaseNumberOfAttempts();
        }

        return true;
    }
    public boolean checkForDiapason(int number){
        Diapason diapason;
        if(number == model.getRandomNumber()) {
            view.print(View.WIN);
            diapason = Diapason.EQUAL;
            model.setValue(number, diapason);
            calculateStatistic();
            return true;
        }
        if(number > model.getRandomNumber()){
            diapason = Diapason.SECOND;
            view.print(View.BIGGER);
        }
        else {
            diapason = Diapason.FIRST;
            view.print(View.SMALLER);
        }
        model.setValue(number, diapason);
        return false;
    }

    public void setModel(){
        model = new Model();
    }

    public Player getPlayer(){
        return player;
    }

    public void setExamplePlayer(){
        player = new Player("Example");
    }

    private void calculateStatistic(){
        player.setShoots(model.getNumbers());
    }

    public void viewStatisticOfPlayer(Player player){
        calculateStatistic();
            view.printStatisticOfPlayer(player.getName(), player.getShoots());
    }
    public void viewStatisticTable(){
        view.printStatisticTable(players, numberOfPlayers);
    }
    public void viewNumberOfAttempts(Player player){
        view.printNumberOfAttempts(player.getName(), model.getNumberOfAttempts());
    }
}
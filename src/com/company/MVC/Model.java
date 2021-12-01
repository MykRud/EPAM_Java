package com.company.MVC;

import java.util.Random;

public class Model {
    private int[] numbers;
    private int size;
    private int randomNumber;
    private int firstDiapason = 0;
    private int secondDiapason = 100;
    private int enteredValue;
    private int numberOfAttempts;

    public Model(){
        this.numbers = new int[10];
        Random random = new Random();
        randomNumber = random.nextInt(100);
    }

    public int getEnteredValue() {
        return enteredValue;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getSize() {
        return size;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getDiapason(Diapason diapason) {
        if(diapason == Diapason.FIRST)
            return firstDiapason;
        else
            return secondDiapason;
    }

    public void setValue(int number, Diapason diapason){
        enteredValue = number;
        if(numbers.length == size)
            doubleArray();
        this.numbers[size++] = enteredValue;
        if(diapason == Diapason.FIRST)
            firstDiapason = number;
        else if(diapason == Diapason.SECOND)
            secondDiapason = number;
    }

    private void doubleArray(){
        int[] temp = numbers;
        numbers = new int[temp.length*2];
        for(int i = 0; i < size; i++)
            numbers[i] = temp[i];
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public void increaseNumberOfAttempts(){
        numberOfAttempts++;
    }

    public int getNumberOfAttempts(){
        return numberOfAttempts;
    }
}
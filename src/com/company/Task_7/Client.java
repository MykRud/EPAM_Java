package com.company.Task_7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Client {
    private final Deposit[] deposits;
    private int size;



    public Client() {
        this.deposits = new Deposit[10];
    }

    public boolean addDeposit(Deposit deposit){
        for(int i = 0; i < deposits.length; i++){
            if(deposits[i] == null) {
                deposits[i] = deposit;
                size++;
                return true;
            }
        }
        return false;
    }

    public Deposit getDeposit(int n) {
        return deposits[n];
    }

    public BigDecimal totalIncome(){
        BigDecimal sum = new BigDecimal("0");
        for (int i = 0; i < size; i++)
            sum = sum.add(deposits[i].income(deposits[i].Period()));
        sum = sum.setScale(2, RoundingMode.HALF_EVEN);
        return sum;
    }

    public BigDecimal[] maxIncome(){
        BigDecimal[] maxAmount = new BigDecimal[deposits.length];
        for (int i = 0; i < size; i++) {
            maxAmount[i] = deposits[i].income(deposits[i].Period());
        }
        return maxAmount;
    }

    public BigDecimal getIncomeByNumber(int n){
        if(n >= deposits.length || deposits[n] == null){
            return BigDecimal.valueOf(0);
        }
        return deposits[n].income(n).setScale(2, RoundingMode.HALF_EVEN);
    }
}
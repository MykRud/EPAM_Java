package com.company.Task_7;

import java.math.BigDecimal;

public class BaseDeposit extends Deposit{
    public BaseDeposit(BigDecimal amount, int period) {
        super(amount, period);
        calculateAmountOfEachMonth();
        calculateIncomeOfEachMonth();
        
    }

    private void calculateIncomeOfEachMonth() {
        for (int i = 0; i < Period(); i++) {
            incomeOfEachMonth[i] = amountOfEachMonth[i].subtract(Amount());
        }
    }

    private void calculateAmountOfEachMonth() {
        amountOfEachMonth[0] = BigDecimal.valueOf(0);
        for(int i = 1; i < Period()+1; i++){
            amountOfEachMonth[i-1] = Amount().multiply(BigDecimal.valueOf(pow(i)));
        }
    }

    public BigDecimal income(int month) {
        return incomeOfEachMonth[month-1];
    }

    private double pow(int count) {
        double res = 1;
        for (int i = 0; i < count; i++) {
            res *= 1 + 0.05;
        }
        return res;
    }
}

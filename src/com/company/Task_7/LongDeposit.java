package com.company.Task_7;

import java.math.BigDecimal;

public class LongDeposit extends Deposit{
    public LongDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
        calculateAmountOfEachMonth();
        calculateIncomeOfEachMonth();
    }

    public BigDecimal income(int month) {
        return incomeOfEachMonth[month-1];
    }

    private void calculateIncomeOfEachMonth() {

        for (int i = 0; i < Period(); i++) {
            incomeOfEachMonth[i] = amountOfEachMonth[i].subtract(Amount());
        }
    }

    private void calculateAmountOfEachMonth() {
            for(int i = 1; i < Period()+1; i++){
                if(i <= 7) {
                    amountOfEachMonth[i-1] = Amount();
                    continue;
                }
                amountOfEachMonth[i-1] = Amount().multiply(BigDecimal.valueOf(pow(i-7)));
        }

    }

    private double pow(int count) {
        double res = 1;
        for (int i = 0; i < count; i++) {
            res *= 1 + 0.15;
        }
        return res;
    }

}

package com.company.Task_7;

import java.math.BigDecimal;

public class SpecialDeposit extends Deposit{
    public SpecialDeposit(BigDecimal amount, int period) {
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
        amountOfEachMonth[0] = Amount().multiply(BigDecimal.valueOf(1.01));
        for(int i = 1; i < Period(); i++){
            amountOfEachMonth[i] = amountOfEachMonth[i-1].multiply(BigDecimal.valueOf(1.0 + (double)(i+1)/100));
        }
    }

    public BigDecimal income(int month) {
        return incomeOfEachMonth[month-1];
    }
}

package com.company.Task_7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDeposit extends Deposit{

    public SpecialDeposit(BigDecimal amount, int period) {
        super(amount, period);
    }

    protected void calculateAmountOfEachMonth() {
        getAmountOfEachMonth()[0] = Amount().multiply(BigDecimal.valueOf(1.01));
        for(int i = 1; i < Period(); i++){
            getAmountOfEachMonth()[i] = getAmountOfEachMonth()[i-1].multiply(BigDecimal.valueOf(1.0 + (double)(i+1)/100));
        }
    }

    public BigDecimal income(int month) {
        return getIncomeOfEachMonth()[month-1].setScale(2, RoundingMode.HALF_EVEN);
    }
}

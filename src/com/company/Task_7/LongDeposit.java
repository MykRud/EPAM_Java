package com.company.Task_7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDeposit extends Deposit{

    public LongDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    protected void calculateAmountOfEachMonth() {
            for(int i = 1; i < Period()+1; i++){
                if(i < 7) {
                    getAmountOfEachMonth()[i-1] = Amount();
                    continue;
                }
                getAmountOfEachMonth()[i-1] = Amount().multiply(BigDecimal.valueOf(pow(i-6)));
        }
    }

    public BigDecimal income(int month) {
        return getIncomeOfEachMonth()[month-1].setScale(2, RoundingMode.HALF_EVEN);
    }

    private double pow(int count) {
        double res = 1;
        for (int i = 0; i < count; i++) {
            res *= 1 + 0.15;
        }
        return res;
    }
}
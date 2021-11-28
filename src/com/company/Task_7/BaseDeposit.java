package com.company.Task_7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit {

    public BaseDeposit(BigDecimal amount, int period) {
        super(amount, period);
    }

    public void calculateAmountOfEachMonth() {
        getAmountOfEachMonth()[0] = BigDecimal.valueOf(0);
        for (int i = 1; i < Period() + 1; i++) {
            getAmountOfEachMonth()[i - 1] = Amount().multiply(BigDecimal.valueOf(pow(i)));
        }
    }

    public BigDecimal income(int month) {
        return getIncomeOfEachMonth()[month - 1].setScale(2, RoundingMode.HALF_EVEN);
    }

    protected double pow(int count) {
        double res = 1;
        for (int i = 0; i < count; i++) {
            res *= 1 + 0.05;
        }
        return res;
    }
}
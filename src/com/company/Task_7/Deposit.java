package com.company.Task_7;

import java.math.BigDecimal;

public abstract class Deposit {
    private BigDecimal amount;
    private int period;
    public BigDecimal[] amountOfEachMonth;
    public BigDecimal[] incomeOfEachMonth;

    public BigDecimal Amount() {
        return amount;
    }

    public int Period() {
        return period;
    }

    public Deposit(BigDecimal depositAmount, int depositPeriod) {
        this.amount = depositAmount;
        this.period = depositPeriod;
        amountOfEachMonth = new BigDecimal[depositPeriod];
        incomeOfEachMonth = new BigDecimal[depositPeriod];
    }
    public abstract BigDecimal income(int month);
}

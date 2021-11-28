package com.company.Task_7;

import java.math.BigDecimal;

public abstract class Deposit {
    private final BigDecimal amount;
    private final int period;
    private final BigDecimal[] amountOfEachMonth;
    private final BigDecimal[] incomeOfEachMonth;

    public Deposit(BigDecimal depositAmount, int depositPeriod) {
        this.amount = depositAmount;
        this.period = depositPeriod;
        amountOfEachMonth = new BigDecimal[depositPeriod];
        incomeOfEachMonth = new BigDecimal[depositPeriod];
        calculateAmountOfEachMonth();
        calculateIncomeOfEachMonth();
    }

    public BigDecimal[] getAmountOfEachMonth() {
        return amountOfEachMonth;
    }

    public BigDecimal[] getIncomeOfEachMonth() {
        return incomeOfEachMonth;
    }

    protected void calculateIncomeOfEachMonth() {
        for (int i = 0; i < Period(); i++) {
            incomeOfEachMonth[i] = amountOfEachMonth[i].subtract(Amount());
        }
    }
    protected abstract void calculateAmountOfEachMonth();

    public BigDecimal Amount() {
        return amount;
    }

    public int Period() {
        return period;
    }

    public abstract BigDecimal income(int month);

}
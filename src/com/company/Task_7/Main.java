package com.company.Task_7;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BaseDeposit bd = new BaseDeposit(BigDecimal.valueOf(1000), 3);
        BigDecimal big1 = bd.income(3);
        System.out.println(big1);
        SpecialDeposit sd = new SpecialDeposit(BigDecimal.valueOf(1000), 3);
        BigDecimal big2 = sd.income(3);
        System.out.println(big2);
        LongDeposit ld = new LongDeposit(BigDecimal.valueOf(1000), 10);
        BigDecimal big3 = ld.income(9);
        System.out.println(big3);
    }
}

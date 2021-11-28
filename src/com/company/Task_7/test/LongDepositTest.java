package com.company.Task_7.test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class LongDepositTest extends BasisForTesting {

    @Test
    void calculateAmountOfEachMonth() {
        BigDecimal[] amountOfEachMonth = getLongDeposit().getAmountOfEachMonth();
        assertArrayEquals(scale(getLongDepositExample()), scale(amountOfEachMonth));
    }

    @Test
    void income() {
        assertEquals(BigDecimal.valueOf(2257.50).setScale(2, RoundingMode.HALF_EVEN),
                getLongDeposit().income(8).setScale(2, RoundingMode.HALF_EVEN));
        assertThrows(IndexOutOfBoundsException.class, () -> getBaseDeposit().income(16));
    }

}
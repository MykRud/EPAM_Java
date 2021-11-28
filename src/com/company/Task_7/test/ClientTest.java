package com.company.Task_7.test;

import com.company.Task_7.BaseDeposit;
import com.company.Task_7.Client;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest extends BasisForTesting{

    private final Client person = new Client();

    @Test
    void addDeposit() {
        person.addDeposit(getBaseDeposit());
        assertEquals(getBaseDeposit(), person.getDeposit(0));
        assertTrue(person.addDeposit(getBaseDeposit()));
    }

    private void fillArray(){
        for(int i = 0; i < 5; i++){
            person.addDeposit(new BaseDeposit(BigDecimal.valueOf(1000), 5));
        }
    }

    @Test
    void totalIncome() {
        fillArray();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < 5; i++)
            sum = sum.add(person.getDeposit(i).income(person.getDeposit(i).Period()));
        assertEquals(sum, person.totalIncome());
    }

    @Test
    void maxIncome() {
        fillArray();
       for(int i = 0; i < 5; i++)
           assertEquals(BigDecimal.valueOf(276.28), person.maxIncome()[i]);
    }

    @Test
    void getIncomeByNumber() {
        fillArray();
        assertEquals(BigDecimal.valueOf(50.00).setScale(2, RoundingMode.HALF_EVEN), person.getIncomeByNumber(1));
       assertThrows(IndexOutOfBoundsException.class, () -> person.getIncomeByNumber(0));
    }
}
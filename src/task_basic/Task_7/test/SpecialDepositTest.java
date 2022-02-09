package task_basic.Task_7.test;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class SpecialDepositTest extends BasisForTesting {

    @Test
    void calculateAmountOfEachMonth() {
        BigDecimal[] amountOfEachMonth = getSpecialDeposit().getAmountOfEachMonth();
        assertArrayEquals(scale(getSpecialDepositExample()), scale(amountOfEachMonth));
    }

    @Test
    void income() {
        assertEquals(BigDecimal.valueOf(151.00).setScale(2, RoundingMode.HALF_EVEN),
                getSpecialDeposit().income(2).setScale(2, RoundingMode.HALF_EVEN));
        assertThrows(IndexOutOfBoundsException.class, () -> getBaseDeposit().income(20));
    }

}
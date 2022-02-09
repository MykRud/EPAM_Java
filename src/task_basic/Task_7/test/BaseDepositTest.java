package task_basic.Task_7.test;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class BaseDepositTest extends BasisForTesting {

    @Test
    void calculateAmountOfEachMonth() {
        BigDecimal[] amountOfEachMonth = getBaseDeposit().getAmountOfEachMonth();
        assertArrayEquals(scale(getBaseDepositExample()), scale(amountOfEachMonth));
    }

    @Test
    void income() {
        assertEquals(BigDecimal.valueOf(102.50).setScale(2, RoundingMode.HALF_EVEN),
                getBaseDeposit().income(2).setScale(2, RoundingMode.HALF_EVEN));
        assertThrows(IndexOutOfBoundsException.class, () -> getBaseDeposit().income(10));
    }
}
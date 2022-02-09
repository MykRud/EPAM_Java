package task_basic.Task_4.Test;

import task_basic.Task_3.SortOrder;
import task_basic.Task_3.Sorting;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Test");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After Test");
    }

    @Test
    public void sortingAscending() throws IOException {
        Assertions.assertTrue(Sorting.IsSorted(new int[]{1, 2, 6, 7}, SortOrder.Ascending));
    }

    @Test
    public void sortingDescending() throws IOException {
        assertTrue(Sorting.IsSorted(new int[]{9, 7, 4, 1}, SortOrder.Descending));
    }

    @Test(expected = IOException.class)
    public void isSorted() throws IOException {
        assertFalse(Sorting.IsSorted(new int[]{2, 7, 9, 1}, SortOrder.Ascending));
        assertFalse(Sorting.IsSorted(new int[]{2, 7, 9, 1}, SortOrder.Descending));
    }
}
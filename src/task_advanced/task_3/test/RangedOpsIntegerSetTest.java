package task_advanced.task_3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_3.RangedOpsIntegerSet;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class RangedOpsIntegerSetTest {

    static RangedOpsIntegerSet set;

    @BeforeEach
    void init(){
        set = new RangedOpsIntegerSet();
    }

    @Test
    void shouldAdd() {
        set.add(5);
        set.add(7);
        set.add(1);
        set.add(-1);
        set.add(3);
        set.add(-1);
        set.add(24);
        set.add(-8);
        assertEquals("5 7 1 -1 3 24 -8", set.toString());
    }

    @Test
    void shouldAddRange(){
        set.add(1, 6);
        assertEquals("1 2 3 4 5", set.toString());
    }

    @Test
    void shouldAddOnlyOneNumberOfRange(){
        set.add(1, 6);
        set.add(4, 7);
        assertEquals("1 2 3 4 5 6", set.toString());
    }

    @Test
    void shouldIgnoreSameNumber(){
        set.add(1);
        set.add(1);
        set.add(2);
        assertEquals("1 2", set.toString());
    }

    @Test
    void shouldRemove() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(1);
        assertEquals("2 3", set.toString());
    }

    @Test
    void shouldRemoveIntegersOfRange() {
        set.add(1, 6);
        set.remove(3, 6);
        assertEquals("1 2", set.toString());
    }

    @Test
    void shouldNotRemoveAnyNumberOfRange() {
        set.add(1, 3);
        set.remove(3, 10);
        assertEquals("1 2", set.toString());
    }

    @Test
    void shouldReturnSize() {
        set.add(1, 8);
        assertEquals(7, set.size());
    }

    @Test
    void iterator() {
        set.add(1, 3);
        set.add(3);
        set.add(4);
        set.add(5);
        Iterator<Integer> iterator = set.iterator();
        String result = "";
        while(iterator.hasNext())
            result += iterator.next();
        assertEquals("12345", result );
    }
}
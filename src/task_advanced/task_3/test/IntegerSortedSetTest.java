package task_advanced.task_3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_3.IntegerSortedSet;

import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class IntegerSortedSetTest {

    static IntegerSortedSet set;

    @BeforeEach
    void init(){
        set = new IntegerSortedSet();
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
        assertEquals("[1, -1, 3, 5, 7, -8, 24]", set.toString());
    }

    @Test
    void shouldIgnoreSameElementsWhileAdding(){
        set.add(1);
        set.add(-1);
        set.add(3);
        set.add(-1); // should be ignored
        set.add(9);
        set.add(-9);
        set.add(-9); // should be ignored
        set.add(24);
        assertEquals("[1, -1, 3, 9, -9, 24]", set.toString());
    }

    @Test
    void shouldRemove() {
        // initialising
        set.add(5);
        set.add(7);
        set.add(1);
        set.add(-1);
        set.add(3);
        set.add(-1);
        set.add(24);
        set.add(-9);
        // removing
        set.remove(7);
        set.remove(-1);
        set.remove(-9);
        // result
        assertEquals("[1, 3, 5, 24]", set.toString());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhileRemoving(){
        set.add(4);
        assertThrows(IllegalArgumentException.class, () -> set.remove(5));
    }

    @Test
    void shouldFindElement() {
        set.add(4);
        set.add(5);
        set.add(3);
        assertEquals(5, set.search(5));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhileSearching(){
        assertThrows(IllegalArgumentException.class, () -> set.search(5));
    }

    @Test
    void shouldAddCollection() {
        set.add(1);
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(15);
        treeSet.add(12);
        treeSet.add(25);
        treeSet.add(17);
        treeSet.add(19);
        set.addOf(treeSet);
        set.add(10);
        assertEquals("[1, 10, 12, 15, 17, 19, 25]", set.toString());
    }

    @Test
    void iterator() {
        set.add(5);
        set.add(6);
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        String result = "";
        while(iterator.hasNext())
            result += iterator.next();
        assertEquals("156", result );
    }
}
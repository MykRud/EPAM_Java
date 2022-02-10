package task_advanced.task_1.secondTask.test;

import task_advanced.task_1.secondTask.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayImplTest {

    static ArrayImpl<String> array;

    @Before
    public static void init() {
        array = new ArrayImpl<String>();
    }

    @Test
    void add() {
        init();
        array.add("Hello");
        assertEquals("Hello", array.get(0));
    }

    @Test
    void set() {
        init();
        array.set(0, "Smile");
        assertEquals("Smile", array.get(0));
    }

    @Test
    void get() {
        init();
        array.add("Hello");
        assertEquals("Hello", array.get(0));

    }

    @Test
    void indexOf() {
        init();
        array.add("Hello");
        assertEquals(0, array.indexOf("Hello"));
    }

    @Test
    void remove() {
        init();
        array.add("Hello");
        array.add("Smile");
        array.remove(0);
        assertEquals("Smile", array.get(0));
    }

    @Test
    void clear() {
        init();
        array.add("Hello");
        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    void size() {
        init();
        array.add("Hello");
        array.add("Smile");
        assertEquals(2, array.size());
    }

    @Test
    void hasNext() {
        init();
        array.add("Hello");
        Iterator<String> iterator = array.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    void next() {
        init();
        array.add("Hello");
        Iterator<String> iterator = array.iterator();
        assertEquals("Hello", iterator.next());
    }

    @Test
    void removeIter() {
        init();
        array.add("Hello");
        array.add("Zero");
        Iterator<String> iterator = array.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals("Hello"))
                iterator.remove();
        }
        assertEquals("Zero", array.get(0));
    }
}
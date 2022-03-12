package task_advanced.task_3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_3.IntStringCappedMap;

import java.util.Iterator;
import java.util.Map;

class IntStringCappedMapTest {

    static IntStringCappedMap map;

    @BeforeEach
    void init(){
        map = new IntStringCappedMap(15);
    }

    @Test
    void shouldPutEntries() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        assertEquals("{3=Three, 4=Four, 5=Five}", map.toString());
    }

    @Test
    void shouldReplaceValueWithSameKey() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(4, "NewFour");
        map.put(5, "Five");
        assertEquals("{3=Three, 4=NewFour, 5=Five}", map.toString());
    }

    @Test
    void shouldReturnValueByKey() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        assertEquals("Three", map.get(3));
    }

    @Test
    void shouldRemoveValueByKey() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.remove(2);
        assertEquals("{1=One, 3=Three}", map.toString());
    }

    @Test
    void shouldReturnNull() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        assertEquals(null, map.remove(5));
    }

    @Test
    void shouldReturnSize() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        assertEquals(3, map.size());
    }

    @Test
    void shouldReturnTrueIfMapIsEmpty() {
        assertTrue(map.isEmpty());
    }

    @Test
    void shouldReturnTrueIfMapContainsKey() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        assertTrue(map.containsKey(1));
    }

    @Test
    void shouldReturnTrueIfMapContainsValue() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        assertTrue(map.containsValue("One"));
    }

    @Test
    void shouldClearMap() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.clear();
        assertEquals("{}", map.toString());
    }

    @Test
    void shouldReturnSetOfEntries() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        assertEquals("[3=Three, 4=Four, 5=Five]", map.entrySet().toString());
    }

    @Test
    void iterator() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        assertEquals("1=One", iterator.next().toString() );
    }
}
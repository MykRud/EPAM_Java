package task_advanced.task_3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_3.IntegerSortedSet;
import task_advanced.task_3.PairStringList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairStringListTest {

    static PairStringList list;

    @BeforeEach
    void init(){
        list = new PairStringList();
    }

    @Test
    void shouldAddPair() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        assertEquals("[One, One, Two, Two, Three, Three]", list.toString());
    }

    @Test
    void shouldAddPairByIndex() {
        list.add("One");
        list.add("Three");
        list.add("Four");
        list.add("Five");
        list.add(6, "Something");
        assertEquals("[One, One, Three, Three, Four, Four, Five, Five, Something, Something]", list.toString());
    }

    @Test
    void shouldRemoveByIndex() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.remove(1);
        assertEquals("[Two, Two, Three, Three]", list.toString());
    }

    @Test
    void shouldRemoveByValue() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.remove("One");
        assertEquals("[Two, Two, Three, Three]", list.toString());
    }

    @Test
    void shouldReturnValueByIndex() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        assertEquals("One", list.get(1));

    }

    @Test
    void shouldAddElementsOfCollection() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        list.add("One");
        list.addOf(arrayList);
        assertEquals("[One, One, First, First, Second, Second, Third, Third]", list.toString());
    }

    @Test
    void shouldAddElementsOfCollectionToIndex() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        list.add("One1");
        list.add("Two2");
        list.add("Three3");
        list.addOf(3, arrayList);
        assertEquals("[One1, One1, Two2, Two2, Third, Third, Second, Second, First, First, Three3, Three3]", list.toString());

    }

    @Test
    void shouldSetValue() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.set(3, "First");
        assertEquals("[One, One, First, First, Three, Three]", list.toString());
    }

    @Test
    void iterator() {
        list.add("A");
        list.add("B");
        list.add("C");
        Iterator<String> iterator = list.iterator();
        String result = "";
        while(iterator.hasNext())
            result += iterator.next();
        assertEquals("AABBCC", result );
    }
}
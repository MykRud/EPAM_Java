package task_advanced.task_6.patterns.iterator;

import java.util.Iterator;

public class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new IntArrayTwoTimesIterator(array);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new IntArrayThreeTimesIterator(array);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new IntArrayFiveTimesIterator(array);
        }

    public static Iterable<String> table(String[] columns, int[] rows){
        return new Table(columns, rows);
    }



}

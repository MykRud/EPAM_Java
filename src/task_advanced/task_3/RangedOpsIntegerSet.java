package task_advanced.task_3;

import java.util.Arrays;
import java.util.Iterator;

public class RangedOpsIntegerSet implements Iterable<Integer>{
    private int[] setOfIntegers;
    private static final int DEFAULT_SIZE = 10;
    private int size;

    public RangedOpsIntegerSet(int[] setOfIntegers) {
        this.setOfIntegers = setOfIntegers;
    }

    public RangedOpsIntegerSet() {
        this.setOfIntegers = new int[DEFAULT_SIZE];
    }

    private void grow() {
        setOfIntegers = Arrays.copyOf(setOfIntegers, setOfIntegers.length * 2);
    }

    private boolean checkForUnique(int value){
        for(int elem : setOfIntegers)
            if(elem == value)
                return false;
            return true;
    }

    public boolean add(int fromInclusive, int toExclusive) {
        while(true) {
            if ((toExclusive - fromInclusive) > (setOfIntegers.length - size))
                grow();
            else
                break;
        }
        int previousSize = size;
        int i = size;
        for (int j = fromInclusive; j < toExclusive; j++) {
            if(checkForUnique(j)) {
                setOfIntegers[i++] = j;
                size++;
            }
        }
        return previousSize < size;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        if (size < (toExclusive - fromInclusive))
            return false;
        int previousSize = size;
        for (int i = fromInclusive; i < toExclusive; i++) {
            for (int numberToDelete : setOfIntegers) {
                if (numberToDelete == i) {
                    this.remove(numberToDelete);
                }
            }
        }
        return size < previousSize;
    }

    public boolean add(int single_value) {
        if (size == setOfIntegers.length)
            grow();
        if(!checkForUnique(single_value))
            return false;
        setOfIntegers[size++] = single_value;
        return true;
    }

    public boolean remove(int single_value) {
        if (size == 0)
            return false;
        int[] newSetOfIntegers = new int[setOfIntegers.length];
        for (int i = 0, j = 0; i < setOfIntegers.length; i++, j++) {
            if (setOfIntegers[i] == single_value) {
                i++;
                size--;
            }
            newSetOfIntegers[j] = setOfIntegers[i];
        }
        setOfIntegers = newSetOfIntegers;
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size - 1; i++)
            sb.append(setOfIntegers[i]).append(" ");
        return sb.append(setOfIntegers[size - 1]).toString();
    }

    private class IteratorImpl implements Iterator<Integer>{
        int currentPosition = -1;

        @Override
        public boolean hasNext(){
            return currentPosition + 1 < size;
        }

        @Override
        public Integer next(){
            return setOfIntegers[++currentPosition];
        }

        @Override
        public void remove(){
            RangedOpsIntegerSet.this.remove(setOfIntegers[currentPosition]);
        }
    }

    public static void main(String[] args) {
        RangedOpsIntegerSet set = new RangedOpsIntegerSet();
        set.add(1, 5);
        System.out.println(set.add(1, 6));
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(13);
        set.add(14);
        set.add(15);
        System.out.println(set);
        System.out.println(set.remove(60, 70));

        Iterator<Integer> iterator = set.iterator();
        int current;
        while(iterator.hasNext()) {
            current = iterator.next();
            if(current % 2 == 0)
                iterator.remove();
        }
        for(int elem : set)
            System.out.println(elem);
    }
}

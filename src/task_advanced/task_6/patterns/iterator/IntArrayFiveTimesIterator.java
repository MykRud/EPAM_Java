package task_advanced.task_6.patterns.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayFiveTimesIterator implements Iterator<Integer> {
    private int[] array;
    private int size;
    private int currentPosition;
    private int currentSizeOfSourceArray = -1;
    private int counter;

    public IntArrayFiveTimesIterator(int[] array) {
        this.array = array;
        size = array.length * 5;
    }

    @Override
    public boolean hasNext() {
        if(size > currentPosition)
            return true;
        else
            return false;
    }

    @Override
    public Integer next() {
        if(!hasNext())
            throw new NoSuchElementException();

        counter++;

        if(counter > 5)
            counter = 1;

        if(counter == 1)
            ++currentSizeOfSourceArray;

        currentPosition++;
        return array[currentSizeOfSourceArray];
    }
}

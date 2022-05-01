package task_advanced.task_6.patterns.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayTwoTimesIterator implements Iterator<Integer> {
    private int[] array;
    private int size;
    private int currentPosition;
    private int currentSizeOfSourceArray;

    public IntArrayTwoTimesIterator(int[] array) {
        this.array = array;
        size = array.length * 2;
    }

    @Override
    public boolean hasNext() {
        if(size > currentPosition )
            return true;
        else
            return false;
    }

    @Override
    public Integer next() {
        if(!hasNext())
            throw new NoSuchElementException();
        if(currentPosition % 2 != 0)
            currentSizeOfSourceArray--;
        currentPosition++;
        return array[currentSizeOfSourceArray++];
    }

    //public static void main(String[] args) {
      //  IntArrayTwoTimesIterator iter = new IntArrayTwoTimesIterator(new int[]{1, 2, 3});
        //while(iter.hasNext())
          //  System.out.println(iter.next());
    //}
}

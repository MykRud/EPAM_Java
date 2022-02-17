package task_advanced.task_2.businessLogic.collections.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl<T> implements Stack<T> {

    private Object[] stack;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public StackImpl() {
        stack = new Object[DEFAULT_CAPACITY];
        // this.type = ((ParameterizedType) getClass()
        //       .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private void grow() {
        Object[] newStack = new Object[stack.length * 2];
        for (int i = 0; i < size; i++)
            newStack[i] = stack[i];
        stack = newStack;
    }

    @Override
    public void push(T element) {
        if (stack.length == size)
            grow();
        stack[size++] = element;
    }

    @Override
    public T pop() {
        T temp = (T) stack[size - 1];
        stack[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T top() {
        return (T) stack[size - 1];
    }

    @Override
    public void clear() {
        stack = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] asList() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++)
            array[i] = stack[i];
        return (T[]) array;
    }

    @Override
    public void setList(T[] array) {
        stack = array;
        size = array.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(stack[i]).append(" ");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public IteratorImpl iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {

        private int currentPosition = size - 1;

        @Override
        public boolean hasNext() {
            return currentPosition >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) stack[currentPosition--];
        }

        @Override
        public void remove() {
            Object[] newStack = new Object[stack.length];
            for (int i = size - 1, j = size - 1; i >= 0; i--, j--) {
                if (i == currentPosition + 1) {
                    j++;
                    continue;
                }
                newStack[j - 1] = stack[i];
            }
            currentPosition--;
            stack = newStack;
            size--;
        }
    }

    public static void main(String[] args) {
        StackImpl<String> stack = new StackImpl<>();
        stack.push("String1");
        stack.push("String2");
        stack.push("String3");
        stack.push("String4");
        stack.push("String5");

        System.out.println(stack);

        stack.pop();
        stack.pop();

        System.out.println(stack);

        System.out.println(stack.top());

        System.out.println(stack);

        System.out.println(stack.top());

        for (String str : stack)
            System.out.println(str);

        Iterator<String> iter = stack.iterator();
        while (iter.hasNext()) {
            String nextElem = iter.next();
            if (nextElem.equals("String2"))
                iter.remove();
        }
        System.out.println(stack);
    }
}


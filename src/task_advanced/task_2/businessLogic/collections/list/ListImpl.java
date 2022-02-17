package task_advanced.task_2.businessLogic.collections.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl<T> implements IList<T> {

    private Node<T> head;

    private int size;

    public ListImpl() {
        head = new Node<>(null, null);
        //this.type = ((ParameterizedType)getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public void addFirst(T element) {
        Node<T> newNode;
        if (head.getNext() == null) {
            newNode = new Node<>(element, null);
            head.setNext(newNode);
            size++;
            return;
        }
        newNode = new Node<>(element, head.getNext());
        head.setNext(newNode);
        size++;
    }

    @Override
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element, null);
        if (head.getNext() == null) {
            head.setNext(newNode);
            size++;
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        size++;
    }

    @Override
    public boolean remove(T element) {
        Node<T> current = head;
        while (!current.getNext().getValue().equals(element)) {
            current = current.getNext();
        }
        if (current.getNext() == null)
            return false;
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    @Override
    public void removeFirst() {
        head.setNext(head.getNext().getNext());
        size--;
    }

    @Override
    public void removeLast() {
        Node<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        current.setNext(null);
        size--;
    }

    @Override
    public T getFirst() {
        return head.getNext().getValue();
    }

    @Override
    public T getLast() {
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public T search(T element) {
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(element))
                return current.getNext().getValue();
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void clear() {
        head.setNext(null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public T[] asList() {
        Object[] list = new Object[size];
        Iterator<T> iter = iterator();
        int i = 0;
        while (iter.hasNext())
            list[i++] = iter.next();
        return (T[]) list;
    }

    @Override
    public void setList(T[] array) {
        Node<T> current = head;
        for (int i = 0; i < array.length; i++) {
            current.setNext(new Node<>(array[i], null));
            current = current.getNext();
        }
        size = array.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current.getNext() != null) {
            sb.append(current.getNext() + " ");
            current = current.getNext();
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public IteratorImpl iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {

        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition != size;
        }

        @Override
        public T next() {
            if (currentPosition > size)
                throw new NoSuchElementException();
            Node<T> current = head;
            for (int i = 0; i < currentPosition; i++)
                current = current.getNext();
            currentPosition++;
            return current.getNext().getValue();
        }

        @Override
        public void remove() {
            Node<T> current = head;
            for (int i = 0; i < currentPosition; i++)
                current = current.getNext();
            currentPosition--;
            ListImpl.this.remove(current.getValue());
        }
    }

    private static class Node<T> {

        private Node<T> next;
        private T value;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value.toString();
        }

    }

    public static void main(String[] args) {
        IList<String> list = new ListImpl<>();
        list.addLast("FirstString");
        list.addFirst("ZeroString");
        System.out.println(list);
        System.out.println("Should be ZeroString: " + list.getFirst());
        System.out.println("Should be FirstString: " + list.getLast());
        list.remove("ZeroString");
        System.out.println("After removing: " + list);
        list.removeLast();
        System.out.println("After removing last: " + list);
        list.addLast("NewFirstString");
        list.addLast("NewSecondString");
        list.addLast("NewThirdString");
        list.addLast("NewFourthString");
        list.addLast("NewFifthString");
        list.addLast("NewSixthString");
        System.out.println("After adding 6 elements: " + list);
        list.removeFirst();
        System.out.println("After removing first element: " + list);
        System.out.println(list.search("NewSecondString"));

        for (Object obj : list) {
            System.out.println(obj);
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String toRemove = iterator.next();
            if (toRemove.equals("NewFourthString"))
                iterator.remove();
        }
        System.out.println("After removing with iterator: " + list);
    }
}

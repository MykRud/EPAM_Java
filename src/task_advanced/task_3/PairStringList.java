package task_advanced.task_3;

import com.sun.xml.xsom.XSUnionSimpleType;

import java.util.*;

public class PairStringList implements Iterable<String>{
    private Node head;
    private int size;

    public void add(String value){
        Node newElement = new Node(value);
        if(head == null){
            head = newElement;
            head.setNext(new Node(newElement.getValue()));
            size++;
            size++;
            return;
        }
        Node currentNode = head;
        while(currentNode.getNext() != null)
            currentNode = currentNode.getNext();
        currentNode.setNext(newElement);
        newElement.setNext(new Node(newElement.getValue()));
        size++;
        size++;
    }

    public boolean add(int index, String value){
        if(index >= size)
            return false;

        Node nodeToAdd = new Node(value);

        Node currentNode = head;
        if(index % 2 == 0)
            index++;
        for(int i = 0; i < index; i++)
            currentNode = currentNode.getNext();
        Node nextNode = currentNode.getNext();
        currentNode.setNext(nodeToAdd);
        Node tempNode = new Node(nodeToAdd.getValue());
        tempNode.setNext(nextNode);
        nodeToAdd.setNext(tempNode);
        size++;
        size++;
        return true;



        /*Node newElement = new Node(value);
        if(index >= size)
            return false;

        Node currentNode = head;
        Node prevNode = head;
        for(int i = 0; i < index; i++) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if(index % 2 != 0) {
            prevNode = prevNode.getNext();
            currentNode = currentNode.getNext();
        }
        if(prevNode == head){
            Node prevHead = head;
            head = newElement;
            Node dublicate = new Node(newElement.getValue());
            dublicate.setNext(prevHead);
            newElement.setNext(dublicate);
            size++;
            size++;
            return true;
        }
        prevNode.setNext(newElement);
        Node dublicate = new Node(newElement.getValue());
        newElement.setNext(dublicate);
        dublicate.setNext(currentNode.getNext());
        size++;
        size++;
        return true;*/
    }

    public boolean remove(String value){
        Node currentNode = head;
        while(currentNode.getNext() != null){
            if(currentNode.getNext().getValue().equals(value)){
                if(currentNode == head) {
                    head = currentNode.getNext().getNext();
                    size--;
                    size--;
                    return true;
                }
                Node prevNode = currentNode;
                currentNode = currentNode.getNext();
                prevNode.setNext(currentNode.getNext().getNext());
                size--;
                size--;
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public boolean remove(int index){
        if(index >= size)
            return false;

        Node currentNode = head;
        for(int i = 0; i < index; i++)
            currentNode = currentNode.getNext();
        return remove(currentNode.getValue());
    }

    public String get(int index){
        if(index >= size)
            return null;

        Node currentNode = head;
        for(int i = 0; i < index; i++)
            currentNode = currentNode.getNext();
        return currentNode.getValue();
    }

    public void addOf(Collection<String> collection){
        Iterator<String> iter = collection.iterator();
        while(iter.hasNext())
            this.add(iter.next());
    }

    public void addOf(int index, Collection<String> collection){
        Iterator<String> iter = collection.iterator();
        while(iter.hasNext())
            this.add(index, iter.next());
    }

    public boolean set(int index, String value){
        if(index >= size)
            return false;
        Node nodeToSet = new Node(value);
        if(index % 2 != 0)
            index--;
        Node currentNode = head;
        Node beforeNode = head;

        for(int i = 0; i < index; i++){
            beforeNode = currentNode;
            currentNode = currentNode.getNext();
        }

        Node tempNode = new Node(nodeToSet.getValue());
        tempNode.setNext(currentNode.getNext().getNext());
        if(beforeNode == head) {
            head = nodeToSet;
            head.setNext(tempNode);
            return true;
        }
        beforeNode.setNext(nodeToSet);
        nodeToSet.setNext(tempNode);
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node currentNode = head;

        for(int i = 0; i < size-1; i++) {
            sb.append(currentNode.getValue()).append(", ");
            currentNode = currentNode.getNext();
        }
        sb.append(currentNode.getValue()).append("]");

        return sb.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return new PairStringListIterator();
    }

    private class PairStringListIterator implements Iterator<String>{

        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return size - 1 != currentIndex;
        }

        @Override
        public String next() {
            return get(++currentIndex);
        }
    }

    private static class Node{
        private String value;
        private Node next;

        public Node(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString(){
            return value;
        }
    }

    public static void main(String[] args) {
        PairStringList list = new PairStringList();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Five");
        list.add(6, "Something");
        list.remove(9);
        list.remove("Two");
        System.out.println(list.get(5));
        TreeSet<String> exampleSet = new TreeSet<>();
        exampleSet.add("SomeString1");
        exampleSet.add("SomeString2");
        exampleSet.add("SomeString3");
        list.addOf(2, exampleSet);

        System.out.println(list);

        System.out.println("--------------------------");

        list.set(7, "Hello");
        System.out.println(list);

        Iterator<String> iter = list.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
    }

}

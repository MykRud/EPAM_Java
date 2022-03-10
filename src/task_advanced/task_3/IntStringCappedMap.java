package task_advanced.task_3;

import java.util.*;

public class IntStringCappedMap implements Map<Integer, String> {
    private Entry head = null;
    private final int MAX_CAPACITY;
    private int size;
    private int totalCapacityOfValues;

    public IntStringCappedMap(int MAX_CAPACITY) {
        this.MAX_CAPACITY = MAX_CAPACITY;
    }

    @Override
    public String put(Integer key, String value){
        checkForCapacity(value);
        Entry newEntry = new Entry(key, value);
        if(head == null) {
            head = newEntry;
            totalCapacityOfValues = value.length();
            size++;
            return value;
        }
        if(checkForUnique(newEntry))
            return null;
        reduceEntries(value);
        Entry current = head;
        while(current.getNext() != null)
            current = current.getNext();
        current.setNext(newEntry);
        size++;
        totalCapacityOfValues += value.length();
        return value;
    }

    private boolean checkForUnique(Entry entryToCheck) {
        Entry currentEntry = head;
        if(head.getKey() == entryToCheck.getKey()) {
            head = entryToCheck;
            head.setNext(currentEntry.getNext());
            return true;
        }
            while(currentEntry.getNext() != null) {
            if(currentEntry.getNext().getKey() == entryToCheck.getKey()) {
                Entry prevEntry = currentEntry.getNext();
                currentEntry.setNext(entryToCheck);
                entryToCheck.setNext(prevEntry.getNext());
                return true;
            }
            currentEntry = currentEntry.getNext();
        }
        return false;
    }

    private void checkForCapacity(String value) {
        if(value.length() > MAX_CAPACITY)
            throw new IllegalArgumentException();
    }

    private void reduceEntries(String value){
        while(MAX_CAPACITY  < totalCapacityOfValues + value.length()){
            removeCurrentEntry();
        }
    }

    public void removeCurrentEntry() {
        totalCapacityOfValues -= head.getValue().length();
        head = head.getNext();
        size--;
    }
    @Override
    public String get(Object objKey){
        int key = (int)objKey;
        Entry current = head;
        while(current.getNext() != null) {
            current = current.getNext();
            if(current.getKey() == key)
                return current.getValue();
        }
        return null;
    }

    @Override
    public String remove(Object objKey){
        int key = (int)objKey;
        Entry currentEntry = head;
        if(head.getKey() == key){
            head = head.getNext();
            head.setNext(currentEntry.getNext());
            size--;
            return currentEntry.getValue();
        }
        while(currentEntry.getNext() != null){
            if(currentEntry.getNext().getKey() == key){
                Entry prevEntry = currentEntry.getNext();
                currentEntry.setNext(prevEntry.getNext());
                size--;
                return prevEntry.getValue();
            }
            currentEntry = currentEntry.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int numberKey = (int)key;
        Entry current = head;
        while(current.getNext() != null){
            if(current.getKey() == numberKey)
                return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        String stringValue = (String)value;
        Entry current = head;
        while(current.getNext() != null){
            if(current.getValue().equals(stringValue))
                return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
        for(int i = 0; i < m.size(); i++){
            for(Map.Entry<? extends Integer, ? extends String> entries : m.entrySet()){
                this.put(entries.getKey(), entries.getValue());
            }
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public AbstractSet<Integer> keySet() {
        return new AbstractSet<>() {
            @Override
            public Iterator<Integer> iterator() {
                return new KeyIterator();
            }

            @Override
            public int size() {
                return size;
            }

            class KeyIterator implements Iterator<Integer>{

                final EntryIterator iter = new EntryIterator();

                @Override
                public boolean hasNext() {
                    return iter.hasNext();

                }

                @Override
                public Integer next() {
                    return iter.next().getKey();
                }
            }
        };
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    public AbstractSet<Map.Entry<Integer, String>> entrySet(){
        return new AbstractSet<>() {
            @Override
            public Iterator<Map.Entry<Integer, String>> iterator() {
                return new EntryIterator();
            }

            @Override
            public int size() {
                return size();
            }

        };
    }

    public Iterator<Map.Entry<Integer, String>> iterator(){
        return new EntryIterator();
    }

    class EntryIterator implements Iterator<Map.Entry<Integer, String>>{

        int currentIndex;

        @Override
        public boolean hasNext() {
            Entry currentEntry = head;
            for(int i = 0; i < currentIndex; i++){
                if(currentEntry.getNext() == null)
                    return false;
                currentEntry = currentEntry.getNext();
            }
            return true;
        }

        @Override
        public Map.Entry<Integer, String> next() {
            Entry currentEntry = head;
            for(int i = 0; i < currentIndex; i++){
                currentEntry = currentEntry.getNext();
            }
            currentIndex++;
            Map.Entry<Integer, String> qwe;

            return currentEntry;
        }
    }

    private static class Entry implements Map.Entry<Integer, String> {
        private int key;
        private String value;
        private Entry next;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String setValue(String value) {
            this.value = value;
            return value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

    }

    @Override
    public String toString(){
        Entry currentEntry = head;
        StringBuilder sb = new StringBuilder("{");
        for(int i = 0; i < size-1; i++){
            sb.append(currentEntry.getKey()).append("=").append(currentEntry.getValue()).append(", ");
            currentEntry = currentEntry.getNext();
        }
        sb.append(currentEntry.getKey()).append("=").append(currentEntry.getValue()).append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        IntStringCappedMap map = new IntStringCappedMap(15);
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(3, "NewThree");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(5, "NewFive");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.remove(5);
        System.out.println(map.get(6));

        System.out.println(map);

        AbstractSet<Map.Entry<Integer, String>> set = map.entrySet();
        System.out.println(set);

        Iterator<Map.Entry<Integer, String>> iter = set.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());

        System.out.println(new java.util.TreeMap<>(map));
        System.out.println("--------------------");

        System.out.println(map.keySet());
    }
}

package task_advanced.task_6.patterns.decorator;

import java.util.*;

public class Decorators {
    public static void main(String[] args) {
        final List<String> list = Arrays.asList("1,2,3,4,5,6,7,8,9,10".split(","));
        List<String> someList = evenIndexElementsSubList(list);
    }
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {

        return new AbstractList<>(){
            private List<String> list = new ArrayList<>();
            private int size;

            {
                for(int i = 0; i < sourceList.size(); i++){
                    if(i % 2 != 0)
                        continue;
                    list.add(sourceList.get(i));
                    size++;
                }
            }

            @Override
            public int size() {
                return size;
            }

            @Override
            public String get(int index) {
                return list.get(index);
            }

            @Override
            public Iterator<String> iterator(){
                return list.iterator();
            }
        };
    }


}

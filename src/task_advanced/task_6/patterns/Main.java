package task_advanced.task_6.patterns;

import task_advanced.task_6.patterns.decorator.Decorators;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                "Nine", "Ten");
        list = Decorators.evenIndexElementsSubList(list);
        for(String element : list){
            System.out.println(element);
        }
    }
}

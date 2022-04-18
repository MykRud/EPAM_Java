package task_advanced.task_4.part1;

public class Main {
       public static void main(String[] args) throws InterruptedException {
        FirstThread th1 = new FirstThread("First Thread");
        th1.join();
        new SecondThread("Second Thread");
    }
}

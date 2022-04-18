package task_advanced.task_4.part1;

public class FirstThread extends Thread {
    public FirstThread(String name) {
        super(name);
        start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(333);
                System.out.println(getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

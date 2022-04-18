package task_advanced.task_4.part1;

public class SecondThread implements Runnable {

    Thread t;

    public SecondThread(String name) {
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(333);
                System.out.println(t.getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

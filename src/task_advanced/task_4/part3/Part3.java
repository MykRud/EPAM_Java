package task_advanced.task_4.part3;

public class Part3 {

    private int counter;
    private int counter2;

    public void incrementCounter(){
        counter++;
    }

    public void incrementCounter2(){
        counter2++;
    }

    public void compare(){
        new ThreadWithoutSynchronization(this);
        new ThreadWithoutSynchronization(this);
        new ThreadWithoutSynchronization(this);
    }
    public void compareSync(){
        new ThreadWithSynchronization(this);
        new ThreadWithSynchronization(this);
        new ThreadWithSynchronization(this);
    }

    private class ThreadWithoutSynchronization implements Runnable{
        Part3 obj;
        Thread thread;

        public ThreadWithoutSynchronization(Part3 obj){
            this.obj = obj;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread: " + (obj.counter == obj.counter2));
                obj.incrementCounter();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                obj.incrementCounter2();
            }
        }
    }

    private class ThreadWithSynchronization implements Runnable{
        final Part3 obj;
        Thread thread;

        public ThreadWithSynchronization(Part3 obj){
            this.obj = obj;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run(){
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread: " + (obj.counter == obj.counter2));
                    obj.incrementCounter();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj.incrementCounter2();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Non synchronized...");
        Part3 obj1 = new Part3();
        obj1.compare();

        Thread.sleep(1100);

        System.out.println("");
        System.out.println("Synchronized...");

        Part3 partSync = new Part3();
        partSync.compareSync();
    }
}

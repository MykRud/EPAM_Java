package task_advanced.task_4.part2;

import java.io.IOException;

public class Spam {
    private Thread[] threads;

    private String[] messages;
    private int[] millis;


    public Spam(String[] messages, int[] millis){
        this.messages = messages;
        this.millis = millis;
        threads = new Thread[messages.length];
    }
    public void start(){
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Worker(messages[i], millis[i]);;
        }
    }
    public void stop(){
        for(int i = 0; i < threads.length; i++){
            threads[i].interrupt();
        }
    }
    private static class Worker extends Thread{
        private String message;
        private int millis;

        public Worker(String message, int millis){
            this.message = message;
            this.millis = millis;
            start();
        }

        @Override
        public void run(){
            long startTime = System.currentTimeMillis();
            while(true){
                if(millis < (System.currentTimeMillis() - startTime)){
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(message);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };
        Spam spam = new Spam(messages, times);
        spam.start();
        char symbol;
        symbol = (char)System.in.read();
        if(symbol == '\n')
            System.out.println("qweqweqwe");
    }
}

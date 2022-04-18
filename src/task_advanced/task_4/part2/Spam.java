package task_advanced.task_4.part2;

import java.io.IOException;
import java.io.InputStream;

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
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(message);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 3333, 222 };
        Spam spam = new Spam(messages, times);
        new Thread(() -> {
            NewInputStream nis = new NewInputStream();
            char symbol;
            while(true){
                try {
                    symbol = (char)nis.read();
                    if(symbol == "\n"){
                        spam.stop();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
            spam.start();
    }

}

class NewInputStream extends InputStream {
    @Override
    public int read() throws IOException {
        return System.in.read();
    }
}

package task_advanced.task_5.Auction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Auction {

    private static CountDownLatch START = new CountDownLatch(3); // три кола аукціону за один лот
    private static int priceForLot = 0; // початкова ціна за лот, яку учасник повинен заплатити, щоб брати
                                        // участь у аукціоні

    public static void main(String[] args) throws InterruptedException {
        // формуємо список учасників аукціону
        List<Client> clients = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            clients.add(new Client(i));
        }

        // цикл, що відповідає за кількість лотів
        for(int i = 0; i < 10; i++){
            for(Client client : clients)
                client.reset();
            priceForLot = 0;
            System.out.println("Лот номер " + i);
            priceForLot = (int) (Math.random() * 1000); // початкова ціна за лот
            int maxPrice = priceForLot;
            System.out.println("Початкова ціна за лот: " + priceForLot);

            while(START.getCount() != 0){
                for (Client client : clients) {
                    boolean isGood = false;
                        int possiblyPrice = (int) (Math.random() * 1000);
                        if (possiblyPrice >= maxPrice && client.price <= possiblyPrice) {
                            maxPrice = possiblyPrice;
                            client.price = possiblyPrice;
                            isGood = true;
                            client.canTakePartInNextLot = true;
                        }
                    if (isGood)
                        new Thread(client).start();
                }
                START.countDown();
            }
            Thread.sleep(1000);
            START = new CountDownLatch(3);
            int winner = 0;
            for(int j = 1; j < clients.size(); j++){
                if(clients.get(j).price > clients.get(winner).price)
                    winner = clients.get(j).id;
            }
            if(clients.get(winner).price == 0)
                System.out.println("Ніхто не переміг у аукціоні за лот " + i);
            System.out.println("Аукціон за " + i +" лот завершений. " +
                    "Переміг клієнт " + winner + " з ціною " + clients.get(winner).price);
            System.out.println("\n");
        }

    }

    public static class Client implements Runnable{
        public int price;
        private int id;
        public boolean canTakePartInNextLot = false;

        public Client(int id){
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Клієнт номер %d приєднується до аукціону з ціною %d\n", id, price);
                START.await();
                //System.out.printf("Клієнт номер %d заплатив %d\n", id, price);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void reset(){
            price = 0;
        }

        @Override
        public String toString(){
            return String.valueOf(id);
        }
    }
}

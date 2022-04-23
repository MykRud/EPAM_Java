package task_advanced.task_5.Airport;

public class MainAirport {
    public static void main(String[] args) {
        Airport someAirport = new Airport();
        for(int i = 0; i < 100; i++){
            new Passenger(i, someAirport).start();
        }
    }
}

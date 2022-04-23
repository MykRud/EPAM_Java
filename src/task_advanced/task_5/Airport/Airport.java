package task_advanced.task_5.Airport;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private static List<Terminal> terminals = new ArrayList<>();

    static {
        for(int i = 0; i < 10; i++){
            terminals.add(new Terminal(i));
        }
    }

    public Terminal joinTerminal(Passenger passenger) {
        Terminal availableTerminal = terminals.get(0);
        for(Terminal terminal : terminals){
            if(terminal.getNumberOfPeopleInQueue() < availableTerminal.getNumberOfPeopleInQueue()){
                availableTerminal = terminal;
            }
        }
        availableTerminal.incrementNumberOfPeopleInQueue();
        return availableTerminal;
    }

}

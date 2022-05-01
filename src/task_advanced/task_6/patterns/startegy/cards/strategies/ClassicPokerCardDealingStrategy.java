package task_advanced.task_6.patterns.startegy.cards.strategies;

import task_advanced.task_6.patterns.startegy.cards.Card;
import task_advanced.task_6.patterns.startegy.cards.CardDealingStrategy;
import task_advanced.task_6.patterns.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClassicPokerCardDealingStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {

        Map<String, List<Card>> map = new LinkedHashMap<>();

        // Form players' lists
        List<List<Card>> listsOfPlayersCard = new ArrayList<>();
        for(int i = 0; i < players; i++) {
            listsOfPlayersCard.add(new ArrayList<>());
        }
        for (int i = 0; i < 5; i++) {
            int currentPlayer = 0;
            while (currentPlayer < players) {
                listsOfPlayersCard.get(currentPlayer).add(deck.dealCard());
                currentPlayer++;
            }
        }
        // Put players' lists
        for (int i = 0; i < players; i++) {
            String name = "Player " + (i + 1);
            map.put(name, listsOfPlayersCard.get(i));
        }
        // Remaining
        map.put("Remaining", deck.restCards());
        return map;
    }
}

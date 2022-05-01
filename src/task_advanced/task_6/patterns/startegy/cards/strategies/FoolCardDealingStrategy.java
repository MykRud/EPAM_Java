package task_advanced.task_6.patterns.startegy.cards.strategies;

import task_advanced.task_6.patterns.startegy.cards.Card;
import task_advanced.task_6.patterns.startegy.cards.CardDealingStrategy;
import task_advanced.task_6.patterns.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoolCardDealingStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> map = new LinkedHashMap<>();

        // "{Player 1=[51, 48, 45, 42, 39, 36], " +
        //                        "Player 2=[50, 47, 44, 41, 38, 35], " +
        //                        "Player 3=[49, 46, 43, 40, 37, 34], " +
        //                        "Remaining=[32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], " +
        //                        "Trump card=[33]}"

        // Form players' lists
        List<List<Card>> listsOfPlayersCard = new ArrayList<>();
        for(int i = 0; i < players; i++) {
            listsOfPlayersCard.add(new ArrayList<>());
        }
        for (int i = 0; i < 6; i++) {
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
        // Trump card
        List<Card> listOfTrumpCards = new ArrayList<>();
        Card trumpCard = deck.dealCard();
        listOfTrumpCards.add(trumpCard);
        map.put("Trump card", listOfTrumpCards);
        // Remaining
        map.put("Remaining", deck.restCards());
        return map;
    }
}

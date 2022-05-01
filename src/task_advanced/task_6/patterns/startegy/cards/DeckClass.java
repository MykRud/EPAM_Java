package task_advanced.task_6.patterns.startegy.cards;

import java.util.ArrayList;
import java.util.List;

public class DeckClass implements Deck{
    private int size;
    private List<Card> cards = new ArrayList<>();

    public DeckClass(int size) {
        this.size = size;
        fillListOfCards();
    }

    private void fillListOfCards() {
        for(int i = 0; i < size; i++)
            cards.add(new CardClass(String.valueOf(i)));
    }

    @Override
    public Card dealCard() {
        if(size == 0)
            return null;
        Card card = cards.get(size);
        cards.remove(size);
        size--;
        return card;

    }

    @Override
    public List<Card> restCards() {
        List<Card> tempList = new ArrayList<>(cards);
        cards = new ArrayList<>();
        size = 0;
        return tempList;
    }

    @Override
    public int size() {
        return size;
    }
}

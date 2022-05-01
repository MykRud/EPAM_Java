package task_advanced.task_6.patterns.startegy.cards;

import java.util.List;

public interface Deck{
    Card dealCard();
    List<Card> restCards();
    int size();
}

package task_advanced.task_6.patterns.startegy.cards;

import java.util.List;
import java.util.Map;

public interface CardDealingStrategy {
    Map<String, List<Card>> dealStacks(Deck deck, int players);
}

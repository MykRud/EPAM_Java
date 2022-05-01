package task_advanced.task_6.patterns.startegy.cards;

import task_advanced.task_6.patterns.startegy.cards.strategies.BridgeCardDealingStrategy;
import task_advanced.task_6.patterns.startegy.cards.strategies.ClassicPokerCardDealingStrategy;
import task_advanced.task_6.patterns.startegy.cards.strategies.FoolCardDealingStrategy;
import task_advanced.task_6.patterns.startegy.cards.strategies.TexasHoldemCardDealingStrategy;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new TexasHoldemCardDealingStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPokerCardDealingStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new BridgeCardDealingStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new FoolCardDealingStrategy();
    }

}

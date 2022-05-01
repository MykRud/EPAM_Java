package task_advanced.task_6.patterns.startegy.cards;

public class CardClass implements Card{
    private String number;

    public CardClass(String number) {
        this.number = number;
    }

    @Override
    public String name() {
        return number;
    }
}

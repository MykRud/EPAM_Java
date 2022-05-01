package task_advanced.task_6.patterns.factory.plot.plots;

import task_advanced.task_6.patterns.factory.plot.Character;
import task_advanced.task_6.patterns.factory.plot.EpicCrisis;
import task_advanced.task_6.patterns.factory.plot.Plot;

public class ContemporaryDisneyPlot implements Plot {
    private Character hero;
    private EpicCrisis epicCrisis;
    private Character funnyFriend;

    public ContemporaryDisneyPlot(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
    }

    @Override
    public String toString(){
        return hero.name() + " feels a bit awkward and uncomfortable. " +
                "But personal issues fades, when a big trouble comes - " + epicCrisis.name() + ". " +
                hero.name() + " stands up against it, but with no success at first.But putting self " +
                "together and help of friends, including spectacular funny " + funnyFriend.name() +
                " restore the spirit and " + hero.name() + " overcomes the crisis and gains gratitude and respect";
    }
}

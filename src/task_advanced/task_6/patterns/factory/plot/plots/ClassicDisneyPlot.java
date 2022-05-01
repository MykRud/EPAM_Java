package task_advanced.task_6.patterns.factory.plot.plots;

import task_advanced.task_6.patterns.factory.plot.Character;
import task_advanced.task_6.patterns.factory.plot.Plot;

public class ClassicDisneyPlot implements Plot {
    private Character hero;
    private Character beloved;
    private Character villain;

    public ClassicDisneyPlot(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
    }


    @Override
    public String toString(){
        return hero.name() + " is great. " + hero.name() +" and " + beloved.name() + " love each other. " +
                villain.name() + " interferes with their happiness but loses in the end.";
    }
}

package task_advanced.task_6.patterns.factory.plot.plots;

import task_advanced.task_6.patterns.factory.plot.Character;
import task_advanced.task_6.patterns.factory.plot.EpicCrisis;
import task_advanced.task_6.patterns.factory.plot.Plot;

public class MarvelPlot implements Plot {
    private Character[] heroes;
    private EpicCrisis epicCrisis;
    private Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(epicCrisis.name()).append(" threatens the world. But brave ");
        for(int i = 0; i < heroes.length - 1; i++)
            sb.append(heroes[i].name()).append(", brave ");
        sb.append(heroes[heroes.length-1].name()).append(" on guard. So, no way that intrigues of ");
        sb.append(villain.name()).append(" overcome the willpower of inflexible heroes");
        return sb.toString();
    }
}

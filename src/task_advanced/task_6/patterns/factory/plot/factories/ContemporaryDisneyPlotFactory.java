package task_advanced.task_6.patterns.factory.plot.factories;

import task_advanced.task_6.patterns.factory.plot.Character;
import task_advanced.task_6.patterns.factory.plot.EpicCrisis;
import task_advanced.task_6.patterns.factory.plot.Plot;
import task_advanced.task_6.patterns.factory.plot.PlotFactory;
import task_advanced.task_6.patterns.factory.plot.plots.ClassicDisneyPlot;
import task_advanced.task_6.patterns.factory.plot.plots.ContemporaryDisneyPlot;

public class ContemporaryDisneyPlotFactory implements PlotFactory {
    private ContemporaryDisneyPlot plot;

    public ContemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        plot = new ContemporaryDisneyPlot(hero, epicCrisis, funnyFriend);
    }

    @Override
    public Plot plot() {
        return plot;
    }
}

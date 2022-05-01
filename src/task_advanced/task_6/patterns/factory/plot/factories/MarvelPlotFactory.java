package task_advanced.task_6.patterns.factory.plot.factories;

import task_advanced.task_6.patterns.factory.plot.Character;
import task_advanced.task_6.patterns.factory.plot.EpicCrisis;
import task_advanced.task_6.patterns.factory.plot.Plot;
import task_advanced.task_6.patterns.factory.plot.PlotFactory;
import task_advanced.task_6.patterns.factory.plot.plots.MarvelPlot;

public class MarvelPlotFactory implements PlotFactory {
    private MarvelPlot plot;

    public MarvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        plot = new MarvelPlot(heroes, epicCrisis, villain);
    }

    @Override
    public Plot plot() {
        return plot;
    }
}

package task_advanced.task_6.patterns.factory.plot.factories;

import task_advanced.task_6.patterns.factory.plot.Character;
import task_advanced.task_6.patterns.factory.plot.Plot;
import task_advanced.task_6.patterns.factory.plot.PlotFactory;
import task_advanced.task_6.patterns.factory.plot.plots.ClassicDisneyPlot;

public class ClassicDisneyPlotFactory implements PlotFactory {
    private ClassicDisneyPlot plot;

    public ClassicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        plot = new ClassicDisneyPlot(hero, beloved, villain);
    }

    @Override
    public Plot plot() {
        return plot;
    }
}

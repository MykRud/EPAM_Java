package task_advanced.task_6.patterns.factory.plot;

import task_advanced.task_6.patterns.factory.plot.factories.ClassicDisneyPlotFactory;
import task_advanced.task_6.patterns.factory.plot.factories.ContemporaryDisneyPlotFactory;
import task_advanced.task_6.patterns.factory.plot.factories.MarvelPlotFactory;

public class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new ClassicDisneyPlotFactory(hero, beloved, villain);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new ContemporaryDisneyPlotFactory(hero, epicCrisis, funnyFriend);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new MarvelPlotFactory(heroes, epicCrisis, villain);
    }
}

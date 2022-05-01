package task_advanced.task_6.patterns;

import task_advanced.task_6.patterns.factory.plot.Plot;
import task_advanced.task_6.patterns.factory.plot.PlotFactories;
import task_advanced.task_6.patterns.factory.plot.PlotFactory;

import java.io.IOException;
import java.util.logging.*;

public class MainForLogging {
    public static Logger logger = Logger.getLogger("MainForLogging");
    static{
        try {
            // Simple Formatter
            Handler fileHandlerForSimple = new FileHandler("src/task_advanced/task_6/patterns/mainInSimple.log");
            fileHandlerForSimple.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandlerForSimple);

            // XML Formatter
            Handler fileHandlerForXML = new FileHandler("src/task_advanced/task_6/patterns/mainInXML.xml");
            fileHandlerForXML.setFormatter(new XMLFormatter());
            logger.addHandler(fileHandlerForXML);

            // Level
            logger.setLevel(Level.WARNING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        logger.log(Level.WARNING, "Створення PlotFactories");
        PlotFactories plotFactories = new PlotFactories();
        logger.log(Level.WARNING, "Створення конкретної фабрики (Classic Disney)");
        PlotFactory plotFactory = plotFactories.classicDisneyPlotFactory(() -> "Sara", () -> "Adam", () -> "Joe");
        logger.log(Level.WARNING, "Отримуємо сюжет");
        Plot plot = plotFactory.plot();
        logger.log(Level.WARNING, "Вивід сюжету на консоль");
        System.out.println(plot);
        logger.log(Level.WARNING, "Кінець роботи");
    }
}

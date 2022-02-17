package task_advanced.task_2.database;

import task_advanced.task_2.businessLogic.collections.Container;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLFormatter implements Formatter {

    public static final String DEFAULT_PATH_XML = "src\\task_advanced\\task_2\\newCities.xml";

    @Override
    public void read(Container container, String file) {

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)))) {
            Object[] array = (Object[]) decoder.readObject();
            container.setList(array);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File has not been found");
        }

    }

    @Override
    public void save(Container container, String file) {
        Object[] array = container.asList();

        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.writeObject(array);
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("ERROR: While Creating or Opening the File dvd.xml");
        }
    }
}

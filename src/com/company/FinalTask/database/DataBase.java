package com.company.FinalTask.database;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.entities.Country;
import com.company.FinalTask.business.services.CityService;
import com.company.FinalTask.business.services.CountryService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static final String PACKAGE = CountryService.class.getPackage().getName();
    private JAXBContext jc;

    public DataBase() throws JAXBException {
        jc = JAXBContext.newInstance(PACKAGE);
    }

    public void save(CountryService countryService) {
        try (OutputStream os = new FileOutputStream("src\\com\\company\\FinalTask\\countries.xml")) {
            jc = JAXBContext.newInstance(CountryService.class);
            Marshaller m = jc.createMarshaller();
            m.marshal(countryService, os);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void save(CityService cityService) {

        try (OutputStream os = new FileOutputStream("src\\com\\company\\FinalTask\\cities.xml")) {
            jc = JAXBContext.newInstance(CityService.class);
            Marshaller m = jc.createMarshaller();
            m.marshal(cityService, os);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Country> readCountries() throws SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("src\\com\\company\\FinalTask\\countries.xsd"));

        CountryService cs = null;
        File xmlFile = new File("src\\com\\company\\FinalTask\\countries.xml");

        try (InputStream in = new FileInputStream(xmlFile)) {

            jc = JAXBContext.newInstance(CountryService.class);
            Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            if (in.read() == -1)
                return new ArrayList<>();
            cs = (CountryService) jaxbUnmarshaller.unmarshal(xmlFile);

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return cs != null ? cs.getListOfCountries() : new ArrayList<>();
    }

    public List<City> readCities() throws SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("src\\com\\company\\FinalTask\\cities.xsd"));

        CityService cs = null;
        File xmlFile = new File("src\\com\\company\\FinalTask\\cities.xml");
        try (InputStream in = new FileInputStream(xmlFile)) {
            jc = JAXBContext.newInstance(CityService.class);
            Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            if (in.read() == -1)
                return new ArrayList<>();
            cs = (CityService) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return cs != null ? cs.getListOfCities() : new ArrayList<>();
    }

}

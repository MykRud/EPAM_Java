package com.company.FinalTask.database;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.entities.Country;
import com.company.FinalTask.business.services.CityService;
import com.company.FinalTask.business.services.CountryService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private JAXBContext jc;



    private File fileOfCityXML;
    private File fileOfCountryXSD;
    private File fileOfCityXSD;

    {
        String separator = File.separator;
    }


    public DataBase() throws JAXBException {
        jc = JAXBContext.newInstance(ObjectFactory.class);
    }

    public void save(CountryService countryService, String file) {
        try (OutputStream os = new FileOutputStream(DataPresentation.getPathXML(file))) {
            jc = JAXBContext.newInstance(CountryService.class);
            Marshaller m = jc.createMarshaller();
            m.marshal(countryService, os);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void save(CityService cityService, String file) {

        try (OutputStream os = new FileOutputStream(DataPresentation.getPathXML(file))) {
            jc = JAXBContext.newInstance(CityService.class);
            Marshaller m = jc.createMarshaller();
            m.marshal(cityService, os);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Country> readCountries(String fileXML, String fileXSD) throws SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(DataPresentation.getPathXSD(fileXSD)));

        CountryService cs = null;
        File xmlFile = new File(DataPresentation.getPathXML(fileXML));

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
        return cs != null ? cs.getList() : new ArrayList<>();
    }

    public List<City> readCities(String fileXML, String fileXSD) throws SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(DataPresentation.getPathXSD(fileXSD)));

        CityService cs = null;
        File xmlFile = new File(DataPresentation.getPathXML(fileXML));
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
        return cs != null ? cs.getList() : new ArrayList<>();
    }

}

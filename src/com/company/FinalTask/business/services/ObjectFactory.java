package com.company.FinalTask.business.services;


import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.XMLConstants;

import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private static final QName QNAME = new QName(
            XMLConstants.NULL_NS_URI, "countries");

    @XmlElementDecl(name = "CountryService")
    public JAXBElement<CountryService> createData(CountryService value) {
        return new JAXBElement<>(
                QNAME, CountryService.class, null, value
        );
    }

    @XmlElementDecl(name = "CityService")
    public JAXBElement<CityService> createData(CityService value) {
        return new JAXBElement<>(
                QNAME, CityService.class, null, value
        );
    }

}

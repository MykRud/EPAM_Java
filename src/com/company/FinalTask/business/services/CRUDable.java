package com.company.FinalTask.business.services;

import org.xml.sax.SAXException;

public interface CRUDable {

    public void read() throws SAXException;

    public void save();

}

package com.company;


import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "dataObj")
public class XMLHelper {
    @XmlElement
    private int i;
}

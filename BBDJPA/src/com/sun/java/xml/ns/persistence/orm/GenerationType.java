//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.02.25 at 11:22:16 AM PST 
//


package com.sun.java.xml.ns.persistence.orm;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for generation-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="generation-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="TABLE"/>
 *     &lt;enumeration value="SEQUENCE"/>
 *     &lt;enumeration value="IDENTITY"/>
 *     &lt;enumeration value="AUTO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum GenerationType {

    AUTO,
    IDENTITY,
    SEQUENCE,
    TABLE;

    public String value() {
        return name();
    }

    public static GenerationType fromValue(final String v) {
        return valueOf(v);
    }

}
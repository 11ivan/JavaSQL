//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.01.10 a las 10:59:31 AM CET 
//


package org.example.newxmlschema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para densidadesType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="densidadesType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="g/cm3"/>
 *     &lt;enumeration value="Kg/L"/>
 *     &lt;enumeration value="Kg/m3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "densidadesType")
@XmlEnum
public enum DensidadesType {

    @XmlEnumValue("g/cm3")
    G_CM_3("g/cm3"),
    @XmlEnumValue("Kg/L")
    KG_L("Kg/L"),
    @XmlEnumValue("Kg/m3")
    KG_M_3("Kg/m3");
    private final String value;

    DensidadesType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DensidadesType fromValue(String v) {
        for (DensidadesType c: DensidadesType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

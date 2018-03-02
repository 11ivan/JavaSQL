//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.02 a las 01:42:51 PM CET 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Madre" minOccurs="0"/>
 *         &lt;element ref="{}Padre" minOccurs="0"/>
 *         &lt;element ref="{}Nombre" minOccurs="0"/>
 *         &lt;element ref="{}Apellidos" minOccurs="0"/>
 *         &lt;element ref="{}FechaNacimiento" minOccurs="0"/>
 *         &lt;element ref="{}Sexo" minOccurs="0"/>
 *         &lt;element ref="{}PastaFavorita" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "Ciudadane")
public class Ciudadane {

    @XmlElementRefs({
        @XmlElementRef(name = "Apellidos", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Nombre", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Padre", type = Padre.class, required = false),
        @XmlElementRef(name = "PastaFavorita", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "FechaNacimiento", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Sexo", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Madre", type = Madre.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "ID")
    protected Byte id;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Padre }
     * {@link String }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Madre }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getID() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setID(Byte value) {
        this.id = value;
    }

}

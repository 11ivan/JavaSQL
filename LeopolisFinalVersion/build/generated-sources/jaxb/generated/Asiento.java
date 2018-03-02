//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.02 a las 01:42:51 PM CET 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{}Ciudadane" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Matrimonio" minOccurs="0"/>
 *         &lt;element ref="{}Fecha" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ciudadane",
    "matrimonio",
    "fecha"
})
@XmlRootElement(name = "Asiento")
public class Asiento {

    @XmlElement(name = "Ciudadane")
    protected List<Ciudadane> ciudadane;
    @XmlElement(name = "Matrimonio")
    protected Byte matrimonio;
    @XmlElement(name = "Fecha")
    protected String fecha;
    @XmlAttribute(name = "ID")
    protected Short id;
    @XmlAttribute(name = "Tipo")
    protected String tipo;

    /**
     * Gets the value of the ciudadane property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ciudadane property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCiudadane().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ciudadane }
     * 
     * 
     */
    public List<Ciudadane> getCiudadane() {
        if (ciudadane == null) {
            ciudadane = new ArrayList<Ciudadane>();
        }
        return this.ciudadane;
    }

    /**
     * Obtiene el valor de la propiedad matrimonio.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getMatrimonio() {
        return matrimonio;
    }

    /**
     * Define el valor de la propiedad matrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setMatrimonio(Byte value) {
        this.matrimonio = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getID() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setID(Short value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}

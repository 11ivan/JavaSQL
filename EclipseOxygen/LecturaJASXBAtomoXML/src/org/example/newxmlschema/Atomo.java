//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.01.10 a las 10:59:31 AM CET 
//


package org.example.newxmlschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para atomo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="atomo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simbolo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero_atomico" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="peso_atomico" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="punto_ebullicion" type="{http://www.example.org/NewXMLSchema}ebullicionType"/>
 *         &lt;element name="densidad" type="{http://www.example.org/NewXMLSchema}densidadType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="estado" type="{http://www.w3.org/2001/XMLSchema}string" default="liquido" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "atomo", propOrder = {
    "nombre",
    "simbolo",
    "numeroAtomico",
    "pesoAtomico",
    "puntoEbullicion",
    "densidad"
})
public class Atomo {

    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String simbolo;
    @XmlElement(name = "numero_atomico")
    protected double numeroAtomico;
    @XmlElement(name = "peso_atomico")
    protected double pesoAtomico;
    @XmlElement(name = "punto_ebullicion", required = true)
    protected EbullicionType puntoEbullicion;
    @XmlElement(required = true)
    protected DensidadType densidad;
    @XmlAttribute(name = "estado")
    protected String estado;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad simbolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Define el valor de la propiedad simbolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimbolo(String value) {
        this.simbolo = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroAtomico.
     * 
     */
    public double getNumeroAtomico() {
        return numeroAtomico;
    }

    /**
     * Define el valor de la propiedad numeroAtomico.
     * 
     */
    public void setNumeroAtomico(double value) {
        this.numeroAtomico = value;
    }

    /**
     * Obtiene el valor de la propiedad pesoAtomico.
     * 
     */
    public double getPesoAtomico() {
        return pesoAtomico;
    }

    /**
     * Define el valor de la propiedad pesoAtomico.
     * 
     */
    public void setPesoAtomico(double value) {
        this.pesoAtomico = value;
    }

    /**
     * Obtiene el valor de la propiedad puntoEbullicion.
     * 
     * @return
     *     possible object is
     *     {@link EbullicionType }
     *     
     */
    public EbullicionType getPuntoEbullicion() {
        return puntoEbullicion;
    }

    /**
     * Define el valor de la propiedad puntoEbullicion.
     * 
     * @param value
     *     allowed object is
     *     {@link EbullicionType }
     *     
     */
    public void setPuntoEbullicion(EbullicionType value) {
        this.puntoEbullicion = value;
    }

    /**
     * Obtiene el valor de la propiedad densidad.
     * 
     * @return
     *     possible object is
     *     {@link DensidadType }
     *     
     */
    public DensidadType getDensidad() {
        return densidad;
    }

    /**
     * Define el valor de la propiedad densidad.
     * 
     * @param value
     *     allowed object is
     *     {@link DensidadType }
     *     
     */
    public void setDensidad(DensidadType value) {
        this.densidad = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        if (estado == null) {
            return "liquido";
        } else {
            return estado;
        }
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

}

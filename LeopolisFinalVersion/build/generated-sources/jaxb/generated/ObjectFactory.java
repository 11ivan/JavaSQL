//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.02 a las 01:42:51 PM CET 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Nombre_QNAME = new QName("", "Nombre");
    private final static QName _FechaNacimiento_QNAME = new QName("", "FechaNacimiento");
    private final static QName _Apellidos_QNAME = new QName("", "Apellidos");
    private final static QName _Fecha_QNAME = new QName("", "Fecha");
    private final static QName _PastaFavorita_QNAME = new QName("", "PastaFavorita");
    private final static QName _Sexo_QNAME = new QName("", "Sexo");
    private final static QName _Matrimonio_QNAME = new QName("", "Matrimonio");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Ciudadane }
     * 
     */
    public Ciudadane createCiudadane() {
        return new Ciudadane();
    }

    /**
     * Create an instance of {@link Madre }
     * 
     */
    public Madre createMadre() {
        return new Madre();
    }

    /**
     * Create an instance of {@link Padre }
     * 
     */
    public Padre createPadre() {
        return new Padre();
    }

    /**
     * Create an instance of {@link Errores }
     * 
     */
    public Errores createErrores() {
        return new Errores();
    }

    /**
     * Create an instance of {@link Incidencia }
     * 
     */
    public Incidencia createIncidencia() {
        return new Incidencia();
    }

    /**
     * Create an instance of {@link Asiento }
     * 
     */
    public Asiento createAsiento() {
        return new Asiento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Nombre")
    public JAXBElement<String> createNombre(String value) {
        return new JAXBElement<String>(_Nombre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "FechaNacimiento")
    public JAXBElement<String> createFechaNacimiento(String value) {
        return new JAXBElement<String>(_FechaNacimiento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Apellidos")
    public JAXBElement<String> createApellidos(String value) {
        return new JAXBElement<String>(_Apellidos_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Fecha")
    public JAXBElement<String> createFecha(String value) {
        return new JAXBElement<String>(_Fecha_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PastaFavorita")
    public JAXBElement<String> createPastaFavorita(String value) {
        return new JAXBElement<String>(_PastaFavorita_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Sexo")
    public JAXBElement<String> createSexo(String value) {
        return new JAXBElement<String>(_Sexo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Matrimonio")
    public JAXBElement<Byte> createMatrimonio(Byte value) {
        return new JAXBElement<Byte>(_Matrimonio_QNAME, Byte.class, null, value);
    }

}

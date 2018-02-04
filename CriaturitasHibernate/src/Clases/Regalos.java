/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import exceptions.ExcepcionRegalos;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author icastillo
 */
@Entity
@Table(name = "Regalos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regalos.findAll", query = "SELECT r FROM Regalos r")
    , @NamedQuery(name = "Regalos.findById", query = "SELECT r FROM Regalos r WHERE r.id = :id")
    , @NamedQuery(name = "Regalos.findByDenominacion", query = "SELECT r FROM Regalos r WHERE r.denominacion = :denominacion")
    , @NamedQuery(name = "Regalos.findByAncho", query = "SELECT r FROM Regalos r WHERE r.ancho = :ancho")
    , @NamedQuery(name = "Regalos.findByLargo", query = "SELECT r FROM Regalos r WHERE r.largo = :largo")
    , @NamedQuery(name = "Regalos.findByAlto", query = "SELECT r FROM Regalos r WHERE r.alto = :alto")
    , @NamedQuery(name = "Regalos.findByTipo", query = "SELECT r FROM Regalos r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "Regalos.findByEdadMinima", query = "SELECT r FROM Regalos r WHERE r.edadMinima = :edadMinima")
    , @NamedQuery(name = "Regalos.findByPrecio", query = "SELECT r FROM Regalos r WHERE r.precio = :precio")
    , @NamedQuery(name = "Regalos.findBySuperficie", query = "SELECT r FROM Regalos r WHERE r.superficie = :superficie")})
public class Regalos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;
    @Basic(optional = false)
    @Column(name = "Denominacion")
    private String denominacion;
    @Column(name = "Ancho")
    private Short ancho;
    @Column(name = "Largo")
    private Short largo;
    @Column(name = "Alto")
    private Short alto;
    @Column(name = "Tipo")
    private Character tipo;
    @Basic(optional = false)
    @Column(name = "EdadMinima")
    private short edadMinima;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Precio")
    private BigDecimal precio;
    @Column(name = "Superficie")
    private Short superficie;
    @JoinColumn(name = "GoesTo", referencedColumnName = "ID")
    @ManyToOne
    private Criaturitas goesTo;

    public Regalos() {
    }

    public Regalos(Short id) {
        this.id = id;
    }

    public Regalos(Short id, String denominacion, short edadMinima, BigDecimal precio) {
        this.id = id;
        this.denominacion = denominacion;
        this.edadMinima = edadMinima;
        this.precio = precio;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) throws ExcepcionRegalos{
        
        if(denominacion.replaceAll(" ", "").length()==0 ){
            throw new ExcepcionRegalos("La denominacion no puede estar vacía");
        }else if(denominacion.length()<=50){
            throw new ExcepcionRegalos("La longitud debe ser menor o igual a 50 caracteres");
        }else{
            this.denominacion = denominacion;
        }
    }

    public Short getAncho() {
        return ancho;
    }

    public void setAncho(Short ancho) {
        this.ancho = ancho;
    }

    public Short getLargo() {
        return largo;
    }

    public void setLargo(Short largo) {
        this.largo = largo;
    }

    public Short getAlto() {
        return alto;
    }

    public void setAlto(Short alto) {
        this.alto = alto;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public short getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(short edadMinima) throws ExcepcionRegalos{
        if(edadMinima<0){
            throw new ExcepcionRegalos("La denominacion no puede estar vacía");
        }else if(denominacion.length()<=50){
            throw new ExcepcionRegalos("La longitud debe ser menor o igual a 50 caracteres");
        }else{
            this.edadMinima = edadMinima;
        }       
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Short getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Short superficie) {
        this.superficie = superficie;
    }

    public Criaturitas getGoesTo() {
        return goesTo;
    }

    public void setGoesTo(Criaturitas goesTo) {
        this.goesTo = goesTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regalos)) {
            return false;
        }
        Regalos other = (Regalos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Regalos[ id=" + id + " ]";
    }
    
}

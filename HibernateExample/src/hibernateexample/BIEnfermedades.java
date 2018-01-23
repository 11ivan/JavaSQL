/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author icastillo
 */
@Entity
@Table(name = "BI_Enfermedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BIEnfermedades.findAll", query = "SELECT b FROM BIEnfermedades b")
    , @NamedQuery(name = "BIEnfermedades.findById", query = "SELECT b FROM BIEnfermedades b WHERE b.id = :id")
    , @NamedQuery(name = "BIEnfermedades.findByNombre", query = "SELECT b FROM BIEnfermedades b WHERE b.nombre = :nombre")})
public class BIEnfermedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bIEnfermedades")
    private Collection<BIMascotasEnfermedades> bIMascotasEnfermedadesCollection;

    public BIEnfermedades() {
    }

    public BIEnfermedades(Short id) {
        this.id = id;
    }

    public BIEnfermedades(Short id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<BIMascotasEnfermedades> getBIMascotasEnfermedadesCollection() {
        return bIMascotasEnfermedadesCollection;
    }

    public void setBIMascotasEnfermedadesCollection(Collection<BIMascotasEnfermedades> bIMascotasEnfermedadesCollection) {
        this.bIMascotasEnfermedadesCollection = bIMascotasEnfermedadesCollection;
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
        if (!(object instanceof BIEnfermedades)) {
            return false;
        }
        BIEnfermedades other = (BIEnfermedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernateexample.BIEnfermedades[ id=" + id + " ]";
    }
    
}

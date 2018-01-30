/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "Criaturitas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criaturitas.findAll", query = "SELECT c FROM Criaturitas c")
    , @NamedQuery(name = "Criaturitas.findById", query = "SELECT c FROM Criaturitas c WHERE c.id = :id")
    , @NamedQuery(name = "Criaturitas.findByNombre", query = "SELECT c FROM Criaturitas c WHERE c.nombre = :nombre")})
public class Criaturitas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "goesTo")
    private Collection<Regalos> regalosCollection;

    public Criaturitas() {
    }

    public Criaturitas(Short id) {
        this.id = id;
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
    public Collection<Regalos> getRegalosCollection() {
        return regalosCollection;
    }

    public void setRegalosCollection(Collection<Regalos> regalosCollection) {
        this.regalosCollection = regalosCollection;
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
        if (!(object instanceof Criaturitas)) {
            return false;
        }
        Criaturitas other = (Criaturitas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Criaturitas[ id=" + id + " ]";
    }
    
}

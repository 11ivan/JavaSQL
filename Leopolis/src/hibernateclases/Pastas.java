/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateclases;

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
@Table(name = "Pastas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pastas.findAll", query = "SELECT p FROM Pastas p")
    , @NamedQuery(name = "Pastas.findByNombrePasta", query = "SELECT p FROM Pastas p WHERE p.nombrePasta = :nombrePasta")})
public class Pastas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NombrePasta")
    private String nombrePasta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pastafavorita")
    private Collection<Ciudadanes> ciudadanesCollection;

    public Pastas() {
    }

    public Pastas(String nombrePasta) {
        this.nombrePasta = nombrePasta;
    }

    public String getNombrePasta() {
        return nombrePasta;
    }

    public void setNombrePasta(String nombrePasta) {
        this.nombrePasta = nombrePasta;
    }

    @XmlTransient
    public Collection<Ciudadanes> getCiudadanesCollection() {
        return ciudadanesCollection;
    }

    public void setCiudadanesCollection(Collection<Ciudadanes> ciudadanesCollection) {
        this.ciudadanesCollection = ciudadanesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombrePasta != null ? nombrePasta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pastas)) {
            return false;
        }
        Pastas other = (Pastas) object;
        if ((this.nombrePasta == null && other.nombrePasta != null) || (this.nombrePasta != null && !this.nombrePasta.equals(other.nombrePasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernate.Pastas[ nombrePasta=" + nombrePasta + " ]";
    }
    
}

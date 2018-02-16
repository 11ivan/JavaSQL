/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateclases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author icastillo
 */
@Entity
@Table(name = "Matrimonios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matrimonios.findAll", query = "SELECT m FROM Matrimonios m")
    , @NamedQuery(name = "Matrimonios.findById", query = "SELECT m FROM Matrimonios m WHERE m.id = :id")
    , @NamedQuery(name = "Matrimonios.findByFechamatrimonio", query = "SELECT m FROM Matrimonios m WHERE m.fechamatrimonio = :fechamatrimonio")
    , @NamedQuery(name = "Matrimonios.findByFechafin", query = "SELECT m FROM Matrimonios m WHERE m.fechafin = :fechafin")})
public class Matrimonios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Fecha_matrimonio")
    @Temporal(TemporalType.DATE)
    private Date fechamatrimonio;
    @Column(name = "Fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @JoinColumn(name = "IDConyuge1", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ciudadanes iDConyuge1;
    @JoinColumn(name = "IDConyuge2", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ciudadanes iDConyuge2;

    public Matrimonios() {
    }

    public Matrimonios(Integer id) {
        this.id = id;
    }

    public Matrimonios(Integer id, Date fechamatrimonio) {
        this.id = id;
        this.fechamatrimonio = fechamatrimonio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechamatrimonio() {
        return fechamatrimonio;
    }

    public void setFechamatrimonio(Date fechamatrimonio) {
        this.fechamatrimonio = fechamatrimonio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Ciudadanes getIDConyuge1() {
        return iDConyuge1;
    }

    public void setIDConyuge1(Ciudadanes iDConyuge1) {
        this.iDConyuge1 = iDConyuge1;
    }

    public Ciudadanes getIDConyuge2() {
        return iDConyuge2;
    }

    public void setIDConyuge2(Ciudadanes iDConyuge2) {
        this.iDConyuge2 = iDConyuge2;
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
        if (!(object instanceof Matrimonios)) {
            return false;
        }
        Matrimonios other = (Matrimonios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernate.Matrimonios[ id=" + id + " ]";
    }
    
}

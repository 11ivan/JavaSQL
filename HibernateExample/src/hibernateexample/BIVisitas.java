/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

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
@Table(name = "BI_Visitas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BIVisitas.findAll", query = "SELECT b FROM BIVisitas b")
    , @NamedQuery(name = "BIVisitas.findByIDVisita", query = "SELECT b FROM BIVisitas b WHERE b.iDVisita = :iDVisita")
    , @NamedQuery(name = "BIVisitas.findByFecha", query = "SELECT b FROM BIVisitas b WHERE b.fecha = :fecha")
    , @NamedQuery(name = "BIVisitas.findByTemperatura", query = "SELECT b FROM BIVisitas b WHERE b.temperatura = :temperatura")
    , @NamedQuery(name = "BIVisitas.findByPeso", query = "SELECT b FROM BIVisitas b WHERE b.peso = :peso")})
public class BIVisitas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDVisita")
    private Integer iDVisita;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Temperatura")
    private short temperatura;
    @Basic(optional = false)
    @Column(name = "Peso")
    private int peso;
    @JoinColumn(name = "Mascota", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private BIMascotas mascota;

    public BIVisitas() {
    }

    public BIVisitas(Integer iDVisita) {
        this.iDVisita = iDVisita;
    }

    public BIVisitas(Integer iDVisita, Date fecha, short temperatura, int peso) {
        this.iDVisita = iDVisita;
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.peso = peso;
    }

    public Integer getIDVisita() {
        return iDVisita;
    }

    public void setIDVisita(Integer iDVisita) {
        this.iDVisita = iDVisita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(short temperatura) {
        this.temperatura = temperatura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public BIMascotas getMascota() {
        return mascota;
    }

    public void setMascota(BIMascotas mascota) {
        this.mascota = mascota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVisita != null ? iDVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BIVisitas)) {
            return false;
        }
        BIVisitas other = (BIVisitas) object;
        if ((this.iDVisita == null && other.iDVisita != null) || (this.iDVisita != null && !this.iDVisita.equals(other.iDVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernateexample.BIVisitas[ iDVisita=" + iDVisita + " ]";
    }
    
}

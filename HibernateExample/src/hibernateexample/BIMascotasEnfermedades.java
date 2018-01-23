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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "BI_Mascotas_Enfermedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BIMascotasEnfermedades.findAll", query = "SELECT b FROM BIMascotasEnfermedades b")
    , @NamedQuery(name = "BIMascotasEnfermedades.findByIDEnfermedad", query = "SELECT b FROM BIMascotasEnfermedades b WHERE b.bIMascotasEnfermedadesPK.iDEnfermedad = :iDEnfermedad")
    , @NamedQuery(name = "BIMascotasEnfermedades.findByMascota", query = "SELECT b FROM BIMascotasEnfermedades b WHERE b.bIMascotasEnfermedadesPK.mascota = :mascota")
    , @NamedQuery(name = "BIMascotasEnfermedades.findByFechaInicio", query = "SELECT b FROM BIMascotasEnfermedades b WHERE b.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "BIMascotasEnfermedades.findByFechaCura", query = "SELECT b FROM BIMascotasEnfermedades b WHERE b.fechaCura = :fechaCura")})
public class BIMascotasEnfermedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BIMascotasEnfermedadesPK bIMascotasEnfermedadesPK;
    @Basic(optional = false)
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FechaCura")
    @Temporal(TemporalType.DATE)
    private Date fechaCura;
    @JoinColumn(name = "IDEnfermedad", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BIEnfermedades bIEnfermedades;
    @JoinColumn(name = "Mascota", referencedColumnName = "Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BIMascotas bIMascotas;

    public BIMascotasEnfermedades() {
    }

    public BIMascotasEnfermedades(BIMascotasEnfermedadesPK bIMascotasEnfermedadesPK) {
        this.bIMascotasEnfermedadesPK = bIMascotasEnfermedadesPK;
    }

    public BIMascotasEnfermedades(BIMascotasEnfermedadesPK bIMascotasEnfermedadesPK, Date fechaInicio) {
        this.bIMascotasEnfermedadesPK = bIMascotasEnfermedadesPK;
        this.fechaInicio = fechaInicio;
    }

    public BIMascotasEnfermedades(short iDEnfermedad, String mascota) {
        this.bIMascotasEnfermedadesPK = new BIMascotasEnfermedadesPK(iDEnfermedad, mascota);
    }

    public BIMascotasEnfermedadesPK getBIMascotasEnfermedadesPK() {
        return bIMascotasEnfermedadesPK;
    }

    public void setBIMascotasEnfermedadesPK(BIMascotasEnfermedadesPK bIMascotasEnfermedadesPK) {
        this.bIMascotasEnfermedadesPK = bIMascotasEnfermedadesPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCura() {
        return fechaCura;
    }

    public void setFechaCura(Date fechaCura) {
        this.fechaCura = fechaCura;
    }

    public BIEnfermedades getBIEnfermedades() {
        return bIEnfermedades;
    }

    public void setBIEnfermedades(BIEnfermedades bIEnfermedades) {
        this.bIEnfermedades = bIEnfermedades;
    }

    public BIMascotas getBIMascotas() {
        return bIMascotas;
    }

    public void setBIMascotas(BIMascotas bIMascotas) {
        this.bIMascotas = bIMascotas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bIMascotasEnfermedadesPK != null ? bIMascotasEnfermedadesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BIMascotasEnfermedades)) {
            return false;
        }
        BIMascotasEnfermedades other = (BIMascotasEnfermedades) object;
        if ((this.bIMascotasEnfermedadesPK == null && other.bIMascotasEnfermedadesPK != null) || (this.bIMascotasEnfermedadesPK != null && !this.bIMascotasEnfermedadesPK.equals(other.bIMascotasEnfermedadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernateexample.BIMascotasEnfermedades[ bIMascotasEnfermedadesPK=" + bIMascotasEnfermedadesPK + " ]";
    }
    
}

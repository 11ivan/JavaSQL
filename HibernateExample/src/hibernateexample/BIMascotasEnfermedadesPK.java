/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author icastillo
 */
@Embeddable
public class BIMascotasEnfermedadesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDEnfermedad")
    private short iDEnfermedad;
    @Basic(optional = false)
    @Column(name = "Mascota")
    private String mascota;

    public BIMascotasEnfermedadesPK() {
    }

    public BIMascotasEnfermedadesPK(short iDEnfermedad, String mascota) {
        this.iDEnfermedad = iDEnfermedad;
        this.mascota = mascota;
    }

    public short getIDEnfermedad() {
        return iDEnfermedad;
    }

    public void setIDEnfermedad(short iDEnfermedad) {
        this.iDEnfermedad = iDEnfermedad;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDEnfermedad;
        hash += (mascota != null ? mascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BIMascotasEnfermedadesPK)) {
            return false;
        }
        BIMascotasEnfermedadesPK other = (BIMascotasEnfermedadesPK) object;
        if (this.iDEnfermedad != other.iDEnfermedad) {
            return false;
        }
        if ((this.mascota == null && other.mascota != null) || (this.mascota != null && !this.mascota.equals(other.mascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernateexample.BIMascotasEnfermedadesPK[ iDEnfermedad=" + iDEnfermedad + ", mascota=" + mascota + " ]";
    }
    
}

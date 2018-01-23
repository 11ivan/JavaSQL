/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author icastillo
 */
@Entity
@Table(name = "BI_Mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BIMascotas.findAll", query = "SELECT b FROM BIMascotas b")
    , @NamedQuery(name = "BIMascotas.findByCodigo", query = "SELECT b FROM BIMascotas b WHERE b.codigo = :codigo")
    , @NamedQuery(name = "BIMascotas.findByRaza", query = "SELECT b FROM BIMascotas b WHERE b.raza = :raza")
    , @NamedQuery(name = "BIMascotas.findByEspecie", query = "SELECT b FROM BIMascotas b WHERE b.especie = :especie")
    , @NamedQuery(name = "BIMascotas.findByFechaNacimiento", query = "SELECT b FROM BIMascotas b WHERE b.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "BIMascotas.findByFechaFallecimiento", query = "SELECT b FROM BIMascotas b WHERE b.fechaFallecimiento = :fechaFallecimiento")
    , @NamedQuery(name = "BIMascotas.findByAlias", query = "SELECT b FROM BIMascotas b WHERE b.alias = :alias")})
public class BIMascotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "Raza")
    private String raza;
    @Basic(optional = false)
    @Column(name = "Especie")
    private String especie;
    @Basic(optional = false)
    @Column(name = "FechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "FechaFallecimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaFallecimiento;
    @Basic(optional = false)
    @Column(name = "Alias")
    private String alias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private Collection<BIVisitas> bIVisitasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bIMascotas")
    private Collection<BIMascotasEnfermedades> bIMascotasEnfermedadesCollection;
    @JoinColumn(name = "CodigoPropietario", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private BIClientes codigoPropietario;

    public BIMascotas() {
    }

    public BIMascotas(String codigo) {
        this.codigo = codigo;
    }

    public BIMascotas(String codigo, String raza, String especie, Date fechaNacimiento, String alias) {
        this.codigo = codigo;
        this.raza = raza;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.alias = alias;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @XmlTransient
    public Collection<BIVisitas> getBIVisitasCollection() {
        return bIVisitasCollection;
    }

    public void setBIVisitasCollection(Collection<BIVisitas> bIVisitasCollection) {
        this.bIVisitasCollection = bIVisitasCollection;
    }

    @XmlTransient
    public Collection<BIMascotasEnfermedades> getBIMascotasEnfermedadesCollection() {
        return bIMascotasEnfermedadesCollection;
    }

    public void setBIMascotasEnfermedadesCollection(Collection<BIMascotasEnfermedades> bIMascotasEnfermedadesCollection) {
        this.bIMascotasEnfermedadesCollection = bIMascotasEnfermedadesCollection;
    }

    public BIClientes getCodigoPropietario() {
        return codigoPropietario;
    }

    public void setCodigoPropietario(BIClientes codigoPropietario) {
        this.codigoPropietario = codigoPropietario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BIMascotas)) {
            return false;
        }
        BIMascotas other = (BIMascotas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernateexample.BIMascotas[ codigo=" + codigo + " ]";
    }
    
}

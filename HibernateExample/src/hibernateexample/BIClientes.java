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
@Table(name = "BI_Clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BIClientes.findAll", query = "SELECT b FROM BIClientes b")
    , @NamedQuery(name = "BIClientes.findByCodigo", query = "SELECT b FROM BIClientes b WHERE b.codigo = :codigo")
    , @NamedQuery(name = "BIClientes.findByTelefono", query = "SELECT b FROM BIClientes b WHERE b.telefono = :telefono")
    , @NamedQuery(name = "BIClientes.findByDireccion", query = "SELECT b FROM BIClientes b WHERE b.direccion = :direccion")
    , @NamedQuery(name = "BIClientes.findByNumeroCuenta", query = "SELECT b FROM BIClientes b WHERE b.numeroCuenta = :numeroCuenta")
    , @NamedQuery(name = "BIClientes.findByNombre", query = "SELECT b FROM BIClientes b WHERE b.nombre = :nombre")})
public class BIClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "Telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "NumeroCuenta")
    private String numeroCuenta;
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPropietario")
    private Collection<BIMascotas> bIMascotasCollection;

    public BIClientes() {
    }

    public BIClientes(Integer codigo) {
        this.codigo = codigo;
    }

    public BIClientes(Integer codigo, String telefono, String direccion) {
        this.codigo = codigo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<BIMascotas> getBIMascotasCollection() {
        return bIMascotasCollection;
    }

    public void setBIMascotasCollection(Collection<BIMascotas> bIMascotasCollection) {
        this.bIMascotasCollection = bIMascotasCollection;
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
        if (!(object instanceof BIClientes)) {
            return false;
        }
        BIClientes other = (BIClientes) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernateexample.BIClientes[ codigo=" + codigo + " ]";
    }
    
}

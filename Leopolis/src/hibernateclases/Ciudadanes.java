/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateclases;

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
@Table(name = "Ciudadanes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudadanes.findAll", query = "SELECT c FROM Ciudadanes c")
    , @NamedQuery(name = "Ciudadanes.findById", query = "SELECT c FROM Ciudadanes c WHERE c.id = :id")
    , @NamedQuery(name = "Ciudadanes.findByNombre", query = "SELECT c FROM Ciudadanes c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Ciudadanes.findByApellidos", query = "SELECT c FROM Ciudadanes c WHERE c.apellidos = :apellidos")
    , @NamedQuery(name = "Ciudadanes.findByFechanacimiento", query = "SELECT c FROM Ciudadanes c WHERE c.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "Ciudadanes.findByFechamuerte", query = "SELECT c FROM Ciudadanes c WHERE c.fechamuerte = :fechamuerte")
    , @NamedQuery(name = "Ciudadanes.findBySexo", query = "SELECT c FROM Ciudadanes c WHERE c.sexo = :sexo")
    , @NamedQuery(name = "Ciudadanes.findByKGpasta", query = "SELECT c FROM Ciudadanes c WHERE c.kGpasta = :kGpasta")})
public class Ciudadanes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "Fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "Fecha_muerte")
    @Temporal(TemporalType.DATE)
    private Date fechamuerte;
    @Basic(optional = false)
    @Column(name = "Sexo")
    private Character sexo;
    @Basic(optional = false)
    @Column(name = "KG_pasta")
    private short kGpasta;
    @OneToMany(mappedBy = "iDmadre")
    private Collection<Ciudadanes> ciudadanesCollection;
    @JoinColumn(name = "ID_madre", referencedColumnName = "ID")
    @ManyToOne
    private Ciudadanes iDmadre;
    @OneToMany(mappedBy = "iDpadre")
    private Collection<Ciudadanes> ciudadanesCollection1;
    @JoinColumn(name = "ID_padre", referencedColumnName = "ID")
    @ManyToOne
    private Ciudadanes iDpadre;
    @JoinColumn(name = "Pasta_favorita", referencedColumnName = "NombrePasta")
    @ManyToOne(optional = false)
    private Pastas pastafavorita;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDConyuge1")
    private Collection<Matrimonios> matrimoniosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDConyuge2")
    private Collection<Matrimonios> matrimoniosCollection1;

    public Ciudadanes() {
    }

    public Ciudadanes(Integer id) {
        this.id = id;
    }

    public Ciudadanes(Integer id, String nombre, String apellidos, Date fechanacimiento, Character sexo, short kGpasta) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.sexo = sexo;
        this.kGpasta = kGpasta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Date getFechamuerte() {
        return fechamuerte;
    }

    public void setFechamuerte(Date fechamuerte) {
        this.fechamuerte = fechamuerte;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public short getKGpasta() {
        return kGpasta;
    }

    public void setKGpasta(short kGpasta) {
        this.kGpasta = kGpasta;
    }

    @XmlTransient
    public Collection<Ciudadanes> getCiudadanesCollection() {
        return ciudadanesCollection;
    }

    public void setCiudadanesCollection(Collection<Ciudadanes> ciudadanesCollection) {
        this.ciudadanesCollection = ciudadanesCollection;
    }

    public Ciudadanes getIDmadre() {
        return iDmadre;
    }

    public void setIDmadre(Ciudadanes iDmadre) {
        this.iDmadre = iDmadre;
    }

    @XmlTransient
    public Collection<Ciudadanes> getCiudadanesCollection1() {
        return ciudadanesCollection1;
    }

    public void setCiudadanesCollection1(Collection<Ciudadanes> ciudadanesCollection1) {
        this.ciudadanesCollection1 = ciudadanesCollection1;
    }

    public Ciudadanes getIDpadre() {
        return iDpadre;
    }

    public void setIDpadre(Ciudadanes iDpadre) {
        this.iDpadre = iDpadre;
    }

    public Pastas getPastafavorita() {
        return pastafavorita;
    }

    public void setPastafavorita(Pastas pastafavorita) {
        this.pastafavorita = pastafavorita;
    }

    @XmlTransient
    public Collection<Matrimonios> getMatrimoniosCollection() {
        return matrimoniosCollection;
    }

    public void setMatrimoniosCollection(Collection<Matrimonios> matrimoniosCollection) {
        this.matrimoniosCollection = matrimoniosCollection;
    }

    @XmlTransient
    public Collection<Matrimonios> getMatrimoniosCollection1() {
        return matrimoniosCollection1;
    }

    public void setMatrimoniosCollection1(Collection<Matrimonios> matrimoniosCollection1) {
        this.matrimoniosCollection1 = matrimoniosCollection1;
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
        if (!(object instanceof Ciudadanes)) {
            return false;
        }
        Ciudadanes other = (Ciudadanes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernate.Ciudadanes[ id=" + id + " ]";
    }
    
}

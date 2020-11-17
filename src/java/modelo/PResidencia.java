/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "p_residencia", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PResidencia.findAll", query = "SELECT p FROM PResidencia p")
    , @NamedQuery(name = "PResidencia.findByIdPResidencia", query = "SELECT p FROM PResidencia p WHERE p.idPResidencia = :idPResidencia")
    , @NamedQuery(name = "PResidencia.findByNombre", query = "SELECT p FROM PResidencia p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PResidencia.findByEstado", query = "SELECT p FROM PResidencia p WHERE p.estado = :estado")})
public class PResidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_p_residencia")
    private Integer idPResidencia;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estado;
    @OneToMany(mappedBy = "idProvinciaResidencia")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idProvinciaResidencia")
    private List<Matricula> matriculaList; 

    public PResidencia() {
    } 

    public PResidencia(Integer idPResidencia) {
        this.idPResidencia = idPResidencia;
    }

    public Integer getIdPResidencia() {
        return idPResidencia;
    }

    public void setIdPResidencia(Integer idPResidencia) {
        this.idPResidencia = idPResidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getEstado() {
        return estado;
    }

    public void setEstado(Date estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPResidencia != null ? idPResidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PResidencia)) {
            return false;
        }
        PResidencia other = (PResidencia) object;
        if ((this.idPResidencia == null && other.idPResidencia != null) || (this.idPResidencia != null && !this.idPResidencia.equals(other.idPResidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}

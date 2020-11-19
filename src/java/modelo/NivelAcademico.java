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
@Table(name = "nivel_academico", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelAcademico.findAll", query = "SELECT n FROM NivelAcademico n")
    , @NamedQuery(name = "NivelAcademico.findByIdNivelAcademico", query = "SELECT n FROM NivelAcademico n WHERE n.idNivelAcademico = :idNivelAcademico")
    , @NamedQuery(name = "NivelAcademico.findByNivelAcademico", query = "SELECT n FROM NivelAcademico n WHERE n.nivelAcademico = :nivelAcademico")
    , @NamedQuery(name = "NivelAcademico.findByEstado", query = "SELECT n FROM NivelAcademico n WHERE n.estado = :estado")
    , @NamedQuery(name = "NivelAcademico.findByFechaDeRegistro", query = "SELECT n FROM NivelAcademico n WHERE n.fechaDeRegistro = :fechaDeRegistro")})
public class NivelAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_academico")
    private Integer idNivelAcademico;
    @Size(max = 45)
    @Column(name = "nivel_academico")
    private String nivelAcademico;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<Materia> materiaList;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<MatriculaHistorial> matriculaHistorialList;

    public NivelAcademico() {
    }

    public NivelAcademico(Integer idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public Integer getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(Integer idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    @XmlTransient
    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
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
    @XmlTransient
    public List<MatriculaHistorial> getMatriculaHistorialList() {
        return matriculaHistorialList;
    }

    public void setMatriculaHistorialList(List<MatriculaHistorial> matriculaHistorialList) {
        this.matriculaHistorialList = matriculaHistorialList;
    }
    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivelAcademico != null ? idNivelAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAcademico)) {
            return false;
        }
        NivelAcademico other = (NivelAcademico) object;
        if ((this.idNivelAcademico == null && other.idNivelAcademico != null) || (this.idNivelAcademico != null && !this.idNivelAcademico.equals(other.idNivelAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nivelAcademico;
    }
    
}

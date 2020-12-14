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
@Table(name = "periodo_academico", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademico.findAll", query = "SELECT p FROM PeriodoAcademico p")
    , @NamedQuery(name = "PeriodoAcademico.findByIdPeriodoAcademico", query = "SELECT p FROM PeriodoAcademico p WHERE p.idPeriodoAcademico = :idPeriodoAcademico")
    , @NamedQuery(name = "PeriodoAcademico.findByNombre", query = "SELECT p FROM PeriodoAcademico p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PeriodoAcademico.findByDescripcion", query = "SELECT p FROM PeriodoAcademico p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "PeriodoAcademico.findByModalidad", query = "SELECT p FROM PeriodoAcademico p WHERE p.modalidad = :modalidad")
    , @NamedQuery(name = "PeriodoAcademico.findByEstado", query = "SELECT p FROM PeriodoAcademico p WHERE p.estado = :estado")
    , @NamedQuery(name = "PeriodoAcademico.findByFechaDeRegistro", query = "SELECT p FROM PeriodoAcademico p WHERE p.fechaDeRegistro = :fechaDeRegistro")
    , @NamedQuery(name = "PeriodoAcademico.findByA\u00f1oPeriodoAcademico", query = "SELECT p FROM PeriodoAcademico p WHERE p.a\u00f1oPeriodoAcademico = :a\u00f1oPeriodoAcademico")})
public class PeriodoAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo_academico")
    private Integer idPeriodoAcademico;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "modalidad")
    private Integer modalidad;
   @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @Size(max = 45)
    @Column(name = "a\u00f1o_periodo_academico")
    private String añoPeriodoAcademico;
    @OneToMany(mappedBy = "idPeriodoAcademico")
    private List<Notas> notasList;
    @OneToMany(mappedBy = "idPeriodoAcademicoSemestre")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idPeriodoAcademico")
    private List<NivelAcademico> nivelAcademicoList;
    @OneToMany(mappedBy = "idPeriodoAcademico")
    private List<MatriculaHistorial> matriculaHistorialList;
    @OneToMany(mappedBy = "idPeriodoAcademico")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idPeriodoAcademico")
    private List<Materia> materiaList;
     @OneToMany(mappedBy = "idPeriodoAcademico")
    private List<Asistencia> asistenciaList;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public Integer getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(Integer idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public String getAñoPeriodoAcademico() {
        return añoPeriodoAcademico;
    }

    public void setAñoPeriodoAcademico(String añoPeriodoAcademico) {
        this.añoPeriodoAcademico = añoPeriodoAcademico;
    }
    @XmlTransient
    public List<MatriculaHistorial> getMatriculaHistorialList() {
        return matriculaHistorialList;
    }

    public void setMatriculaHistorialList(List<MatriculaHistorial> matriculaHistorialList) {
        this.matriculaHistorialList = matriculaHistorialList;
    }

    @XmlTransient
    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }
  
    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }
    @XmlTransient
    public List<NivelAcademico> getNivelAcademicoList() {
        return nivelAcademicoList;
    }

    public void setNivelAcademicoList(List<NivelAcademico> nivelAcademicoList) {
        this.nivelAcademicoList = nivelAcademicoList;
    }
    @XmlTransient
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }
    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }
    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodoAcademico != null ? idPeriodoAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoAcademico)) {
            return false;
        }
        PeriodoAcademico other = (PeriodoAcademico) object;
        if ((this.idPeriodoAcademico == null && other.idPeriodoAcademico != null) || (this.idPeriodoAcademico != null && !this.idPeriodoAcademico.equals(other.idPeriodoAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

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
@Table(name = "titulo_carrera", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TituloCarrera.findAll", query = "SELECT t FROM TituloCarrera t")
    , @NamedQuery(name = "TituloCarrera.findByIdTituloCarrera", query = "SELECT t FROM TituloCarrera t WHERE t.idTituloCarrera = :idTituloCarrera")
    , @NamedQuery(name = "TituloCarrera.findByNombreTitulo", query = "SELECT t FROM TituloCarrera t WHERE t.nombreTitulo = :nombreTitulo")
    , @NamedQuery(name = "TituloCarrera.findByDescripcion", query = "SELECT t FROM TituloCarrera t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TituloCarrera.findByEstado", query = "SELECT t FROM TituloCarrera t WHERE t.estado = :estado")
    , @NamedQuery(name = "TituloCarrera.findByFechaDeRegistro", query = "SELECT t FROM TituloCarrera t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TituloCarrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_titulo_carrera")
    private Integer idTituloCarrera;
    @Size(max = 45)
    @Column(name = "nombre_titulo")
    private String nombreTitulo;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTituloCarrera")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idTituloCarrera")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idTituloCarrera")
    private List<Materia> materiaList;
    @OneToMany(mappedBy = "idTituloCarrera")
    private List<Notas> notasList;
    @OneToMany(mappedBy = "idTituloCarrera")
    private List<NivelAcademico> nivelAcademicoList;
    @OneToMany(mappedBy = "idTituloCarrera")
    private List<MatriculaHistorial> matriculaHistorialList;
    
    public TituloCarrera() {
    }

    public TituloCarrera(Integer idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }

    public Integer getIdTituloCarrera() {
        return idTituloCarrera;
    }

    public void setIdTituloCarrera(Integer idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }

    public String getNombreTitulo() {
        return nombreTitulo;
    }

    public void setNombreTitulo(String nombreTitulo) {
        this.nombreTitulo = nombreTitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }
    @XmlTransient
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }
    @XmlTransient
    public List<NivelAcademico> getNivelAcademicoList() {
        return nivelAcademicoList;
    }

    public void setNivelAcademicoList(List<NivelAcademico> nivelAcademicoList) {
       
        this.nivelAcademicoList = nivelAcademicoList;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTituloCarrera != null ? idTituloCarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TituloCarrera)) {
            return false;
        }
        TituloCarrera other = (TituloCarrera) object;
        if ((this.idTituloCarrera == null && other.idTituloCarrera != null) || (this.idTituloCarrera != null && !this.idTituloCarrera.equals(other.idTituloCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreTitulo;
    }
    
}

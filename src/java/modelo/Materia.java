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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "materia", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria")
    , @NamedQuery(name = "Materia.findByCodMateria", query = "SELECT m FROM Materia m WHERE m.codMateria = :codMateria")
    , @NamedQuery(name = "Materia.findByDescripcion", query = "SELECT m FROM Materia m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Materia.findByMateria", query = "SELECT m FROM Materia m WHERE m.materia = :materia")
    , @NamedQuery(name = "Materia.findByCreditos", query = "SELECT m FROM Materia m WHERE m.creditos = :creditos")
    , @NamedQuery(name = "Materia.findByNumHoras", query = "SELECT m FROM Materia m WHERE m.numHoras = :numHoras")
    , @NamedQuery(name = "Materia.findByNumHorasSemanales", query = "SELECT m FROM Materia m WHERE m.numHorasSemanales = :numHorasSemanales")
    , @NamedQuery(name = "Materia.findByEstado", query = "SELECT m FROM Materia m WHERE m.estado = :estado")
    , @NamedQuery(name = "Materia.findByFechaDeRegistro", query = "SELECT m FROM Materia m WHERE m.fechaDeRegistro = :fechaDeRegistro")
, @NamedQuery(name = "Materia.findAllMateriaByCarrera", query = "SELECT m FROM Materia m WHERE m.idTituloCarrera = :idTituloCarrera AND m.idNivelAcademico = :idNivelAcademico")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia")
    private Integer idMateria;
    @Size(max = 45)
    @Column(name = "cod_materia")
    private String codMateria;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "materia")
    private String materia;
    @Size(max = 45)
    @Column(name = "creditos")
    private String creditos;
    @Column(name = "num_horas")
    private Integer numHoras;
    @Column(name = "num_horas_semanales")
    private Integer numHorasSemanales;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idMateria")
    private List<Notas> notasList;
    @OneToMany(mappedBy = "idMateria")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idMateria")
    private List<MateriaCarrera> materiaCarreraList;
    @OneToMany(mappedBy = "idMateria")
    private List<MatriculaHistorial> matriculaHistorialList;
    @OneToMany(mappedBy = "idMateria")
    private List<Matricula> matriculaList;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_periodo_academico", referencedColumnName = "id_periodo_academico")
    @ManyToOne
    private PeriodoAcademico idPeriodoAcademico;
    @JoinColumn(name = "id_titulo_carrera", referencedColumnName = "id_titulo_carrera")
    @ManyToOne
    private TituloCarrera idTituloCarrera;
    @OneToMany(mappedBy = "idMateria")
    private List<CoRequisitosMateria> coRequisitosMateriaList;
    @OneToMany(mappedBy = "idMateria")
    private List<PreRequisitosMateria> preRequisitosMateriaList;
    @OneToMany(mappedBy = "idMateria")
    private List<Asistencia> asistenciaList;

    public Materia() {
    }

    public Materia(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public Integer getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(Integer numHoras) {
        this.numHoras = numHoras;
    }

    public Integer getNumHorasSemanales() {
        return numHorasSemanales;
    }

    public void setNumHorasSemanales(Integer numHorasSemanales) {
        this.numHorasSemanales = numHorasSemanales;
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
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    @XmlTransient
    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
    }

    @XmlTransient
    public List<MateriaCarrera> getMateriaCarreraList() {
        return materiaCarreraList;
    }

    public void setMateriaCarreraList(List<MateriaCarrera> materiaCarreraList) {
        this.materiaCarreraList = materiaCarreraList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }
    @XmlTransient
    public List<MatriculaHistorial> getMatriculaHistorialList() {
        return matriculaHistorialList;
    }

    public void setMatriculaHistorialList(List<MatriculaHistorial> matriculaHistorialList) {
        this.matriculaHistorialList = matriculaHistorialList;
    }
    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public TituloCarrera getIdTituloCarrera() {
        return idTituloCarrera;
    }

    public void setIdTituloCarrera(TituloCarrera idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }

    public PeriodoAcademico getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(PeriodoAcademico idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }
      @XmlTransient
    public List<CoRequisitosMateria> getCoRequisitosMateriaList() {
        return coRequisitosMateriaList;
    }

    public void setCoRequisitosMateriaList(List<CoRequisitosMateria> coRequisitosMateriaList) {
        this.coRequisitosMateriaList = coRequisitosMateriaList;
    }
    @XmlTransient
    public List<PreRequisitosMateria> getPreRequisitosMateriaList() {
        return preRequisitosMateriaList;
    }

    public void setPreRequisitosMateriaList(List<PreRequisitosMateria> preRequisitosMateriaList) {
        this.preRequisitosMateriaList = preRequisitosMateriaList;
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
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}

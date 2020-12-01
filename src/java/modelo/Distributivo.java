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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "distributivo", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributivo.findAll", query = "SELECT d FROM Distributivo d")
    , @NamedQuery(name = "Distributivo.findByIdDistributivo", query = "SELECT d FROM Distributivo d WHERE d.idDistributivo = :idDistributivo")
    , @NamedQuery(name = "Distributivo.findByFechaVinculacion", query = "SELECT d FROM Distributivo d WHERE d.fechaVinculacion = :fechaVinculacion")
    , @NamedQuery(name = "Distributivo.findByNroHorasActividasdesDocencia", query = "SELECT d FROM Distributivo d WHERE d.nroHorasActividasdesDocencia = :nroHorasActividasdesDocencia")
    , @NamedQuery(name = "Distributivo.findByNroHorasSemanalGestionAcademica", query = "SELECT d FROM Distributivo d WHERE d.nroHorasSemanalGestionAcademica = :nroHorasSemanalGestionAcademica")
    , @NamedQuery(name = "Distributivo.findByDetalleHorasGestionAcademica", query = "SELECT d FROM Distributivo d WHERE d.detalleHorasGestionAcademica = :detalleHorasGestionAcademica")
    , @NamedQuery(name = "Distributivo.findByTotalHorasSemanalFuncionario", query = "SELECT d FROM Distributivo d WHERE d.totalHorasSemanalFuncionario = :totalHorasSemanalFuncionario")
    , @NamedQuery(name = "Distributivo.findByNumeroDeSemanas", query = "SELECT d FROM Distributivo d WHERE d.numeroDeSemanas = :numeroDeSemanas")
    , @NamedQuery(name = "Distributivo.findByEstado", query = "SELECT d FROM Distributivo d WHERE d.estado = :estado")
    , @NamedQuery(name = "Distributivo.findByFechaDeRegistro", query = "SELECT d FROM Distributivo d WHERE d.fechaDeRegistro = :fechaDeRegistro")})
public class Distributivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distributivo")
    private Integer idDistributivo;
    @Column(name = "fecha_vinculacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVinculacion;
    @Column(name = "nro_horas_actividasdes_docencia")
    private Integer nroHorasActividasdesDocencia;
    @Column(name = "nro_horas_semanal_gestion_academica")
    private Integer nroHorasSemanalGestionAcademica;
    @Size(max = 45)
    @Column(name = "detalle_horas_gestion_academica")
    private String detalleHorasGestionAcademica;
    @Column(name = "total_horas_semanal_funcionario")
    private Integer totalHorasSemanalFuncionario;
    @Column(name = "numero_de_semanas")
    private Integer numeroDeSemanas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @JoinColumn(name = "id_jornada_academica", referencedColumnName = "id_jornada_academica")
    @ManyToOne
    private JornadaAcademica idJornadaAcademica;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_paralelo", referencedColumnName = "id_paralelo")
    @ManyToOne
    private Paralelo idParalelo;
    @JoinColumn(name = "id_perfil_academico", referencedColumnName = "id_perfil_academico")
    @ManyToOne
    private PerfilAcademico idPerfilAcademico;
    @JoinColumn(name = "id_periodo_academico_semestre", referencedColumnName = "id_periodo_academico")
    @ManyToOne
    private PeriodoAcademico idPeriodoAcademicoSemestre;
    @JoinColumn(name = "id_tabla_investigacion", referencedColumnName = "id_tabla_investigacion")
    @ManyToOne
    private TablaInvestigacion idTablaInvestigacion;
    @JoinColumn(name = "id_tipo_tiempo", referencedColumnName = "id_tipo_tiempo")
    @ManyToOne
    private TipoTiempo idTipoTiempo;
    @JoinColumn(name = "id_titulo_carrera", referencedColumnName = "id_titulo_carrera")
    @ManyToOne
    private TituloCarrera idTituloCarrera;
    @OneToMany(mappedBy = "idDistributivo")
    private List<DistributivoMateria> distributivoMateriaList;

    public Distributivo() {
    }

    public Distributivo(Integer idDistributivo) {
        this.idDistributivo = idDistributivo;
    }

    public Distributivo(Integer idDistributivo, int estado) {
        this.idDistributivo = idDistributivo;
        this.estado = estado;
    }

    public Integer getIdDistributivo() {
        return idDistributivo;
    }

    public void setIdDistributivo(Integer idDistributivo) {
        this.idDistributivo = idDistributivo;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public Integer getNroHorasActividasdesDocencia() {
        return nroHorasActividasdesDocencia;
    }

    public void setNroHorasActividasdesDocencia(Integer nroHorasActividasdesDocencia) {
        this.nroHorasActividasdesDocencia = nroHorasActividasdesDocencia;
    }

    public Integer getNroHorasSemanalGestionAcademica() {
        return nroHorasSemanalGestionAcademica;
    }

    public void setNroHorasSemanalGestionAcademica(Integer nroHorasSemanalGestionAcademica) {
        this.nroHorasSemanalGestionAcademica = nroHorasSemanalGestionAcademica;
    }

    public String getDetalleHorasGestionAcademica() {
        return detalleHorasGestionAcademica;
    }

    public void setDetalleHorasGestionAcademica(String detalleHorasGestionAcademica) {
        this.detalleHorasGestionAcademica = detalleHorasGestionAcademica;
    }

    public Integer getTotalHorasSemanalFuncionario() {
        return totalHorasSemanalFuncionario;
    }

    public void setTotalHorasSemanalFuncionario(Integer totalHorasSemanalFuncionario) {
        this.totalHorasSemanalFuncionario = totalHorasSemanalFuncionario;
    }

    public Integer getNumeroDeSemanas() {
        return numeroDeSemanas;
    }

    public void setNumeroDeSemanas(Integer numeroDeSemanas) {
        this.numeroDeSemanas = numeroDeSemanas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public JornadaAcademica getIdJornadaAcademica() {
        return idJornadaAcademica;
    }

    public void setIdJornadaAcademica(JornadaAcademica idJornadaAcademica) {
        this.idJornadaAcademica = idJornadaAcademica;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public Paralelo getIdParalelo() {
        return idParalelo;
    }

    public void setIdParalelo(Paralelo idParalelo) {
        this.idParalelo = idParalelo;
    }

    public PerfilAcademico getIdPerfilAcademico() {
        return idPerfilAcademico;
    }

    public void setIdPerfilAcademico(PerfilAcademico idPerfilAcademico) {
        this.idPerfilAcademico = idPerfilAcademico;
    }

    public PeriodoAcademico getIdPeriodoAcademicoSemestre() {
        return idPeriodoAcademicoSemestre;
    }

    public void setIdPeriodoAcademicoSemestre(PeriodoAcademico idPeriodoAcademicoSemestre) {
        this.idPeriodoAcademicoSemestre = idPeriodoAcademicoSemestre;
    }

    public TablaInvestigacion getIdTablaInvestigacion() {
        return idTablaInvestigacion;
    }

    public void setIdTablaInvestigacion(TablaInvestigacion idTablaInvestigacion) {
        this.idTablaInvestigacion = idTablaInvestigacion;
    }

    public TipoTiempo getIdTipoTiempo() {
        return idTipoTiempo;
    }

    public void setIdTipoTiempo(TipoTiempo idTipoTiempo) {
        this.idTipoTiempo = idTipoTiempo;
    }

    public TituloCarrera getIdTituloCarrera() {
        return idTituloCarrera;
    }

    public void setIdTituloCarrera(TituloCarrera idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }
    @XmlTransient
    public List<DistributivoMateria> getDistributivoMateriaList() {
        return distributivoMateriaList;
    }

    public void setDistributivoMateriaList(List<DistributivoMateria> distributivoMateriaList) {
        this.distributivoMateriaList = distributivoMateriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistributivo != null ? idDistributivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributivo)) {
            return false;
        }
        Distributivo other = (Distributivo) object;
        if ((this.idDistributivo == null && other.idDistributivo != null) || (this.idDistributivo != null && !this.idDistributivo.equals(other.idDistributivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.detalleHorasGestionAcademica;

    }
    
}

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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "matricula", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m")
    , @NamedQuery(name = "Matricula.findByIdMatricula", query = "SELECT m FROM Matricula m WHERE m.idMatricula = :idMatricula")
    , @NamedQuery(name = "Matricula.findByFechaMatricula", query = "SELECT m FROM Matricula m WHERE m.fechaMatricula = :fechaMatricula")
    , @NamedQuery(name = "Matricula.findByCodMatricula", query = "SELECT m FROM Matricula m WHERE m.codMatricula = :codMatricula")
    , @NamedQuery(name = "Matricula.findByAnioGraduacion", query = "SELECT m FROM Matricula m WHERE m.anioGraduacion = :anioGraduacion")
    , @NamedQuery(name = "Matricula.findByModalidad", query = "SELECT m FROM Matricula m WHERE m.modalidad = :modalidad")
    , @NamedQuery(name = "Matricula.findByFechaInicioCarrera", query = "SELECT m FROM Matricula m WHERE m.fechaInicioCarrera = :fechaInicioCarrera")
    , @NamedQuery(name = "Matricula.findByTituloEducacionSuperior", query = "SELECT m FROM Matricula m WHERE m.tituloEducacionSuperior = :tituloEducacionSuperior")
    , @NamedQuery(name = "Matricula.findByEspecifiqueTitulo", query = "SELECT m FROM Matricula m WHERE m.especifiqueTitulo = :especifiqueTitulo")
    , @NamedQuery(name = "Matricula.findByTituloOtorgado", query = "SELECT m FROM Matricula m WHERE m.tituloOtorgado = :tituloOtorgado")
    , @NamedQuery(name = "Matricula.findByOcupacionEstudiante", query = "SELECT m FROM Matricula m WHERE m.ocupacionEstudiante = :ocupacionEstudiante")
    , @NamedQuery(name = "Matricula.findByNombreEmpresa", query = "SELECT m FROM Matricula m WHERE m.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Matricula.findByEmpleoIngresosEconomicos", query = "SELECT m FROM Matricula m WHERE m.empleoIngresosEconomicos = :empleoIngresosEconomicos")
    , @NamedQuery(name = "Matricula.findByBonoDesarrolloHumano", query = "SELECT m FROM Matricula m WHERE m.bonoDesarrolloHumano = :bonoDesarrolloHumano")
    , @NamedQuery(name = "Matricula.findByIngresoHogar", query = "SELECT m FROM Matricula m WHERE m.ingresoHogar = :ingresoHogar")
    , @NamedQuery(name = "Matricula.findByNroMiembrosHogar", query = "SELECT m FROM Matricula m WHERE m.nroMiembrosHogar = :nroMiembrosHogar")
    , @NamedQuery(name = "Matricula.findByMateriaRepetida", query = "SELECT m FROM Matricula m WHERE m.materiaRepetida = :materiaRepetida")
    , @NamedQuery(name = "Matricula.findByMateriaPerdida", query = "SELECT m FROM Matricula m WHERE m.materiaPerdida = :materiaPerdida")
    , @NamedQuery(name = "Matricula.findByEstado", query = "SELECT m FROM Matricula m WHERE m.estado = :estado")
    , @NamedQuery(name = "Matricula.findByFechaDeRegistro", query = "SELECT m FROM Matricula m WHERE m.fechaDeRegistro = :fechaDeRegistro")
    , @NamedQuery(name = "Matricula.findByNumeroFormulario", query = "SELECT m FROM Matricula m WHERE m.numeroFormulario = :numeroFormulario")})
public class Matricula implements Serializable {

    @OneToMany(mappedBy = "idMatricula")
    private List<FormalizarMatricula> formalizarMatriculaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_matricula")
    private Integer idMatricula;
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @Size(max = 45)
    @Column(name = "cod_matricula")
    private String codMatricula;
    @Size(max = 45)
    @Column(name = "anio_graduacion")
    private String anioGraduacion;
    @Column(name = "modalidad")
    private Integer modalidad;
    @Size(max = 45)
    @Column(name = "fecha_inicio_carrera")
    private String fechaInicioCarrera;
    @Size(max = 45)
    @Column(name = "titulo_educacion_superior")
    private String tituloEducacionSuperior;
    @Size(max = 45)
    @Column(name = "especifique_titulo")
    private String especifiqueTitulo;
    @Size(max = 45)
    @Column(name = "titulo_otorgado")
    private String tituloOtorgado;
    @Column(name = "ocupacion_estudiante")
    private Integer ocupacionEstudiante;
    @Size(max = 45)
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Size(max = 45)
    @Column(name = "empleo_ingresos_economicos")
    private String empleoIngresosEconomicos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bono_desarrollo_humano")
    private int bonoDesarrolloHumano;
    @Size(max = 45)
    @Column(name = "ingreso_hogar")
    private String ingresoHogar;
    @Size(max = 45)
    @Column(name = "nro_miembros_hogar")
    private String nroMiembrosHogar;
    @Column(name = "materia_repetida")
    private Integer materiaRepetida;
    @Column(name = "materia_perdida")
    private Integer materiaPerdida;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @Column(name = "numero_formulario")
    private Integer numeroFormulario;
    @JoinColumn(name = "id_periodo_academico", referencedColumnName = "id_periodo_academico")
    @ManyToOne
    private PeriodoAcademico idPeriodoAcademico;
    @JoinColumn(name = "id_alcance_vinculacion", referencedColumnName = "id_alcance_vinculacion")
    @ManyToOne
    private AlcanceVinculacion idAlcanceVinculacion;
    @JoinColumn(name = "id_formacion_madre", referencedColumnName = "id_formacion_academica_madre")
    @ManyToOne
    private FormacionAcademicaMadre idFormacionMadre;
    @JoinColumn(name = "id_formacion_padre", referencedColumnName = "id_formacion_academica")
    @ManyToOne
    private FormacionAcademica idFormacionPadre;
    @JoinColumn(name = "id_tipo_carrera", referencedColumnName = "id_tipo_carrera")
    @ManyToOne
    private TipoCarrera idTipoCarrera;
    @JoinColumn(name = "id_jornada_academica", referencedColumnName = "id_jornada_academica")
    @ManyToOne
    private JornadaAcademica idJornadaAcademica;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_paralelo", referencedColumnName = "id_paralelo")
    @ManyToOne
    private Paralelo idParalelo;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_tipo_colegio", referencedColumnName = "id_tipo_colegio")
    @ManyToOne
    private TipoColegio idTipoColegio;
    @JoinColumn(name = "id_practicas_preProfesionales_vinculacion", referencedColumnName = "id_practicasPreprofesionales_vinculacion")
    @ManyToOne
    private PracticaspreprofesionalesVinculacion idpracticaspreProfesionalesvinculacion;
    @JoinColumn(name = "id_sector_economico_empresa", referencedColumnName = "id_sector_economico_empresa")
    @ManyToOne
    private SectorEconomicoEmpresa idSectorEconomicoEmpresa;
    @JoinColumn(name = "id_tipo_matricula", referencedColumnName = "id_tipo_matricula")
    @ManyToOne
    private TipoMatricula idTipoMatricula;
    @JoinColumn(name = "id_tipo_institucion", referencedColumnName = "id_tipo_institucion_practicaspreprofesionales")
    @ManyToOne
    private TipoInstitucionPracticas idTipoInstitucion;
    @JoinColumn(name = "id_tipo_bachillerato", referencedColumnName = "id_tipo_bachillerato")
    @ManyToOne
    private TipoBachillerato idTipoBachillerato;
    @JoinColumn(name = "id_titulo_carrera", referencedColumnName = "id_titulo_carrera")
    @ManyToOne
    private TituloCarrera idTituloCarrera;

    public Matricula() {
    }

    public Matricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Matricula(Integer idMatricula, int bonoDesarrolloHumano) {
        this.idMatricula = idMatricula;
        this.bonoDesarrolloHumano = bonoDesarrolloHumano;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getCodMatricula() {
        return codMatricula;
    }

    public void setCodMatricula(String codMatricula) {
        this.codMatricula = codMatricula;
    }

    public String getAnioGraduacion() {
        return anioGraduacion;
    }

    public void setAnioGraduacion(String anioGraduacion) {
        this.anioGraduacion = anioGraduacion;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public String getFechaInicioCarrera() {
        return fechaInicioCarrera;
    }

    public void setFechaInicioCarrera(String fechaInicioCarrera) {
        this.fechaInicioCarrera = fechaInicioCarrera;
    }

    public String getTituloEducacionSuperior() {
        return tituloEducacionSuperior;
    }

    public void setTituloEducacionSuperior(String tituloEducacionSuperior) {
        this.tituloEducacionSuperior = tituloEducacionSuperior;
    }

    public String getEspecifiqueTitulo() {
        return especifiqueTitulo;
    }

    public void setEspecifiqueTitulo(String especifiqueTitulo) {
        this.especifiqueTitulo = especifiqueTitulo;
    }

    public String getTituloOtorgado() {
        return tituloOtorgado;
    }

    public void setTituloOtorgado(String tituloOtorgado) {
        this.tituloOtorgado = tituloOtorgado;
    }

    public Integer getOcupacionEstudiante() {
        return ocupacionEstudiante;
    }

    public void setOcupacionEstudiante(Integer ocupacionEstudiante) {
        this.ocupacionEstudiante = ocupacionEstudiante;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmpleoIngresosEconomicos() {
        return empleoIngresosEconomicos;
    }

    public void setEmpleoIngresosEconomicos(String empleoIngresosEconomicos) {
        this.empleoIngresosEconomicos = empleoIngresosEconomicos;
    }

    public int getBonoDesarrolloHumano() {
        return bonoDesarrolloHumano;
    }

    public void setBonoDesarrolloHumano(int bonoDesarrolloHumano) {
        this.bonoDesarrolloHumano = bonoDesarrolloHumano;
    }

    public String getIngresoHogar() {
        return ingresoHogar;
    }

    public void setIngresoHogar(String ingresoHogar) {
        this.ingresoHogar = ingresoHogar;
    }

    public String getNroMiembrosHogar() {
        return nroMiembrosHogar;
    }

    public void setNroMiembrosHogar(String nroMiembrosHogar) {
        this.nroMiembrosHogar = nroMiembrosHogar;
    }

    public Integer getMateriaRepetida() {
        return materiaRepetida;
    }

    public void setMateriaRepetida(Integer materiaRepetida) {
        this.materiaRepetida = materiaRepetida;
    }

    public Integer getMateriaPerdida() {
        return materiaPerdida;
    }

    public void setMateriaPerdida(Integer materiaPerdida) {
        this.materiaPerdida = materiaPerdida;
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

    public Integer getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(Integer numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public PeriodoAcademico getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(PeriodoAcademico idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public AlcanceVinculacion getIdAlcanceVinculacion() {
        return idAlcanceVinculacion;
    }

    public void setIdAlcanceVinculacion(AlcanceVinculacion idAlcanceVinculacion) {
        this.idAlcanceVinculacion = idAlcanceVinculacion;
    }

    public FormacionAcademicaMadre getIdFormacionMadre() {
        return idFormacionMadre;
    }

    public void setIdFormacionMadre(FormacionAcademicaMadre idFormacionMadre) {
        this.idFormacionMadre = idFormacionMadre;
    }

    public FormacionAcademica getIdFormacionPadre() {
        return idFormacionPadre;
    }

    public void setIdFormacionPadre(FormacionAcademica idFormacionPadre) {
        this.idFormacionPadre = idFormacionPadre;
    }

    public TipoCarrera getIdTipoCarrera() {
        return idTipoCarrera;
    }

    public void setIdTipoCarrera(TipoCarrera idTipoCarrera) {
        this.idTipoCarrera = idTipoCarrera;
    }

    public JornadaAcademica getIdJornadaAcademica() {
        return idJornadaAcademica;
    }

    public void setIdJornadaAcademica(JornadaAcademica idJornadaAcademica) {
        this.idJornadaAcademica = idJornadaAcademica;
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

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public TipoColegio getIdTipoColegio() {
        return idTipoColegio;
    }

    public void setIdTipoColegio(TipoColegio idTipoColegio) {
        this.idTipoColegio = idTipoColegio;
    }

    public PracticaspreprofesionalesVinculacion getIdpracticaspreProfesionalesvinculacion() {
        return idpracticaspreProfesionalesvinculacion;
    }

    public void setIdpracticaspreProfesionalesvinculacion(PracticaspreprofesionalesVinculacion idpracticaspreProfesionalesvinculacion) {
        this.idpracticaspreProfesionalesvinculacion = idpracticaspreProfesionalesvinculacion;
    }

    public SectorEconomicoEmpresa getIdSectorEconomicoEmpresa() {
        return idSectorEconomicoEmpresa;
    }

    public void setIdSectorEconomicoEmpresa(SectorEconomicoEmpresa idSectorEconomicoEmpresa) {
        this.idSectorEconomicoEmpresa = idSectorEconomicoEmpresa;
    }

    public TipoMatricula getIdTipoMatricula() {
        return idTipoMatricula;
    }

    public void setIdTipoMatricula(TipoMatricula idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    public TipoInstitucionPracticas getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    public void setIdTipoInstitucion(TipoInstitucionPracticas idTipoInstitucion) {
        this.idTipoInstitucion = idTipoInstitucion;
    }

    public TipoBachillerato getIdTipoBachillerato() {
        return idTipoBachillerato;
    }

    public void setIdTipoBachillerato(TipoBachillerato idTipoBachillerato) {
        this.idTipoBachillerato = idTipoBachillerato;
    }

    public TituloCarrera getIdTituloCarrera() {
        return idTituloCarrera;
    }

    public void setIdTituloCarrera(TituloCarrera idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DatosPersonales dp= this.getIdPersona().getIdDatosPersonales();
        String datos=dp.getApellidos()+" "+dp.getNombres();
        return "Matriculado: "+datos;
    }

    @XmlTransient
    public List<FormalizarMatricula> getFormalizarMatriculaList() {
        return formalizarMatriculaList;
    }

    public void setFormalizarMatriculaList(List<FormalizarMatricula> formalizarMatriculaList) {
        this.formalizarMatriculaList = formalizarMatriculaList;
    }

}

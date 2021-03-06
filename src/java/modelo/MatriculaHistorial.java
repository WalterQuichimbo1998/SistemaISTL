/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author walter
 */
@Entity
@Table(name = "matricula_historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatriculaHistorial.findAll", query = "SELECT m FROM MatriculaHistorial m")
    , @NamedQuery(name = "MatriculaHistorial.findByIdMatriculaHistorial", query = "SELECT m FROM MatriculaHistorial m WHERE m.idMatriculaHistorial = :idMatriculaHistorial")
    , @NamedQuery(name = "MatriculaHistorial.findByIdiomaAncestral", query = "SELECT m FROM MatriculaHistorial m WHERE m.idiomaAncestral = :idiomaAncestral")
    , @NamedQuery(name = "MatriculaHistorial.findByDescripcionIdiomaAncestral", query = "SELECT m FROM MatriculaHistorial m WHERE m.descripcionIdiomaAncestral = :descripcionIdiomaAncestral")
    , @NamedQuery(name = "MatriculaHistorial.findByPaisNacionalidad", query = "SELECT m FROM MatriculaHistorial m WHERE m.paisNacionalidad = :paisNacionalidad")
    , @NamedQuery(name = "MatriculaHistorial.findByPaisResidencia", query = "SELECT m FROM MatriculaHistorial m WHERE m.paisResidencia = :paisResidencia")
    , @NamedQuery(name = "MatriculaHistorial.findByPoseeDiscapacidad", query = "SELECT m FROM MatriculaHistorial m WHERE m.poseeDiscapacidad = :poseeDiscapacidad")
    , @NamedQuery(name = "MatriculaHistorial.findByEstado", query = "SELECT m FROM MatriculaHistorial m WHERE m.estado = :estado")
    , @NamedQuery(name = "MatriculaHistorial.findByFechaDeRegistro", query = "SELECT m FROM MatriculaHistorial m WHERE m.fechaDeRegistro = :fechaDeRegistro")
    , @NamedQuery(name = "MatriculaHistorial.findByParticipacionPracticas", query = "SELECT m FROM MatriculaHistorial m WHERE m.participacionPracticas = :participacionPracticas")
    , @NamedQuery(name = "MatriculaHistorial.findByParticipacionVinculacion", query = "SELECT m FROM MatriculaHistorial m WHERE m.participacionVinculacion = :participacionVinculacion")
    , @NamedQuery(name = "MatriculaHistorial.findByFechaMatricula", query = "SELECT m FROM MatriculaHistorial m WHERE m.fechaMatricula = :fechaMatricula")
    , @NamedQuery(name = "MatriculaHistorial.findByAnioGraduacion", query = "SELECT m FROM MatriculaHistorial m WHERE m.anioGraduacion = :anioGraduacion")
    , @NamedQuery(name = "MatriculaHistorial.findByModalidad", query = "SELECT m FROM MatriculaHistorial m WHERE m.modalidad = :modalidad")
    , @NamedQuery(name = "MatriculaHistorial.findByFechaInicioCarrera", query = "SELECT m FROM MatriculaHistorial m WHERE m.fechaInicioCarrera = :fechaInicioCarrera")
    , @NamedQuery(name = "MatriculaHistorial.findByTituloEducacionSuperior", query = "SELECT m FROM MatriculaHistorial m WHERE m.tituloEducacionSuperior = :tituloEducacionSuperior")
    , @NamedQuery(name = "MatriculaHistorial.findByEspecifiqueTitulo", query = "SELECT m FROM MatriculaHistorial m WHERE m.especifiqueTitulo = :especifiqueTitulo")
    , @NamedQuery(name = "MatriculaHistorial.findByTituloOtorgado", query = "SELECT m FROM MatriculaHistorial m WHERE m.tituloOtorgado = :tituloOtorgado")
    , @NamedQuery(name = "MatriculaHistorial.findByOcupacionEstudiante", query = "SELECT m FROM MatriculaHistorial m WHERE m.ocupacionEstudiante = :ocupacionEstudiante")
    , @NamedQuery(name = "MatriculaHistorial.findByNombreEmpresa", query = "SELECT m FROM MatriculaHistorial m WHERE m.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "MatriculaHistorial.findByEmpleoIngresosEconomicos", query = "SELECT m FROM MatriculaHistorial m WHERE m.empleoIngresosEconomicos = :empleoIngresosEconomicos")
    , @NamedQuery(name = "MatriculaHistorial.findByBonoDesarrolloHumano", query = "SELECT m FROM MatriculaHistorial m WHERE m.bonoDesarrolloHumano = :bonoDesarrolloHumano")
    , @NamedQuery(name = "MatriculaHistorial.findByIngresoHogar", query = "SELECT m FROM MatriculaHistorial m WHERE m.ingresoHogar = :ingresoHogar")
    , @NamedQuery(name = "MatriculaHistorial.findByNroMiembrosHogar", query = "SELECT m FROM MatriculaHistorial m WHERE m.nroMiembrosHogar = :nroMiembrosHogar")
    , @NamedQuery(name = "MatriculaHistorial.findByMateriaRepetida", query = "SELECT m FROM MatriculaHistorial m WHERE m.materiaRepetida = :materiaRepetida")
    , @NamedQuery(name = "MatriculaHistorial.findByMateriaPerdida", query = "SELECT m FROM MatriculaHistorial m WHERE m.materiaPerdida = :materiaPerdida")
    , @NamedQuery(name = "MatriculaHistorial.findByNumeroFormulario", query = "SELECT m FROM MatriculaHistorial m WHERE m.numeroFormulario = :numeroFormulario")
    , @NamedQuery(name = "MatriculaHistorial.findByCodigoMatricula", query = "SELECT m FROM MatriculaHistorial m WHERE m.codigoMatricula = :codigoMatricula")
    , @NamedQuery(name = "MatriculaHistorial.findByNombreContacto", query = "SELECT m FROM MatriculaHistorial m WHERE m.nombreContacto = :nombreContacto")
    , @NamedQuery(name = "MatriculaHistorial.findByApellidoContacto", query = "SELECT m FROM MatriculaHistorial m WHERE m.apellidoContacto = :apellidoContacto")
    , @NamedQuery(name = "MatriculaHistorial.findByCelularContacto", query = "SELECT m FROM MatriculaHistorial m WHERE m.celularContacto = :celularContacto")
    , @NamedQuery(name = "MatriculaHistorial.findByParentescoContacto", query = "SELECT m FROM MatriculaHistorial m WHERE m.parentescoContacto = :parentescoContacto")
    , @NamedQuery(name = "MatriculaHistorial.findByNumCarnetConadis", query = "SELECT m FROM MatriculaHistorial m WHERE m.numCarnetConadis = :numCarnetConadis")
    , @NamedQuery(name = "MatriculaHistorial.findByPorcentajeDiscapacidad", query = "SELECT m FROM MatriculaHistorial m WHERE m.porcentajeDiscapacidad = :porcentajeDiscapacidad")
    , @NamedQuery(name = "MatriculaHistorial.findByDescripcionDiscapacidad", query = "SELECT m FROM MatriculaHistorial m WHERE m.descripcionDiscapacidad = :descripcionDiscapacidad")
    , @NamedQuery(name = "MatriculaHistorial.findByNrohoraspracticasPreprosionales", query = "SELECT m FROM MatriculaHistorial m WHERE m.nrohoraspracticasPreprosionales = :nrohoraspracticasPreprosionales")
    , @NamedQuery(name = "MatriculaHistorial.findByCategoriaMigratoria", query = "SELECT m FROM MatriculaHistorial m WHERE m.categoriaMigratoria = :categoriaMigratoria")
    , @NamedQuery(name = "MatriculaHistorial.findByProvinciaResidencia", query = "SELECT m FROM MatriculaHistorial m WHERE m.provinciaResidencia = :provinciaResidencia")
    , @NamedQuery(name = "MatriculaHistorial.findByCantonResidencia", query = "SELECT m FROM MatriculaHistorial m WHERE m.cantonResidencia = :cantonResidencia")
    , @NamedQuery(name = "MatriculaHistorial.findByNacionalidad", query = "SELECT m FROM MatriculaHistorial m WHERE m.nacionalidad = :nacionalidad")})
public class MatriculaHistorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_matricula_historial")
    private Integer idMatriculaHistorial;
    @Size(max = 45)
    @Column(name = "idioma_ancestral")
    private String idiomaAncestral;
    @Size(max = 255)
    @Column(name = "descripcion_idioma_ancestral")
    private String descripcionIdiomaAncestral;
    @Size(max = 45)
    @Column(name = "pais_nacionalidad")
    private String paisNacionalidad;
    @Size(max = 45)
    @Column(name = "pais_residencia")
    private String paisResidencia;
    @Size(max = 45)
    @Column(name = "posee_discapacidad")
    private String poseeDiscapacidad;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @Size(max = 45)
    @Column(name = "participacion_practicas")
    private String participacionPracticas;
    @Size(max = 45)
    @Column(name = "participacion_vinculacion")
    private String participacionVinculacion;
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @Size(max = 45)
    @Column(name = "anio_graduacion")
    private String anioGraduacion;
    @Column(name = "modalidad")
    private Integer modalidad;
    @Column(name = "fecha_inicio_carrera")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioCarrera;
    @Size(max = 45)
    @Column(name = "titulo_educacion_superior")
    private String tituloEducacionSuperior;
    @Size(max = 60)
    @Column(name = "especifique_titulo")
    private String especifiqueTitulo;
    @Size(max = 60)
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
    @Column(name = "bono_desarrollo_humano")
    private Integer bonoDesarrolloHumano;
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
    @Column(name = "numero_formulario")
    private Integer numeroFormulario;
    @Column(name = "codigo_matricula")
    private Integer codigoMatricula;
    @Size(max = 45)
    @Column(name = "nombre_contacto")
    private String nombreContacto;
    @Size(max = 45)
    @Column(name = "apellido_contacto")
    private String apellidoContacto;
    @Size(max = 45)
    @Column(name = "celular_contacto")
    private String celularContacto;
    @Size(max = 45)
    @Column(name = "parentesco_contacto")
    private String parentescoContacto;
    @Size(max = 45)
    @Column(name = "num_carnet_conadis")
    private String numCarnetConadis;
    @Size(max = 45)
    @Column(name = "porcentaje_discapacidad")
    private String porcentajeDiscapacidad;
    @Size(max = 45)
    @Column(name = "descripcion_discapacidad")
    private String descripcionDiscapacidad;
    @Size(max = 45)
    @Column(name = "nro_horas_practicasPreprosionales")
    private String nrohoraspracticasPreprosionales;
    @Size(max = 45)
    @Column(name = "categoria_migratoria")
    private String categoriaMigratoria;
    @Size(max = 45)
    @Column(name = "provincia_residencia")
    private String provinciaResidencia;
    @Size(max = 45)
    @Column(name = "canton_residencia")
    private String cantonResidencia;
    @Size(max = 45)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne
    private EstadoCivil idEstadoCivil;
    @JoinColumn(name = "id_alcance_vinculacion", referencedColumnName = "id_alcance_vinculacion")
    @ManyToOne
    private AlcanceVinculacion idAlcanceVinculacion;
    @JoinColumn(name = "id_tipo_institucion_practicasPreprofesionales", referencedColumnName = "id_tipo_institucion_practicaspreprofesionales")
    @ManyToOne
    private TipoInstitucionPracticas idtipoinstitucionpracticasPreprofesionales;
    @JoinColumn(name = "id_tipo_carrera", referencedColumnName = "id_tipo_carrera")
    @ManyToOne
    private TipoCarrera idTipoCarrera;
    @JoinColumn(name = "id_canton_nacimiento", referencedColumnName = "id_canton")
    @ManyToOne
    private Canton idCantonNacimiento;
    @JoinColumn(name = "id_residencia", referencedColumnName = "id_residencia")
    @ManyToOne
    private Residencia idResidencia;
    @JoinColumn(name = "id_tipo_discapacidad", referencedColumnName = "id_tipo_discapacidad")
    @ManyToOne
    private TipoDiscapacidad idTipoDiscapacidad;
    @JoinColumn(name = "id_tipo_matricula", referencedColumnName = "id_tipo_matricula")
    @ManyToOne
    private TipoMatricula idTipoMatricula;
    @JoinColumn(name = "id_sector_economico_empresa", referencedColumnName = "id_sector_economico_empresa")
    @ManyToOne
    private SectorEconomicoEmpresa idSectorEconomicoEmpresa;
    @JoinColumn(name = "id_tipo_bachillerato", referencedColumnName = "id_tipo_bachillerato")
    @ManyToOne
    private TipoBachillerato idTipoBachillerato;
    @JoinColumn(name = "id_tipo_colegio", referencedColumnName = "id_tipo_colegio")
    @ManyToOne
    private TipoColegio idTipoColegio;
    @JoinColumn(name = "id_tipo_sangre", referencedColumnName = "id_tipo_sangre")
    @ManyToOne
    private TipoSangre idTipoSangre;
    @JoinColumn(name = "id_periodo_academico", referencedColumnName = "id_periodo_academico")
    @ManyToOne
    private PeriodoAcademico idPeriodoAcademico;
    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne
    private TipoIdentificacion idTipoIdentificacion;
    @JoinColumn(name = "id_titulo_carrera", referencedColumnName = "id_titulo_carrera")
    @ManyToOne
    private TituloCarrera idTituloCarrera;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @JoinColumn(name = "id_etnia", referencedColumnName = "id_etnia")
    @ManyToOne
    private Etnia idEtnia;
    @JoinColumn(name = "id_formacion_madre", referencedColumnName = "id_formacion_academica_madre")
    @ManyToOne
    private FormacionAcademicaMadre idFormacionMadre;
    @JoinColumn(name = "id_formacion_padre", referencedColumnName = "id_formacion_academica")
    @ManyToOne
    private FormacionAcademica idFormacionPadre;
    @JoinColumn(name = "id_jornada_academica", referencedColumnName = "id_jornada_academica")
    @ManyToOne
    private JornadaAcademica idJornadaAcademica;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne
    private Matricula idMatricula;
    @JoinColumn(name = "id_tipo_operador", referencedColumnName = "id_tipo_operador")
    @ManyToOne
    private TipoOperador idTipoOperador;
    @JoinColumn(name = "id_provincia_residencia", referencedColumnName = "id_p_residencia")
    @ManyToOne
    private PResidencia idProvinciaResidencia;
    @JoinColumn(name = "id_sector_practicasPreprofesionales", referencedColumnName = "id_sector_economico_empresa")
    @ManyToOne
    private SectorEconomicoEmpresa idsectorpracticasPreprofesionales;
    @JoinColumn(name = "id_provincia_nacimiento", referencedColumnName = "id_provincia")
    @ManyToOne
    private Provincia idProvinciaNacimiento;
    @JoinColumn(name = "id_nacionalidad", referencedColumnName = "id_nacionalidad")
    @ManyToOne
    private Nacionalidad idNacionalidad;
    @JoinColumn(name = "id_paralelo", referencedColumnName = "id_paralelo")
    @ManyToOne
    private Paralelo idParalelo;

    public MatriculaHistorial() {
    }

    public MatriculaHistorial(Integer idMatriculaHistorial) {
        this.idMatriculaHistorial = idMatriculaHistorial;
    }

    public Integer getIdMatriculaHistorial() {
        return idMatriculaHistorial;
    }

    public void setIdMatriculaHistorial(Integer idMatriculaHistorial) {
        this.idMatriculaHistorial = idMatriculaHistorial;
    }

    public String getIdiomaAncestral() {
        return idiomaAncestral;
    }

    public void setIdiomaAncestral(String idiomaAncestral) {
        this.idiomaAncestral = idiomaAncestral;
    }

    public String getDescripcionIdiomaAncestral() {
        return descripcionIdiomaAncestral;
    }

    public void setDescripcionIdiomaAncestral(String descripcionIdiomaAncestral) {
        this.descripcionIdiomaAncestral = descripcionIdiomaAncestral;
    }

    public String getPaisNacionalidad() {
        return paisNacionalidad;
    }

    public void setPaisNacionalidad(String paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }

    public String getPaisResidencia() {
        return paisResidencia;
    }

    public void setPaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }

    public String getPoseeDiscapacidad() {
        return poseeDiscapacidad;
    }

    public void setPoseeDiscapacidad(String poseeDiscapacidad) {
        this.poseeDiscapacidad = poseeDiscapacidad;
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

    public String getParticipacionPracticas() {
        return participacionPracticas;
    }

    public void setParticipacionPracticas(String participacionPracticas) {
        this.participacionPracticas = participacionPracticas;
    }

    public String getParticipacionVinculacion() {
        return participacionVinculacion;
    }

    public void setParticipacionVinculacion(String participacionVinculacion) {
        this.participacionVinculacion = participacionVinculacion;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
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

    public Date getFechaInicioCarrera() {
        return fechaInicioCarrera;
    }

    public void setFechaInicioCarrera(Date fechaInicioCarrera) {
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

    public Integer getBonoDesarrolloHumano() {
        return bonoDesarrolloHumano;
    }

    public void setBonoDesarrolloHumano(Integer bonoDesarrolloHumano) {
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

    public Integer getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(Integer numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public Integer getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(Integer codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public String getCelularContacto() {
        return celularContacto;
    }

    public void setCelularContacto(String celularContacto) {
        this.celularContacto = celularContacto;
    }

    public String getParentescoContacto() {
        return parentescoContacto;
    }

    public void setParentescoContacto(String parentescoContacto) {
        this.parentescoContacto = parentescoContacto;
    }

    public String getNumCarnetConadis() {
        return numCarnetConadis;
    }

    public void setNumCarnetConadis(String numCarnetConadis) {
        this.numCarnetConadis = numCarnetConadis;
    }

    public String getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }

    public void setPorcentajeDiscapacidad(String porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }

    public String getDescripcionDiscapacidad() {
        return descripcionDiscapacidad;
    }

    public void setDescripcionDiscapacidad(String descripcionDiscapacidad) {
        this.descripcionDiscapacidad = descripcionDiscapacidad;
    }

    public String getNrohoraspracticasPreprosionales() {
        return nrohoraspracticasPreprosionales;
    }

    public void setNrohoraspracticasPreprosionales(String nrohoraspracticasPreprosionales) {
        this.nrohoraspracticasPreprosionales = nrohoraspracticasPreprosionales;
    }

    public String getCategoriaMigratoria() {
        return categoriaMigratoria;
    }

    public void setCategoriaMigratoria(String categoriaMigratoria) {
        this.categoriaMigratoria = categoriaMigratoria;
    }

    public String getProvinciaResidencia() {
        return provinciaResidencia;
    }

    public void setProvinciaResidencia(String provinciaResidencia) {
        this.provinciaResidencia = provinciaResidencia;
    }

    public String getCantonResidencia() {
        return cantonResidencia;
    }

    public void setCantonResidencia(String cantonResidencia) {
        this.cantonResidencia = cantonResidencia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public EstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public AlcanceVinculacion getIdAlcanceVinculacion() {
        return idAlcanceVinculacion;
    }

    public void setIdAlcanceVinculacion(AlcanceVinculacion idAlcanceVinculacion) {
        this.idAlcanceVinculacion = idAlcanceVinculacion;
    }

    public TipoInstitucionPracticas getIdtipoinstitucionpracticasPreprofesionales() {
        return idtipoinstitucionpracticasPreprofesionales;
    }

    public void setIdtipoinstitucionpracticasPreprofesionales(TipoInstitucionPracticas idtipoinstitucionpracticasPreprofesionales) {
        this.idtipoinstitucionpracticasPreprofesionales = idtipoinstitucionpracticasPreprofesionales;
    }

    public TipoCarrera getIdTipoCarrera() {
        return idTipoCarrera;
    }

    public void setIdTipoCarrera(TipoCarrera idTipoCarrera) {
        this.idTipoCarrera = idTipoCarrera;
    }

    public Canton getIdCantonNacimiento() {
        return idCantonNacimiento;
    }

    public void setIdCantonNacimiento(Canton idCantonNacimiento) {
        this.idCantonNacimiento = idCantonNacimiento;
    }

    public Residencia getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(Residencia idResidencia) {
        this.idResidencia = idResidencia;
    }

    public TipoDiscapacidad getIdTipoDiscapacidad() {
        return idTipoDiscapacidad;
    }

    public void setIdTipoDiscapacidad(TipoDiscapacidad idTipoDiscapacidad) {
        this.idTipoDiscapacidad = idTipoDiscapacidad;
    }

    public TipoMatricula getIdTipoMatricula() {
        return idTipoMatricula;
    }

    public void setIdTipoMatricula(TipoMatricula idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    public SectorEconomicoEmpresa getIdSectorEconomicoEmpresa() {
        return idSectorEconomicoEmpresa;
    }

    public void setIdSectorEconomicoEmpresa(SectorEconomicoEmpresa idSectorEconomicoEmpresa) {
        this.idSectorEconomicoEmpresa = idSectorEconomicoEmpresa;
    }

    public TipoBachillerato getIdTipoBachillerato() {
        return idTipoBachillerato;
    }

    public void setIdTipoBachillerato(TipoBachillerato idTipoBachillerato) {
        this.idTipoBachillerato = idTipoBachillerato;
    }

    public TipoColegio getIdTipoColegio() {
        return idTipoColegio;
    }

    public void setIdTipoColegio(TipoColegio idTipoColegio) {
        this.idTipoColegio = idTipoColegio;
    }

    public TipoSangre getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(TipoSangre idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    public PeriodoAcademico getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(PeriodoAcademico idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public TipoIdentificacion getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(TipoIdentificacion idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public TituloCarrera getIdTituloCarrera() {
        return idTituloCarrera;
    }

    public void setIdTituloCarrera(TituloCarrera idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public Etnia getIdEtnia() {
        return idEtnia;
    }

    public void setIdEtnia(Etnia idEtnia) {
        this.idEtnia = idEtnia;
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

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Matricula getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matricula idMatricula) {
        this.idMatricula = idMatricula;
    }

    public TipoOperador getIdTipoOperador() {
        return idTipoOperador;
    }

    public void setIdTipoOperador(TipoOperador idTipoOperador) {
        this.idTipoOperador = idTipoOperador;
    }

    public PResidencia getIdProvinciaResidencia() {
        return idProvinciaResidencia;
    }

    public void setIdProvinciaResidencia(PResidencia idProvinciaResidencia) {
        this.idProvinciaResidencia = idProvinciaResidencia;
    }

    public SectorEconomicoEmpresa getIdsectorpracticasPreprofesionales() {
        return idsectorpracticasPreprofesionales;
    }

    public void setIdsectorpracticasPreprofesionales(SectorEconomicoEmpresa idsectorpracticasPreprofesionales) {
        this.idsectorpracticasPreprofesionales = idsectorpracticasPreprofesionales;
    }

    public Provincia getIdProvinciaNacimiento() {
        return idProvinciaNacimiento;
    }

    public void setIdProvinciaNacimiento(Provincia idProvinciaNacimiento) {
        this.idProvinciaNacimiento = idProvinciaNacimiento;
    }

    public Nacionalidad getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Nacionalidad idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public Paralelo getIdParalelo() {
        return idParalelo;
    }

    public void setIdParalelo(Paralelo idParalelo) {
        this.idParalelo = idParalelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatriculaHistorial != null ? idMatriculaHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaHistorial)) {
            return false;
        }
        MatriculaHistorial other = (MatriculaHistorial) object;
        if ((this.idMatriculaHistorial == null && other.idMatriculaHistorial != null) || (this.idMatriculaHistorial != null && !this.idMatriculaHistorial.equals(other.idMatriculaHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.MatriculaHistorial[ idMatriculaHistorial=" + idMatriculaHistorial + " ]";
    }
    
}

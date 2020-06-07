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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "persona", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Persona.findByIdiomaAncestral", query = "SELECT p FROM Persona p WHERE p.idiomaAncestral = :idiomaAncestral")
    , @NamedQuery(name = "Persona.findByDescripcionIdiomaAncestral", query = "SELECT p FROM Persona p WHERE p.descripcionIdiomaAncestral = :descripcionIdiomaAncestral")
    , @NamedQuery(name = "Persona.findByPaisNacionalidad", query = "SELECT p FROM Persona p WHERE p.paisNacionalidad = :paisNacionalidad")
    , @NamedQuery(name = "Persona.findByPaisResidencia", query = "SELECT p FROM Persona p WHERE p.paisResidencia = :paisResidencia")
    , @NamedQuery(name = "Persona.findByPoseeDiscapacidad", query = "SELECT p FROM Persona p WHERE p.poseeDiscapacidad = :poseeDiscapacidad")
    , @NamedQuery(name = "Persona.findByEstado", query = "SELECT p FROM Persona p WHERE p.estado = :estado")
    , @NamedQuery(name = "Persona.findByFechaDeRegistro", query = "SELECT p FROM Persona p WHERE p.fechaDeRegistro = :fechaDeRegistro")
    , @NamedQuery(name = "Persona.findByParticipacionPracticas", query = "SELECT p FROM Persona p WHERE p.participacionPracticas = :participacionPracticas")
    , @NamedQuery(name = "Persona.findByParticipacionVinculacion", query = "SELECT p FROM Persona p WHERE p.participacionVinculacion = :participacionVinculacion")})
public class Persona implements Serializable {

    @Column(name = "estado")
    private Integer estado;
    @OneToMany(mappedBy = "idPersona")
    private List<FormalizarMatricula> formalizarMatriculaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
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
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @Size(max = 45)
    @Column(name = "participacion_practicas")
    private String participacionPracticas;
    @Size(max = 45)
    @Column(name = "participacion_vinculacion")
    private String participacionVinculacion;
    @OneToMany(mappedBy = "idPersona")
    private List<Notas> notasList;
    @JoinColumn(name = "id_canton_nacimiento", referencedColumnName = "id_canton")
    @ManyToOne
    private Canton idCantonNacimiento;
    @JoinColumn(name = "id_contacto_emergencia", referencedColumnName = "id_contacto")
    @ManyToOne(optional = false)
    private ContactoEmergencia idContactoEmergencia;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne(optional = false)
    private TipoIdentificacion idTipoIdentificacion;
    @JoinColumn(name = "id_tipo_sangre", referencedColumnName = "id_tipo_sangre")
    @ManyToOne(optional = false)
    private TipoSangre idTipoSangre;
    @JoinColumn(name = "id_tipo_operador", referencedColumnName = "id_tipo_operador")
    @ManyToOne(optional = false)
    private TipoOperador idTipoOperador;
    @JoinColumn(name = "id_discapacidad", referencedColumnName = "id_discapacidad")
    @ManyToOne(optional = false)
    private Discapacidad idDiscapacidad;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(optional = false)
    private EstadoCivil idEstadoCivil;
    @JoinColumn(name = "id_etnia", referencedColumnName = "id_etnia")
    @ManyToOne(optional = false)
    private Etnia idEtnia;
    @JoinColumn(name = "id_nacionalidad", referencedColumnName = "id_nacionalidad")
    @ManyToOne(optional = false)
    private Nacionalidad idNacionalidad;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_provincia_residencia", referencedColumnName = "id_p_residencia")
    @ManyToOne
    private PResidencia idProvinciaResidencia;
    @JoinColumn(name = "id_provincia_nacimiento", referencedColumnName = "id_provincia")
    @ManyToOne
    private Provincia idProvinciaNacimiento;
    @JoinColumn(name = "id_residencia", referencedColumnName = "id_residencia")
    @ManyToOne
    private Residencia idResidencia;
    @OneToMany(mappedBy = "idPersona")
    private List<Matricula> matriculaList;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, int estado) {
        this.idPersona = idPersona;
        this.estado = estado;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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

    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    public Canton getIdCantonNacimiento() {
        return idCantonNacimiento;
    }

    public void setIdCantonNacimiento(Canton idCantonNacimiento) {
        this.idCantonNacimiento = idCantonNacimiento;
    }

    public ContactoEmergencia getIdContactoEmergencia() {
        return idContactoEmergencia;
    }

    public void setIdContactoEmergencia(ContactoEmergencia idContactoEmergencia) {
        this.idContactoEmergencia = idContactoEmergencia;
    }

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public TipoIdentificacion getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(TipoIdentificacion idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public TipoSangre getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(TipoSangre idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    public TipoOperador getIdTipoOperador() {
        return idTipoOperador;
    }

    public void setIdTipoOperador(TipoOperador idTipoOperador) {
        this.idTipoOperador = idTipoOperador;
    }

    public Discapacidad getIdDiscapacidad() {
        return idDiscapacidad;
    }

    public void setIdDiscapacidad(Discapacidad idDiscapacidad) {
        this.idDiscapacidad = idDiscapacidad;
    }

    public EstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Etnia getIdEtnia() {
        return idEtnia;
    }

    public void setIdEtnia(Etnia idEtnia) {
        this.idEtnia = idEtnia;
    }

    public Nacionalidad getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Nacionalidad idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public PResidencia getIdProvinciaResidencia() {
        return idProvinciaResidencia;
    }

    public void setIdProvinciaResidencia(PResidencia idProvinciaResidencia) {
        this.idProvinciaResidencia = idProvinciaResidencia;
    }

    public Provincia getIdProvinciaNacimiento() {
        return idProvinciaNacimiento;
    }

    public void setIdProvinciaNacimiento(Provincia idProvinciaNacimiento) {
        this.idProvinciaNacimiento = idProvinciaNacimiento;
    }

    public Residencia getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(Residencia idResidencia) {
        this.idResidencia = idResidencia;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.idDatosPersonales.getApellidos()+" "+this.idDatosPersonales.getNombres();
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<FormalizarMatricula> getFormalizarMatriculaList() {
        return formalizarMatriculaList;
    }

    public void setFormalizarMatriculaList(List<FormalizarMatricula> formalizarMatriculaList) {
        this.formalizarMatriculaList = formalizarMatriculaList;
    }
    
}

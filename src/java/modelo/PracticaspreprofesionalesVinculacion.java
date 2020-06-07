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

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "practicaspreprofesionales_vinculacion", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "PracticaspreprofesionalesVinculacion.findAll", query = "SELECT p FROM PracticaspreprofesionalesVinculacion p")
    , @NamedQuery(name = "PracticaspreprofesionalesVinculacion.findByIdpracticasPreprofesionalesvinculacion", query = "SELECT p FROM PracticaspreprofesionalesVinculacion p WHERE p.idpracticasPreprofesionalesvinculacion = :idpracticasPreprofesionalesvinculacion")
    , @NamedQuery(name = "PracticaspreprofesionalesVinculacion.findByNrohoraspracticasPreprosionales", query = "SELECT p FROM PracticaspreprofesionalesVinculacion p WHERE p.nrohoraspracticasPreprosionales = :nrohoraspracticasPreprosionales")
    , @NamedQuery(name = "PracticaspreprofesionalesVinculacion.findByFechaDeRegistro", query = "SELECT p FROM PracticaspreprofesionalesVinculacion p WHERE p.fechaDeRegistro = :fechaDeRegistro")
    , @NamedQuery(name = "PracticaspreprofesionalesVinculacion.findByEstado", query = "SELECT p FROM PracticaspreprofesionalesVinculacion p WHERE p.estado = :estado")})
public class PracticaspreprofesionalesVinculacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_practicasPreprofesionales_vinculacion")
    private Integer idpracticasPreprofesionalesvinculacion;
    @Size(max = 45)
    @Column(name = "nro_horas_practicasPreprosionales")
    private String nrohoraspracticasPreprosionales;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(mappedBy = "idpracticaspreProfesionalesvinculacion")
    private List<Matricula> matriculaList;
    @JoinColumn(name = "id_alcance_vinculacion", referencedColumnName = "id_alcance_vinculacion")
    @ManyToOne
    private AlcanceVinculacion idAlcanceVinculacion;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @JoinColumn(name = "id_sector_practicasPreprofesionales", referencedColumnName = "id_sector_economico")
    @ManyToOne
    private SectorEconomico idsectorpracticasPreprofesionales;
    @JoinColumn(name = "id_tipo_institucion_practicasPreprofesionales", referencedColumnName = "id_tipo_institucion_practicaspreprofesionales")
    @ManyToOne
    private TipoInstitucionPracticas idtipoinstitucionpracticasPreprofesionales;

    public PracticaspreprofesionalesVinculacion() {
    }

    public PracticaspreprofesionalesVinculacion(Integer idpracticasPreprofesionalesvinculacion) {
        this.idpracticasPreprofesionalesvinculacion = idpracticasPreprofesionalesvinculacion;
    }

    public Integer getIdpracticasPreprofesionalesvinculacion() {
        return idpracticasPreprofesionalesvinculacion;
    }

    public void setIdpracticasPreprofesionalesvinculacion(Integer idpracticasPreprofesionalesvinculacion) {
        this.idpracticasPreprofesionalesvinculacion = idpracticasPreprofesionalesvinculacion;
    }

    public String getNrohoraspracticasPreprosionales() {
        return nrohoraspracticasPreprosionales;
    }

    public void setNrohoraspracticasPreprosionales(String nrohoraspracticasPreprosionales) {
        this.nrohoraspracticasPreprosionales = nrohoraspracticasPreprosionales;
    }

    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public AlcanceVinculacion getIdAlcanceVinculacion() {
        return idAlcanceVinculacion;
    }

    public void setIdAlcanceVinculacion(AlcanceVinculacion idAlcanceVinculacion) {
        this.idAlcanceVinculacion = idAlcanceVinculacion;
    }

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public SectorEconomico getIdsectorpracticasPreprofesionales() {
        return idsectorpracticasPreprofesionales;
    }

    public void setIdsectorpracticasPreprofesionales(SectorEconomico idsectorpracticasPreprofesionales) {
        this.idsectorpracticasPreprofesionales = idsectorpracticasPreprofesionales;
    }

    public TipoInstitucionPracticas getIdtipoinstitucionpracticasPreprofesionales() {
        return idtipoinstitucionpracticasPreprofesionales;
    }

    public void setIdtipoinstitucionpracticasPreprofesionales(TipoInstitucionPracticas idtipoinstitucionpracticasPreprofesionales) {
        this.idtipoinstitucionpracticasPreprofesionales = idtipoinstitucionpracticasPreprofesionales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpracticasPreprofesionalesvinculacion != null ? idpracticasPreprofesionalesvinculacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PracticaspreprofesionalesVinculacion)) {
            return false;
        }
        PracticaspreprofesionalesVinculacion other = (PracticaspreprofesionalesVinculacion) object;
        if ((this.idpracticasPreprofesionalesvinculacion == null && other.idpracticasPreprofesionalesvinculacion != null) || (this.idpracticasPreprofesionalesvinculacion != null && !this.idpracticasPreprofesionalesvinculacion.equals(other.idpracticasPreprofesionalesvinculacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.idDatosPersonales.getNombres()+""+this.idDatosPersonales.getApellidos();
    }
    
}

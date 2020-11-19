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
@Table(name = "tipo_institucion_practicas", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoInstitucionPracticas.findAll", query = "SELECT t FROM TipoInstitucionPracticas t")
    , @NamedQuery(name = "TipoInstitucionPracticas.findByIdTipoInstitucionPracticaspreprofesionales", query = "SELECT t FROM TipoInstitucionPracticas t WHERE t.idTipoInstitucionPracticaspreprofesionales = :idTipoInstitucionPracticaspreprofesionales")
    , @NamedQuery(name = "TipoInstitucionPracticas.findByNombreTipo", query = "SELECT t FROM TipoInstitucionPracticas t WHERE t.nombreTipo = :nombreTipo")
    , @NamedQuery(name = "TipoInstitucionPracticas.findByEstado", query = "SELECT t FROM TipoInstitucionPracticas t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoInstitucionPracticas.findByFechaDeRegistro", query = "SELECT t FROM TipoInstitucionPracticas t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoInstitucionPracticas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_institucion_practicaspreprofesionales")
    private Integer idTipoInstitucionPracticaspreprofesionales;
    @Size(max = 45)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idtipoinstitucionpracticasPreprofesionales")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idtipoinstitucionpracticasPreprofesionales")
    private List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList;
    @OneToMany(mappedBy = "idtipoinstitucionpracticasPreprofesionales")
    private List<MatriculaHistorial> matriculaHistorialList;

    public TipoInstitucionPracticas() {
    }

    public TipoInstitucionPracticas(Integer idTipoInstitucionPracticaspreprofesionales) {
        this.idTipoInstitucionPracticaspreprofesionales = idTipoInstitucionPracticaspreprofesionales;
    }

    public Integer getIdTipoInstitucionPracticaspreprofesionales() {
        return idTipoInstitucionPracticaspreprofesionales;
    }

    public void setIdTipoInstitucionPracticaspreprofesionales(Integer idTipoInstitucionPracticaspreprofesionales) {
        this.idTipoInstitucionPracticaspreprofesionales = idTipoInstitucionPracticaspreprofesionales;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
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
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @XmlTransient
    public List<PracticaspreprofesionalesVinculacion> getPracticaspreprofesionalesVinculacionList() {
        return practicaspreprofesionalesVinculacionList;
    }

    public void setPracticaspreprofesionalesVinculacionList(List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList) {
        this.practicaspreprofesionalesVinculacionList = practicaspreprofesionalesVinculacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoInstitucionPracticaspreprofesionales != null ? idTipoInstitucionPracticaspreprofesionales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInstitucionPracticas)) {
            return false;
        }
        TipoInstitucionPracticas other = (TipoInstitucionPracticas) object;
        if ((this.idTipoInstitucionPracticaspreprofesionales == null && other.idTipoInstitucionPracticaspreprofesionales != null) || (this.idTipoInstitucionPracticaspreprofesionales != null && !this.idTipoInstitucionPracticaspreprofesionales.equals(other.idTipoInstitucionPracticaspreprofesionales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreTipo;
    }
    
}

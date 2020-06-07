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

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "alcance_vinculacion", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "AlcanceVinculacion.findAll", query = "SELECT a FROM AlcanceVinculacion a")
    , @NamedQuery(name = "AlcanceVinculacion.findByIdAlcanceVinculacion", query = "SELECT a FROM AlcanceVinculacion a WHERE a.idAlcanceVinculacion = :idAlcanceVinculacion")
    , @NamedQuery(name = "AlcanceVinculacion.findByAlcance", query = "SELECT a FROM AlcanceVinculacion a WHERE a.alcance = :alcance")
    , @NamedQuery(name = "AlcanceVinculacion.findByEstado", query = "SELECT a FROM AlcanceVinculacion a WHERE a.estado = :estado")
    , @NamedQuery(name = "AlcanceVinculacion.findByFechaDeRegistro", query = "SELECT a FROM AlcanceVinculacion a WHERE a.fechaDeRegistro = :fechaDeRegistro")})
public class AlcanceVinculacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alcance_vinculacion")
    private Integer idAlcanceVinculacion;
    @Size(max = 45)
    @Column(name = "alcance")
    private String alcance;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idAlcanceVinculacion")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idAlcanceVinculacion")
    private List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList;

    public AlcanceVinculacion() {
    }

    public AlcanceVinculacion(Integer idAlcanceVinculacion) {
        this.idAlcanceVinculacion = idAlcanceVinculacion;
    }

    public Integer getIdAlcanceVinculacion() {
        return idAlcanceVinculacion;
    }

    public void setIdAlcanceVinculacion(Integer idAlcanceVinculacion) {
        this.idAlcanceVinculacion = idAlcanceVinculacion;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
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

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public List<PracticaspreprofesionalesVinculacion> getPracticaspreprofesionalesVinculacionList() {
        return practicaspreprofesionalesVinculacionList;
    }

    public void setPracticaspreprofesionalesVinculacionList(List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList) {
        this.practicaspreprofesionalesVinculacionList = practicaspreprofesionalesVinculacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlcanceVinculacion != null ? idAlcanceVinculacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlcanceVinculacion)) {
            return false;
        }
        AlcanceVinculacion other = (AlcanceVinculacion) object;
        if ((this.idAlcanceVinculacion == null && other.idAlcanceVinculacion != null) || (this.idAlcanceVinculacion != null && !this.idAlcanceVinculacion.equals(other.idAlcanceVinculacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.alcance;
    }
    
}

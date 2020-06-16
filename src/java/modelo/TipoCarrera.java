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
@Table(name = "tipo_carrera", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCarrera.findAll", query = "SELECT t FROM TipoCarrera t")
    , @NamedQuery(name = "TipoCarrera.findByIdTipoCarrera", query = "SELECT t FROM TipoCarrera t WHERE t.idTipoCarrera = :idTipoCarrera")
    , @NamedQuery(name = "TipoCarrera.findByNombre", query = "SELECT t FROM TipoCarrera t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoCarrera.findByEstado", query = "SELECT t FROM TipoCarrera t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoCarrera.findByFechaDeRegistro", query = "SELECT t FROM TipoCarrera t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoCarrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_carrera")
    private Integer idTipoCarrera;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTipoCarrera")
    private List<Matricula> matriculaList;

    public TipoCarrera() {
    }

    public TipoCarrera(Integer idTipoCarrera) {
        this.idTipoCarrera = idTipoCarrera;
    }

    public Integer getIdTipoCarrera() {
        return idTipoCarrera;
    }

    public void setIdTipoCarrera(Integer idTipoCarrera) {
        this.idTipoCarrera = idTipoCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCarrera != null ? idTipoCarrera.hashCode() : 0);
        return hash;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCarrera)) {
            return false;
        }
        TipoCarrera other = (TipoCarrera) object;
        if ((this.idTipoCarrera == null && other.idTipoCarrera != null) || (this.idTipoCarrera != null && !this.idTipoCarrera.equals(other.idTipoCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}

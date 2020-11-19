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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "tipo_matricula", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMatricula.findAll", query = "SELECT t FROM TipoMatricula t")
    , @NamedQuery(name = "TipoMatricula.findByIdTipoMatricula", query = "SELECT t FROM TipoMatricula t WHERE t.idTipoMatricula = :idTipoMatricula")
    , @NamedQuery(name = "TipoMatricula.findByTipo", query = "SELECT t FROM TipoMatricula t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoMatricula.findByDescripcion", query = "SELECT t FROM TipoMatricula t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoMatricula.findByEstado", query = "SELECT t FROM TipoMatricula t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoMatricula.findByFechaDeRegistro", query = "SELECT t FROM TipoMatricula t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoMatricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_matricula")
    private Integer idTipoMatricula;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTipoMatricula")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idTipoMatricula")
    private List<MatriculaHistorial> matriculaHistorialList;
 
    public TipoMatricula() {
    }

    public TipoMatricula(Integer idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    public TipoMatricula(Integer idTipoMatricula, int estado) {
        this.idTipoMatricula = idTipoMatricula;
        this.estado = estado;
    }

    public Integer getIdTipoMatricula() {
        return idTipoMatricula;
    }

    public void setIdTipoMatricula(Integer idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMatricula != null ? idTipoMatricula.hashCode() : 0);
        return hash;
    }

  

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMatricula)) {
            return false;
        }
        TipoMatricula other = (TipoMatricula) object;
        if ((this.idTipoMatricula == null && other.idTipoMatricula != null) || (this.idTipoMatricula != null && !this.idTipoMatricula.equals(other.idTipoMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
    
}

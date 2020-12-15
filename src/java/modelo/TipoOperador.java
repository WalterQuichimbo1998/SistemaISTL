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
@Table(name = "tipo_operador", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOperador.findAll", query = "SELECT t FROM TipoOperador t")
    , @NamedQuery(name = "TipoOperador.findByIdTipoOperador", query = "SELECT t FROM TipoOperador t WHERE t.idTipoOperador = :idTipoOperador")
    , @NamedQuery(name = "TipoOperador.findByOperador", query = "SELECT t FROM TipoOperador t WHERE t.operador = :operador")
    , @NamedQuery(name = "TipoOperador.findByEstado", query = "SELECT t FROM TipoOperador t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoOperador.findByFechaDeRegistro", query = "SELECT t FROM TipoOperador t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoOperador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_operador")
    private Integer idTipoOperador;
    @Size(max = 45)
    @Column(name = "operador")
    private String operador;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTipoOperador")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idTipoOperador")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "idTipoOperador")
    private List<MatriculaHistorial> matriculaHistorialList;

    public TipoOperador() {
    }

    public TipoOperador(Integer idTipoOperador) {
        this.idTipoOperador = idTipoOperador;
    }

    public Integer getIdTipoOperador() {
        return idTipoOperador;
    }

    public void setIdTipoOperador(Integer idTipoOperador) {
        this.idTipoOperador = idTipoOperador;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoOperador != null ? idTipoOperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOperador)) {
            return false;
        }
        TipoOperador other = (TipoOperador) object;
        if ((this.idTipoOperador == null && other.idTipoOperador != null) || (this.idTipoOperador != null && !this.idTipoOperador.equals(other.idTipoOperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return operador;
    }
    
}

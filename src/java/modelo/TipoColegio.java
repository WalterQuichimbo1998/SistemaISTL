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
@Table(name = "tipo_colegio", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoColegio.findAll", query = "SELECT t FROM TipoColegio t")
    , @NamedQuery(name = "TipoColegio.findByIdTipoColegio", query = "SELECT t FROM TipoColegio t WHERE t.idTipoColegio = :idTipoColegio")
    , @NamedQuery(name = "TipoColegio.findByTipo", query = "SELECT t FROM TipoColegio t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoColegio.findByObservacion", query = "SELECT t FROM TipoColegio t WHERE t.observacion = :observacion")
    , @NamedQuery(name = "TipoColegio.findByEstado", query = "SELECT t FROM TipoColegio t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoColegio.findByFechaDeRegistro", query = "SELECT t FROM TipoColegio t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoColegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_colegio")
    private Integer idTipoColegio;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTipoColegio")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idTipoColegio")
    private List<MatriculaHistorial> matriculaHistorialList;

    public TipoColegio() {
    }

    public TipoColegio(Integer idTipoColegio) {
        this.idTipoColegio = idTipoColegio;
    }

    public Integer getIdTipoColegio() {
        return idTipoColegio;
    }

    public void setIdTipoColegio(Integer idTipoColegio) {
        this.idTipoColegio = idTipoColegio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoColegio != null ? idTipoColegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoColegio)) {
            return false;
        }
        TipoColegio other = (TipoColegio) object;
        if ((this.idTipoColegio == null && other.idTipoColegio != null) || (this.idTipoColegio != null && !this.idTipoColegio.equals(other.idTipoColegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
}

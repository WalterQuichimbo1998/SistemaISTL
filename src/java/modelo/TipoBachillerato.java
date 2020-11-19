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
@Table(name = "tipo_bachillerato", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBachillerato.findAll", query = "SELECT t FROM TipoBachillerato t")
    , @NamedQuery(name = "TipoBachillerato.findByIdTipoBachillerato", query = "SELECT t FROM TipoBachillerato t WHERE t.idTipoBachillerato = :idTipoBachillerato")
    , @NamedQuery(name = "TipoBachillerato.findByTipo", query = "SELECT t FROM TipoBachillerato t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoBachillerato.findByEstado", query = "SELECT t FROM TipoBachillerato t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoBachillerato.findByFechaDeRegistro", query = "SELECT t FROM TipoBachillerato t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoBachillerato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_bachillerato")
    private Integer idTipoBachillerato;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;   
    @OneToMany(mappedBy = "idTipoBachillerato")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idTipoBachillerato")
    private List<MatriculaHistorial> matriculaHistorialList;

    public TipoBachillerato() {
    }

    public TipoBachillerato(Integer idTipoBachillerato) {
        this.idTipoBachillerato = idTipoBachillerato;
    }

    public TipoBachillerato(Integer idTipoBachillerato, int estado) {
        this.idTipoBachillerato = idTipoBachillerato;
        this.estado = estado;
    }

    public Integer getIdTipoBachillerato() {
        return idTipoBachillerato;
    }

    public void setIdTipoBachillerato(Integer idTipoBachillerato) {
        this.idTipoBachillerato = idTipoBachillerato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }
    @XmlTransient
    public List<MatriculaHistorial> getMatriculaHistorialList() {
        return matriculaHistorialList;
    }

    public void setMatriculaHistorialList(List<MatriculaHistorial> matriculaHistorialList) {
        this.matriculaHistorialList = matriculaHistorialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBachillerato != null ? idTipoBachillerato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBachillerato)) {
            return false;
        }
        TipoBachillerato other = (TipoBachillerato) object;
        if ((this.idTipoBachillerato == null && other.idTipoBachillerato != null) || (this.idTipoBachillerato != null && !this.idTipoBachillerato.equals(other.idTipoBachillerato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
    
}

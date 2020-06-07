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
@Table(name = "tipo_tiempo", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoTiempo.findAll", query = "SELECT t FROM TipoTiempo t")
    , @NamedQuery(name = "TipoTiempo.findByIdTipoTiempo", query = "SELECT t FROM TipoTiempo t WHERE t.idTipoTiempo = :idTipoTiempo")
    , @NamedQuery(name = "TipoTiempo.findByTipo", query = "SELECT t FROM TipoTiempo t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoTiempo.findByEstado", query = "SELECT t FROM TipoTiempo t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoTiempo.findByFechaDeRegistro", query = "SELECT t FROM TipoTiempo t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoTiempo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_tiempo")
    private Integer idTipoTiempo;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTipoTiempo")
    private List<Distributivo> distributivoList;

    public TipoTiempo() {
    }

    public TipoTiempo(Integer idTipoTiempo) {
        this.idTipoTiempo = idTipoTiempo;
    }

    public Integer getIdTipoTiempo() {
        return idTipoTiempo;
    }

    public void setIdTipoTiempo(Integer idTipoTiempo) {
        this.idTipoTiempo = idTipoTiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTiempo != null ? idTipoTiempo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTiempo)) {
            return false;
        }
        TipoTiempo other = (TipoTiempo) object;
        if ((this.idTipoTiempo == null && other.idTipoTiempo != null) || (this.idTipoTiempo != null && !this.idTipoTiempo.equals(other.idTipoTiempo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
    
}

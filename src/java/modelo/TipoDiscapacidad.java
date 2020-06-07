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

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "tipo_discapacidad", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoDiscapacidad.findAll", query = "SELECT t FROM TipoDiscapacidad t")
    , @NamedQuery(name = "TipoDiscapacidad.findByIdTipoDiscapacidad", query = "SELECT t FROM TipoDiscapacidad t WHERE t.idTipoDiscapacidad = :idTipoDiscapacidad")
    , @NamedQuery(name = "TipoDiscapacidad.findByDiscapacidad", query = "SELECT t FROM TipoDiscapacidad t WHERE t.discapacidad = :discapacidad")
    , @NamedQuery(name = "TipoDiscapacidad.findByObservacion", query = "SELECT t FROM TipoDiscapacidad t WHERE t.observacion = :observacion")
    , @NamedQuery(name = "TipoDiscapacidad.findByEstado", query = "SELECT t FROM TipoDiscapacidad t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoDiscapacidad.findByFechaDeRegistro", query = "SELECT t FROM TipoDiscapacidad t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoDiscapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_discapacidad")
    private Integer idTipoDiscapacidad;
    @Size(max = 45)
    @Column(name = "discapacidad")
    private String discapacidad;
    @Size(max = 45)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idTipoDiscapacidad")
    private List<Discapacidad> discapacidadList;

    public TipoDiscapacidad() {
    }

    public TipoDiscapacidad(Integer idTipoDiscapacidad) {
        this.idTipoDiscapacidad = idTipoDiscapacidad;
    }

    public TipoDiscapacidad(Integer idTipoDiscapacidad, int estado) {
        this.idTipoDiscapacidad = idTipoDiscapacidad;
        this.estado = estado;
    }

    public Integer getIdTipoDiscapacidad() {
        return idTipoDiscapacidad;
    }

    public void setIdTipoDiscapacidad(Integer idTipoDiscapacidad) {
        this.idTipoDiscapacidad = idTipoDiscapacidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public List<Discapacidad> getDiscapacidadList() {
        return discapacidadList;
    }

    public void setDiscapacidadList(List<Discapacidad> discapacidadList) {
        this.discapacidadList = discapacidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDiscapacidad != null ? idTipoDiscapacidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDiscapacidad)) {
            return false;
        }
        TipoDiscapacidad other = (TipoDiscapacidad) object;
        if ((this.idTipoDiscapacidad == null && other.idTipoDiscapacidad != null) || (this.idTipoDiscapacidad != null && !this.idTipoDiscapacidad.equals(other.idTipoDiscapacidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.discapacidad;
    }
    
}

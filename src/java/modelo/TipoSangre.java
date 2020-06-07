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
import javax.persistence.CascadeType;
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
@Table(name = "tipo_sangre", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoSangre.findAll", query = "SELECT t FROM TipoSangre t")
    , @NamedQuery(name = "TipoSangre.findByIdTipoSangre", query = "SELECT t FROM TipoSangre t WHERE t.idTipoSangre = :idTipoSangre")
    , @NamedQuery(name = "TipoSangre.findByTipo", query = "SELECT t FROM TipoSangre t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoSangre.findByEstado", query = "SELECT t FROM TipoSangre t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoSangre.findByFechaDeRegistro", query = "SELECT t FROM TipoSangre t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TipoSangre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_sangre")
    private Integer idTipoSangre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoSangre")
    private List<Persona> personaList;

    public TipoSangre() {
    }

    public TipoSangre(Integer idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    public TipoSangre(Integer idTipoSangre, int estado) {
        this.idTipoSangre = idTipoSangre;
        this.estado = estado;
    }

    public Integer getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(Integer idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
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

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSangre != null ? idTipoSangre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSangre)) {
            return false;
        }
        TipoSangre other = (TipoSangre) object;
        if ((this.idTipoSangre == null && other.idTipoSangre != null) || (this.idTipoSangre != null && !this.idTipoSangre.equals(other.idTipoSangre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
    
}

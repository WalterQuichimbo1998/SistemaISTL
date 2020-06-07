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
import javax.validation.constraints.Size;

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "tipo_operador", catalog = "sistema_gestion", schema = "")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoOperador")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idTipoOperador")
    private List<Usuario> usuarioList;

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

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

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
        return this.operador;
    }
    
}

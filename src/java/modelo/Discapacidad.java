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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "discapacidad", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "Discapacidad.findAll", query = "SELECT d FROM Discapacidad d")
    , @NamedQuery(name = "Discapacidad.findByIdDiscapacidad", query = "SELECT d FROM Discapacidad d WHERE d.idDiscapacidad = :idDiscapacidad")
    , @NamedQuery(name = "Discapacidad.findByNumCarnetConadis", query = "SELECT d FROM Discapacidad d WHERE d.numCarnetConadis = :numCarnetConadis")
    , @NamedQuery(name = "Discapacidad.findByPorcentajeDiscapacidad", query = "SELECT d FROM Discapacidad d WHERE d.porcentajeDiscapacidad = :porcentajeDiscapacidad")
    , @NamedQuery(name = "Discapacidad.findByDescripcion", query = "SELECT d FROM Discapacidad d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Discapacidad.findByEstado", query = "SELECT d FROM Discapacidad d WHERE d.estado = :estado")
    , @NamedQuery(name = "Discapacidad.findByFechaDeRegistro", query = "SELECT d FROM Discapacidad d WHERE d.fechaDeRegistro = :fechaDeRegistro")})
public class Discapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_discapacidad")
    private Integer idDiscapacidad;
    @Size(max = 45)
    @Column(name = "num_carnet_conadis")
    private String numCarnetConadis;
    @Size(max = 45)
    @Column(name = "porcentaje_discapacidad")
    private String porcentajeDiscapacidad;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDiscapacidad")
    private List<Persona> personaList;
    @JoinColumn(name = "id_tipo_discapacidad", referencedColumnName = "id_tipo_discapacidad")
    @ManyToOne
    private TipoDiscapacidad idTipoDiscapacidad;

    public Discapacidad() {
    }

    public Discapacidad(Integer idDiscapacidad) {
        this.idDiscapacidad = idDiscapacidad;
    }

    public Integer getIdDiscapacidad() {
        return idDiscapacidad;
    }

    public void setIdDiscapacidad(Integer idDiscapacidad) {
        this.idDiscapacidad = idDiscapacidad;
    }

    public String getNumCarnetConadis() {
        return numCarnetConadis;
    }

    public void setNumCarnetConadis(String numCarnetConadis) {
        this.numCarnetConadis = numCarnetConadis;
    }

    public String getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }

    public void setPorcentajeDiscapacidad(String porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public TipoDiscapacidad getIdTipoDiscapacidad() {
        return idTipoDiscapacidad;
    }

    public void setIdTipoDiscapacidad(TipoDiscapacidad idTipoDiscapacidad) {
        this.idTipoDiscapacidad = idTipoDiscapacidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiscapacidad != null ? idDiscapacidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discapacidad)) {
            return false;
        }
        Discapacidad other = (Discapacidad) object;
        if ((this.idDiscapacidad == null && other.idDiscapacidad != null) || (this.idDiscapacidad != null && !this.idDiscapacidad.equals(other.idDiscapacidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.numCarnetConadis;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "materia_carrera", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaCarrera.findAll", query = "SELECT m FROM MateriaCarrera m")
    , @NamedQuery(name = "MateriaCarrera.findByIdMateriaCarrera", query = "SELECT m FROM MateriaCarrera m WHERE m.idMateriaCarrera = :idMateriaCarrera")
    , @NamedQuery(name = "MateriaCarrera.findByEstado", query = "SELECT m FROM MateriaCarrera m WHERE m.estado = :estado")
    , @NamedQuery(name = "MateriaCarrera.findByFechaDeRegistro", query = "SELECT m FROM MateriaCarrera m WHERE m.fechaDeRegistro = :fechaDeRegistro")})
public class MateriaCarrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia_carrera")
    private Integer idMateriaCarrera;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "id_titulo_carrera", referencedColumnName = "id_titulo_carrera")
    @ManyToOne
    private TituloCarrera idTituloCarrera;

    public MateriaCarrera() {
    }

    public MateriaCarrera(Integer idMateriaCarrera) {
        this.idMateriaCarrera = idMateriaCarrera;
    }

    public Integer getIdMateriaCarrera() {
        return idMateriaCarrera;
    }

    public void setIdMateriaCarrera(Integer idMateriaCarrera) {
        this.idMateriaCarrera = idMateriaCarrera;
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

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public TituloCarrera getIdTituloCarrera() {
        return idTituloCarrera;
    }

    public void setIdTituloCarrera(TituloCarrera idTituloCarrera) {
        this.idTituloCarrera = idTituloCarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaCarrera != null ? idMateriaCarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaCarrera)) {
            return false;
        }
        MateriaCarrera other = (MateriaCarrera) object;
        if ((this.idMateriaCarrera == null && other.idMateriaCarrera != null) || (this.idMateriaCarrera != null && !this.idMateriaCarrera.equals(other.idMateriaCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.MateriaCarrera[ idMateriaCarrera=" + idMateriaCarrera + " ]";
    }
    
}

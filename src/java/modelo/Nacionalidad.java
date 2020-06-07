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
@Table(name = "nacionalidad", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "Nacionalidad.findAll", query = "SELECT n FROM Nacionalidad n")
    , @NamedQuery(name = "Nacionalidad.findByIdNacionalidad", query = "SELECT n FROM Nacionalidad n WHERE n.idNacionalidad = :idNacionalidad")
    , @NamedQuery(name = "Nacionalidad.findByTipoNacionalidad", query = "SELECT n FROM Nacionalidad n WHERE n.tipoNacionalidad = :tipoNacionalidad")
    , @NamedQuery(name = "Nacionalidad.findByCategoriaMigratoria", query = "SELECT n FROM Nacionalidad n WHERE n.categoriaMigratoria = :categoriaMigratoria")
    , @NamedQuery(name = "Nacionalidad.findByEstado", query = "SELECT n FROM Nacionalidad n WHERE n.estado = :estado")
    , @NamedQuery(name = "Nacionalidad.findByFechaDeRegistro", query = "SELECT n FROM Nacionalidad n WHERE n.fechaDeRegistro = :fechaDeRegistro")})
public class Nacionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nacionalidad")
    private Integer idNacionalidad;
    @Size(max = 45)
    @Column(name = "tipo_nacionalidad")
    private String tipoNacionalidad;
    @Size(max = 45)
    @Column(name = "categoria_migratoria")
    private String categoriaMigratoria;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    @ManyToOne
    private Canton idCanton;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    @ManyToOne
    private Provincia idProvincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNacionalidad")
    private List<Persona> personaList;

    public Nacionalidad() {
    }

    public Nacionalidad(Integer idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public Integer getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Integer idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getTipoNacionalidad() {
        return tipoNacionalidad;
    }

    public void setTipoNacionalidad(String tipoNacionalidad) {
        this.tipoNacionalidad = tipoNacionalidad;
    }

    public String getCategoriaMigratoria() {
        return categoriaMigratoria;
    }

    public void setCategoriaMigratoria(String categoriaMigratoria) {
        this.categoriaMigratoria = categoriaMigratoria;
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

    public Canton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Canton idCanton) {
        this.idCanton = idCanton;
    }

    public Provincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincia idProvincia) {
        this.idProvincia = idProvincia;
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
        hash += (idNacionalidad != null ? idNacionalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalidad)) {
            return false;
        }
        Nacionalidad other = (Nacionalidad) object;
        if ((this.idNacionalidad == null && other.idNacionalidad != null) || (this.idNacionalidad != null && !this.idNacionalidad.equals(other.idNacionalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipoNacionalidad;
    }
    
}

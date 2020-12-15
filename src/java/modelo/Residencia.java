/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "residencia", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Residencia.findAll", query = "SELECT r FROM Residencia r")
    , @NamedQuery(name = "Residencia.findByIdResidencia", query = "SELECT r FROM Residencia r WHERE r.idResidencia = :idResidencia")
    , @NamedQuery(name = "Residencia.findByNombre", query = "SELECT r FROM Residencia r WHERE r.nombre = :nombre")})
public class Residencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_residencia")
    private Integer idResidencia;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idResidencia")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idResidencia")
    private List<MatriculaHistorial> matriculaHistorialList;

    public Residencia() {
    }

    public Residencia(Integer idResidencia) {
        this.idResidencia = idResidencia;
    }

    public Integer getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(Integer idResidencia) {
        this.idResidencia = idResidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idResidencia != null ? idResidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Residencia)) {
            return false;
        }
        Residencia other = (Residencia) object;
        if ((this.idResidencia == null && other.idResidencia != null) || (this.idResidencia != null && !this.idResidencia.equals(other.idResidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

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
@Table(name = "etnia", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etnia.findAll", query = "SELECT e FROM Etnia e")
    , @NamedQuery(name = "Etnia.findByIdEtnia", query = "SELECT e FROM Etnia e WHERE e.idEtnia = :idEtnia")
    , @NamedQuery(name = "Etnia.findByTipoEtnia", query = "SELECT e FROM Etnia e WHERE e.tipoEtnia = :tipoEtnia")
    , @NamedQuery(name = "Etnia.findByEstado", query = "SELECT e FROM Etnia e WHERE e.estado = :estado")
    , @NamedQuery(name = "Etnia.findByFechaDeRegistro", query = "SELECT e FROM Etnia e WHERE e.fechaDeRegistro = :fechaDeRegistro")})
public class Etnia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etnia")
    private Integer idEtnia;
    @Size(max = 45)
    @Column(name = "tipo_etnia")
    private String tipoEtnia;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idEtnia")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idEtnia")
    private List<MatriculaHistorial> matriculaHistorialList;

    public Etnia() {
    }

    public Etnia(Integer idEtnia) {
        this.idEtnia = idEtnia;
    }

    public Integer getIdEtnia() {
        return idEtnia;
    }

    public void setIdEtnia(Integer idEtnia) {
        this.idEtnia = idEtnia;
    }

    public String getTipoEtnia() {
        return tipoEtnia;
    }

    public void setTipoEtnia(String tipoEtnia) {
        this.tipoEtnia = tipoEtnia;
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
        hash += (idEtnia != null ? idEtnia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etnia)) {
            return false;
        }
        Etnia other = (Etnia) object;
        if ((this.idEtnia == null && other.idEtnia != null) || (this.idEtnia != null && !this.idEtnia.equals(other.idEtnia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoEtnia;
    }
    
}

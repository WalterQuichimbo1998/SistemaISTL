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
@Table(name = "paralelo", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paralelo.findAll", query = "SELECT p FROM Paralelo p")
    , @NamedQuery(name = "Paralelo.findByIdParalelo", query = "SELECT p FROM Paralelo p WHERE p.idParalelo = :idParalelo")
    , @NamedQuery(name = "Paralelo.findByNombreParalelo", query = "SELECT p FROM Paralelo p WHERE p.nombreParalelo = :nombreParalelo")
    , @NamedQuery(name = "Paralelo.findByEstado", query = "SELECT p FROM Paralelo p WHERE p.estado = :estado")
    , @NamedQuery(name = "Paralelo.findByFechaDeRegistro", query = "SELECT p FROM Paralelo p WHERE p.fechaDeRegistro = :fechaDeRegistro")})
public class Paralelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paralelo")
    private Integer idParalelo;
    @Size(max = 45)
    @Column(name = "nombre_paralelo")
    private String nombreParalelo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idParalelo")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idParalelo")
    private List<Matricula> matriculaList;

    public Paralelo() {
    }

    public Paralelo(Integer idParalelo) {
        this.idParalelo = idParalelo;
    }

    public Integer getIdParalelo() {
        return idParalelo;
    }

    public void setIdParalelo(Integer idParalelo) {
        this.idParalelo = idParalelo;
    }

    public String getNombreParalelo() {
        return nombreParalelo;
    }

    public void setNombreParalelo(String nombreParalelo) {
        this.nombreParalelo = nombreParalelo;
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
    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
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
        hash += (idParalelo != null ? idParalelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paralelo)) {
            return false;
        }
        Paralelo other = (Paralelo) object;
        if ((this.idParalelo == null && other.idParalelo != null) || (this.idParalelo != null && !this.idParalelo.equals(other.idParalelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreParalelo;
    }
    
}

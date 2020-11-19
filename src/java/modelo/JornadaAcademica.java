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
@Table(name = "jornada_academica", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JornadaAcademica.findAll", query = "SELECT j FROM JornadaAcademica j")
    , @NamedQuery(name = "JornadaAcademica.findByIdJornadaAcademica", query = "SELECT j FROM JornadaAcademica j WHERE j.idJornadaAcademica = :idJornadaAcademica")
    , @NamedQuery(name = "JornadaAcademica.findByTipoJornada", query = "SELECT j FROM JornadaAcademica j WHERE j.tipoJornada = :tipoJornada")
    , @NamedQuery(name = "JornadaAcademica.findByEstado", query = "SELECT j FROM JornadaAcademica j WHERE j.estado = :estado")
    , @NamedQuery(name = "JornadaAcademica.findByFechaDeRegistro", query = "SELECT j FROM JornadaAcademica j WHERE j.fechaDeRegistro = :fechaDeRegistro")})
public class JornadaAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jornada_academica")
    private Integer idJornadaAcademica;
    @Size(max = 45)
    @Column(name = "tipo_jornada")
    private String tipoJornada;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idJornadaAcademica")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idJornadaAcademica")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idJornadaAcademica")
    private List<MatriculaHistorial> matriculaHistorialList;

    public JornadaAcademica() {
    }

    public JornadaAcademica(Integer idJornadaAcademica) {
        this.idJornadaAcademica = idJornadaAcademica;
    }

    public Integer getIdJornadaAcademica() {
        return idJornadaAcademica;
    }

    public void setIdJornadaAcademica(Integer idJornadaAcademica) {
        this.idJornadaAcademica = idJornadaAcademica;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
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
        hash += (idJornadaAcademica != null ? idJornadaAcademica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JornadaAcademica)) {
            return false;
        }
        JornadaAcademica other = (JornadaAcademica) object;
        if ((this.idJornadaAcademica == null && other.idJornadaAcademica != null) || (this.idJornadaAcademica != null && !this.idJornadaAcademica.equals(other.idJornadaAcademica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoJornada;
    }
    
}

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
@Table(name = "formacion_academica", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormacionAcademica.findAll", query = "SELECT f FROM FormacionAcademica f")
    , @NamedQuery(name = "FormacionAcademica.findByIdFormacionAcademica", query = "SELECT f FROM FormacionAcademica f WHERE f.idFormacionAcademica = :idFormacionAcademica")
    , @NamedQuery(name = "FormacionAcademica.findByNivel", query = "SELECT f FROM FormacionAcademica f WHERE f.nivel = :nivel")
    , @NamedQuery(name = "FormacionAcademica.findByEstado", query = "SELECT f FROM FormacionAcademica f WHERE f.estado = :estado")
    , @NamedQuery(name = "FormacionAcademica.findByFechaDeRegistro", query = "SELECT f FROM FormacionAcademica f WHERE f.fechaDeRegistro = :fechaDeRegistro")})
public class FormacionAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formacion_academica")
    private Integer idFormacionAcademica;
    @Size(max = 45)
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idFormacionPadre")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idFormacionPadre")
    private List<MatriculaHistorial> matriculaHistorialList;

    public FormacionAcademica() {
    }

    public FormacionAcademica(Integer idFormacionAcademica) {
        this.idFormacionAcademica = idFormacionAcademica;
    }

    public Integer getIdFormacionAcademica() {
        return idFormacionAcademica;
    }

    public void setIdFormacionAcademica(Integer idFormacionAcademica) {
        this.idFormacionAcademica = idFormacionAcademica;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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
        hash += (idFormacionAcademica != null ? idFormacionAcademica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormacionAcademica)) {
            return false;
        }
        FormacionAcademica other = (FormacionAcademica) object;
        if ((this.idFormacionAcademica == null && other.idFormacionAcademica != null) || (this.idFormacionAcademica != null && !this.idFormacionAcademica.equals(other.idFormacionAcademica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nivel;
    }

}

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

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "formacion_academica_madre", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "FormacionAcademicaMadre.findAll", query = "SELECT f FROM FormacionAcademicaMadre f")
    , @NamedQuery(name = "FormacionAcademicaMadre.findByIdFormacionAcademicaMadre", query = "SELECT f FROM FormacionAcademicaMadre f WHERE f.idFormacionAcademicaMadre = :idFormacionAcademicaMadre")
    , @NamedQuery(name = "FormacionAcademicaMadre.findByNivel", query = "SELECT f FROM FormacionAcademicaMadre f WHERE f.nivel = :nivel")
    , @NamedQuery(name = "FormacionAcademicaMadre.findByEstado", query = "SELECT f FROM FormacionAcademicaMadre f WHERE f.estado = :estado")
    , @NamedQuery(name = "FormacionAcademicaMadre.findByFechaDeRegistro", query = "SELECT f FROM FormacionAcademicaMadre f WHERE f.fechaDeRegistro = :fechaDeRegistro")})
public class FormacionAcademicaMadre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formacion_academica_madre")
    private Integer idFormacionAcademicaMadre;
    @Size(max = 45)
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idFormacionMadre")
    private List<Matricula> matriculaList;

    public FormacionAcademicaMadre() {
    }

    public FormacionAcademicaMadre(Integer idFormacionAcademicaMadre) {
        this.idFormacionAcademicaMadre = idFormacionAcademicaMadre;
    }

    public Integer getIdFormacionAcademicaMadre() {
        return idFormacionAcademicaMadre;
    }

    public void setIdFormacionAcademicaMadre(Integer idFormacionAcademicaMadre) {
        this.idFormacionAcademicaMadre = idFormacionAcademicaMadre;
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

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormacionAcademicaMadre != null ? idFormacionAcademicaMadre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormacionAcademicaMadre)) {
            return false;
        }
        FormacionAcademicaMadre other = (FormacionAcademicaMadre) object;
        if ((this.idFormacionAcademicaMadre == null && other.idFormacionAcademicaMadre != null) || (this.idFormacionAcademicaMadre != null && !this.idFormacionAcademicaMadre.equals(other.idFormacionAcademicaMadre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nivel;
    }
    
}

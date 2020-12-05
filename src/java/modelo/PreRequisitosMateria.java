/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CyberMás
 */
@Entity
@Table(name = "pre_requisitos_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreRequisitosMateria.findAll", query = "SELECT p FROM PreRequisitosMateria p")
    , @NamedQuery(name = "PreRequisitosMateria.findByIdPreRequisitosMateria", query = "SELECT p FROM PreRequisitosMateria p WHERE p.idPreRequisitosMateria = :idPreRequisitosMateria")})
public class PreRequisitosMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pre_requisitos_materia")
    private Integer idPreRequisitosMateria;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "id_materia_pre", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateriaPre;

    public PreRequisitosMateria() {
    }

    public PreRequisitosMateria(Integer idPreRequisitosMateria) {
        this.idPreRequisitosMateria = idPreRequisitosMateria;
    }

    public Integer getIdPreRequisitosMateria() {
        return idPreRequisitosMateria;
    }

    public void setIdPreRequisitosMateria(Integer idPreRequisitosMateria) {
        this.idPreRequisitosMateria = idPreRequisitosMateria;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Materia getIdMateriaPre() {
        return idMateriaPre;
    }

    public void setIdMateriaPre(Materia idMateriaPre) {
        this.idMateriaPre = idMateriaPre;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPreRequisitosMateria != null ? idPreRequisitosMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreRequisitosMateria)) {
            return false;
        }
        PreRequisitosMateria other = (PreRequisitosMateria) object;
        if ((this.idPreRequisitosMateria == null && other.idPreRequisitosMateria != null) || (this.idPreRequisitosMateria != null && !this.idPreRequisitosMateria.equals(other.idPreRequisitosMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PreRequisitosMateria[ idPreRequisitosMateria=" + idPreRequisitosMateria + " ]";
    }
    
}

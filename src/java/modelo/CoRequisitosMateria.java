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
@Table(name = "co_requisitos_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoRequisitosMateria.findAll", query = "SELECT c FROM CoRequisitosMateria c")
    , @NamedQuery(name = "CoRequisitosMateria.findByIdCoRequisitosMateria", query = "SELECT c FROM CoRequisitosMateria c WHERE c.idCoRequisitosMateria = :idCoRequisitosMateria")})
public class CoRequisitosMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_co_requisitos_materia")
    private Integer idCoRequisitosMateria;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "id_materia_co", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateriaCo;

    public CoRequisitosMateria() {
    }

    public CoRequisitosMateria(Integer idCoRequisitosMateria) {
        this.idCoRequisitosMateria = idCoRequisitosMateria;
    }

    public Integer getIdCoRequisitosMateria() {
        return idCoRequisitosMateria;
    }

    public void setIdCoRequisitosMateria(Integer idCoRequisitosMateria) {
        this.idCoRequisitosMateria = idCoRequisitosMateria;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Materia getIdMateriaCo() {
        return idMateriaCo;
    }

    public void setIdMateriaCo(Materia idMateriaCo) {
        this.idMateriaCo = idMateriaCo;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoRequisitosMateria != null ? idCoRequisitosMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoRequisitosMateria)) {
            return false;
        }
        CoRequisitosMateria other = (CoRequisitosMateria) object;
        if ((this.idCoRequisitosMateria == null && other.idCoRequisitosMateria != null) || (this.idCoRequisitosMateria != null && !this.idCoRequisitosMateria.equals(other.idCoRequisitosMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CoRequisitosMateria[ idCoRequisitosMateria=" + idCoRequisitosMateria + " ]";
    }
    
}

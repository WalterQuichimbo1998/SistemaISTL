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
@Table(name = "distributivo_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistributivoMateria.findAll", query = "SELECT d FROM DistributivoMateria d")
    , @NamedQuery(name = "DistributivoMateria.findByIdDistributivoMateria", query = "SELECT d FROM DistributivoMateria d WHERE d.idDistributivoMateria = :idDistributivoMateria")})
public class DistributivoMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distributivo_materia")
    private Integer idDistributivoMateria;
    @JoinColumn(name = "id_distributivo", referencedColumnName = "id_distributivo")
    @ManyToOne
    private Distributivo idDistributivo;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_titulo_carrera", referencedColumnName = "id_titulo_carrera")
    @ManyToOne
    private TituloCarrera idTituloCarrera;

    public DistributivoMateria() {
    }

    public DistributivoMateria(Integer idDistributivoMateria) {
        this.idDistributivoMateria = idDistributivoMateria;
    }

    public Integer getIdDistributivoMateria() {
        return idDistributivoMateria;
    }

    public void setIdDistributivoMateria(Integer idDistributivoMateria) {
        this.idDistributivoMateria = idDistributivoMateria;
    }

    public Distributivo getIdDistributivo() {
        return idDistributivo;
    }

    public void setIdDistributivo(Distributivo idDistributivo) {
        this.idDistributivo = idDistributivo;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
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
        hash += (idDistributivoMateria != null ? idDistributivoMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistributivoMateria)) {
            return false;
        }
        DistributivoMateria other = (DistributivoMateria) object;
        if ((this.idDistributivoMateria == null && other.idDistributivoMateria != null) || (this.idDistributivoMateria != null && !this.idDistributivoMateria.equals(other.idDistributivoMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DistributivoMateria[ idDistributivoMateria=" + idDistributivoMateria + " ]";
    }
    
}

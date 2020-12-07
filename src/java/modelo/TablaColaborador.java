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
@Table(name = "tabla_colaborador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablaColaborador.findAll", query = "SELECT t FROM TablaColaborador t")
    , @NamedQuery(name = "TablaColaborador.findByIdTablaColaborador", query = "SELECT t FROM TablaColaborador t WHERE t.idTablaColaborador = :idTablaColaborador")})
public class TablaColaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabla_colaborador")
    private Integer idTablaColaborador;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @JoinColumn(name = "id_tabla_investigacion", referencedColumnName = "id_tabla_investigacion")
    @ManyToOne
    private TablaInvestigacion idTablaInvestigacion;

    public TablaColaborador() {
    }

    public TablaColaborador(Integer idTablaColaborador) {
        this.idTablaColaborador = idTablaColaborador;
    }

    public Integer getIdTablaColaborador() {
        return idTablaColaborador;
    }

    public void setIdTablaColaborador(Integer idTablaColaborador) {
        this.idTablaColaborador = idTablaColaborador;
    }

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public TablaInvestigacion getIdTablaInvestigacion() {
        return idTablaInvestigacion;
    }

    public void setIdTablaInvestigacion(TablaInvestigacion idTablaInvestigacion) {
        this.idTablaInvestigacion = idTablaInvestigacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablaColaborador != null ? idTablaColaborador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaColaborador)) {
            return false;
        }
        TablaColaborador other = (TablaColaborador) object;
        if ((this.idTablaColaborador == null && other.idTablaColaborador != null) || (this.idTablaColaborador != null && !this.idTablaColaborador.equals(other.idTablaColaborador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TablaColaborador[ idTablaColaborador=" + idTablaColaborador + " ]";
    }
    
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tabla_investigacion", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablaInvestigacion.findAll", query = "SELECT t FROM TablaInvestigacion t")
    , @NamedQuery(name = "TablaInvestigacion.findByIdTablaInvestigacion", query = "SELECT t FROM TablaInvestigacion t WHERE t.idTablaInvestigacion = :idTablaInvestigacion")
    , @NamedQuery(name = "TablaInvestigacion.findByHoraSemanaInvestigacion", query = "SELECT t FROM TablaInvestigacion t WHERE t.horaSemanaInvestigacion = :horaSemanaInvestigacion")
    , @NamedQuery(name = "TablaInvestigacion.findByNombreProyectoInvestigacion", query = "SELECT t FROM TablaInvestigacion t WHERE t.nombreProyectoInvestigacion = :nombreProyectoInvestigacion")
    , @NamedQuery(name = "TablaInvestigacion.findByEstado", query = "SELECT t FROM TablaInvestigacion t WHERE t.estado = :estado")
    , @NamedQuery(name = "TablaInvestigacion.findByFechaDeRegistro", query = "SELECT t FROM TablaInvestigacion t WHERE t.fechaDeRegistro = :fechaDeRegistro")})
public class TablaInvestigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabla_investigacion")
    private Integer idTablaInvestigacion;
    @Size(max = 45)
    @Column(name = "hora_semana_investigacion")
    private String horaSemanaInvestigacion;
    @Size(max = 45)
    @Column(name = "nombre_proyecto_investigacion")
    private String nombreProyectoInvestigacion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @OneToMany(mappedBy = "idTablaInvestigacion")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idTablaInvestigacion")
    private List<TablaColaborador> tablaColaboradorList;

    public TablaInvestigacion() {
    }

    public TablaInvestigacion(Integer idTablaInvestigacion) {
        this.idTablaInvestigacion = idTablaInvestigacion;
    }

    public Integer getIdTablaInvestigacion() {
        return idTablaInvestigacion;
    }

    public void setIdTablaInvestigacion(Integer idTablaInvestigacion) {
        this.idTablaInvestigacion = idTablaInvestigacion;
    }

    public String getHoraSemanaInvestigacion() {
        return horaSemanaInvestigacion;
    }

    public void setHoraSemanaInvestigacion(String horaSemanaInvestigacion) {
        this.horaSemanaInvestigacion = horaSemanaInvestigacion;
    }

    public String getNombreProyectoInvestigacion() {
        return nombreProyectoInvestigacion;
    }

    public void setNombreProyectoInvestigacion(String nombreProyectoInvestigacion) {
        this.nombreProyectoInvestigacion = nombreProyectoInvestigacion;
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

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    @XmlTransient
    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
    }
     @XmlTransient
    public List<TablaColaborador> getTablaColaboradorList() {
        return tablaColaboradorList;
    }

    public void setTablaColaboradorList(List<TablaColaborador> tablaColaboradorList) {
        this.tablaColaboradorList = tablaColaboradorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablaInvestigacion != null ? idTablaInvestigacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaInvestigacion)) {
            return false;
        }
        TablaInvestigacion other = (TablaInvestigacion) object;
        if ((this.idTablaInvestigacion == null && other.idTablaInvestigacion != null) || (this.idTablaInvestigacion != null && !this.idTablaInvestigacion.equals(other.idTablaInvestigacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreProyectoInvestigacion;
    }
    
}

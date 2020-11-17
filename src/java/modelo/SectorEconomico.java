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
@Table(name = "sector_economico", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SectorEconomico.findAll", query = "SELECT s FROM SectorEconomico s")
    , @NamedQuery(name = "SectorEconomico.findByIdSectorEconomico", query = "SELECT s FROM SectorEconomico s WHERE s.idSectorEconomico = :idSectorEconomico")
    , @NamedQuery(name = "SectorEconomico.findBySectorEconomico", query = "SELECT s FROM SectorEconomico s WHERE s.sectorEconomico = :sectorEconomico")
    , @NamedQuery(name = "SectorEconomico.findByEstado", query = "SELECT s FROM SectorEconomico s WHERE s.estado = :estado")
    , @NamedQuery(name = "SectorEconomico.findByFechaDeRegistro", query = "SELECT s FROM SectorEconomico s WHERE s.fechaDeRegistro = :fechaDeRegistro")})
public class SectorEconomico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sector_economico")
    private Integer idSectorEconomico;
    @Size(max = 45)
    @Column(name = "sector_economico")
    private String sectorEconomico;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idsectorpracticasPreprofesionales")
    private List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList;

    public SectorEconomico() {
    }

    public SectorEconomico(Integer idSectorEconomico) {
        this.idSectorEconomico = idSectorEconomico;
    }

    public Integer getIdSectorEconomico() {
        return idSectorEconomico;
    }

    public void setIdSectorEconomico(Integer idSectorEconomico) {
        this.idSectorEconomico = idSectorEconomico;
    }

    public String getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
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
    public List<PracticaspreprofesionalesVinculacion> getPracticaspreprofesionalesVinculacionList() {
        return practicaspreprofesionalesVinculacionList;
    }

    public void setPracticaspreprofesionalesVinculacionList(List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList) {
        this.practicaspreprofesionalesVinculacionList = practicaspreprofesionalesVinculacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSectorEconomico != null ? idSectorEconomico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SectorEconomico)) {
            return false;
        }
        SectorEconomico other = (SectorEconomico) object;
        if ((this.idSectorEconomico == null && other.idSectorEconomico != null) || (this.idSectorEconomico != null && !this.idSectorEconomico.equals(other.idSectorEconomico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sectorEconomico;
    }
    
}

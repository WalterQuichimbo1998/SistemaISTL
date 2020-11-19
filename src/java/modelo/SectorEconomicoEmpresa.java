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
@Table(name = "sector_economico_empresa", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SectorEconomicoEmpresa.findAll", query = "SELECT s FROM SectorEconomicoEmpresa s")
    , @NamedQuery(name = "SectorEconomicoEmpresa.findByIdSectorEconomicoEmpresa", query = "SELECT s FROM SectorEconomicoEmpresa s WHERE s.idSectorEconomicoEmpresa = :idSectorEconomicoEmpresa")
    , @NamedQuery(name = "SectorEconomicoEmpresa.findByNombre", query = "SELECT s FROM SectorEconomicoEmpresa s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SectorEconomicoEmpresa.findByEstado", query = "SELECT s FROM SectorEconomicoEmpresa s WHERE s.estado = :estado")
    , @NamedQuery(name = "SectorEconomicoEmpresa.findByFechaDeRegistro", query = "SELECT s FROM SectorEconomicoEmpresa s WHERE s.fechaDeRegistro = :fechaDeRegistro")})
public class SectorEconomicoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sector_economico_empresa")
    private Integer idSectorEconomicoEmpresa;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idSectorEconomicoEmpresa")
    private List<Matricula> matriculaList;
    @OneToMany(mappedBy = "idsectorpracticasPreprofesionales")
    private List<Matricula> matriculaList1;
    @OneToMany(mappedBy = "idSectorEconomicoEmpresa")
    private List<MatriculaHistorial> matriculaHistorialList;


    public SectorEconomicoEmpresa() {
    }

    public SectorEconomicoEmpresa(Integer idSectorEconomicoEmpresa) {
        this.idSectorEconomicoEmpresa = idSectorEconomicoEmpresa;
    }

    public Integer getIdSectorEconomicoEmpresa() {
        return idSectorEconomicoEmpresa;
    }

    public void setIdSectorEconomicoEmpresa(Integer idSectorEconomicoEmpresa) {
        this.idSectorEconomicoEmpresa = idSectorEconomicoEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    @XmlTransient
    public List<Matricula> getMatriculaList1() {
        return matriculaList1;
    }

    public void setMatriculaList1(List<Matricula> matriculaList1) {
        this.matriculaList1 = matriculaList1;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSectorEconomicoEmpresa != null ? idSectorEconomicoEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SectorEconomicoEmpresa)) {
            return false;
        }
        SectorEconomicoEmpresa other = (SectorEconomicoEmpresa) object;
        if ((this.idSectorEconomicoEmpresa == null && other.idSectorEconomicoEmpresa != null) || (this.idSectorEconomicoEmpresa != null && !this.idSectorEconomicoEmpresa.equals(other.idSectorEconomicoEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

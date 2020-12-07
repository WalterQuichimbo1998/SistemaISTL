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
@Table(name = "perfil_academico", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilAcademico.findAll", query = "SELECT p FROM PerfilAcademico p")
    , @NamedQuery(name = "PerfilAcademico.findByIdPerfilAcademico", query = "SELECT p FROM PerfilAcademico p WHERE p.idPerfilAcademico = :idPerfilAcademico")
    , @NamedQuery(name = "PerfilAcademico.findByIdDatosPersonales", query = "SELECT p FROM PerfilAcademico p WHERE p.idDatosPersonales = :idDatosPersonales")
    , @NamedQuery(name = "PerfilAcademico.findByTituloContrato", query = "SELECT p FROM PerfilAcademico p WHERE p.tituloContrato = :tituloContrato")
    , @NamedQuery(name = "PerfilAcademico.findByOtrosTitulos", query = "SELECT p FROM PerfilAcademico p WHERE p.otrosTitulos = :otrosTitulos")
    , @NamedQuery(name = "PerfilAcademico.findByGradoOcupacional", query = "SELECT p FROM PerfilAcademico p WHERE p.gradoOcupacional = :gradoOcupacional")
    , @NamedQuery(name = "PerfilAcademico.findByEstado", query = "SELECT p FROM PerfilAcademico p WHERE p.estado = :estado")
    , @NamedQuery(name = "PerfilAcademico.findByFechaDeRegistro", query = "SELECT p FROM PerfilAcademico p WHERE p.fechaDeRegistro = :fechaDeRegistro")})
public class PerfilAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil_academico")
    private Integer idPerfilAcademico;

    @Size(max = 45)
    @Column(name = "titulo_contrato")
    private String tituloContrato;
    @Size(max = 45)
    @Column(name = "otros_titulos")
    private String otrosTitulos;
    @Size(max = 45)
    @Column(name = "grado_ocupacional")
    private String gradoOcupacional;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idPerfilAcademico")
    private List<Distributivo> distributivoList;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    public PerfilAcademico() {
    }

    public PerfilAcademico(Integer idPerfilAcademico) {
        this.idPerfilAcademico = idPerfilAcademico;
    }

    public Integer getIdPerfilAcademico() {
        return idPerfilAcademico;
    }

    public void setIdPerfilAcademico(Integer idPerfilAcademico) {
        this.idPerfilAcademico = idPerfilAcademico;
    }

    public DatosPersonales getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(DatosPersonales idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    

   

    public String getTituloContrato() {
        return tituloContrato;
    }

    public void setTituloContrato(String tituloContrato) {
        this.tituloContrato = tituloContrato;
    }

    public String getOtrosTitulos() {
        return otrosTitulos;
    }

    public void setOtrosTitulos(String otrosTitulos) {
        this.otrosTitulos = otrosTitulos;
    }

    public String getGradoOcupacional() {
        return gradoOcupacional;
    }

    public void setGradoOcupacional(String gradoOcupacional) {
        this.gradoOcupacional = gradoOcupacional;
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
    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfilAcademico != null ? idPerfilAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilAcademico)) {
            return false;
        }
        PerfilAcademico other = (PerfilAcademico) object;
        if ((this.idPerfilAcademico == null && other.idPerfilAcademico != null) || (this.idPerfilAcademico != null && !this.idPerfilAcademico.equals(other.idPerfilAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idDatosPersonales.getNombres()+" -> "+tituloContrato;
    }
    
}

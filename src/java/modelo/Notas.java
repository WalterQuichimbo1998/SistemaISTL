/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "notas", catalog = "sistema_gestion", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notas.findAll", query = "SELECT n FROM Notas n")
    , @NamedQuery(name = "Notas.findByIdNotas", query = "SELECT n FROM Notas n WHERE n.idNotas = :idNotas")
    , @NamedQuery(name = "Notas.findByParcialUno", query = "SELECT n FROM Notas n WHERE n.parcialUno = :parcialUno")
    , @NamedQuery(name = "Notas.findByParcialDos", query = "SELECT n FROM Notas n WHERE n.parcialDos = :parcialDos")
    , @NamedQuery(name = "Notas.findByNotaSupletorio", query = "SELECT n FROM Notas n WHERE n.notaSupletorio = :notaSupletorio")
    , @NamedQuery(name = "Notas.findByNotaRemedial", query = "SELECT n FROM Notas n WHERE n.notaRemedial = :notaRemedial")
    , @NamedQuery(name = "Notas.findByNotaFinal", query = "SELECT n FROM Notas n WHERE n.notaFinal = :notaFinal")
    , @NamedQuery(name = "Notas.findByEstado", query = "SELECT n FROM Notas n WHERE n.estado = :estado")
    , @NamedQuery(name = "Notas.findByFechaDeRegistro", query = "SELECT n FROM Notas n WHERE n.fechaDeRegistro = :fechaDeRegistro")})
public class Notas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notas")
    private Integer idNotas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "parcial_uno")
    private Double parcialUno;
    @Column(name = "parcial_dos")
    private Double parcialDos;
    @Column(name = "nota_supletorio")
    private Double notaSupletorio;
    @Column(name = "nota_remedial")
    private Double notaRemedial;
    @Column(name = "nota_final")
    private Double notaFinal;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id_datos_personales")
    @ManyToOne
    private DatosPersonales idDatosPersonales;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;

    public Notas() {
    }

    public Notas(Integer idNotas) {
        this.idNotas = idNotas;
    }

    public Integer getIdNotas() {
        return idNotas;
    }

    public void setIdNotas(Integer idNotas) {
        this.idNotas = idNotas;
    }

    public Double getParcialUno() {
        return parcialUno;
    }

    public void setParcialUno(Double parcialUno) {
        this.parcialUno = parcialUno;
    }

    public Double getParcialDos() {
        return parcialDos;
    }

    public void setParcialDos(Double parcialDos) {
        this.parcialDos = parcialDos;
    }

    public Double getNotaSupletorio() {
        return notaSupletorio;
    }

    public void setNotaSupletorio(Double notaSupletorio) {
        this.notaSupletorio = notaSupletorio;
    }

    public Double getNotaRemedial() {
        return notaRemedial;
    }

    public void setNotaRemedial(Double notaRemedial) {
        this.notaRemedial = notaRemedial;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
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

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotas != null ? idNotas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.idNotas == null && other.idNotas != null) || (this.idNotas != null && !this.idNotas.equals(other.idNotas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Notas[ idNotas=" + idNotas + " ]";
    }
    
}

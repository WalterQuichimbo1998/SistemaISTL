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
 * @author Lenovo
 */
@Entity
@Table(name = "formalizar_matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormalizarMatricula.findAll", query = "SELECT f FROM FormalizarMatricula f")
    , @NamedQuery(name = "FormalizarMatricula.findByIdFormalizarMatricula", query = "SELECT f FROM FormalizarMatricula f WHERE f.idFormalizarMatricula = :idFormalizarMatricula")
    , @NamedQuery(name = "FormalizarMatricula.findByCodigoMatricula", query = "SELECT f FROM FormalizarMatricula f WHERE f.codigoMatricula = :codigoMatricula")
    , @NamedQuery(name = "FormalizarMatricula.findByFechaHoraRegistro", query = "SELECT f FROM FormalizarMatricula f WHERE f.fechaHoraRegistro = :fechaHoraRegistro")})
public class FormalizarMatricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formalizar_matricula")
    private Integer idFormalizarMatricula;
    @Column(name = "codigo_matricula")
    private Integer codigoMatricula;
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne
    private Matricula idMatricula;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
    @JoinColumn(name = "id_paralelo", referencedColumnName = "id_paralelo")
    @ManyToOne
    private Paralelo idParalelo;
    @JoinColumn(name = "id_periodo_academico", referencedColumnName = "id_periodo_academico")
    @ManyToOne
    private PeriodoAcademico idPeriodoAcademico;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_tipo_matricula", referencedColumnName = "id_tipo_matricula")
    @ManyToOne
    private TipoMatricula idTipoMatricula;

    public FormalizarMatricula() {
    }

    public FormalizarMatricula(Integer idFormalizarMatricula) {
        this.idFormalizarMatricula = idFormalizarMatricula;
    }

    public Integer getIdFormalizarMatricula() {
        return idFormalizarMatricula;
    }

    public void setIdFormalizarMatricula(Integer idFormalizarMatricula) {
        this.idFormalizarMatricula = idFormalizarMatricula;
    }

    public Integer getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(Integer codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public Matricula getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matricula idMatricula) {
        this.idMatricula = idMatricula;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public Paralelo getIdParalelo() {
        return idParalelo;
    }

    public void setIdParalelo(Paralelo idParalelo) {
        this.idParalelo = idParalelo;
    }

    public PeriodoAcademico getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(PeriodoAcademico idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public TipoMatricula getIdTipoMatricula() {
        return idTipoMatricula;
    }

    public void setIdTipoMatricula(TipoMatricula idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormalizarMatricula != null ? idFormalizarMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormalizarMatricula)) {
            return false;
        }
        FormalizarMatricula other = (FormalizarMatricula) object;
        if ((this.idFormalizarMatricula == null && other.idFormalizarMatricula != null) || (this.idFormalizarMatricula != null && !this.idFormalizarMatricula.equals(other.idFormalizarMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FormalizarMatricula[ idFormalizarMatricula=" + idFormalizarMatricula + " ]";
    }
    
}

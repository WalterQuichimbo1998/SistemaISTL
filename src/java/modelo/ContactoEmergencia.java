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
import javax.persistence.CascadeType;
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

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "contacto_emergencia", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "ContactoEmergencia.findAll", query = "SELECT c FROM ContactoEmergencia c")
    , @NamedQuery(name = "ContactoEmergencia.findByIdContacto", query = "SELECT c FROM ContactoEmergencia c WHERE c.idContacto = :idContacto")
    , @NamedQuery(name = "ContactoEmergencia.findByNombres", query = "SELECT c FROM ContactoEmergencia c WHERE c.nombres = :nombres")
    , @NamedQuery(name = "ContactoEmergencia.findByApellidos", query = "SELECT c FROM ContactoEmergencia c WHERE c.apellidos = :apellidos")
    , @NamedQuery(name = "ContactoEmergencia.findByCelular", query = "SELECT c FROM ContactoEmergencia c WHERE c.celular = :celular")
    , @NamedQuery(name = "ContactoEmergencia.findByParentesco", query = "SELECT c FROM ContactoEmergencia c WHERE c.parentesco = :parentesco")
    , @NamedQuery(name = "ContactoEmergencia.findByEstado", query = "SELECT c FROM ContactoEmergencia c WHERE c.estado = :estado")
    , @NamedQuery(name = "ContactoEmergencia.findByFechaDeRegistro", query = "SELECT c FROM ContactoEmergencia c WHERE c.fechaDeRegistro = :fechaDeRegistro")})
public class ContactoEmergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto")
    private Integer idContacto;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "celular")
    private String celular;
    @Size(max = 45)
    @Column(name = "parentesco")
    private String parentesco;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContactoEmergencia")
    private List<Persona> personaList;

    public ContactoEmergencia() {
    }

    public ContactoEmergencia(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContacto != null ? idContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactoEmergencia)) {
            return false;
        }
        ContactoEmergencia other = (ContactoEmergencia) object;
        if ((this.idContacto == null && other.idContacto != null) || (this.idContacto != null && !this.idContacto.equals(other.idContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombres+" "+this.apellidos;
    }
    
}

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

/**
 *
 * @author JANETH
 */
@Entity
@Table(name = "datos_personales", catalog = "sistema_gestion", schema = "")
@NamedQueries({
    @NamedQuery(name = "DatosPersonales.findAll", query = "SELECT d FROM DatosPersonales d")
    , @NamedQuery(name = "DatosPersonales.findByIdDatosPersonales", query = "SELECT d FROM DatosPersonales d WHERE d.idDatosPersonales = :idDatosPersonales")
    , @NamedQuery(name = "DatosPersonales.findByNombres", query = "SELECT d FROM DatosPersonales d WHERE d.nombres = :nombres")
    , @NamedQuery(name = "DatosPersonales.findByApellidos", query = "SELECT d FROM DatosPersonales d WHERE d.apellidos = :apellidos")
    , @NamedQuery(name = "DatosPersonales.findByCorreoElectronico", query = "SELECT d FROM DatosPersonales d WHERE d.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "DatosPersonales.findByDireccion", query = "SELECT d FROM DatosPersonales d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "DatosPersonales.findByFechaNacimiento", query = "SELECT d FROM DatosPersonales d WHERE d.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "DatosPersonales.findByNumIdentificacion", query = "SELECT d FROM DatosPersonales d WHERE d.numIdentificacion = :numIdentificacion")
    , @NamedQuery(name = "DatosPersonales.findBySexo", query = "SELECT d FROM DatosPersonales d WHERE d.sexo = :sexo")
    , @NamedQuery(name = "DatosPersonales.findByGenero", query = "SELECT d FROM DatosPersonales d WHERE d.genero = :genero")
    , @NamedQuery(name = "DatosPersonales.findByCelular", query = "SELECT d FROM DatosPersonales d WHERE d.celular = :celular")
    , @NamedQuery(name = "DatosPersonales.findByConvencional", query = "SELECT d FROM DatosPersonales d WHERE d.convencional = :convencional")
    , @NamedQuery(name = "DatosPersonales.findByCodPostal", query = "SELECT d FROM DatosPersonales d WHERE d.codPostal = :codPostal")
    , @NamedQuery(name = "DatosPersonales.findByEdad", query = "SELECT d FROM DatosPersonales d WHERE d.edad = :edad")
    , @NamedQuery(name = "DatosPersonales.findByEstado", query = "SELECT d FROM DatosPersonales d WHERE d.estado = :estado")
    , @NamedQuery(name = "DatosPersonales.findByFechaDeRegistro", query = "SELECT d FROM DatosPersonales d WHERE d.fechaDeRegistro = :fechaDeRegistro")})
public class DatosPersonales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_personales")
    private Integer idDatosPersonales;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 45)
    @Column(name = "num_identificacion")
    private String numIdentificacion;
    @Size(max = 45)
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "genero")
    private Integer genero;
    @Size(max = 45)
    @Column(name = "celular")
    private String celular;
    @Size(max = 45)
    @Column(name = "convencional")
    private String convencional;
    @Size(max = 45)
    @Column(name = "cod_postal")
    private String codPostal;
    @Size(max = 45)
    @Column(name = "foto")
    private String foto="foto/0000000000.png";
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_de_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRegistro;
    @OneToMany(mappedBy = "idDatosPersonales")
    private List<PerfilAcademico> perfilAcademicoList;
    @OneToMany(mappedBy = "idDatosPersonales")
    private List<TablaInvestigacion> tablaInvestigacionList;
    @OneToMany(mappedBy = "idDatosPersonales")
    private List<Distributivo> distributivoList;
    @OneToMany(mappedBy = "idDatosPersonales")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idDatosPersonales")
    private List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList;
    @OneToMany(mappedBy = "idDatosPersonales")
    private List<Usuario> usuarioList;

    public DatosPersonales() {
    }

    public DatosPersonales(Integer idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public Integer getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(Integer idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }
    

   
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getConvencional() {
        return convencional;
    }

    public void setConvencional(String convencional) {
        this.convencional = convencional;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getFoto() {
        if(foto== null ||foto.equals("") ){
         return foto="foto/0000000000.png";
        }else{
        return foto;
        }
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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

    public List<PerfilAcademico> getPerfilAcademicoList() {
        return perfilAcademicoList;
    }

    public void setPerfilAcademicoList(List<PerfilAcademico> perfilAcademicoList) {
        this.perfilAcademicoList = perfilAcademicoList;
    }

    public List<TablaInvestigacion> getTablaInvestigacionList() {
        return tablaInvestigacionList;
    }

    public void setTablaInvestigacionList(List<TablaInvestigacion> tablaInvestigacionList) {
        this.tablaInvestigacionList = tablaInvestigacionList;
    }

    public List<Distributivo> getDistributivoList() {
        return distributivoList;
    }

    public void setDistributivoList(List<Distributivo> distributivoList) {
        this.distributivoList = distributivoList;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public List<PracticaspreprofesionalesVinculacion> getPracticaspreprofesionalesVinculacionList() {
        return practicaspreprofesionalesVinculacionList;
    }

    public void setPracticaspreprofesionalesVinculacionList(List<PracticaspreprofesionalesVinculacion> practicaspreprofesionalesVinculacionList) {
        this.practicaspreprofesionalesVinculacionList = practicaspreprofesionalesVinculacionList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosPersonales != null ? idDatosPersonales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosPersonales)) {
            return false;
        }
        DatosPersonales other = (DatosPersonales) object;
        if ((this.idDatosPersonales == null && other.idDatosPersonales != null) || (this.idDatosPersonales != null && !this.idDatosPersonales.equals(other.idDatosPersonales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombres+" "+this.apellidos;
    }
    
}

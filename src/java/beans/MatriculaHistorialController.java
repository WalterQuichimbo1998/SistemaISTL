package beans;

import modelo.MatriculaHistorial;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.MatriculaHistorialFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Matricula;

@Named("matriculaHistorialController")
@SessionScoped
public class MatriculaHistorialController implements Serializable {

    @EJB
    private dao.MatriculaHistorialFacade ejbFacade;
    private List<MatriculaHistorial> items = null;
    private MatriculaHistorial selected;
    private MatriculaHistorial mh;

    public MatriculaHistorialController() {
    }

    public MatriculaHistorial getSelected() {
        return selected;
    }

    public void setSelected(MatriculaHistorial selected) {
        this.selected = selected;
    }

    public MatriculaHistorial getMh() {
        return mh;
    }

    public void setMh(MatriculaHistorial mh) {
        this.mh = mh;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MatriculaHistorialFacade getFacade() {
        return ejbFacade;
    }

    public MatriculaHistorial prepareCreate() {
        selected = new MatriculaHistorial();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Creado correctamente");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create2() {
        persist2(PersistAction.CREATE);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MatriculaHistorialUpdated"));
    }

    public void update2() {
        persist3(PersistAction.UPDATE);
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MatriculaHistorialDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MatriculaHistorial> getItems() {
        if (items == null) {
            if (AccesoBean.obtenerMatricula().getIdMatricula() != null) {
                items = getFacade().listaMatriculaHistorial(AccesoBean.obtenerMatricula().getIdMatricula());
            } else {
                items = null;
            }

        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    private void persist2(PersistAction persistAction) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {

                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    private void persist3(PersistAction persistAction) {
        if (mh != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(mh);
                } else {
                    getFacade().remove(mh);
                }

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {

                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MatriculaHistorial getMatriculaHistorial(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MatriculaHistorial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MatriculaHistorial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MatriculaHistorial.class)
    public static class MatriculaHistorialControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatriculaHistorialController controller = (MatriculaHistorialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "matriculaHistorialController");
            return controller.getMatriculaHistorial(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MatriculaHistorial) {
                MatriculaHistorial o = (MatriculaHistorial) object;
                return getStringKey(o.getIdMatriculaHistorial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MatriculaHistorial.class.getName()});
                return null;
            }
        }

    }

    public void crearMH(Matricula m) {
        if (m.getIdPeriodoAcademico() != null) {
            mh = ejbFacade.virificarMatriculaHistorial(m.getIdNivelAcademico().getIdNivelAcademico(), m.getIdPeriodoAcademico().getIdPeriodoAcademico(), m.getIdMatricula());
            if (mh != null) {
                try {
                    this.mh.setIdTipoIdentificacion(m.getIdTipoIdentificacion());
                    this.mh.setIdEtnia(m.getIdEtnia());
                    this.mh.setIdTipoSangre(m.getIdTipoSangre());
                    this.mh.setIdNacionalidad(m.getIdNacionalidad());
                    this.mh.setIdEstadoCivil(m.getIdEstadoCivil());
                    this.mh.setIdDatosPersonales(m.getIdDatosPersonales());
                    this.mh.setIdProvinciaResidencia(m.getIdProvinciaResidencia());
                    this.mh.setIdCantonNacimiento(m.getIdCantonNacimiento());
                    this.mh.setIdProvinciaNacimiento(m.getIdProvinciaNacimiento());
                    this.mh.setIdResidencia(m.getIdResidencia());
                    this.mh.setIdNivelAcademico(m.getIdNivelAcademico());
                    this.mh.setIdTipoMatricula(m.getIdTipoMatricula());
                    this.mh.setIdTituloCarrera(m.getIdTituloCarrera());
                    this.mh.setIdPeriodoAcademico(m.getIdPeriodoAcademico());
                    this.mh.setIdTipoColegio(m.getIdTipoColegio());
                    this.mh.setIdTipoBachillerato(m.getIdTipoBachillerato());
                    this.mh.setIdJornadaAcademica(m.getIdJornadaAcademica());
                    this.mh.setIdFormacionPadre(m.getIdFormacionPadre());
                    this.mh.setIdFormacionMadre(m.getIdFormacionMadre());
                    this.mh.setIdTipoCarrera(m.getIdTipoCarrera());
                    this.mh.setIdSectorEconomicoEmpresa(m.getIdSectorEconomicoEmpresa());
                    this.mh.setIdParalelo(m.getIdParalelo());
                    this.mh.setIdTipoDiscapacidad(m.getIdTipoDiscapacidad());
                    this.mh.setIdtipoinstitucionpracticasPreprofesionales(m.getIdtipoinstitucionpracticasPreprofesionales());
                    this.mh.setIdsectorpracticasPreprofesionales(m.getIdsectorpracticasPreprofesionales());
                    this.mh.setIdAlcanceVinculacion(m.getIdAlcanceVinculacion());
                    this.mh.setIdMatricula(m);
                    this.mh.setIdiomaAncestral(m.getIdiomaAncestral());
                    this.mh.setDescripcionIdiomaAncestral(m.getDescripcionIdiomaAncestral());
                    this.mh.setPaisNacionalidad(m.getPaisNacionalidad());
                    this.mh.setPaisResidencia(m.getPaisResidencia());
                    this.mh.setPoseeDiscapacidad(m.getPoseeDiscapacidad());
                    this.mh.setParticipacionPracticas(m.getParticipacionPracticas());
                    this.mh.setParticipacionVinculacion(m.getParticipacionVinculacion());
                    this.mh.setFechaMatricula(m.getFechaMatricula());
                    this.mh.setAnioGraduacion(m.getAnioGraduacion());
                    this.mh.setModalidad(m.getModalidad());
                    this.mh.setFechaInicioCarrera(m.getFechaInicioCarrera());
                    this.mh.setTituloEducacionSuperior(m.getTituloEducacionSuperior());
                    this.mh.setEspecifiqueTitulo(m.getEspecifiqueTitulo());
                    this.mh.setTituloOtorgado(m.getTituloOtorgado());
                    this.mh.setOcupacionEstudiante(m.getOcupacionEstudiante());
                    this.mh.setNombreEmpresa(m.getNombreEmpresa());
                    this.mh.setEmpleoIngresosEconomicos(m.getEmpleoIngresosEconomicos());
                    this.mh.setBonoDesarrolloHumano(m.getBonoDesarrolloHumano());
                    this.mh.setIngresoHogar(m.getIngresoHogar());
                    this.mh.setNroMiembrosHogar(m.getNroMiembrosHogar());
                    this.mh.setMateriaRepetida(m.getMateriaRepetida());
                    this.mh.setMateriaPerdida(m.getMateriaPerdida());
                    this.mh.setNumeroFormulario(m.getNumeroFormulario());
                    this.mh.setCodigoMatricula(m.getCodigoMatricula());
                    this.mh.setNombreContacto(m.getNombreContacto());
                    this.mh.setApellidoContacto(m.getApellidoContacto());
                    this.mh.setCelularContacto(m.getCelularContacto());
                    this.mh.setParentescoContacto(m.getParentescoContacto());
                    this.mh.setNumCarnetConadis(m.getNumCarnetConadis());
                    this.mh.setPorcentajeDiscapacidad(m.getPorcentajeDiscapacidad());
                    this.mh.setDescripcionDiscapacidad(m.getDescripcionDiscapacidad());
                    this.mh.setNrohoraspracticasPreprosionales(m.getNrohoraspracticasPreprosionales());
                    this.mh.setCategoriaMigratoria(m.getCategoriaMigratoria());
                    this.mh.setProvinciaResidencia(m.getProvinciaResidencia());
                    this.mh.setCantonResidencia(m.getCantonResidencia());
                    update2();
                } catch (Exception e) {
                }
            } else {
                try {
                    this.selected.setIdTipoIdentificacion(m.getIdTipoIdentificacion());
                    this.selected.setIdEtnia(m.getIdEtnia());
                    this.selected.setIdTipoSangre(m.getIdTipoSangre());
                    this.selected.setIdNacionalidad(m.getIdNacionalidad());
                    this.selected.setIdEstadoCivil(m.getIdEstadoCivil());
                    this.selected.setIdDatosPersonales(m.getIdDatosPersonales());
                    this.selected.setIdProvinciaResidencia(m.getIdProvinciaResidencia());
                    this.selected.setIdCantonNacimiento(m.getIdCantonNacimiento());
                    this.selected.setIdProvinciaNacimiento(m.getIdProvinciaNacimiento());
                    this.selected.setIdResidencia(m.getIdResidencia());
                    this.selected.setIdNivelAcademico(m.getIdNivelAcademico());
                    this.selected.setIdTipoMatricula(m.getIdTipoMatricula());
                    this.selected.setIdTituloCarrera(m.getIdTituloCarrera());
                    this.selected.setIdPeriodoAcademico(m.getIdPeriodoAcademico());
                    this.selected.setIdTipoColegio(m.getIdTipoColegio());
                    this.selected.setIdTipoBachillerato(m.getIdTipoBachillerato());
                    this.selected.setIdJornadaAcademica(m.getIdJornadaAcademica());
                    this.selected.setIdFormacionPadre(m.getIdFormacionPadre());
                    this.selected.setIdFormacionMadre(m.getIdFormacionMadre());
                    this.selected.setIdTipoCarrera(m.getIdTipoCarrera());
                    this.selected.setIdSectorEconomicoEmpresa(m.getIdSectorEconomicoEmpresa());
                    this.selected.setIdParalelo(m.getIdParalelo());
                    this.selected.setIdTipoDiscapacidad(m.getIdTipoDiscapacidad());
                    this.selected.setIdtipoinstitucionpracticasPreprofesionales(m.getIdtipoinstitucionpracticasPreprofesionales());
                    this.selected.setIdsectorpracticasPreprofesionales(m.getIdsectorpracticasPreprofesionales());
                    this.selected.setIdAlcanceVinculacion(m.getIdAlcanceVinculacion());
                    this.selected.setIdMatricula(m);
                    this.selected.setIdiomaAncestral(m.getIdiomaAncestral());
                    this.selected.setDescripcionIdiomaAncestral(m.getDescripcionIdiomaAncestral());
                    this.selected.setPaisNacionalidad(m.getPaisNacionalidad());
                    this.selected.setPaisResidencia(m.getPaisResidencia());
                    this.selected.setPoseeDiscapacidad(m.getPoseeDiscapacidad());
                    this.selected.setParticipacionPracticas(m.getParticipacionPracticas());
                    this.selected.setParticipacionVinculacion(m.getParticipacionVinculacion());
                    this.selected.setFechaMatricula(m.getFechaMatricula());
                    this.selected.setAnioGraduacion(m.getAnioGraduacion());
                    this.selected.setModalidad(m.getModalidad());
                    this.selected.setFechaInicioCarrera(m.getFechaInicioCarrera());
                    this.selected.setTituloEducacionSuperior(m.getTituloEducacionSuperior());
                    this.selected.setEspecifiqueTitulo(m.getEspecifiqueTitulo());
                    this.selected.setTituloOtorgado(m.getTituloOtorgado());
                    this.selected.setOcupacionEstudiante(m.getOcupacionEstudiante());
                    this.selected.setNombreEmpresa(m.getNombreEmpresa());
                    this.selected.setEmpleoIngresosEconomicos(m.getEmpleoIngresosEconomicos());
                    this.selected.setBonoDesarrolloHumano(m.getBonoDesarrolloHumano());
                    this.selected.setIngresoHogar(m.getIngresoHogar());
                    this.selected.setNroMiembrosHogar(m.getNroMiembrosHogar());
                    this.selected.setMateriaRepetida(m.getMateriaRepetida());
                    this.selected.setMateriaPerdida(m.getMateriaPerdida());
                    this.selected.setNumeroFormulario(m.getNumeroFormulario());
                    this.selected.setCodigoMatricula(m.getCodigoMatricula());
                    this.selected.setNombreContacto(m.getNombreContacto());
                    this.selected.setApellidoContacto(m.getApellidoContacto());
                    this.selected.setCelularContacto(m.getCelularContacto());
                    this.selected.setParentescoContacto(m.getParentescoContacto());
                    this.selected.setNumCarnetConadis(m.getNumCarnetConadis());
                    this.selected.setPorcentajeDiscapacidad(m.getPorcentajeDiscapacidad());
                    this.selected.setDescripcionDiscapacidad(m.getDescripcionDiscapacidad());
                    this.selected.setNrohoraspracticasPreprosionales(m.getNrohoraspracticasPreprosionales());
                    this.selected.setCategoriaMigratoria(m.getCategoriaMigratoria());
                    this.selected.setProvinciaResidencia(m.getProvinciaResidencia());
                    this.selected.setCantonResidencia(m.getCantonResidencia());
                    create2();
                } catch (Exception e) {
                }
            }

        }
    }
}

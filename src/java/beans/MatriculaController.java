package beans;

import modelo.Matricula;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.MatriculaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Canton;
import modelo.Provincia;

@Named("matriculaController")
@SessionScoped
public class MatriculaController implements Serializable {

    @EJB
    private dao.MatriculaFacade ejbFacade;
    @EJB
    private dao.ProvinciaFacade ejbFacadeP;  
    @EJB
    private dao.CantonFacade ejbFacadeC; 

    private List<Matricula> items = null;
    private List<Matricula> listaMatricula = null;
    private Matricula selected;
    private List<Provincia> listaProvincias = null;
    private List<Canton> listaCanton = null;

    public MatriculaController() {
    }

    public Matricula getSelected() {
        if (ejbFacade.virifcarMatricula(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales()) != null) {
        if ("Estudiante".equals(AccesoBean.obtenerIdPersona().getIdTipoOperador().getOperador())) {
            this.selected=AccesoBean.obtenerMatricula();
        }
        }
        return selected;
    }

    public void setSelected(Matricula selected) {
        this.selected = selected;
    }    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public List<Provincia> getListaProvincias() {
       if (selected.getIdNacionalidad()!= null) {
            return listaProvincias= ejbFacadeP.listaProvincia(selected.getIdNacionalidad().getIdNacionalidad());
        } else {
            return null;
        }

    }

    public void setListaProvincias(List<Provincia> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<Canton> getListaCanton() {
        
        if (selected.getIdNacionalidad() != null) {
            if (getListaProvincias().contains(selected.getIdProvinciaNacimiento()) == true) {
                return listaCanton = ejbFacadeC.listaCanton(selected.getIdProvinciaNacimiento().getIdProvincia());
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public void setListaCanton(List<Canton> listaCanton) {
        this.listaCanton = listaCanton;
    }

    public List<Matricula> getListaMatricula() {
        return listaMatricula;
    }

    public void setListaMatricula(List<Matricula> listaMatricula) {
        this.listaMatricula = listaMatricula;
    }
    
    
    private MatriculaFacade getFacade() {
        return ejbFacade;
    }

    public Matricula prepareCreate() {
        selected = new Matricula();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
   
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MatriculaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        items = null;
    }
    public void create2() {
   this.selected.setIdDatosPersonales(AccesoBean.obtenerIdPersona().getIdDatosPersonales());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MatriculaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            selected=AccesoBean.obtenerMatricula();
        }
        selected=ejbFacade.obtenerMatricula(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales());
    }

    public void update() {
         if(selected.getIdNacionalidad()==null){
            this.selected.setIdProvinciaNacimiento(null);
            this.selected.setIdCantonNacimiento(null);
        }
        if(selected.getIdProvinciaNacimiento()==null){
            this.selected.setIdCantonNacimiento(null);
        }

        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MatriculaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MatriculaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Matricula> getItems() {
        if (items == null) {
            items = getFacade().findAll();
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

    public Matricula getMatricula(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Matricula> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Matricula> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Matricula.class)
    public static class MatriculaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatriculaController controller = (MatriculaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "matriculaController");
            return controller.getMatricula(getKey(value));
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
            if (object instanceof Matricula) {
                Matricula o = (Matricula) object;
                return getStringKey(o.getIdMatricula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Matricula.class.getName()});
                return null;
            }
        }

    }
    public void verificarCrear(){
        Matricula ma=ejbFacade.virifcarMatricula(selected.getIdDatosPersonales().getIdDatosPersonales());
        if(ma==null){
            create();
        }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Estudiante ya estÃ¡ matriculado.", ""));

        }
    }
    public Boolean datosP(){
        boolean r=false;
        if(ejbFacade.virifcarMatricula(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales())==null){
            r=false;
        }else{
            r=true;
        }
        return r;
    }
   
}

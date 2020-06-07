package beans;

import modelo.PracticaspreprofesionalesVinculacion;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.PracticaspreprofesionalesVinculacionFacade;

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

@Named("practicaspreprofesionalesVinculacionController")
@SessionScoped
public class PracticaspreprofesionalesVinculacionController implements Serializable {

    @EJB
    private dao.PracticaspreprofesionalesVinculacionFacade ejbFacade;
    private List<PracticaspreprofesionalesVinculacion> items = null;
    private PracticaspreprofesionalesVinculacion selected;

    public PracticaspreprofesionalesVinculacionController() {
    }

    public PracticaspreprofesionalesVinculacion getSelected() {
        return selected;
    }

    public void setSelected(PracticaspreprofesionalesVinculacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PracticaspreprofesionalesVinculacionFacade getFacade() {
        return ejbFacade;
    }

    public PracticaspreprofesionalesVinculacion prepareCreate() {
        selected = new PracticaspreprofesionalesVinculacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PracticaspreprofesionalesVinculacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PracticaspreprofesionalesVinculacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PracticaspreprofesionalesVinculacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PracticaspreprofesionalesVinculacion> getItems() {
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

    public PracticaspreprofesionalesVinculacion getPracticaspreprofesionalesVinculacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PracticaspreprofesionalesVinculacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PracticaspreprofesionalesVinculacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PracticaspreprofesionalesVinculacion.class)
    public static class PracticaspreprofesionalesVinculacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PracticaspreprofesionalesVinculacionController controller = (PracticaspreprofesionalesVinculacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "practicaspreprofesionalesVinculacionController");
            return controller.getPracticaspreprofesionalesVinculacion(getKey(value));
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
            if (object instanceof PracticaspreprofesionalesVinculacion) {
                PracticaspreprofesionalesVinculacion o = (PracticaspreprofesionalesVinculacion) object;
                return getStringKey(o.getIdpracticasPreprofesionalesvinculacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PracticaspreprofesionalesVinculacion.class.getName()});
                return null;
            }
        }

    }

}

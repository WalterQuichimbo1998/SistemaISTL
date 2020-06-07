package beans;

import modelo.AlcanceVinculacion;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.AlcanceVinculacionFacade;

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

@Named("alcanceVinculacionController")
@SessionScoped
public class AlcanceVinculacionController implements Serializable {

    @EJB
    private dao.AlcanceVinculacionFacade ejbFacade;
    private List<AlcanceVinculacion> items = null;
    private AlcanceVinculacion selected;

    public AlcanceVinculacionController() {
    }

    public AlcanceVinculacion getSelected() {
        return selected;
    }

    public void setSelected(AlcanceVinculacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AlcanceVinculacionFacade getFacade() {
        return ejbFacade;
    }

    public AlcanceVinculacion prepareCreate() {
        selected = new AlcanceVinculacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AlcanceVinculacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AlcanceVinculacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AlcanceVinculacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AlcanceVinculacion> getItems() {
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

    public AlcanceVinculacion getAlcanceVinculacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AlcanceVinculacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AlcanceVinculacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AlcanceVinculacion.class)
    public static class AlcanceVinculacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlcanceVinculacionController controller = (AlcanceVinculacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alcanceVinculacionController");
            return controller.getAlcanceVinculacion(getKey(value));
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
            if (object instanceof AlcanceVinculacion) {
                AlcanceVinculacion o = (AlcanceVinculacion) object;
                return getStringKey(o.getIdAlcanceVinculacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AlcanceVinculacion.class.getName()});
                return null;
            }
        }

    }

}

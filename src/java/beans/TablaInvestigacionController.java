package beans;

import modelo.TablaInvestigacion;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.TablaInvestigacionFacade;

import java.io.Serializable;
import java.util.Date;
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

@Named("tablaInvestigacionController")
@SessionScoped
public class TablaInvestigacionController implements Serializable {

    @EJB
    private dao.TablaInvestigacionFacade ejbFacade;
    private List<TablaInvestigacion> items = null;
    private TablaInvestigacion selected;

    public TablaInvestigacionController() {
    }

    public TablaInvestigacion getSelected() {
        return selected;
    }

    public void setSelected(TablaInvestigacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TablaInvestigacionFacade getFacade() {
        return ejbFacade;
    }

    public TablaInvestigacion prepareCreate() {
        selected = new TablaInvestigacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setFechaDeRegistro(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TablaInvestigacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TablaInvestigacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TablaInvestigacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TablaInvestigacion> getItems() {
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

    public TablaInvestigacion getTablaInvestigacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TablaInvestigacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TablaInvestigacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TablaInvestigacion.class)
    public static class TablaInvestigacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TablaInvestigacionController controller = (TablaInvestigacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tablaInvestigacionController");
            return controller.getTablaInvestigacion(getKey(value));
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
            if (object instanceof TablaInvestigacion) {
                TablaInvestigacion o = (TablaInvestigacion) object;
                return getStringKey(o.getIdTablaInvestigacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TablaInvestigacion.class.getName()});
                return null;
            }
        }

    }

}

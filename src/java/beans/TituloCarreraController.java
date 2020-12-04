package beans;

import modelo.TituloCarrera;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.TituloCarreraFacade;

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

@Named("tituloCarreraController")
@SessionScoped
public class TituloCarreraController implements Serializable {

    @EJB
    private dao.TituloCarreraFacade ejbFacade;
    private List<TituloCarrera> items = null;
    private List<TituloCarrera> lista = null;
    private TituloCarrera selected;

    public TituloCarreraController() {
    }

    public TituloCarrera getSelected() {
        return selected;
    }

    public void setSelected(TituloCarrera selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TituloCarreraFacade getFacade() {
        return ejbFacade;
    }

    public TituloCarrera prepareCreate() {
        selected = new TituloCarrera();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TituloCarreraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TituloCarreraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TituloCarreraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TituloCarrera> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<TituloCarrera> getLista() {
        if (lista == null) {
            lista = getFacade().listaTitulos(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales());
        }
        return lista;
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

    public TituloCarrera getTituloCarrera(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TituloCarrera> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TituloCarrera> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TituloCarrera.class)
    public static class TituloCarreraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TituloCarreraController controller = (TituloCarreraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tituloCarreraController");
            return controller.getTituloCarrera(getKey(value));
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
            if (object instanceof TituloCarrera) {
                TituloCarrera o = (TituloCarrera) object;
                return getStringKey(o.getIdTituloCarrera());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TituloCarrera.class.getName()});
                return null;
            }
        }

    }

}

package beans;

import modelo.SectorEconomicoEmpresa;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.SectorEconomicoEmpresaFacade;

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

@Named("sectorEconomicoEmpresaController")
@SessionScoped
public class SectorEconomicoEmpresaController implements Serializable {

    @EJB
    private dao.SectorEconomicoEmpresaFacade ejbFacade;
    private List<SectorEconomicoEmpresa> items = null;
    private SectorEconomicoEmpresa selected;

    public SectorEconomicoEmpresaController() {
    }

    public SectorEconomicoEmpresa getSelected() {
        return selected;
    }

    public void setSelected(SectorEconomicoEmpresa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SectorEconomicoEmpresaFacade getFacade() {
        return ejbFacade;
    }

    public SectorEconomicoEmpresa prepareCreate() {
        selected = new SectorEconomicoEmpresa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SectorEconomicoEmpresaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SectorEconomicoEmpresaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SectorEconomicoEmpresaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SectorEconomicoEmpresa> getItems() {
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

    public SectorEconomicoEmpresa getSectorEconomicoEmpresa(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SectorEconomicoEmpresa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SectorEconomicoEmpresa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SectorEconomicoEmpresa.class)
    public static class SectorEconomicoEmpresaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SectorEconomicoEmpresaController controller = (SectorEconomicoEmpresaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sectorEconomicoEmpresaController");
            return controller.getSectorEconomicoEmpresa(getKey(value));
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
            if (object instanceof SectorEconomicoEmpresa) {
                SectorEconomicoEmpresa o = (SectorEconomicoEmpresa) object;
                return getStringKey(o.getIdSectorEconomicoEmpresa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SectorEconomicoEmpresa.class.getName()});
                return null;
            }
        }

    }

}

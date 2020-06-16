package beans;

import modelo.PerfilAcademico;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.PerfilAcademicoFacade;

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

@Named("perfilAcademicoController")
@SessionScoped
public class PerfilAcademicoController implements Serializable {

    @EJB
    private dao.PerfilAcademicoFacade ejbFacade;
    private List<PerfilAcademico> items = null;
    private PerfilAcademico selected;

    public PerfilAcademicoController() {
    }

    public PerfilAcademico getSelected() {
        return selected;
    }

    public void setSelected(PerfilAcademico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PerfilAcademicoFacade getFacade() {
        return ejbFacade;
    }

    public PerfilAcademico prepareCreate() {
        selected = new PerfilAcademico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setFechaDeRegistro(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PerfilAcademicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PerfilAcademicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PerfilAcademicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PerfilAcademico> getItems() {
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

    public PerfilAcademico getPerfilAcademico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PerfilAcademico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PerfilAcademico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PerfilAcademico.class)
    public static class PerfilAcademicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PerfilAcademicoController controller = (PerfilAcademicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "perfilAcademicoController");
            return controller.getPerfilAcademico(getKey(value));
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
            if (object instanceof PerfilAcademico) {
                PerfilAcademico o = (PerfilAcademico) object;
                return getStringKey(o.getIdPerfilAcademico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PerfilAcademico.class.getName()});
                return null;
            }
        }

    }

}

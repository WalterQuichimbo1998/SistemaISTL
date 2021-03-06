package beans;

import modelo.Distributivo;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.DistributivoFacade;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("distributivoController")
@SessionScoped
public class DistributivoController implements Serializable {

    @EJB
    private dao.DistributivoFacade ejbFacade;
    private List<Distributivo> items = null;
    private Distributivo selected;

    public DistributivoController() {
    }

    public Distributivo getSelected() {
        return selected;
    }

    public void setSelected(Distributivo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DistributivoFacade getFacade() {
        return ejbFacade;
    }

    public Distributivo prepareCreate() {
        selected = new Distributivo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (getFacade().verificarDistributivo(selected.getIdDatosPersonales().getIdDatosPersonales(), selected.getIdPeriodoAcademicoSemestre().getIdPeriodoAcademico()) == null) {
            this.selected.setFechaDeRegistro(new Date());
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DistributivoCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ya existe el distributivo con esté docente y esté período.", ""));

        }
    }

    public void update() {
        if (getFacade().verificarDistributivo2(selected.getIdDatosPersonales().getIdDatosPersonales(), selected.getIdPeriodoAcademicoSemestre().getIdPeriodoAcademico(), selected.getIdDistributivo()) == null) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DistributivoUpdated"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ya existe el distributivo con esté docente y esté período.", ""));

        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DistributivoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Distributivo> getItems() {
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

    public Distributivo getDistributivo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Distributivo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Distributivo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Distributivo.class)
    public static class DistributivoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistributivoController controller = (DistributivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoController");
            return controller.getDistributivo(getKey(value));
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
            if (object instanceof Distributivo) {
                Distributivo o = (Distributivo) object;
                return getStringKey(o.getIdDistributivo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distributivo.class.getName()});
                return null;
            }
        }

    }

}

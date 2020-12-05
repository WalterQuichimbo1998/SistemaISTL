package beans;

import modelo.CoRequisitosMateria;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.CoRequisitosMateriaFacade;

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
import modelo.Materia;

@Named("coRequisitosMateriaController")
@SessionScoped
public class CoRequisitosMateriaController implements Serializable {

    @EJB
    private dao.CoRequisitosMateriaFacade ejbFacade;
    private List<CoRequisitosMateria> items = null;
    private CoRequisitosMateria selected;
    private Materia selectedM;

    public CoRequisitosMateriaController() {
    }

    public CoRequisitosMateria getSelected() {
        return selected;
    }

    public void setSelected(CoRequisitosMateria selected) {
        this.selected = selected;
    }

    public Materia getSelectedM() {
        return selectedM;
    }

    public void setSelectedM(Materia selectedM) {
        this.selectedM = selectedM;
    }
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CoRequisitosMateriaFacade getFacade() {
        return ejbFacade;
    }

    public CoRequisitosMateria prepareCreate() {
        selected = new CoRequisitosMateria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CoRequisitosMateriaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CoRequisitosMateriaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CoRequisitosMateriaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CoRequisitosMateria> getItems() {
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

    public CoRequisitosMateria getCoRequisitosMateria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CoRequisitosMateria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CoRequisitosMateria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CoRequisitosMateria.class)
    public static class CoRequisitosMateriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CoRequisitosMateriaController controller = (CoRequisitosMateriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "coRequisitosMateriaController");
            return controller.getCoRequisitosMateria(getKey(value));
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
            if (object instanceof CoRequisitosMateria) {
                CoRequisitosMateria o = (CoRequisitosMateria) object;
                return getStringKey(o.getIdCoRequisitosMateria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CoRequisitosMateria.class.getName()});
                return null;
            }
        }

    }

}

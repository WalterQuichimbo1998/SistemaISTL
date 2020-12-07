package beans;

import modelo.TablaColaborador;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.TablaColaboradorFacade;

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
import modelo.TablaInvestigacion;

@Named("tablaColaboradorController")
@SessionScoped
public class TablaColaboradorController implements Serializable {

    @EJB
    private dao.TablaColaboradorFacade ejbFacade;
    private List<TablaColaborador> items = null;
    private TablaColaborador selected;
    private TablaInvestigacion selectedT;

    public TablaColaboradorController() {
    }

    public TablaColaborador getSelected() {
        return selected;
    }

    public void setSelected(TablaColaborador selected) {
        this.selected = selected;
    }

    public TablaInvestigacion getSelectedT() {
        return selectedT;
    }

    public void setSelectedT(TablaInvestigacion selectedT) {
        this.selectedT = selectedT;
    }
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TablaColaboradorFacade getFacade() {
        return ejbFacade;
    }

    public TablaColaborador prepareCreate() {
        selected = new TablaColaborador();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if(ejbFacade.verificarColaborador(selectedT.getIdTablaInvestigacion(), selected.getIdDatosPersonales().getIdDatosPersonales())==null){
        this.selected.setIdTablaInvestigacion(selectedT);
        persist(PersistAction.CREATE, "Colaborador Agregado con éxito.");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        }else{
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Colaborador ya está agregado.", ""));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Colaborador Actualizado con éxito.");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Colaborador Eliminado con éxito.");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TablaColaborador> getItems() {
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

    public TablaColaborador getTablaColaborador(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TablaColaborador> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TablaColaborador> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TablaColaborador.class)
    public static class TablaColaboradorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TablaColaboradorController controller = (TablaColaboradorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tablaColaboradorController");
            return controller.getTablaColaborador(getKey(value));
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
            if (object instanceof TablaColaborador) {
                TablaColaborador o = (TablaColaborador) object;
                return getStringKey(o.getIdTablaColaborador());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TablaColaborador.class.getName()});
                return null;
            }
        }

    }

}

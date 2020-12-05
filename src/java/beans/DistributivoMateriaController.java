package beans;

import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.DistributivoMateriaFacade;
import dao.MateriaFacade;
import dao.NivelAcademicoFacade;

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
import modelo.Distributivo;
import modelo.DistributivoMateria;
import modelo.Materia;
import modelo.NivelAcademico;

@Named("distributivoMateriaController")
@SessionScoped
public class DistributivoMateriaController implements Serializable {

    @EJB
    private dao.DistributivoMateriaFacade ejbFacade;
    private List<DistributivoMateria> items = null;
    private DistributivoMateria selected;
    private Distributivo selectedD;
    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    private List<NivelAcademico> itemsNivelAcademico = null;
    @EJB
    private MateriaFacade ejbFacadeMateria;
    private List<Materia> itemsMateria = null;

    public DistributivoMateriaController() {
    }

    public DistributivoMateria getSelected() {
        return selected;
    }

    public void setSelected(DistributivoMateria selected) {
        this.selected = selected;
    }

    public Distributivo getSelectedD() {
        return selectedD;
    }

    public void setSelectedD(Distributivo selectedD) {
        this.selectedD = selectedD;
        items = null;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DistributivoMateriaFacade getFacade() {
        return ejbFacade;
    }

    public DistributivoMateria prepareCreate() {
        selected = new DistributivoMateria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (ejbFacade.verificarMateriaDistributivo(selectedD.getIdDistributivo(), selected.getIdNivelAcademico().getIdNivelAcademico(), selected.getIdMateria().getIdMateria()) == null) {
            this.selected.setIdDistributivo(selectedD);
            persist(PersistAction.CREATE, "Materia distributivo registrada");
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Materia ya registrada.", ""));
            items = null; 
        }

    }

    public void update() {
        if (ejbFacade.verificarMateriaDistributivo2(selectedD.getIdDistributivo(), selected.getIdNivelAcademico().getIdNivelAcademico(), selected.getIdMateria().getIdMateria(), selected.getIdDistributivoMateria()) == null) {
            this.selected.setIdDistributivo(selectedD);
            persist(PersistAction.UPDATE, "Materia distributivo actualizada");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Materia ya registrada.", ""));
            items = null; 
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Materia distributivo eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DistributivoMateria> getItems() {
        if (items == null) {
            if (selectedD != null) {
                items = getFacade().listaDistributivoMaterias(selectedD.getIdDistributivo());
            }
        }
        return items;

    }

    public List<NivelAcademico> getItemsNivelAcademico() {
        if (selected.getIdTituloCarrera() != null) {
            return itemsNivelAcademico = ejbFacadeNivel.listaNiveles(selected.getIdTituloCarrera().getIdTituloCarrera());
        } else {
            return null;
        }
    }

    public void setItemsNivelAcademico(List<NivelAcademico> itemsNivelAcademico) {
        this.itemsNivelAcademico = itemsNivelAcademico;
    }

    public List<Materia> getItemsMateria() {
        if (selected.getIdTituloCarrera() != null) {
            if (getItemsNivelAcademico().contains(selected.getIdNivelAcademico()) == true) {
                return itemsMateria = ejbFacadeMateria.listaMaterias(selected.getIdNivelAcademico().getIdNivelAcademico());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void setItemsMateria(List<Materia> itemsMateria) {
        this.itemsMateria = itemsMateria;
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

    public DistributivoMateria getDistributivoMateria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<DistributivoMateria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DistributivoMateria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DistributivoMateria.class)
    public static class DistributivoMateriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistributivoMateriaController controller = (DistributivoMateriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoMateriaController");
            return controller.getDistributivoMateria(getKey(value));
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
            if (object instanceof DistributivoMateria) {
                DistributivoMateria o = (DistributivoMateria) object;
                return getStringKey(o.getIdDistributivoMateria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DistributivoMateria.class.getName()});
                return null;
            }
        }

    }

}

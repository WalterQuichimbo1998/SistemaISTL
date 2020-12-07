package beans;

import modelo.PreRequisitosMateria;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.PreRequisitosMateriaFacade;

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

@Named("preRequisitosMateriaController")
@SessionScoped
public class PreRequisitosMateriaController implements Serializable {

    @EJB
    private dao.PreRequisitosMateriaFacade ejbFacade;
    @EJB
    private dao.MateriaFacade ejbFacadeMa;
    private List<PreRequisitosMateria> items = null;
    private List<Materia> lista = null;
    private List<Materia> lista2 = null;
    private PreRequisitosMateria selected;
    private Materia selectedM;

    public PreRequisitosMateriaController() {
    }

    public PreRequisitosMateria getSelected() {
        return selected;
    }

    public void setSelected(PreRequisitosMateria selected) {
        this.selected = selected;
    }

    public Materia getSelectedM() {
        return selectedM;
    }

    public void setSelectedM(Materia selectedM) {
        this.selectedM = selectedM;
        lista=null;
        lista2=null;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PreRequisitosMateriaFacade getFacade() {
        return ejbFacade;
    }

    public PreRequisitosMateria prepareCreate() {
        selected = new PreRequisitosMateria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdMateria(selectedM);
        persist(PersistAction.CREATE,"Pre-requisito creado");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        this.selected.setIdMateria(selectedM);
        persist(PersistAction.UPDATE, "Pre-requisito actualizado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Pre-requisito eliminado");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PreRequisitosMateria> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Materia> getLista() {
        if (lista == null) {
            if (selectedM != null) {
                if(selectedM.getIdNivelAcademico().getNivelAcademico().contains("SEG")){
                  lista = ejbFacadeMa.listaMateriasPre(selectedM.getIdTituloCarrera().getIdTituloCarrera(), "PRI%");
                }
                if(selectedM.getIdNivelAcademico().getNivelAcademico().contains("TER")){
                  lista = ejbFacadeMa.listaMateriasPre(selectedM.getIdTituloCarrera().getIdTituloCarrera(), "SEG%");
                }
                if(selectedM.getIdNivelAcademico().getNivelAcademico().contains("CUAR")){
                  lista = ejbFacadeMa.listaMateriasPre(selectedM.getIdTituloCarrera().getIdTituloCarrera(), "TER%");
                }
                if(selectedM.getIdNivelAcademico().getNivelAcademico().contains("QUIN")){
                  lista = ejbFacadeMa.listaMateriasPre(selectedM.getIdTituloCarrera().getIdTituloCarrera(), "CUAR%");
                }
                
            }
        }
        return lista;
    }

    public void setLista(List<Materia> lista) {
        this.lista = lista;
    }

    public List<Materia> getLista2() {
        
        if (lista2 == null) {
            if (selectedM != null) {
               lista2 = ejbFacadeMa.listaMateriasCo(selectedM.getIdTituloCarrera().getIdTituloCarrera(),selectedM.getIdNivelAcademico().getIdNivelAcademico());
            }
        }
        return lista2;
    }

    public void setLista2(List<Materia> lista2) {
        this.lista2 = lista2;
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

    public PreRequisitosMateria getPreRequisitosMateria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PreRequisitosMateria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PreRequisitosMateria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PreRequisitosMateria.class)
    public static class PreRequisitosMateriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PreRequisitosMateriaController controller = (PreRequisitosMateriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "preRequisitosMateriaController");
            return controller.getPreRequisitosMateria(getKey(value));
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
            if (object instanceof PreRequisitosMateria) {
                PreRequisitosMateria o = (PreRequisitosMateria) object;
                return getStringKey(o.getIdPreRequisitosMateria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PreRequisitosMateria.class.getName()});
                return null;
            }
        }

    }

}

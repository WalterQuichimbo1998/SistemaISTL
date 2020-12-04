package beans;

import modelo.NivelAcademico;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.NivelAcademicoFacade;

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
import modelo.TituloCarrera;

@Named("nivelAcademicoController")
@SessionScoped
public class NivelAcademicoController implements Serializable {

    @EJB
    private dao.NivelAcademicoFacade ejbFacade;
    private List<NivelAcademico> items = null;
    private List<NivelAcademico> lista = null;
    private List<NivelAcademico> listaD = null;
    private NivelAcademico selected;
    private TituloCarrera selectedT;
    private Integer id_persona;

    public NivelAcademicoController() {
    }

    public NivelAcademico getSelected() {
        return selected;
    }

    public void setSelected(NivelAcademico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NivelAcademicoFacade getFacade() {
        return ejbFacade;
    }

    public TituloCarrera getSelectedT() {
        return selectedT;
    }

    public void setSelectedT(TituloCarrera selectedT) {
        this.selectedT = selectedT;
        lista=null;
        listaD=null;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }
    
    

    public NivelAcademico prepareCreate() {
        selected = new NivelAcademico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdTituloCarrera(selectedT);
         this.selected.setFechaDeRegistro(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NivelAcademicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            lista=null;
        }
    }

    public void update() {
        this.selected.setIdTituloCarrera(selectedT);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NivelAcademicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NivelAcademicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
             lista=null;
        }
    }

    
    public List<NivelAcademico> getItems() {
        items=null;
        if (items == null) {
//            items = getFacade().findAll();
            items = getFacade().listaCiclos();
        }
        return items;
    }

    public List<NivelAcademico> getLista() {
        if (lista == null) {
           if(selectedT!=null){
                 lista = getFacade().listaNivelesT(selectedT.getIdTituloCarrera());
           }
        }
        return lista;
    }

    public void setLista(List<NivelAcademico> lista) {
        this.lista = lista;
    }

    public List<NivelAcademico> getListaD() {
        listaD=null;
         if (listaD == null) {
           if(selectedT!=null){
                 listaD = getFacade().listaNivelesDistributivo(getId_persona(),selectedT.getIdTituloCarrera());
           }
        }
        return listaD;
    }

    public void setListaD(List<NivelAcademico> listaD) {
        this.listaD = listaD;
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

    public NivelAcademico getNivelAcademico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<NivelAcademico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<NivelAcademico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = NivelAcademico.class)
    public static class NivelAcademicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NivelAcademicoController controller = (NivelAcademicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nivelAcademicoController");
            return controller.getNivelAcademico(getKey(value));
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
            if (object instanceof NivelAcademico) {
                NivelAcademico o = (NivelAcademico) object;
                return getStringKey(o.getIdNivelAcademico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NivelAcademico.class.getName()});
                return null;
            }
        }

    }

}

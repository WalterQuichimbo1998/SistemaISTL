package beans;

import modelo.Materia;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.MateriaFacade;
import dao.NivelAcademicoFacade;
import dao.PreRequisitosMateriaFacade;

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
import modelo.NivelAcademico;
import modelo.PreRequisitosMateria;

@Named("materiaController")
@SessionScoped
public class MateriaController implements Serializable {

    @EJB
    private dao.MateriaFacade ejbFacade;
    private List<Materia> items = null;
    private List<Materia> lista = null;
    private List<Materia> lista2 = null;
    private List<PreRequisitosMateria> listaPre = null;
    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    @EJB
    private PreRequisitosMateriaFacade ejbFacadePre;
    private List<NivelAcademico> itemsNivelAcademico = null;
    private Materia selected;
    private NivelAcademico selectedN;
    

    public MateriaController() {
    }

    public Materia getSelected() {
        return selected;
    }

    public NivelAcademico getSelectedN() {
        return selectedN;
    }

    public void setSelectedN(NivelAcademico selectedN) {
        this.selectedN = selectedN;
        lista2=null;
    }
    

    public void setSelected(Materia selected) {
        this.selected = selected;
        lista2=null;
    }

    public List<Materia> getLista() {
        return lista;
    }

    public void setLista(List<Materia> lista) {
        this.lista = lista;
    }

    public List<NivelAcademico> getItemsNivelAcademico() {
         if (selected.getIdPeriodoAcademico() != null) {
            return itemsNivelAcademico=ejbFacadeNivel.listaNiveles(selected.getIdPeriodoAcademico().getIdPeriodoAcademico());
            
        } else {
            return null;
        }
    }

    public void setItemsNivelAcademico(List<NivelAcademico> itemsNivelAcademico) {
        this.itemsNivelAcademico = itemsNivelAcademico;
    }
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MateriaFacade getFacade() {
        return ejbFacade;
    }

    public Materia prepareCreate() {
        selected = new Materia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdNivelAcademico(selectedN);
        this.selected.setIdTituloCarrera(selectedN.getIdTituloCarrera());
        this.selected.setFechaDeRegistro(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MateriaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            lista2=null;
        }
    }

    public void update() {
        this.selected.setIdNivelAcademico(selectedN);
        this.selected.setIdTituloCarrera(selectedN.getIdTituloCarrera());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MateriaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MateriaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            lista2=null;
        }
    }

    public List<Materia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Materia> getLista2() {
        
       if (lista2 == null) {
           if(selectedN!=null){
                 lista2 = getFacade().listaMaterias(selectedN.getIdNivelAcademico());
           }
        }
        return lista2;
    }

    public void setLista2(List<Materia> lista2) {
        this.lista2 = lista2;
    }

    public List<PreRequisitosMateria> listaPre(Integer id) {
        listaPre=null;
         if (listaPre == null) {
           if(selectedN!=null){
                 listaPre = ejbFacadePre.listaMateriasPreLista(id);
           }
        }
        return listaPre;
    }
    public Boolean verificar(Integer id){
        
       boolean r=false;
        listaPre = ejbFacadePre.listaMateriasPreLista(id);
        if (!listaPre.isEmpty()) {     
             r=true;    
        }else{
            r=false;
        }
        return r;
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

    public Materia getMateria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Materia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Materia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Materia.class)
    public static class MateriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MateriaController controller = (MateriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materiaController");
            return controller.getMateria(getKey(value));
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
            if (object instanceof Materia) {
                Materia o = (Materia) object;
                return getStringKey(o.getIdMateria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Materia.class.getName()});
                return null;
            }
        }

    }

}

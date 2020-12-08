package beans;

import modelo.Usuario;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.UsuarioFacade;

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
import modelo.Matricula;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private dao.UsuarioFacade ejbFacade;
    @EJB
    private dao.MatriculaFacade ejbFacadeMA;
    private List<Usuario> items = null;
    private List<Usuario> lista = null;
    private Usuario selected;
    private Matricula matricula = null;

    public UsuarioController() {
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
//        if(selected.getIdTipoOperador().getIdTipoOperador()==2){
//            if(ejbFacadeMA.virificarMatricula(selected.getIdDatosPersonales().getIdDatosPersonales())==null){
//            MatriculaController mc=new MatriculaController();
//            mc.crearMA(selected.getIdDatosPersonales()); 
//        }
//        }

        this.selected.setFechaDeRegistro(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void existeUsuario() {
        Usuario u = getFacade().existeUsuarioRegistradoAdmin(selected.getUsuario().trim());
        if (u != null) {
            items = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario ya existe.Ingrese otro.", ""));
        } else {
            Usuario u2 = getFacade().existePersonaRegistradoAdmin(selected.getIdDatosPersonales().getIdDatosPersonales(), selected.getIdTipoOperador().getIdTipoOperador());
            if (u2 != null) {
                items = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "La Persona y Rol ya existe en otro usuario. Seleccione otro.", ""));
            } else {
                create();
            }
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void existeUsuario2() {
        Usuario u = getFacade().existeUsuarioRegistradoAdmin2(selected.getIdUsuario(), selected.getUsuario().trim());
        if (u != null) {
            items = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario ya existe.Ingrese otro.", ""));
        } else {
            Usuario u2 = getFacade().existePersonaRegistradoAdmin2(selected.getIdUsuario(),selected.getIdDatosPersonales().getIdDatosPersonales(), selected.getIdTipoOperador().getIdTipoOperador());
            if (u2 != null) {
                items = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "La Persona y Rol ya existe en otro usuario. Seleccione otro.", ""));
            } else {
                update();
            }
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    public void verificarCrear() {
        Usuario us = ejbFacade.virifcarUsuario(selected.getIdDatosPersonales().getIdDatosPersonales());
        if (us == null) {
            create();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El estudiante ya tiene un usuario.", ""));
        }
    }

}

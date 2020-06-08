package beans;

import modelo.DatosPersonales;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.DatosPersonalesFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

@Named("datosPersonalesController")
@SessionScoped
public class DatosPersonalesController implements Serializable {

    @EJB
    private dao.DatosPersonalesFacade ejbFacade;
    private List<DatosPersonales> items = null;
    private DatosPersonales selected;
    private List<DatosPersonales> listaDatos = null;

    public DatosPersonalesController() {
    }

    public DatosPersonales getSelected() {
        if ("Estudiante".equals(AccesoBean.obtenerIdPersona().getIdTipoOperador().getOperador())) {
            this.selected = AccesoBean.obtenerIdPersona().getIdDatosPersonales();
        }
        if ("Profesor".equals(AccesoBean.obtenerIdPersona().getIdTipoOperador().getOperador())) {
            this.selected = AccesoBean.obtenerIdPersona().getIdDatosPersonales();
        }
        return selected;
    }

    public void setSelected(DatosPersonales selected) {
        this.selected = selected;
    }
     /*--------Metodos accesores para la busqueda*/

    public List<DatosPersonales> getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List<DatosPersonales> listaDatos) {
        this.listaDatos = listaDatos;
    }
   
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DatosPersonalesFacade getFacade() {
        return ejbFacade;
    }

    public DatosPersonales prepareCreate() {
        selected = new DatosPersonales();
        initializeEmbeddableKey();
        return selected;
    }


    public void create() {
        if (nom_foto == null) {
            selected.setFoto("foto/0000000000.png");
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DatosPersonalesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        if (nom_foto != null) {
            selected.setFoto("foto/" + nom_foto);
        }
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DatosPersonalesUpdated"));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(new Date());
        msj = "Tus datos se han actualizado hoy " + fecha;
    }

    String msj;
    public String getMsj() {
        return msj;
    }

    public String msj() {
        return msj;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DatosPersonalesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DatosPersonales> getItems() {
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

    public DatosPersonales getDatosPersonales(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<DatosPersonales> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DatosPersonales> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DatosPersonales.class)
    public static class DatosPersonalesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DatosPersonalesController controller = (DatosPersonalesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "datosPersonalesController");
            return controller.getDatosPersonales(getKey(value));
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
            if (object instanceof DatosPersonales) {
                DatosPersonales o = (DatosPersonales) object;
                return getStringKey(o.getIdDatosPersonales());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DatosPersonales.class.getName()});
                return null;
            }
        }

    }
    private String nom_foto = "";
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage();
        try {
            nom_foto = event.getFile().getFileName();
            selected.setFoto("foto/" + event.getFile().getFileName());
            guardarImagen(event.getFile().getInputstream(), nom_foto);
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Imagen subida exitosamente");
        } catch (Exception ex) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("Problemas al subir la imagen");
        }
        FacesContext.getCurrentInstance().addMessage("Mensaje", msg);
    }

    public void guardarImagen(InputStream original, String nombreArchivo) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "foto" + File.separatorChar + nombreArchivo;
        File f = null;
        try {
            f = new File(path);
            FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
            int c = 0;
            while ((c = original.read()) >= 0) {
                out.write(c);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen");
        }
    }

}

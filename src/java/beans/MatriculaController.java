package beans;

import modelo.Matricula;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.MatriculaFacade;
import dao.NivelAcademicoFacade;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import modelo.Canton;
import modelo.DatosPersonales;
import modelo.NivelAcademico;
import modelo.Provincia;

@Named("matriculaController")
@SessionScoped
public class MatriculaController implements Serializable {

    @EJB
    private dao.MatriculaFacade ejbFacade;
    @EJB
    private dao.ProvinciaFacade ejbFacadeP;
    @EJB
    private dao.CantonFacade ejbFacadeC;
    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    private List<NivelAcademico> itemsNivelAcademico = null;
    private List<Matricula> items = null;
    private List<Matricula> listaMatricula = null;
    private Matricula selected;
    private List<Provincia> listaProvincias = null;
    private List<Canton> listaCanton = null;
    private Matricula ms = null;
    private Boolean v = false;

    public MatriculaController() {

    }

    public Matricula getSelected() {
        if (ejbFacade.virifcarMatriculaBD(AccesoBean.obtenerIdPersona().getIdDatosPersonales()) != null) {
            if ("Estudiante".equals(AccesoBean.obtenerIdPersona().getIdTipoOperador().getOperador())) {
                this.selected = AccesoBean.obtenerMatricula();
            }
        }
        return selected;

    }

    public void setSelected(Matricula selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public List<Provincia> getListaProvincias() {
        if (selected == null) {
            return null;
        } else {
            if (selected.getIdNacionalidad() != null) {
                return listaProvincias = ejbFacadeP.listaProvincia(selected.getIdNacionalidad().getIdNacionalidad());
            } else {
                return null;
            }
        }
    }

    public void setListaProvincias(List<Provincia> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<Canton> getListaCanton() {
        if (selected == null) {
            return null;
        } else {
            if (selected.getIdNacionalidad() != null) {
                if (getListaProvincias().contains(selected.getIdProvinciaNacimiento()) == true) {
                    return listaCanton = ejbFacadeC.listaCanton(selected.getIdProvinciaNacimiento().getIdProvincia());
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void setListaCanton(List<Canton> listaCanton) {
        this.listaCanton = listaCanton;
    }

    public List<Matricula> getListaMatricula() {
        return listaMatricula;
    }

    public void setListaMatricula(List<Matricula> listaMatricula) {
        this.listaMatricula = listaMatricula;
    }

    public Matricula getMs() {
        return ms;
    }

    public void setMs(Matricula ms) {
        this.ms = ms;
    }

    private MatriculaFacade getFacade() {
        return ejbFacade;
    }

    public Boolean getV() {
        return v;
    }

    public void setV(Boolean v) {
        this.v = v;
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

    public Matricula prepareCreate() {
        selected = new Matricula();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MatriculaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        items = null;
    }

    public void update() {
        if (selected.getIdNacionalidad() == null) {
            this.selected.setIdProvinciaNacimiento(null);
            this.selected.setIdCantonNacimiento(null);
        }
        if (selected.getIdProvinciaNacimiento() == null) {
            this.selected.setIdCantonNacimiento(null);
        }
//        MatriculaHistorialController mmc=new MatriculaHistorialController();
//        mmc.crearMH(selected); 
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MatriculaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MatriculaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Matricula> getItems() {
        items = null;
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

    public Matricula getMatricula(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Matricula> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Matricula> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Matricula.class)
    public static class MatriculaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatriculaController controller = (MatriculaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "matriculaController");
            return controller.getMatricula(getKey(value));
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
            if (object instanceof Matricula) {
                Matricula o = (Matricula) object;
                return getStringKey(o.getIdMatricula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Matricula.class.getName()});
                return null;
            }
        }

    }

    public void verificarCrear() {
        Matricula ma = ejbFacade.virificarMatricula(selected.getIdDatosPersonales().getIdDatosPersonales());
        if (ma == null) {
            create();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Estudiante ya está matriculado.", ""));

        }
    }

    public Connection getConnection() throws Exception {
        final String DATASOURCE_CONTEXT = "java:app/sistema_gestion"; //nombre de tu pool de conexiones
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        return datasource.getConnection();
    }

    public void crearMA(DatosPersonales dp) {
        if (dp != null) {
            PreparedStatement ps;
            int id = dp.getIdDatosPersonales();
            try {
                ps = getConnection().prepareStatement("INSERT INTO matricula (id_datos_personales) VALUES(?)");
                ps.setInt(1, id);
                ps.execute();
                getConnection().close();
            } catch (SQLException ex) {
                System.out.println("mensaje: " + ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(MatriculaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("error");
        }

    }
}

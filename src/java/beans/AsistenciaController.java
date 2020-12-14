package beans;

import modelo.Asistencia;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.AsistenciaFacade;
import dao.DistributivoMateriaFacade;
import dao.MatriculaFacade;
import dao.NivelAcademicoFacade;
import dao.NotasFacade;
import dao.TituloCarreraFacade;

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
import modelo.DatosPersonales;
import modelo.DistributivoMateria;
import modelo.Materia;
import modelo.NivelAcademico;
import modelo.Notas;
import modelo.PeriodoAcademico;
import modelo.TituloCarrera;

@Named("asistenciaController")
@SessionScoped
public class AsistenciaController implements Serializable {

    @EJB
    private dao.AsistenciaFacade ejbFacade;
    @EJB
    private TituloCarreraFacade ejbFacadeTitulo;
    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    @EJB
    private NotasFacade ejbFacadeNotas;
    private List<Asistencia> items = null;
    private List<Asistencia> lista = null;
    private List<Asistencia> listaAusencias = null;
    private Asistencia selected;
    private PeriodoAcademico selectedPeriodo;
    private List<TituloCarrera> listaTituloDocente = null;
    private List<NivelAcademico> listaNivelesDocente = null;
    private NivelAcademico selectedN;
    private Materia selectedMa;
    private DatosPersonales selectedP;
    private DatosPersonales selectedP2;
    private List<Notas> listaEstudiantesCiclo = null;
    @EJB
    private DistributivoMateriaFacade ejbFacadeD;
    private List<DistributivoMateria> itemsMateriaDistributivo = null;
    private Date fecha1;
    private Date fecha2;

    public AsistenciaController() {
    }

    public Asistencia getSelected() {
        return selected;
    }

    public void setSelected(Asistencia selected) {
        this.selected = selected;
    }

    public NivelAcademico getSelectedN() {
        return selectedN;
    }

    public void setSelectedN(NivelAcademico selectedN) {
        this.selectedN = selectedN;
        selectedMa = null;
        listaEstudiantesCiclo = null;
    }

    public PeriodoAcademico getSelectedPeriodo() {
        return selectedPeriodo;
    }

    public void setSelectedPeriodo(PeriodoAcademico selectedPeriodo) {
        this.selectedPeriodo = selectedPeriodo;
    }

    public Materia getSelectedMa() {
        return selectedMa;
    }

    public void setSelectedMa(Materia selectedMa) {
        this.selectedMa = selectedMa;
        listaEstudiantesCiclo = null;
    }

    public DatosPersonales getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(DatosPersonales selectedP) {
        this.selectedP = selectedP;
    }

    public DatosPersonales getSelectedP2() {
        return selectedP2;
    }

    public void setSelectedP2(DatosPersonales selectedP2) {
        this.selectedP2 = selectedP2;

        listaAusencias = null;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsistenciaFacade getFacade() {
        return ejbFacade;
    }

    public Asistencia prepareCreate() {
        selected = new Asistencia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (getFacade().asistenciaEstudianteV(selectedP.getIdDatosPersonales(), selectedMa.getIdMateria(), selectedN.getIdNivelAcademico(), selectedPeriodo.getIdPeriodoAcademico(), selected.getFechaRegistro()) == null) {
            this.selected.setIdDatosPersonales(selectedP);
            this.selected.setIdMateria(selectedMa);
            this.selected.setIdNivelAcademico(selectedN);
            this.selected.setIdPeriodoAcademico(selectedPeriodo);
            persist(PersistAction.CREATE, "Ausencia registrada");
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
                listaAusencias = null;
            }
        } else {
            listaAusencias = null;
            JsfUtil.addErrorMessage("Este registro con está fecha ya existe.");

        }
    }

    public void update() {

        if (getFacade().asistenciaEstudianteV2(selectedP.getIdDatosPersonales(), selectedMa.getIdMateria(), selectedN.getIdNivelAcademico(), selectedPeriodo.getIdPeriodoAcademico(), selected.getFechaRegistro(), selected.getIdAsistencia()) == null) {
            this.selected.setIdDatosPersonales(selectedP);
            this.selected.setIdMateria(selectedMa);
            this.selected.setIdNivelAcademico(selectedN);
            this.selected.setIdPeriodoAcademico(selectedPeriodo);

            persist(PersistAction.UPDATE, "Ausencia actualizada");
            listaAusencias = null;
        } else {
            listaAusencias = null;
            JsfUtil.addErrorMessage("Este registro con está fecha ya existe.");
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Ausencia eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;
            listaAusencias = null;
        }
    }

    public List<Asistencia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<TituloCarrera> getListaTituloDocente() {
        listaTituloDocente = null;
        if (listaTituloDocente == null) {
            if (selectedPeriodo != null) {
                listaTituloDocente = ejbFacadeTitulo.listaTitulos(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales(), selectedPeriodo.getIdPeriodoAcademico());
            }
        }
        return listaTituloDocente;
    }

    public void setListaTituloDocente(List<TituloCarrera> listaTituloDocente) {
        this.listaTituloDocente = listaTituloDocente;
    }

    public List<NivelAcademico> listaNivelesDocente(Integer id) {
        listaNivelesDocente = null;
        if (listaNivelesDocente == null) {
            if (selectedPeriodo != null) {
                listaNivelesDocente = ejbFacadeNivel.listaNivelesDistributivo(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales(), id, selectedPeriodo.getIdPeriodoAcademico());
            }
        }
        return listaNivelesDocente;
    }

    public List<Notas> getListaEstudiantesCiclo() {

        if (listaEstudiantesCiclo == null) {
            if (selectedPeriodo != null) {
                if (selectedN != null) {
                    if (selectedMa != null) {
                        listaEstudiantesCiclo = ejbFacadeNotas.listaNotasCiclo(selectedN.getIdNivelAcademico(), selectedMa.getIdMateria(), selectedPeriodo.getIdPeriodoAcademico());
                    }
                }
            }
        }
        return listaEstudiantesCiclo;
    }

    public void setListaEstudiantesCiclo(List<Notas> listaEstudiantesCiclo) {
        this.listaEstudiantesCiclo = listaEstudiantesCiclo;
    }

    public List<DistributivoMateria> getItemsMateriaDistributivo() {
        itemsMateriaDistributivo = null;
        if (itemsMateriaDistributivo == null) {
            itemsMateriaDistributivo = ejbFacadeD.listaMateriasProfersorDistri(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales(), selectedN.getIdNivelAcademico(), selectedPeriodo.getIdPeriodoAcademico());

        }
        return itemsMateriaDistributivo;
    }

    public void setItemsMateriaDistributivo(List<DistributivoMateria> itemsMateriaDistributivo) {
        this.itemsMateriaDistributivo = itemsMateriaDistributivo;
    }

    public List<Asistencia> listaAsistencia(Integer id) {
        lista = null;
        if (lista == null) {
            if (selectedPeriodo != null) {
                if (selectedN != null) {
                    if (selectedMa != null) {
                        lista = getFacade().asistenciaEstudiante(id, selectedMa.getIdMateria(), selectedN.getIdNivelAcademico(), selectedPeriodo.getIdPeriodoAcademico());
                    }
                }
            }
        }
        return lista;
    }

    public List<Asistencia> getListaAusencias() {
        if (listaAusencias == null) {
            if (selectedPeriodo != null) {
                if (selectedN != null) {
                    if (selectedMa != null) {
                        if (selectedP2 != null) {
                            listaAusencias = getFacade().asistenciaEstudiante(selectedP2.getIdDatosPersonales(), selectedMa.getIdMateria(), selectedN.getIdNivelAcademico(), selectedPeriodo.getIdPeriodoAcademico());

                        }
                    }
                }
            }
        }
        return listaAusencias;
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

    public Asistencia getAsistencia(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Asistencia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asistencia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Asistencia.class)
    public static class AsistenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsistenciaController controller = (AsistenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asistenciaController");
            return controller.getAsistencia(getKey(value));
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
            if (object instanceof Asistencia) {
                Asistencia o = (Asistencia) object;
                return getStringKey(o.getIdAsistencia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asistencia.class.getName()});
                return null;
            }
        }

    }

    public String asistencia(Integer num) {
        Asistencia a = lista.get(num);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String f = formato.format(a.getFechaRegistro());
        return f;
    }

    public void imprimirAsistencia() {
        Bean b = new Bean();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(fecha1);
            String f2 = formato.format(fecha2);
            b.imprimirAsistencia(selectedMa.getIdMateria(), selectedN.getIdNivelAcademico(), selectedPeriodo.getIdPeriodoAcademico(), selectedN.getIdTituloCarrera().getNombreTitulo(),f1,f2);

        } catch (Exception e) {
        }
    }

}

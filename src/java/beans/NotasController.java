package beans;

import modelo.Notas;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import dao.DistributivoMateriaFacade;
import dao.MateriaFacade;
import dao.NivelAcademicoFacade;
import dao.NotasFacade;
import dao.TituloCarreraFacade;
import static groovy.util.Eval.x;

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
import modelo.DatosPersonales;
import modelo.DistributivoMateria;
import modelo.Materia;
import modelo.NivelAcademico;
import modelo.PeriodoAcademico;
import modelo.TituloCarrera;

@Named("notasController")
@SessionScoped
public class NotasController implements Serializable {

    @EJB
    private dao.NotasFacade ejbFacade;
    private List<Notas> items = null;
    private List<Notas> lista = null;
    private List<Notas> listaN = null;
    private List<Notas> listaNotasEstu = null;
    private List<Notas> listaExpedienteNotas = null;
    private List<Notas> listaNotasEstudiantesCiclo = null;
    private List<TituloCarrera> listaTitulo = null;
    private List<TituloCarrera> listaTituloDocente = null;

    @EJB
    private dao.DatosPersonalesFacade ejbFacadePer;
    private List<DatosPersonales> itemsEstudiantes = null;
    private List<DatosPersonales> listaEstu = null;
    private List<NivelAcademico> listaNiveles = null;
    private List<NivelAcademico> listaNivelesDocente = null;
    private Notas selected;
    private DatosPersonales selectedP;
    @EJB
    private MateriaFacade ejbFacadeMa;
    private List<Materia> itemsMateria = null;

    @EJB
    private dao.MatriculaFacade ejbFacadeMatricula;

    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    @EJB
    private TituloCarreraFacade ejbFacadeTitulo;
    @EJB
    private DistributivoMateriaFacade ejbFacadeD;
    private List<DistributivoMateria> itemsMateriaDistributivo = null;

    private NivelAcademico selectedN;
    private Materia selectedMa;
    private PeriodoAcademico selectedPeriodo;
    private Boolean v;

    public NotasController() {
    }

    public Notas getSelected() {
        return selected;
    }

    public void setSelected(Notas selected) {
        this.selected = selected;

        listaN = null;

    }

    public NivelAcademico getSelectedN() {
        return selectedN;
    }

    public DatosPersonales getSelectedP() {
        return selectedP;
    }

    public Boolean getV() {
        return v;
    }

    public void setV(Boolean v) {
        this.v = v;
    }

    public PeriodoAcademico getSelectedPeriodo() {
        return selectedPeriodo;
    }

    public void setSelectedPeriodo(PeriodoAcademico selectedPeriodo) {
        this.selectedPeriodo = selectedPeriodo;
        listaTitulo = null;

    }

    public Materia getSelectedMa() {
        return selectedMa;
    }

    public void setSelectedMa(Materia selectedMa) {
        this.selectedMa = selectedMa;
        listaNotasEstudiantesCiclo = null;
    }

    public void setSelectedP(DatosPersonales selectedP) {
        if (v == true) {
            this.selectedP = selectedP;
            this.setV(false);
        }

        listaEstu = null;
    }

    public void setSelectedN(NivelAcademico selectedN) {
        this.selectedN = selectedN;
        listaN = null;
        listaEstu = null;
        selectedMa = null;
        listaNotasEstudiantesCiclo = null;

    }

    public List<Notas> getLista() {
        return lista;
    }

    public void setLista(List<Notas> lista) {
        this.lista = lista;
    }

    public List<DatosPersonales> getItemsEstudiantes() {
        itemsEstudiantes = null;
        if (itemsEstudiantes == null) {
            itemsEstudiantes = ejbFacadePer.listaEstudiantesNotas(selectedN.getIdNivelAcademico());
        }
        return itemsEstudiantes;
    }

    public void setItemsEstudiantes(List<DatosPersonales> itemsEstudiantes) {
        this.itemsEstudiantes = itemsEstudiantes;
    }

    public List<Materia> getItemsMateria() {
        itemsMateria = null;
        if (itemsMateria == null) {
            itemsMateria = ejbFacadeMa.listaMaterias(selectedN.getIdNivelAcademico());
            if (itemsMateria.isEmpty()) {
                System.out.println("nuull");
            }
        }
        return itemsMateria;
    }

    public List<DistributivoMateria> getItemsMateriaDistributivo() {
        itemsMateriaDistributivo = null;
        if (itemsMateriaDistributivo == null) {
            itemsMateriaDistributivo = ejbFacadeD.listaMateriasProfersorDistri(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales(), selectedN.getIdNivelAcademico(),selectedPeriodo.getIdPeriodoAcademico());

        }
        return itemsMateriaDistributivo;
    }

    public void setItemsMateriaDistributivo(List<DistributivoMateria> itemsMateriaDistributivo) {
        this.itemsMateriaDistributivo = itemsMateriaDistributivo;
    }

    public void setItemsMateria(List<Materia> itemsMateria) {
        this.itemsMateria = itemsMateria;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NotasFacade getFacade() {
        return ejbFacade;
    }

    public Notas prepareCreate() {
        selected = new Notas();
        initializeEmbeddableKey();
        return selected;
    }

    public void update() {
        this.selected.setIdDatosPersonales(selected.getIdDatosPersonales());
        this.selected.setIdNivelAcademico(selectedN);
        this.selected.setIdTituloCarrera(selectedN.getIdTituloCarrera());
        this.selected.setIdMateria(selectedMa);
        Double nota = 0.0;
        Double nota_suple = 0.0;
        if (selected.getParcialUno() != null && selected.getParcialDos() == null && selected.getNotaSupletorio() == null) {
            nota = selected.getParcialUno();
            nota = (nota / 2);
            nota = fijarNumero(nota, 2);

        }
        if (selected.getParcialUno() != null && selected.getParcialDos() != null && selected.getNotaSupletorio() == null) {
            nota = (selected.getParcialUno() + selected.getParcialDos());
            nota = (nota / 2);
            if (nota < 7) {
                nota = (nota * 0.40);
                nota = fijarNumero(nota, 2);
            }

        }
        if (selected.getParcialUno() != null && selected.getParcialDos() != null && selected.getNotaSupletorio() != null) {
            nota = (selected.getParcialUno() + selected.getParcialDos());
            nota = (nota / 2);
            if (nota < 7) {
                nota = (nota * 0.40);
                nota_suple = (selected.getNotaSupletorio() * 0.60);
                nota = (nota + nota_suple);
                nota = fijarNumero(nota, 2);
            } else {
                this.selected.setNotaSupletorio(null);
            }
        }
        this.selected.setNotaFinal(nota);
        persist(PersistAction.UPDATE, "Calificado exitosamente.");
        listaN = null;

    }

    public double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NotasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            listaN = null;
        }
    }

    public List<Notas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<TituloCarrera> getListaTitulo() {
        listaTitulo = null;
        if (listaTitulo == null) {
            if (selectedPeriodo != null) {
                listaTitulo = ejbFacadeTitulo.findAll();
            }
        }
        return listaTitulo;
    }

    public void setListaTitulo(List<TituloCarrera> listaTitulo) {
        this.listaTitulo = listaTitulo;
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

    public List<NivelAcademico> listaNiveles(Integer id) {
        listaNiveles = null;
        if (listaNiveles == null) {
            listaNiveles = ejbFacadeNivel.listaNiveles(id);
        }
        return listaNiveles;
    }

    public List<NivelAcademico> listaNivelesDocente(Integer id) {
        listaNivelesDocente = null;
        if (listaNivelesDocente == null) {
            if (selectedPeriodo != null) {
                listaNivelesDocente = ejbFacadeNivel.listaNivelesDistributivo(AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales(), id,selectedPeriodo.getIdPeriodoAcademico());
            } 
        }
        return listaNivelesDocente;
    }

    public List<Notas> getListaN() {
        listaN = null;
        if (listaN == null) {
            if (selectedN != null) {
                listaN = getFacade().verificarNotas(selectedN.getIdNivelAcademico(), selectedN.getIdTituloCarrera().getIdTituloCarrera());
            }
        }
        return listaN;
    }

    public List<DatosPersonales> getListaEstu() {

        if (listaEstu == null) {
            if (selectedN != null) {
                listaEstu = ejbFacadePer.listaEstudiantesCiclos(selectedN.getIdNivelAcademico());
            }
        }
        return listaEstu;
    }

    public List<Notas> getListaNotasEstudiantesCiclo() {

        if (listaNotasEstudiantesCiclo == null) {
            if (selectedPeriodo != null) {
                if (selectedN != null) {
                    if (selectedMa != null) {
                        listaNotasEstudiantesCiclo = ejbFacade.listaNotasCiclo(selectedN.getIdNivelAcademico(), selectedMa.getIdMateria(), selectedPeriodo.getIdPeriodoAcademico());
                    }
                }
            }
        }
        return listaNotasEstudiantesCiclo;
    }

    public void setListaNotasEstudiantesCiclo(List<Notas> listaNotasEstudiantesCiclo) {
        this.listaNotasEstudiantesCiclo = listaNotasEstudiantesCiclo;
    }

    public List<Notas> listaNotasEstudiante(Integer id) {
        return listaNotasEstu = ejbFacade.verificarNotas2(selectedN.getIdNivelAcademico(), id);
    }

    public List<Notas> listaNotasExpediente(Integer id) {
        return listaExpedienteNotas = ejbFacade.verificarNotas2(id, AccesoBean.obtenerIdPersona().getIdDatosPersonales().getIdDatosPersonales());
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

    public Notas getNotas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Notas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Notas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Notas.class)
    public static class NotasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotasController controller = (NotasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notasController");
            return controller.getNotas(getKey(value));
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
            if (object instanceof Notas) {
                Notas o = (Notas) object;
                return getStringKey(o.getIdNotas());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notas.class.getName()});
                return null;
            }
        }

    }

    public void descargarReporte() {
        if (selectedPeriodo != null) {
            ExportarNotasMaterias exportar = new ExportarNotasMaterias();
            try {
                exportar.imprimirNotasMateria(selectedN.getIdNivelAcademico(), selectedMa.getIdMateria(), selectedPeriodo.getIdPeriodoAcademico(), selectedMa.getIdTituloCarrera());
            } catch (Exception e) {
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se a seleccionado un período.", ""));
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.MateriaFacade;
import dao.NivelAcademicoFacade;
import dao.NotasFacade;
import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.Materia;
import modelo.NivelAcademico;
import modelo.Notas;
import modelo.PeriodoAcademico;
import modelo.TituloCarrera;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Lenovo
 */
@Named(value = "exportarNotasMaterias")
@SessionScoped
public class ExportarNotasMaterias implements Serializable {

//    @EJB
//    private PeriodoAcademicoFacade ejbFacadePeriodo;
//    private List<PeriodoAcademico> itemsPeriodoAcademico = null;
    @EJB
    private MateriaFacade ejbFacade;
    private List<Materia> itemsMateria = null;
    private Materia materiaSelected;

    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    private List<NivelAcademico> itemsNivelAcademico = null;
    private NivelAcademico nivelAcademicoSelected;
    @EJB
    private NotasFacade ejbFacadeNotas;
    private TituloCarrera tituloCarreraSelected;
    private PeriodoAcademico periodoSelected;

    @PostConstruct
    public void init() {

    }
   
    public TituloCarrera getTituloCarreraSelected() {
        return tituloCarreraSelected;
    }

    public void setTituloCarreraSelected(TituloCarrera tituloCarreraSelected) {
        this.tituloCarreraSelected = tituloCarreraSelected;
    }

    public PeriodoAcademico getPeriodoSelected() {
        return periodoSelected;
    }

    public void setPeriodoSelected(PeriodoAcademico periodoSelected) {
        this.periodoSelected = periodoSelected;
    }

    public NivelAcademico getNivelAcademicoSelected() {
        return nivelAcademicoSelected;
    }

    public void setNivelAcademicoSelected(NivelAcademico nivelAcademicoSelected) {
        this.nivelAcademicoSelected = nivelAcademicoSelected;
    }

    public List<NivelAcademico> getItemsNivelAcademico() {
        if (getTituloCarreraSelected() != null) {
            return itemsNivelAcademico = ejbFacadeNivel.listaNiveles(getTituloCarreraSelected().getIdTituloCarrera());
        } else {
            return null;
        }
    }

    public Materia getMateriaSelected() {
        return materiaSelected;
    }

    public void setMateriaSelected(Materia materiaSelected) {
        this.materiaSelected = materiaSelected;
    }

    public List<Materia> getItemsMateria() {
        if (getTituloCarreraSelected() != null) {
            if (getItemsNivelAcademico().contains(getNivelAcademicoSelected()) == true) {
                return itemsMateria = ejbFacade.listaMaterias(nivelAcademicoSelected.getIdNivelAcademico());
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

    public void setItemsNivelAcademico(List<NivelAcademico> itemsNivelAcademico) {
        this.itemsNivelAcademico = itemsNivelAcademico;
    }

    private byte[] reportPdf;
    private final String logotipo = "/reportes/logo.jpg";

    public void imprimirMateriasdoc() throws URISyntaxException, JRException, Exception {
        List<Notas> lista = null;
        try {
            lista = ejbFacadeNotas.verificarMaterias(periodoSelected.getIdPeriodoAcademico(), nivelAcademicoSelected.getIdNivelAcademico(), materiaSelected.getIdMateria(),tituloCarreraSelected.getIdTituloCarrera());
            
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        if (!lista.isEmpty()) {
            reportPdf = null;
            File fichero = new File(getClass().getResource("/reportes/MateriaDocente.jasper").toURI());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

            if (jasperReport != null) {
                Map parametros = new HashMap();
                //parametros que enviamos al report.
                parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
                parametros.put("ciclo", nivelAcademicoSelected.getIdNivelAcademico());
                parametros.put("materia", materiaSelected.getIdMateria());
                parametros.put("periodo", periodoSelected.getIdPeriodoAcademico());
                parametros.put("titulo", tituloCarreraSelected.getIdTituloCarrera());
                parametros.put("titulo_carrera", tituloCarreraSelected.getNombreTitulo());

                //Compilamos el archivo XML y lo cargamos en memoria
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
                //Exportamos el reporte a pdf y lo guardamos en disco

                reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

                response.addHeader("Content-disposition", "attachment; filename=materias.pdf");

                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
                lista = null;
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No se encontraron datos.", ""));

        }
    }

    public Connection getConnection() throws Exception {
        final String DATASOURCE_CONTEXT = "java:app/sistema_gestion"; //nombre de tu pool de conexiones
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        return datasource.getConnection();
    }
}

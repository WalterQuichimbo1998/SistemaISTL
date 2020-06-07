/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.MateriaFacade;
import dao.NivelAcademicoFacade;
import dao.TituloCarreraFacade;
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
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.Materia;
import modelo.NivelAcademico;
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

    @EJB
    private TituloCarreraFacade ejbTituloCarrera;
    private List<TituloCarrera> itemsTituloCarrera = null;
    private TituloCarrera tiCarreraSelected = null;

    @EJB
    private NivelAcademicoFacade ejbFacadeNivel;
    private List<NivelAcademico> itemsNivelAcademico = null;
    private NivelAcademico nivelAcademicoSelected;

    @EJB
    private MateriaFacade ejbFacade;
    private List<Materia> itemsMateria = null;
    private Materia materiaSelected;

    @PostConstruct
    public void init() {
        tiCarreraSelected = new TituloCarrera();
        itemsTituloCarrera = ejbTituloCarrera.findAll();

        nivelAcademicoSelected = new NivelAcademico();
        itemsNivelAcademico = ejbFacadeNivel.findAll();

        materiaSelected = new Materia();
        itemsMateria = ejbFacade.findAll();

    }

    public ExportarNotasMaterias() {
    }

    public TituloCarrera getTiCarreraSelected() {
        return tiCarreraSelected;
    }

    public void setTiCarreraSelected(TituloCarrera tiCarreraSelected) {
        this.tiCarreraSelected = tiCarreraSelected;
    }

    public List<TituloCarrera> getItemsTituloCarrera() {
        return itemsTituloCarrera;
    }

    public void setItemsTituloCarrera(List<TituloCarrera> itemsTituloCarrera) {
        this.itemsTituloCarrera = itemsTituloCarrera;
    }

    public TituloCarreraFacade getEjbTituloCarrera() {
        return ejbTituloCarrera;
    }

    public void setEjbTituloCarrera(TituloCarreraFacade ejbTituloCarrera) {
        this.ejbTituloCarrera = ejbTituloCarrera;
    }

    public MateriaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MateriaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Materia> getItemsMateria() {
        System.out.println("1");
        if (tiCarreraSelected.getIdTituloCarrera() != null && nivelAcademicoSelected.getIdNivelAcademico() != null) {
            System.out.println("2");
            return itemsMateria = ejbFacade.getByID(tiCarreraSelected, nivelAcademicoSelected);
        } else {
            return itemsMateria;
        }
    }

    public void setItemsMateria(List<Materia> itemsMateria) {
        this.itemsMateria = itemsMateria;
    }

    public Materia getMateriaSelected() {
        return materiaSelected;
    }

    public void setMateriaSelected(Materia materiaSelected) {
        this.materiaSelected = materiaSelected;
    }

    public NivelAcademicoFacade getEjbFacadeNivel() {
        return ejbFacadeNivel;
    }

    public void setEjbFacadeNivel(NivelAcademicoFacade ejbFacadeNivel) {
        this.ejbFacadeNivel = ejbFacadeNivel;
    }

    public List<NivelAcademico> getItemsNivelAcademico() {
        return itemsNivelAcademico;
    }

    public void setItemsNivelAcademico(List<NivelAcademico> itemsNivelAcademico) {
        this.itemsNivelAcademico = itemsNivelAcademico;
    }

    public NivelAcademico getNivelAcademicoSelected() {
        return nivelAcademicoSelected;
    }

    public void setNivelAcademicoSelected(NivelAcademico nivelAcademicoSelected) {
        this.nivelAcademicoSelected = nivelAcademicoSelected;
    }

//    public void imprimirMateriasdoc() {
//        System.out.println(tiCarreraSelected.getIdTituloCarrera());
//        System.out.println(nivelAcademicoSelected.getIdNivelAcademico());
//        System.out.println(materiaSelected.getIdMateria());
//    }
    private byte[] reportPdf;
    private final String logotipo = "/reportes/logo.jpg";

    public void imprimirMateriasdoc() throws URISyntaxException, JRException, Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/MateriaDocente.jasper").toURI());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

        if (jasperReport != null) {
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("ciclo", nivelAcademicoSelected.getIdNivelAcademico());
            parametros.put("materia", materiaSelected.getIdMateria());
            parametros.put("titulo_carrera", tiCarreraSelected.getIdTituloCarrera());
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

//            JasperExportManager.exportReportToPdfFile(jasperPrint, "");
        }
    }
    
    public Connection getConnection() throws Exception {
    final String DATASOURCE_CONTEXT = "java:app/sistema_gestion"; //nombre de tu pool de conexiones
    Context initialContext = new InitialContext();
    DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
    return datasource.getConnection();
}
}

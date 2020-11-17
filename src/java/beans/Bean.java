package beans;

import beans.util.SessionUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.DatosPersonales;
import modelo.PerfilAcademico;
import modelo.PeriodoAcademico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
@Named(value = "bean")
@SessionScoped

public class Bean implements Serializable {

    @EJB
    private dao.MatriculaFacade ejbFacadeMa;
    @EJB
    private dao.DatosPersonalesFacade ejbFacadeDP;
//    @EJB
//    private PeriodoAcademicoFacade ejbFacadePeriodo;
    private static final long serialVersionUID = 1L;

    private String text;
    private String resultado;

    private String parametro;
    private String parametroFicha;
    private String parametroFicha2;
    private String datos = "";
    private String param1;
    private String param2;
    private String param3;
    private String paramCertificado;
    private String param1Nomina;
    private String param2Nomina;
    private String param3Nomina;
    private final String logotipo = "/reportes/logo.jpg";
    private final String logotipo1 = "/reportes/logo1.png";
    private DatosPersonales datosPersonales = null;

//    private List<PerfilAcademico> itemsPerfilAcademico = null;
    private PeriodoAcademico periodoAcademicoSelected;
//    private List<PerfilAcademico> lista = null;

    public Bean() {
    }

    public String getDatos() {
        String Sesionpersona = "";
        String p = SessionUtils.getUser().getIdDatosPersonales().getNumIdentificacion();
        Sesionpersona = p;
        return Sesionpersona;
    }

    public void filtroper() {
        getDatos();
    }

    public String getParam1Nomina() {
        return param1Nomina;
    }

    public void setParam1Nomina(String param1Nomina) {
        this.param1Nomina = param1Nomina;
    }

    public String getParam2Nomina() {
        return param2Nomina;
    }

    public void setParam2Nomina(String param2Nomina) {
        this.param2Nomina = param2Nomina;
    }

    public String getParam3Nomina() {
        return param3Nomina;
    }

    public void setParam3Nomina(String param3Nomina) {
        this.param3Nomina = param3Nomina;
    }

    public String getParamCertificado() {
        return paramCertificado;
    }

    public void setParamCertificado(String paramCertificado) {
        this.paramCertificado = paramCertificado;
    }

    public String getParametroFicha2() {
        return parametroFicha2;
    }

    public void setParametroFicha2(String parametroFicha2) {
        this.parametroFicha2 = parametroFicha2;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getParametroFicha() {
        return parametroFicha;
    }

    public void setParametroFicha(String parametroFicha) {
        this.parametroFicha = parametroFicha;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public PeriodoAcademico getPeriodoAcademicoSelected() {
        return periodoAcademicoSelected;
    }

    public void setPeriodoAcademicoSelected(PeriodoAcademico periodoAcademicoSelected) {
        this.periodoAcademicoSelected = periodoAcademicoSelected;
    }

    
  

//    public PeriodoAcademicoFacade getEjbFacadePeriodo() {
//        return ejbFacadePeriodo;
//    }
//
//    public void setEjbFacadePeriodo(PeriodoAcademicoFacade ejbFacadePeriodo) {
//        this.ejbFacadePeriodo = ejbFacadePeriodo;
//    }

//
//    public void recibir(){
//    String param1 = getParametro() ;
//        setParametrorecibido(param1);
//    }
    private byte[] reportPdf;
    private byte[] reportficha;

    public void doImprimirReportJasper() throws Exception {
        reportPdf = null;

        File fichero = new File("C:\\Users\\JANETH\\Documents\\NetBeansProjects\\SistemaWebAcademico1\\src\\java\\reportes\\notas.jasper");
        String rutaJrxml = ("C:\\Users\\JANETH\\Documents\\NetBeansProjects\\SistemaWebAcademico1\\src\\java\\reportes\\notas.jrxml");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);
        System.out.println("1");
        if (jasperReport != null) {
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("num_identificacion", getDatos());

            //Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(rutaJrxml);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            //Exportamos el reporte a pdf y lo guardamos en disco

            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.addHeader("Content-disposition", "attachment; filename=notas.pdf");

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();

//         JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
        }
    }

    public void imprimirCertificado() throws Exception {
        reportPdf = null;
        if (ejbFacadeMa.obtenerMatricula2(paramCertificado) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Estudiante no matriculado.", ""));

        } else {
            File fichero = new File(getClass().getResource("/reportes/CertificadoMatricula2.jasper").toURI());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);
            if (jasperReport != null) {
                Map parametros = new HashMap();
                //parametros que enviamos al report.
                parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
                parametros.put("num_identificacion", getParamCertificado());
                parametros.put("periodo", getPeriodoAcademicoSelected().getIdPeriodoAcademico());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
                //Exportamos el reporte a pdf y lo guardamos en disco
                reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

                response.addHeader("Content-disposition", "attachment; filename=certificado.pdf");

                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            }
        }
    }

    public void imprimirNotas() throws Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/notas.jasper").toURI());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);
        if (jasperReport != null) {
            Map parametros = new HashMap();
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            //parametros que enviamos al report.
            parametros.put("num_identificacion", getParametro());

            //Compilamos el archivo XML y lo cargamos en memoria
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            //Exportamos el reporte a pdf y lo guardamos en disco

            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.addHeader("Content-disposition", "attachment; filename=notas.pdf");

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();

//         JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
        }
    }

    public void imprimirNotas2(String cedula) throws Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/notas.jasper").toURI());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);
        if (jasperReport != null) {
            Map parametros = new HashMap();
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("num_identificacion", cedula);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=notas.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        }
    }

    public void doImprimirFicha() throws Exception {
        reportPdf = null;
        if (ejbFacadeMa.obtenerMatricula2(parametroFicha) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Estudiante no matriculado.", ""));

        } else {

            File fichero = new File(getClass().getResource("/reportes/FormularioMatricula.jasper").toURI());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

            if (jasperReport != null) {
                Map parametros = new HashMap();
                //parametros que enviamos al report.
                parametros.put("num_identificacion", getParametroFicha());
                parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
                parametros.put("logo1", this.getClass().getResourceAsStream(logotipo1));
                parametros.put("logoA", this.getClass().getResourceAsStream(logotipo));
                parametros.put("logoB", this.getClass().getResourceAsStream(logotipo1));

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
                //Exportamos el reporte a pdf y lo guardamos en disco

                reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

                response.addHeader("Content-disposition", "attachment; filename=formularioMatricula.pdf");

                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();

//         JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
            }
        }
    }

    public void doImprimirFicha2(String cedula) throws Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/FormularioMatricula.jasper").toURI());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);
        if (jasperReport != null) {
            Map parametros = new HashMap();
            parametros.put("num_identificacion", cedula);
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("logo1", this.getClass().getResourceAsStream(logotipo1));
            parametros.put("logoA", this.getClass().getResourceAsStream(logotipo));
            parametros.put("logoB", this.getClass().getResourceAsStream(logotipo1));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=formularioMatricula.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        }
    }

    public void imprimirfichaFoto() throws Exception {
        String relativePath = "";
        datosPersonales = null;
        datosPersonales = ejbFacadeDP.obtenerFoto(parametroFicha2);
        if (datosPersonales != null) {
            if (datosPersonales.getFoto().equals("foto/0000000000.png")) {
                relativePath = "/resources/foto/0000000000.png";
            } else {
                relativePath = "/resources/" + datosPersonales.getFoto();
            }
        } else {
            relativePath = "/resources/foto/0000000000.png";
        }

        String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        InputStream in = new FileInputStream(absolutePath);
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/Fichafoto2.jasper").toURI());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

        if (jasperReport != null) {
            System.out.println("Reporte:  " + fichero);
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("num_identificacion", getParametroFicha2());
            parametros.put("foto", in);

            //Compilamos el archivo XML y lo cargamos en memoria
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            //Exportamos el reporte a pdf y lo guardamos en disco

            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.addHeader("Content-disposition", "attachment; filename=fichafoto.pdf");

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();

//         JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
        }
    }

    public void imprimirMateriasdoc() throws URISyntaxException, JRException, Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/MateriaDocente.jasper").toURI());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

        if (jasperReport != null) {
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("ciclo", getParam1());
            parametros.put("materia", getParam2());
            parametros.put("titulo_carrera", getParam3());
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

            JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
        }
    }

    public void imprimirNomina() throws Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/NominaEstudiantes.jasper").toURI());
        // String rutaJrxml = ("C:\\Users\\JANETH\\Documents\\NetBeansProjects\\SistemaGestion3\\src\\java\\reportes\\NominaEstudiantes.jrxml");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

        if (jasperReport != null) {
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("nivel", getParam1Nomina());
            parametros.put("periodo", getParam2Nomina());
            parametros.put("titulo_carrera", getParam3Nomina());
            System.out.println(getParam1Nomina());
            System.out.println(getParam2Nomina());
            //Compilamos el archivo XML y lo cargamos en memoria
            // jasperReport = JasperCompileManager.compileReport(rutaJrxml);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            //Exportamos el reporte a pdf y lo guardamos en disco

            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.addHeader("Content-disposition", "attachment; filename=nomina.pdf");

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();

//         JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
        }
    }

    public void imprimirNominaExcel() throws Exception {
        reportPdf = null;

        File fichero = new File(getClass().getResource("/reportes/NominaEstudiantes.jasper").toURI());
        // String rutaJrxml = ("C:\\Users\\JANETH\\Documents\\NetBeansProjects\\SistemaGestion3\\src\\java\\reportes\\NominaEstudiantes.jrxml");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

        if (jasperReport != null) {
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
            parametros.put("nivel", getParam1Nomina());
            parametros.put("periodo", getParam2Nomina());
            parametros.put("titulo_carrera", getParam3Nomina());
            System.out.println(getParam1Nomina());
            System.out.println(getParam2Nomina());
            //Compilamos el archivo XML y lo cargamos en memoria
            // jasperReport = JasperCompileManager.compileReport(rutaJrxml);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());
            //Exportamos el reporte a pdf y lo guardamos en disco

            reportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.addHeader("Content-disposition", "attachment; filename=nomina.xls");

            ServletOutputStream stream = response.getOutputStream();
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
            exporter.exportReport();
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();

//         JasperExportManager.exportReportToPdfFile(jasperPrint, parametro);
        }
    }

//    public static Connection conectar() {
//        Connection con = null;
//
//        try {
//            String url = "jdbc:mysql://158.106.189.175:3306/sistema_gestion?user=glass_gestion01&password=systema_gestion01ITSL";
//            con = (Connection) DriverManager.getConnection(url);
//            if (con != null) {
//                System.out.println("Conexion Satisfactoria");
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return con;
//    }
    public Connection getConnection() throws Exception {
        final String DATASOURCE_CONTEXT = "java:app/sistema_gestion"; //nombre de tu pool de conexiones
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        return datasource.getConnection();
    }

//    public static Connection getConnection() throws Exception {
//        Connection con = null;
//        // Establece el nombre del driver a utilizar
//        String dbDriver = "com.mysql.jdbc.Driver";
//        // Establece la conexion a utilizar contra la base de datos
//        String dbConnString = "jdbc:mysql://158.106.189.175:3306/sistema_gestion";
//        // Establece el usuario de la base de datos
//        String dbUser = "glass_gestion01";
//        // Establece la contrase�a de la base de datos
//        String dbPassword = "systema_gestion01ITSL";
//        // Establece el driver de conexi�n
////        Class.forName(dbDriver).newInstance();
//        // Retorna la conexi�n
//        con = (Connection) DriverManager.getConnection(dbConnString, dbUser, dbPassword);
//        if (con != null) {
//            System.out.println("Conexion Satisfactoria");
//        }
//
//        return con;
//    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResultado() {
        return resultado;
    }

}

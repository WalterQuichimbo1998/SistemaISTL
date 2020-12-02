/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.MateriaFacade;
import dao.MatriculaFacade;
import dao.NivelAcademicoFacade;
import dao.NotasFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.Materia;
import modelo.Matricula;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

/**
 *
 * @author Lenovo
 */
@Named(value = "exportarNotasMaterias")
@SessionScoped
public class ExportarNotasMaterias implements Serializable {

    @EJB
    private MatriculaFacade eFacadeMa;
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
    private List<Notas> listaN = null;
    private List<Materia> listaM = null;
    private List<Matricula> listaEstu = null;

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
            lista = ejbFacadeNotas.verificarMaterias(periodoSelected.getIdPeriodoAcademico(), nivelAcademicoSelected.getIdNivelAcademico(), materiaSelected.getIdMateria(), tituloCarreraSelected.getIdTituloCarrera());

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

    public void reporteExcel() {
        if (getNivelAcademicoSelected() != null) {
            listaM = ejbFacade.listaMaterias(getNivelAcademicoSelected().getIdNivelAcademico());
            listaEstu=null;
            listaEstu = eFacadeMa.obtenerMatriculaEstu(getNivelAcademicoSelected().getIdNivelAcademico());
            if (listaEstu.size()>0) {

                HSSFWorkbook wb = new HSSFWorkbook();
                HSSFSheet sheet = wb.createSheet();
                CellStyle style = wb.createCellStyle();

                HSSFCellStyle csv = wb.createCellStyle();
                csv.setRotation((short) 90);
                csv.setAlignment(CellStyle.ALIGN_CENTER);
                csv.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
             
                Font font = wb.createFont();
                font.setFontName("Arial");

                style.setFillForegroundColor((short) 30);
                style.setBottomBorderColor((short) 8);
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                style.setWrapText(true);

                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font.setColor(HSSFColor.WHITE.index);
                style.setFont(font);

                Font cellFont = wb.createFont();
                cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
                cellFont.setFontHeightInPoints((short) 16);
                cellFont.setFontName("Arial");

                Font cellFont2 = wb.createFont();
                cellFont2.setBoldweight(Font.BOLDWEIGHT_BOLD);
                cellFont2.setFontHeightInPoints((short) 10);
                cellFont2.setFontName("Arial");

                HSSFCellStyle cst = wb.createCellStyle();
                cst.setAlignment(CellStyle.ALIGN_CENTER);
                cst.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                cst.setFont(cellFont);

                HSSFCellStyle cst2 = wb.createCellStyle();
                cst2.setAlignment(CellStyle.ALIGN_CENTER);
                cst2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                cst2.setFont(cellFont2);

                HSSFCellStyle cs = wb.createCellStyle();
                cs.setAlignment(CellStyle.ALIGN_CENTER);
                cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

                HSSFRow filaTitulo = sheet.createRow(0);

                filaTitulo.createCell(0).setCellValue("ISNTITUTO SUPERIOR TECNOLOGÍCO LIMÓN");
                filaTitulo.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(0, 1, 0, (listaM.size() * 4) + 4));
                filaTitulo.getCell(0).setCellStyle(cst);

                HSSFRow filaTitulo2 = sheet.createRow(2);
                filaTitulo2.createCell(0).setCellValue("PERÍODO - " + getPeriodoSelected().getNombre());
                filaTitulo2.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(2, 2, 0, (listaM.size() * 4) + 4));
                filaTitulo2.getCell(0).setCellStyle(cst2);

                HSSFRow filaTitulo3 = sheet.createRow(3);
                filaTitulo3.createCell(0).setCellValue("");
                filaTitulo3.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(3, 3, 0, (listaM.size() * 4) + 4));

                HSSFRow filaTitulo4 = sheet.createRow(4);
                filaTitulo4.createCell(0).setCellValue(getNivelAcademicoSelected().getNivelAcademico() + " CICLO - " + getTituloCarreraSelected().getNombreTitulo());
                filaTitulo4.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(4, 4, 0, (listaM.size() * 4) + 4));
                filaTitulo4.getCell(0).setCellStyle(cst2);

                HSSFRow filaTitulo5 = sheet.createRow(5);
                filaTitulo5.createCell(0).setCellValue("");
                filaTitulo5.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(5, 5, 0, (listaM.size() * 4) + 4));

                HSSFRow filaTitulo6 = sheet.createRow(6);
                filaTitulo6.createCell(0).setCellValue("CUADRO DE NOTAS FINALES");
                filaTitulo6.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(6, 6, 0, (listaM.size() * 4) + 4));
                filaTitulo6.getCell(0).setCellStyle(cst2);

                HSSFRow filaTitulo7 = sheet.createRow(7);
                filaTitulo7.createCell(0).setCellValue("");
                filaTitulo7.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(7, 7, 0, (listaM.size() * 4) + 4));

                HSSFRow row = sheet.createRow(8);

                HSSFCell cell;
                row.createCell(0).setCellValue("N");
                row.getCell(0).setCellStyle(style);
                row.getCell(0).getSheet().addMergedRegion(new CellRangeAddress(8, 10, 0, 0));

                row.createCell(1).setCellValue("Cédula");
                row.getCell(1).setCellStyle(style);
                row.getCell(1).getSheet().addMergedRegion(new CellRangeAddress(8, 10, 1, 1));

                row.createCell(2).setCellValue("Estudiante");
                row.getCell(2).setCellStyle(style);
                row.getCell(2).getSheet().addMergedRegion(new CellRangeAddress(8, 10, 2, 3));

                int c = 4;
                int r = 7;
                for (int i = 0; i < listaM.size(); i++) {
                    row.createCell(c).setCellValue(listaM.get(i).getMateria());
                    row.getCell(c).setCellStyle(style);
                    row.getCell(c).getSheet().addMergedRegion(new CellRangeAddress(8, 9, c, r));
                    c = c + 4;
                    r = r + 4;
                }
                HSSFRow row2 = sheet.createRow(10);
                int c2 = 4;
                int r2 = 7;

                for (int i = 0; i < listaM.size(); i++) {
                    row2.createCell(c2).setCellValue("Primer Parcial");
                    row2.getCell(c2).setCellStyle(csv);
                    c2++;
                    row2.createCell(c2).setCellValue("Segundo Parcial");
                    row2.getCell(c2).setCellStyle(csv);
                    c2++;
                    row2.createCell(c2).setCellValue("Supletorio");
                    row2.getCell(c2).setCellStyle(csv);
                    c2++;
                    row2.createCell(c2).setCellValue("Nota Final");
                    row2.getCell(c2).setCellStyle(csv);
                    c2++;

                }

                int f = 11;
                for (int j = 0; j < listaEstu.size(); j++) {
                    HSSFRow fila = sheet.createRow(f);
                    fila.createCell(0).setCellValue(j + 1);//fila 5 coluna 0
                    fila.getCell(0).setCellStyle(cs);
                    fila.createCell(1).setCellValue(listaEstu.get(j).getIdDatosPersonales().getNumIdentificacion());//fila 5 coluna 1
                    fila.getCell(1).getSheet().addMergedRegion(new CellRangeAddress(f, f, 2, 3));
                    fila.getCell(1).setCellStyle(cs);
                    fila.createCell(2).setCellValue(listaEstu.get(j).getIdDatosPersonales().getNombres()+" "+listaEstu.get(j).getIdDatosPersonales().getApellidos());//fila 5 coluna 2
                    fila.getCell(2).setCellStyle(cs);
                    int c4 = 4;
                    double p = 0;
                    for (int i = 0; i < listaM.size(); i++) {
                        listaN = null;
                        listaN = ejbFacadeNotas.notaEstudiante(listaEstu.get(j).getIdNivelAcademico().getIdNivelAcademico(), listaM.get(i).getIdMateria(), listaEstu.get(j).getIdDatosPersonales().getIdDatosPersonales());
                        if (listaN.size() > 0) {
                            for (int j2 = 0; j2 < listaN.size(); j2++) {
                                fila.createCell(c4).setCellValue(listaN.get(j2).getParcialUno());
                                fila.getCell(c4).setCellStyle(cs);
                                c4++;
                                fila.createCell(c4).setCellValue(listaN.get(j2).getParcialDos());
                                fila.getCell(c4).setCellStyle(cs);
                                c4++;
                                fila.createCell(c4).setCellValue(listaN.get(j2).getNotaSupletorio() == null ? 0 : listaN.get(j2).getNotaSupletorio());
                                fila.getCell(c4).setCellStyle(cs);
                                c4++;
                                fila.createCell(c4).setCellValue(listaN.get(j2).getNotaFinal());
                                fila.getCell(c4).setCellStyle(cs);
                                p = p + listaN.get(j2).getNotaFinal();
                                c4++;

                            }
                        } else {
                            fila.createCell(c4).setCellValue(0);
                            fila.getCell(c4).setCellStyle(cs);
                            c4++;
                            fila.createCell(c4).setCellValue(0);
                            fila.getCell(c4).setCellStyle(cs);
                            c4++;
                            fila.createCell(c4).setCellValue(0);
                            fila.getCell(c4).setCellStyle(cs);
                            c4++;
                            fila.createCell(c4).setCellValue(0);
                            fila.getCell(c4).setCellStyle(cs);
                            c4++;
                        }
                        listaN = null;
                    }
                    fila.createCell(c4).setCellValue(fijarNumero((p / listaM.size()), 2));
                    fila.getCell(c4).setCellStyle(cs);
                    f++;
                    row.createCell(c4).setCellValue("Promedio Global");
                    row.getCell(c4).setCellStyle(csv);
                    row.getCell(c4).getSheet().addMergedRegion(new CellRangeAddress(8, 10, c4, c4));
                }
                try {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    ExternalContext externalContext = facesContext.getExternalContext();
                    externalContext.setResponseContentType("application/vnd.ms-excel");
                    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"reporte_Notas.xls\"");
                    wb.write(externalContext.getResponseOutputStream());
                    facesContext.responseComplete();

                } catch (IOException e) {
                    System.out.println(e);

                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Estudiantes no matriculados en este ciclo.", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selecccione un ciclo.", ""));
        }
    }

    public double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }
}

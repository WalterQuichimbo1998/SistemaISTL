/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.util.SessionUtils;
import dao.DatosPersonalesFacade;
import modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import dao.UsuarioFacade;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.DatosPersonales;
import modelo.Matricula;

@Named(value = "accesoBean")
@SessionScoped
public class AccesoBean implements Serializable {

    public final static String USER_KEY = "auth_user";
    private static final long serialVersionUID = 1094801825228386363L;
    private String message;
    @EJB
    private UsuarioFacade ejbFacade;
    @EJB
    private DatosPersonalesFacade ejbFacadeDatos;
    @EJB
    private dao.MatriculaFacade ejbFacadeMa;
    private Usuario selected;
    private DatosPersonales seleccion;

    public AccesoBean() {
        selected = new Usuario();
    }

    public void doLogin() {
        Usuario us = getEjbFacade().validarUsuario(selected.getUsuario(), selected.getClave());
        message = "bien";
        if (us != null) {
            if (us.getUsuario().equals(selected.getUsuario())) {
                if (us.getClave().equals(selected.getClave())) {
                    try {
                        switch (us.getIdTipoOperador().getIdTipoOperador()) {
                            case 1:
                                asignarRecursoWeb("/vista/template.xhtml", us);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                                break;
                            case 2:
                                asignarRecursoWeb("/Estudiante/templateEstudiante.xhtml", us);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                                Matricula ma = null;
                                try {
                                    ma = ejbFacadeMa.obtenerMatricula(us.getIdDatosPersonales().getIdDatosPersonales());
                                } catch (Exception ex) {
                                }
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("matricula", ma);
                                break;
                            case 3:
                                asignarRecursoWeb("/Profesor/templateProfesor.xhtml", us);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                                break;
                            default:
                                break;
                        }
                        message = "";

                    } catch (IOException e) {
                    }
                } else {
                    message = "Clave incorrecta";
                }

            } else {
                message = "Usuario ínválido";
            }
        } else {
            message = "Usuario o clave incorrecto";
        }
    }

    public void registrar() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String redirect = "/Registro/templateRegistro.xhtml";
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, redirect);

    }

    public void asignarRecursoWeb(String path, Usuario us) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        String url = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, path));
        extContext.getSessionMap().put(USER_KEY, us);
        extContext.redirect(url);
    }

    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        extContext.getSessionMap().remove(USER_KEY);
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        String url = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/index.xhtml"));
        extContext.redirect(url);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public DatosPersonalesFacade getEjbFacadeDatos() {
        return ejbFacadeDatos;
    }

    public void setEjbFacade(UsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public DatosPersonales getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(DatosPersonales seleccion) {
        this.seleccion = seleccion;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

    public void ValidarEmail(String[] args) {

        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        String email = "info@programacionextrema.com";

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
        }
    }

    public static Usuario obtenerIdPersona() {
        Usuario us = null;
        try {
            us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        } catch (Exception e) {
        }
        return us;
    }

    public static Matricula obtenerMatricula() {
        Matricula ma = null;
        try {
            ma = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matricula");
        } catch (Exception e) {
        }
        return ma;
    }

    public Matricula obtenerMatricula1() {
        Matricula ma = null;
        try {
            ma = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matricula");
        } catch (Exception e) {
        }
        return ma;
    }

}

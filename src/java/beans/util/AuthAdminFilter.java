/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.util;

import beans.AccesoBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 *
 */
//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthAdminFilter implements Filter {

    private FilterConfig configuration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.configuration = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, NullPointerException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        if (((HttpServletRequest) request).getSession().getAttribute(AccesoBean.USER_KEY) == null) {
            ((HttpServletResponse) response).sendRedirect("../accessNegado.xhtml");
        } else {
            Usuario u = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
            String[] ruta = httpRequest.getRequestURI().split("/");
            String r1 = ruta[2];
            if ("Estudiante".equals(u.getIdTipoOperador().getOperador()) && !"Estudiante".equals(r1)) {
                ((HttpServletResponse) response).sendRedirect("../Estudiante/templateEstudiante.xhtml");
            } else if ("Profesor".equals(u.getIdTipoOperador().getOperador()) && !"Profesor".equals(r1)) {
                ((HttpServletResponse) response).sendRedirect("../Profesor/templateProfesor.xhtml");
            } else if ("Administrador".equals(u.getIdTipoOperador().getOperador()) && !"vista".equals(r1)) {
                ((HttpServletResponse) response).sendRedirect("../vista/template.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }

    }

    @Override
    public void destroy() {
        configuration = null;
    }
}

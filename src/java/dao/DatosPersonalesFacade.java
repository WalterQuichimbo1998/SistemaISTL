/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.DatosPersonales;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class DatosPersonalesFacade extends AbstractFacade<DatosPersonales> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatosPersonalesFacade() {
        super(DatosPersonales.class);
    }
     public DatosPersonales obtenerFoto(String cedula) {
        DatosPersonales datosPersonales = null;
        try {
            Query q = em.createNativeQuery("SELECT id_datos_personales,num_identificacion,foto FROM datos_personales WHERE num_identificacion = '" + cedula + "';", DatosPersonales.class);
            datosPersonales = (DatosPersonales) q.getSingleResult();
        } catch (Exception e) {
        }
        return datosPersonales;
    }
     public DatosPersonales verificarCedula(String cedula) {
        DatosPersonales datosPersonales = null;
        try {
            Query q = em.createNativeQuery("SELECT id_datos_personales,num_identificacion FROM datos_personales WHERE num_identificacion = '" + cedula + "';", DatosPersonales.class);
            datosPersonales = (DatosPersonales) q.getSingleResult();
        } catch (Exception e) {
        }
        return datosPersonales;
    }
     public DatosPersonales obtenerDatos(Integer id) {
        DatosPersonales datosPersonales = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM datos_personales WHERE id_datos_personales = '" + id + "';", DatosPersonales.class);
            datosPersonales = (DatosPersonales) q.getSingleResult();
        } catch (Exception e) {
        }
        return datosPersonales;
    }
      public List<DatosPersonales> listaEstudiantes() {
        List<DatosPersonales> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT datos_personales.id_datos_personales,datos_personales.nombres,datos_personales.apellidos,matricula.id_matricula FROM matricula\n"
                    + " LEFT JOIN datos_personales ON datos_personales.id_datos_personales= matricula.id_datos_personales \n", DatosPersonales.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
      public List<DatosPersonales> listaEstudiantesNotas(Integer id) {
        List<DatosPersonales> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT datos_personales.id_datos_personales,datos_personales.nombres,datos_personales.apellidos,matricula.id_matricula FROM matricula\n"
                    + " LEFT JOIN datos_personales ON datos_personales.id_datos_personales= matricula.id_datos_personales \n"
                    +"WHERE matricula.id_nivel_academico='" + id + "';", DatosPersonales.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
      public List<DatosPersonales> listaDocentes() {
        List<DatosPersonales> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT datos_personales.id_datos_personales,datos_personales.nombres,datos_personales.apellidos FROM datos_personales\n"
                    + "LEFT JOIN usuario ON usuario.id_datos_personales= datos_personales.id_datos_personales \n"
                    + "LEFT JOIN tipo_operador ON tipo_operador.id_tipo_operador= usuario.id_tipo_operador \n"
                    +"WHERE tipo_operador.id_tipo_operador='" + 3 + "';", DatosPersonales.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
        public List<DatosPersonales> listaEstudiantesCiclos(Integer id) {
        List<DatosPersonales> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT datos_personales.id_datos_personales,datos_personales.nombres,datos_personales.apellidos,matricula.id_matricula FROM datos_personales\n"
                    + " LEFT JOIN matricula ON matricula.id_datos_personales = datos_personales.id_datos_personales \n"
                    +"WHERE matricula.id_nivel_academico='" + id + "';", DatosPersonales.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}

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
import modelo.Matricula;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class MatriculaFacade extends AbstractFacade<Matricula> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatriculaFacade() {
        super(Matricula.class);
    }

    public Matricula obtenerMatricula(Integer id) {
        Matricula ma = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM matricula WHERE id_datos_personales='" + id + "';", Matricula.class);
            ma = (Matricula) q.getSingleResult();
        } catch (Exception e) {
        }
        return ma;
    }
    


    public Matricula obtenerMatricula2(String cedula) {
        Matricula ma = null;
        try {
            Query q = em.createNativeQuery("SELECT datos_personales.id_datos_personales,matricula.id_matricula FROM datos_personales \n"
                    + "LEFT JOIN matricula ON datos_personales.id_datos_personales = matricula.id_datos_personales\n"
                    + "WHERE\n"
                    + "datos_personales.num_identificacion = '"+cedula+"';", Matricula.class);
            ma = (Matricula) q.getSingleResult();
        } catch (Exception e) {
        }
        return ma;
    }
    public Matricula virificarMatricula(Integer id) {

        Matricula ma = null;
        try {
            Query q = em.createNativeQuery("SELECT id_matricula,id_datos_personales FROM matricula WHERE id_datos_personales='" + id + "';", Matricula.class);
            ma = (Matricula) q.getSingleResult();
        } catch (Exception e) {
        }
        return ma;
    }
    
      public List<Matricula> obtenerMatriculaEstu(Integer id) {
            Query q = em.createNativeQuery("SELECT matricula.id_matricula,matricula.id_datos_personales,matricula.id_nivel_academico,datos_personales.apellidos FROM matricula \n"
                    + " LEFT JOIN datos_personales ON datos_personales.id_datos_personales = matricula.id_datos_personales\n"
                    +" WHERE id_nivel_academico='" + id + "' ORDER BY datos_personales.apellidos ASC;", Matricula.class);
        List<Matricula> lista = q.getResultList();
        return lista;
    }

     
}

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
import modelo.MatriculaHistorial;

/**
 *
 * @author CyberMás
 */
@Stateless
public class MatriculaHistorialFacade extends AbstractFacade<MatriculaHistorial> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatriculaHistorialFacade() {
        super(MatriculaHistorial.class);
    }
    public MatriculaHistorial virificarMatriculaHistorial(Integer idN,Integer idP,Integer idM) {
        MatriculaHistorial ma = null;
        try {
            Query q = em.createNativeQuery("SELECT id_matricula_historial,id_nivel_academico,id_periodo_academico FROM matricula_historial WHERE id_matricula='" + idM + "' AND id_nivel_academico ='" + idN + "' AND id_periodo_academico ='" + idP +"';", MatriculaHistorial.class);
            ma = (MatriculaHistorial) q.getSingleResult();
        } catch (Exception e) {
        }
        return ma;
    }
     public List<MatriculaHistorial> listaMatriculaHistorial(Integer id) {
        List<MatriculaHistorial> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM matricula_historial WHERE id_matricula =" + id+ ";", MatriculaHistorial.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}

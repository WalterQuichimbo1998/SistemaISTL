/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Matricula;

/**
 *
 * @author JANETH
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
        Matricula matricula = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM datos_personales\n"
                    + "LEFT JOIN persona ON persona.id_datos_personales=datos_personales.id_datos_personales\n"
                    + "LEFT JOIN matricula ON matricula.id_persona=persona.id_persona\n"
                    + "WHERE datos_personales.id_datos_personales='" + id + "';", Matricula.class);
            matricula = (Matricula) q.getSingleResult();
        } catch (Exception e) {
        }
        return matricula;
    }

}

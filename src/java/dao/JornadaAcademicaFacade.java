/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.JornadaAcademica;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class JornadaAcademicaFacade extends AbstractFacade<JornadaAcademica> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JornadaAcademicaFacade() {
        super(JornadaAcademica.class);
    }
    
}

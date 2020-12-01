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
import modelo.PeriodoAcademico;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class PeriodoAcademicoFacade extends AbstractFacade<PeriodoAcademico> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoAcademicoFacade() {
        super(PeriodoAcademico.class);
    }
    public List<PeriodoAcademico> lista() {
        List<PeriodoAcademico> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM periodo_academico WHERE estado =true;", PeriodoAcademico.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    
}

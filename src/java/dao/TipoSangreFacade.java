/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TipoSangre;

/**
 *
 * @author JANETH
 */
@Stateless
public class TipoSangreFacade extends AbstractFacade<TipoSangre> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoSangreFacade() {
        super(TipoSangre.class);
    }
    
}

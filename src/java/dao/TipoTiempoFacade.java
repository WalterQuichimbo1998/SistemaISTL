/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TipoTiempo;

/**
 *
 * @author JANETH
 */
@Stateless
public class TipoTiempoFacade extends AbstractFacade<TipoTiempo> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoTiempoFacade() {
        super(TipoTiempo.class);
    }
    
}

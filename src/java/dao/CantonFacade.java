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
import modelo.Canton;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class CantonFacade extends AbstractFacade<Canton> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CantonFacade() {
        super(Canton.class);
    }
    
      public List<Canton> listaCanton(Integer id){ 
        Query q=em.createNativeQuery("SELECT * FROM canton WHERE id_provincia ="+id+";", Canton.class);  
        List<Canton> lista=q.getResultList();
        return lista;
    }

}

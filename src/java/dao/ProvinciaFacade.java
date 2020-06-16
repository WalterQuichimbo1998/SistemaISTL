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
import modelo.Provincia;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvinciaFacade() {
        super(Provincia.class);
    }
    
      public List<Provincia> listaProvincia(Integer id){ 
        Query q=em.createNativeQuery("SELECT * FROM provincia WHERE id_nacionalidad ="+id+";", Provincia.class);  
        List<Provincia> lista=q.getResultList();
        return lista;
    }


}

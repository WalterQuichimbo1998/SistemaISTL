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
import modelo.DistributivoMateria;

/**
 *
 * @author CyberMás
 */
@Stateless
public class DistributivoMateriaFacade extends AbstractFacade<DistributivoMateria> {

   @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistributivoMateriaFacade() {
        super(DistributivoMateria.class);
    }
    public List<DistributivoMateria> listaDistributivoMaterias(Integer id) {
        Query q = em.createNativeQuery("SELECT * FROM distributivo_materia WHERE id_distributivo =" + id + ";", DistributivoMateria.class);
        List<DistributivoMateria> lista = q.getResultList();
        return lista;
    }
    
}

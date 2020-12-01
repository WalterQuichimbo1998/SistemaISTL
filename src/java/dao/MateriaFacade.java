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
import modelo.Materia;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class MateriaFacade extends AbstractFacade<Materia> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaFacade() {
        super(Materia.class);
    }

    public List<Materia> listaMaterias(Integer id) {
        Query q = em.createNativeQuery("SELECT * FROM materia WHERE id_nivel_academico =" + id + ";", Materia.class);
        List<Materia> lista = q.getResultList();
        return lista;
    }

}

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
import modelo.PreRequisitosMateria;

/**
 *
 * @author CyberMás
 */
@Stateless
public class PreRequisitosMateriaFacade extends AbstractFacade<PreRequisitosMateria> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreRequisitosMateriaFacade() {
        super(PreRequisitosMateria.class);
    }

    public List<PreRequisitosMateria> listaMateriasPreLista(Integer id1) {
        Query q = em.createNativeQuery("SELECT pre_requisitos_materia.id_pre_requisitos_materia,pre_requisitos_materia.id_materia_pre,pre_requisitos_materia.id_materia_co,materia.id_materia,materia.materia FROM pre_requisitos_materia \n"
                + " LEFT JOIN materia ON materia.id_materia=pre_requisitos_materia.id_materia_pre\n"
                + " WHERE pre_requisitos_materia.id_materia='" + id1 + "';", PreRequisitosMateria.class);
        List<PreRequisitosMateria> lista = q.getResultList();
        return lista;
    }

    public PreRequisitosMateria verificarPre_requisitos(Integer id1) {
        PreRequisitosMateria preRequisitosMateria = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM pre_requisitos_materia\n"
                    + " WHERE id_materia='" + id1 + "';", PreRequisitosMateria.class);
            preRequisitosMateria = (PreRequisitosMateria) q.getSingleResult();
        } catch (Exception e) {
        }
        return preRequisitosMateria;
    }
    public List<PreRequisitosMateria> verificarCo_requisitos(Integer id1) {
        Query q = em.createNativeQuery("SELECT * FROM pre_requisitos_materia\n"
                + " WHERE id_materia_co='" + id1 + "';", PreRequisitosMateria.class);
        List<PreRequisitosMateria> lista = q.getResultList();
        return lista;
    }
}

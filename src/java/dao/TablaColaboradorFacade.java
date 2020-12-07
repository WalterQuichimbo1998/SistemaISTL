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
import modelo.TablaColaborador;

/**
 *
 * @author CyberMás
 */
@Stateless
public class TablaColaboradorFacade extends AbstractFacade<TablaColaborador> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablaColaboradorFacade() {
        super(TablaColaborador.class);
    }
     public TablaColaborador verificarColaborador(Integer id1, Integer id2) {
        TablaColaborador colaborador = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM tabla_colaborador WHERE id_tabla_investigacion='" + id1 + "' AND id_datos_personales='" + id2 + "';", TablaColaborador.class);
            colaborador = (TablaColaborador) q.getSingleResult();
        } catch (Exception e) {
        }
        return colaborador;
    }
     public List<TablaColaborador> listaColaboradores(Integer id) {
        List<TablaColaborador> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT tabla_investigacion.id_tabla_investigacion,tabla_colaborador.id_tabla_colaborador,tabla_colaborador.id_datos_personales FROM tabla_investigacion \n"
                +" LEFT JOIN tabla_colaborador ON tabla_colaborador.id_tabla_investigacion=tabla_investigacion.id_tabla_investigacion\n"
                +" WHERE tabla_investigacion.id_tabla_investigacion='" + id + "';", TablaColaborador.class);
            lista = q.getResultList();
           
        } catch (Exception e) {
        }
        return lista;
    } 
     
}

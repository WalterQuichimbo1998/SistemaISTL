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
import modelo.Distributivo;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class DistributivoFacade extends AbstractFacade<Distributivo> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistributivoFacade() {
        super(Distributivo.class);
    }
      public Distributivo verificarDistributivo(Integer id1,Integer id2) {
        Distributivo dis = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM distributivo\n"
                    +" WHERE id_datos_personales='" + id1 + "' AND id_periodo_academico_semestre='" +id2 +"';", Distributivo.class);
            dis = (Distributivo) q.getSingleResult();
        } catch (Exception e) {
        }
        return dis;
    }
      public Distributivo verificarDistributivo2(Integer id1,Integer id2,Integer id3) {
        Distributivo dis = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM distributivo\n"
                    +" WHERE id_datos_personales='" + id1 + "' AND id_periodo_academico_semestre='" +id2 +"' AND id_distributivo!='" +id3 +"';", Distributivo.class);
            dis = (Distributivo) q.getSingleResult();
        } catch (Exception e) {
        }
        return dis;
    }

}

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
//    public List<Distributivo> listaTitulos(Integer id,Integer id2) {
//        List<Distributivo> lista = null;
//        try {
//            Query q = em.createNativeQuery("SELECT DISTINCT titulo_carrera.id_titulo_carrera,titulo_carrera.nombre_titulo,distributivo.id_distributivo,distributivo.id_periodo_academico_semestre FROM distributivo \n"
//                    + " LEFT JOIN distributivo_materia ON distributivo_materia.id_distributivo= distributivo.id_distributivo  \n"
//                    + " LEFT JOIN titulo_carrera ON titulo_carrera.id_titulo_carrera= distributivo_materia.id_titulo_carrera \n"
//                    +" WHERE distributivo.id_datos_personales='" + id +"' AND distributivo.id_periodo_academico_semestre='" + id2 + "';", Distributivo.class);
//            lista = q.getResultList();
//        } catch (Exception e) {
//        }
//        return lista;
//    }
    
}

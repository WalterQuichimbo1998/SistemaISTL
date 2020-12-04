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
//    public List<Materia> listaMateriasProfersor(Integer id1,Integer id2) {
//        System.out.println("id1: "+id1);
//        System.out.println("id2: "+id2);
//        Query q = em.createNativeQuery("SELECT distributivo_materia.id_distributivo, titulo_carrera.id_titulo_carrera,titulo_carrera.nombre_titulo,materia.id_materia,materia.materia FROM distributivo_materia\n"
//                +"LEFT JOIN distributivo ON distributivo.id_distributivo= distributivo_materia.id_distributivo\n"
//                +"LEFT JOIN titulo_carrera ON titulo_carrera.id_titulo_carrera= distributivo_materia.id_titulo_carrera\n"
//                +"LEFT JOIN materia ON materia.id_materia= distributivo_materia.id_materia\n"
//                +"WHERE distributivo.id_datos_personales=" + id1 + " AND distributivo_materia.id_nivel_academico=" + id2 + ";", Materia.class);
//        List<Materia> lista = q.getResultList();
//        return lista;
//    }
//    
    

}

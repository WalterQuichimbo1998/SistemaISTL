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
    public List<Materia> listaMateriasPre(Integer id1,String nombre) {
        Query q = em.createNativeQuery("SELECT materia.id_materia,materia.materia,nivel_academico.id_nivel_academico,nivel_academico.nivel_academico FROM materia \n"
                +" LEFT JOIN nivel_academico ON nivel_academico.id_nivel_academico=materia.id_nivel_academico\n"
                +" WHERE nivel_academico.id_titulo_carrera='" + id1 +"' AND nivel_academico.nivel_academico LIKE'" + nombre + "';", Materia.class);
        List<Materia> lista = q.getResultList();
        return lista;
    }
    public List<Materia> listaMateriasCo(Integer id1,Integer id2 ) {
        Query q = em.createNativeQuery("SELECT materia.id_materia,materia.materia,nivel_academico.id_nivel_academico,nivel_academico.nivel_academico FROM materia \n"
                +" LEFT JOIN nivel_academico ON nivel_academico.id_nivel_academico=materia.id_nivel_academico\n"
                +" WHERE nivel_academico.id_titulo_carrera='" + id1 +"' AND nivel_academico.id_nivel_academico='" + id2 + "';", Materia.class);
        List<Materia> lista = q.getResultList();
        return lista;
    }
    
//    public List<Materia> listaMateriasPreLista(Integer id1) {
//        Query q = em.createNativeQuery("SELECT materia.id_materia,materia.materia,pre_requisitos_materia.id_pre_requisitos_materia,pre_requisitos_materia.id_materia_pre FROM materia \n"
//                +" LEFT JOIN pre_requisitos_materia ON pre_requisitos_materia.id_materia_pre=materia.id_materia\n"
//                +" WHERE pre_requisitos_materia.id_materia='" + id1 + "';", Materia.class);
//        List<Materia> lista = q.getResultList();
//        return lista;
//    }

    

}

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
    
    public DistributivoMateria verificarMateriaDistributivo(Integer id1,Integer id2,Integer id3) {
        DistributivoMateria nota = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM distributivo_materia\n"
                    +" WHERE id_distributivo='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_materia='" + id3 +"';", DistributivoMateria.class);
            nota = (DistributivoMateria) q.getSingleResult();
        } catch (Exception e) {
        }
        return nota;
    }
    
    public DistributivoMateria verificarMateriaDistributivo2(Integer id1,Integer id2,Integer id3,Integer id4) {
        DistributivoMateria nota = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM distributivo_materia\n"
                    +" WHERE id_distributivo='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_materia='" + id3 +"' AND id_distributivo_materia!='" + id4 +"';", DistributivoMateria.class);
            nota = (DistributivoMateria) q.getSingleResult();
        } catch (Exception e) {
        }
        return nota;
    }
    
    public List<DistributivoMateria> listaDistributivoMaterias(Integer id) {
        Query q = em.createNativeQuery("SELECT * FROM distributivo_materia WHERE id_distributivo =" + id + ";", DistributivoMateria.class);
        List<DistributivoMateria> lista = q.getResultList();
        return lista;
    }
    
    
     public List<DistributivoMateria> listaMateriasProfersorDistri(Integer id1,Integer id2) {
          List<DistributivoMateria> lista = null;
        try {
        Query q = em.createNativeQuery("SELECT distributivo_materia.id_distributivo_materia,distributivo_materia.id_distributivo, titulo_carrera.id_titulo_carrera,titulo_carrera.nombre_titulo,materia.id_materia,materia.materia FROM distributivo_materia\n"
                +"LEFT JOIN distributivo ON distributivo.id_distributivo= distributivo_materia.id_distributivo\n"
                +"LEFT JOIN titulo_carrera ON titulo_carrera.id_titulo_carrera= distributivo_materia.id_titulo_carrera\n"
                +"LEFT JOIN materia ON materia.id_materia= distributivo_materia.id_materia\n"
                +"WHERE distributivo.id_datos_personales=" + id1 + " AND distributivo_materia.id_nivel_academico=" + id2 + ";", DistributivoMateria.class);
       lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    
}

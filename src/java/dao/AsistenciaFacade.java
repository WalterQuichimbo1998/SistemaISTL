/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Asistencia;

/**
 *
 * @author CyberMás
 */
@Stateless
public class AsistenciaFacade extends AbstractFacade<Asistencia> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
       public List<Asistencia> asistenciaEstudiante(Integer id1, Integer id2, Integer id3, Integer id4) {
        List<Asistencia> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM asistencia\n"
                    + " WHERE id_datos_personales='" + id1 + "' AND id_materia='" + id2 + "' AND id_nivel_academico='" + id3 + "' AND id_periodo_academico='" + id4 + "' ORDER BY fecha_registro ASC;", Asistencia.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
       public Asistencia asistenciaEstudianteV(Integer id1, Integer id2, Integer id3, Integer id4,Date fecha) {
        Asistencia a=null;
        try {
             SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(fecha);
            Query q = em.createNativeQuery("SELECT * FROM asistencia\n"
                    + " WHERE id_datos_personales='" + id1 + "' AND id_materia='" + id2 + "' AND id_nivel_academico='" + id3 + "' AND id_periodo_academico='" + id4 +"' AND fecha_registro='" + f1 + "';", Asistencia.class);
            a = (Asistencia) q.getSingleResult();
        } catch (Exception e) {
        }
        return a;
    }
       public Asistencia asistenciaEstudianteV2(Integer id1, Integer id2, Integer id3, Integer id4,Date fecha,Integer id5) {
        Asistencia a=null;
        try {
             SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formato.format(fecha);
            Query q = em.createNativeQuery("SELECT * FROM asistencia\n"
                    + " WHERE id_datos_personales='" + id1 + "' AND id_materia='" + id2 + "' AND id_nivel_academico='" + id3 + "' AND id_periodo_academico='" + id4 +"' AND fecha_registro='" + f1 + "' AND id_asistencia!='" + id5+ "';", Asistencia.class);
            a = (Asistencia) q.getSingleResult();
        } catch (Exception e) {
        }
        return a;
    }
    
}

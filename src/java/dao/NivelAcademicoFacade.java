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
import modelo.NivelAcademico;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class NivelAcademicoFacade extends AbstractFacade<NivelAcademico> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelAcademicoFacade() {
        super(NivelAcademico.class);
    }
     public List<NivelAcademico> listaNiveles(Integer id) {
        Query q = em.createNativeQuery("SELECT * FROM nivel_academico WHERE id_titulo_carrera =" + id + ";", NivelAcademico.class);
        List<NivelAcademico> lista = q.getResultList();
        return lista;
    }
     
     public List<NivelAcademico> listaCiclos() {
        List<NivelAcademico> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT nivel_academico.id_nivel_academico,nivel_academico.nivel_academico,nivel_academico.fecha_de_registro, periodo_academico.id_periodo_academico, periodo_academico.estado FROM periodo_academico\n"
                    + " LEFT JOIN nivel_academico ON nivel_academico.id_periodo_academico=periodo_academico.id_periodo_academico \n"
                    +"WHERE periodo_academico.estado=1", NivelAcademico.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
     public List<NivelAcademico> listaNivelesT(Integer id) {
        Query q = em.createNativeQuery("SELECT * FROM nivel_academico WHERE id_titulo_carrera =" + id + ";", NivelAcademico.class);
        List<NivelAcademico> lista = q.getResultList();
        return lista;
    }
     public List<NivelAcademico> listaNivelesDistributivo(Integer persona,Integer carrera) {
        List<NivelAcademico> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT DISTINCT nivel_academico.id_nivel_academico,nivel_academico.nivel_academico,distributivo_materia.id_distributivo,datos_personales.id_datos_personales,titulo_carrera.id_titulo_carrera,titulo_carrera.nombre_titulo FROM nivel_academico\n"
                    + " LEFT JOIN distributivo_materia ON distributivo_materia.id_nivel_academico= nivel_academico.id_nivel_academico \n"
                    + " LEFT JOIN distributivo ON distributivo.id_distributivo = distributivo_materia.id_distributivo \n"
                    + " LEFT JOIN datos_personales ON datos_personales.id_datos_personales= distributivo.id_datos_personales \n"
                    + " LEFT JOIN titulo_carrera ON titulo_carrera.id_titulo_carrera= nivel_academico.id_titulo_carrera \n"
                    + " WHERE datos_personales.id_datos_personales='" + persona + "' AND nivel_academico.id_titulo_carrera='" + carrera + "'", NivelAcademico.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}


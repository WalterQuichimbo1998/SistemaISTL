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
import modelo.Notas;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class NotasFacade extends AbstractFacade<Notas> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotasFacade() {
        super(Notas.class);
    }

    public List<Notas> verificarNotas(Integer id1, Integer id2) {
        List<Notas> lista2 = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + "WHERE id_nivel_academico='" + id1 + "' AND id_titulo_carrera='" + id2 + "';", Notas.class);
            lista2 = q.getResultList();
            if (lista2 == null) {
                System.out.println("se cargo null");
            }
        } catch (Exception e) {
        }
        return lista2;
    }

    public List<Notas> verificarNotas2(Integer id1, Integer id2) {
        List<Notas> lista2 = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + "WHERE id_nivel_academico='" + id1 + "' AND id_datos_personales='" + id2 + "';", Notas.class);
            lista2 = q.getResultList();
            if (lista2 == null) {
                System.out.println("se cargo null");
            }
        } catch (Exception e) {
        }
        return lista2;
    }

    public List<Notas> verificarMaterias(Integer id1, Integer id2, Integer id3, Integer id4) {
        List<Notas> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + "WHERE id_periodo_academico='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_materia='" + id3 + "' AND id_titulo_carrera='" + id4 + "';", Notas.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public Notas verificarNota(Integer id1, Integer id2, Integer id3) {
        Notas nota = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + " WHERE id_materia='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_datos_personales='" + id3 + "';", Notas.class);
            nota = (Notas) q.getSingleResult();
        } catch (Exception e) {
        }
        return nota;
    }

    public Notas verificarNota2(Integer id1, Integer id2, Integer id3, Integer id4) {
        Notas nota = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + " WHERE id_materia='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_datos_personales='" + id3 + "' AND id_notas !='" + id4 + "';", Notas.class);
            nota = (Notas) q.getSingleResult();
        } catch (Exception e) {
        }
        return nota;
    }

    public List<Notas> listaNotas() {
        List<Notas> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas", Notas.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Notas> listaNotas(Integer id1, Integer id2) {
        List<Notas> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + "WHERE notas.id_nivel_academico='" + id1 + "' AND notas.id_materia='" + id2 + "';", Notas.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Notas> listaNotasCiclo(Integer id1, Integer id2,Integer id3) {
        List<Notas> lista2 = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + " LEFT JOIN datos_personales ON datos_personales.id_datos_personales = notas.id_datos_personales\n"
                    + "WHERE id_nivel_academico='" + id1 + "' AND id_materia='" + id2 +"' AND id_periodo_academico='" + id3 + "' ORDER BY datos_personales.apellidos ASC;", Notas.class);
            lista2 = q.getResultList();
            if (lista2 == null) {
                System.out.println("se cargo null");
            }
        } catch (Exception e) {
        }
        return lista2;
    }

    
    public List<Notas> notaEstudiante(Integer id1, Integer id2, Integer id3, Integer id4) {
        List<Notas> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + " WHERE id_nivel_academico='" + id1 + "' AND id_materia='" + id2 + "' AND id_datos_personales='" + id3 + "' AND id_periodo_academico='" + id4 + "';", Notas.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Notas> verificarNotasEstudiante(Integer id1, Integer id2, Integer id3) {
        List<Notas> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + " WHERE id_datos_personales='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_periodo_academico='" + id3 + "';", Notas.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
    public List<Notas> verificarNotasEstudiante2(Integer id1, Integer id2, Integer id3) {
        List<Notas> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM notas\n"
                    + " WHERE id_datos_personales='" + id1 + "' AND id_nivel_academico='" + id2 + "' AND id_materia='" + id3 +"';", Notas.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
     public List<Notas> obtenerNotasEstu(Integer id, Integer id2) {
        Query q = em.createNativeQuery("SELECT notas.id_notas,notas.id_datos_personales,notas.id_nivel_academico,notas.id_periodo_academico,datos_personales.num_identificacion,nombres,datos_personales.apellidos FROM notas \n"
                + " LEFT JOIN datos_personales ON datos_personales.id_datos_personales = notas.id_datos_personales\n"
                + " WHERE id_nivel_academico='" + id + "' AND id_periodo_academico='" + id2 + "' GROUP BY notas.id_datos_personales ORDER BY datos_personales.apellidos ASC;", Notas.class);
        List<Notas> lista = q.getResultList();
        return lista;
    }

}

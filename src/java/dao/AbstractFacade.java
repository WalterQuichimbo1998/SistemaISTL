/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.DatosPersonales;
import modelo.Materia;
import modelo.NivelAcademico;
import modelo.TituloCarrera;
import modelo.Usuario;

/**
 *
 * @author JANETH
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public DatosPersonales filtrarper(String num_identificacion) {
        EntityManager em = getEntityManager();
        DatosPersonales per = null;
        try {
            TypedQuery<DatosPersonales> query = em.createNamedQuery("DatosPersonales.findEstudiante", DatosPersonales.class);
            query.setParameter("num_identificacion", num_identificacion);
            try {
                per = query.getSingleResult();
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return null;

    }

    public Usuario validarUsuario(String user, String pass) {
        EntityManager em = getEntityManager();
        Usuario usario = null;
        try {
            try {
                TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUserPassword", Usuario.class);
                query.setParameter("usuario", user);
                query.setParameter("clave", pass);
                try {
                    usario = query.getSingleResult();
                } catch (NoResultException e) {
                    usario = null;
                }
            } catch (NullPointerException e) {
                usario = null;
            }
        } catch (NoResultException e) {
            usario = null;
            System.out.println("Error: " + e);
        }
        return usario;
    }
    public List<Materia> getByID(TituloCarrera tc, NivelAcademico na) {
        EntityManager em = getEntityManager();
        List<Materia> list = null;
        try {
            try {
                TypedQuery<Materia> query = em.createNamedQuery("Materia.findAllMateriaByCarrera", Materia.class);
                query.setParameter("idTituloCarrera", tc);
                query.setParameter("idNivelAcademico", na);
                try {
                    list = query.getResultList();
                } catch (NoResultException e) {
                    list = null;
                }
            } catch (NullPointerException e) {
                list = null;
            }
        } catch (NoResultException e) {
            list = null;
            System.out.println("Error: " + e);
        }
        return list;
    }
}

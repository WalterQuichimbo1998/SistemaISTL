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
import modelo.TituloCarrera;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class TituloCarreraFacade extends AbstractFacade<TituloCarrera> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TituloCarreraFacade() {
        super(TituloCarrera.class);
    }
    public List<TituloCarrera> listaTitulos(Integer id,Integer id2) {
        List<TituloCarrera> lista = null;
        try {
            Query q = em.createNativeQuery("SELECT DISTINCT titulo_carrera.id_titulo_carrera,titulo_carrera.nombre_titulo,distributivo_materia.id_distributivo,distributivo.id_periodo_academico_semestre FROM titulo_carrera \n"
                    + " LEFT JOIN distributivo_materia ON distributivo_materia.id_titulo_carrera= titulo_carrera.id_titulo_carrera \n"
                    + " LEFT JOIN distributivo ON distributivo.id_distributivo= distributivo_materia.id_distributivo \n"
                    +" WHERE distributivo.id_datos_personales='" + id +"' AND distributivo.id_periodo_academico_semestre='" + id2 + "';", TituloCarrera.class);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.DatosPersonales;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class DatosPersonalesFacade extends AbstractFacade<DatosPersonales> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatosPersonalesFacade() {
        super(DatosPersonales.class);
    }
     public DatosPersonales obtenerFoto(String cedula) {
        DatosPersonales datosPersonales = null;
        try {
            Query q = em.createNativeQuery("SELECT id_datos_personales,num_identificacion,foto FROM datos_personales WHERE num_identificacion = '" + cedula + "';", DatosPersonales.class);
            datosPersonales = (DatosPersonales) q.getSingleResult();
        } catch (Exception e) {
        }
        return datosPersonales;
    }
     public DatosPersonales obtenerDatos(Integer id) {
        DatosPersonales datosPersonales = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM datos_personales WHERE id_datos_personales = '" + id + "';", DatosPersonales.class);
            datosPersonales = (DatosPersonales) q.getSingleResult();
        } catch (Exception e) {
        }
        return datosPersonales;
    }
}

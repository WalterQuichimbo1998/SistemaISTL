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
import modelo.Usuario;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SistemaGestion3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario virifcarUsuario(Integer id) {
        Usuario us = null;
        try {
            Query q = em.createNativeQuery("SELECT id_usuario,id_datos_personales FROM usuario WHERE id_datos_personales='" + id + "';", Usuario.class);
            us = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return us;
    }
}

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
    public Usuario existeUsuarioRegistradoAdmin(String user) {
        Usuario us = null;
        try {
            Query q = em.createNativeQuery("SELECT id_usuario FROM usuario WHERE usuario='" + user + "';", Usuario.class);
            us = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return us;
    }
    public Usuario existeUsuarioRegistradoAdmin2(Integer id,String user) {
        Usuario us = null;
        try {
            Query q = em.createNativeQuery("SELECT id_usuario FROM usuario WHERE id_usuario!='" + id + "' AND usuario='" + user + "';", Usuario.class);
            us = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return us;
    }
    
    public Usuario existePersonaRegistradoAdmin(Integer id1,Integer id2) {
        Usuario us = null;
        try {
            Query q = em.createNativeQuery("SELECT id_usuario FROM usuario WHERE id_datos_personales='" + id1 + "' AND id_tipo_operador='" + id2 + "';", Usuario.class);
            us = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return us;
    }
    public Usuario existePersonaRegistradoAdmin2(Integer id1,Integer id2,Integer id3) {
        Usuario us = null;
        try {
            Query q = em.createNativeQuery("SELECT id_usuario FROM usuario WHERE id_usuario!='" + id1 + "' AND id_datos_personales='" + id2 + "' AND id_tipo_operador='" + id3 +"';", Usuario.class);
            us = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return us;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Pagina.Usuario;
import Pagina.util.NewHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author usuario
 */
public class UsuarioImplement implements ConsultUsuario {

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        Session sesion = NewHibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM usuario WHERE usuario = '"+usuario.getUsuario() +"'";
        try {
            sesion.beginTransaction();
            model = (Usuario) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        }catch (Exception e){
            sesion.beginTransaction().rollback();
        }
        return model;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario model = this.findByUsuario(usuario); //recuperamos el usuario
        if (model != null){
            if (!usuario.getContrasenia().equals(model.getContrasenia())) {
                model = null;
            }
        }
        return model;

    }
}

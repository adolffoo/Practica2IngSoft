/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Pagina.Usuario;
import dao.ConsultUsuario;
import dao.UsuarioImplement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author adolfo
 */
@ManagedBean
@RequestScoped
public class Login {


    private final HttpServletRequest httpServletRequest; 
    private final FacesContext faceContext; 
    private FacesMessage message; 

    private Usuario usuario;
    private ConsultUsuario usuarioconsult;

    /**
     * Constructor para inicializar los valores de faceContext y
     * httpServletRequest.
     */
    public Login() {
        this.usuarioconsult = new UsuarioImplement();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        if (this.usuario == null) {
            this.usuario = new Usuario();
            
                 }
        }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    /**
     * Método encargado de validar el inicio de sesión.
     *
     * @return El nombre de la vista que va a responder.
     */
    public String login() { //usuario.equals("Mariano") && contrasena.equals("joJo")
        this.usuario = this.usuarioconsult.login(this.usuario);
        if (this.usuario != null) {
            httpServletRequest.getSession().setAttribute("sessionUsuario", usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
            faceContext.addMessage(null, message);
            return "acceso";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
            faceContext.addMessage(null, message);
            if (this.usuario == null) {
            this.usuario = new Usuario();
            
                 }
            return "index";
        }
    }

}

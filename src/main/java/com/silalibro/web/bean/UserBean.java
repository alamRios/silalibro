/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.web.bean;

import com.silalibro.dao.UsuarioDAO;
import com.silalibro.dto.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author ariosa1500
 */
@ManagedBean
@ViewScoped
public class UserBean {
    /* DAO */
    private UsuarioDAO usuarioDAO_; 
    
    /* GLOBAL VARIABLES */
    private String mensaje; 
    
    /* LOGIN VARIABLES */
    private String correo_usr;
    private String passwd_usr;
    
    public UserBean(){
    }
    
    @PostConstruct
    public void setup()  {
        mensaje = "Bienvenido a Silalibro"; 
    }
        
    public void cerrarSesion(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/silalibro");
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void iniciarSesion(){
        try{
            Usuario usuario = usuarioDAO_.iniciarSesion(correo_usr,passwd_usr); 
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCorreo_usr() {
        return correo_usr;
    }

    public void setCorreo_usr(String correo_usr) {
        this.correo_usr = correo_usr;
    }

    public String getPasswd_usr() {
        return passwd_usr;
    }

    public void setPasswd_usr(String passwd_usr) {
        this.passwd_usr = passwd_usr;
    }
    
    
}

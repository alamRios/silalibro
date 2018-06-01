/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.web.bean;

import com.silalibro.dao.CuentaDAO;
import com.silalibro.dao.UsuarioDAO;
import com.silalibro.dto.UsuarioDTO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ariosa1500
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    /* DAO */
    private UsuarioDAO usuarioDAO_;
    private CuentaDAO cuentaDAO_;

    /* GLOBAL VARIABLES */
    private String mensaje;
    private UsuarioDTO usuario;

    /* LOGIN VARIABLES */
    private String correo_usr;
    private String passwd_usr;
    private boolean credencialesIncorrectas;

    public UserBean() {
    }

    @PostConstruct
    public void setup() {
        mensaje = "Bienvenido a Silalibro";
        credencialesIncorrectas = false;
        usuarioDAO_ = new UsuarioDAO();
        cuentaDAO_ = new CuentaDAO(); 
        Integer idusuario = (Integer) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("idusuario");
        try {
            if (idusuario == null) {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.getFlash().setKeepMessages(true);
                context.redirect(context.getRequestContextPath() + "/");
            }
        } catch (Exception ex) {
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/silalibro");
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void iniciarSesion() {
        try {
            usuario = usuarioDAO_.iniciarSesion(correo_usr, passwd_usr);
            if (usuario != null) {
                FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().put("idusuario", usuario.getIdusuario());
                ExternalContext context;
                if (usuario.isAdministrador()) {
                    context = FacesContext.getCurrentInstance().getExternalContext();
                    context.getFlash().setKeepMessages(true);
                    context.redirect(context.getRequestContextPath() + "/administrador/index.xhtml");
                }
                credencialesIncorrectas = false; 
            } else {
                credencialesIncorrectas = true;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Ha ocurrido un error",
                        "Credenciales incorrectas");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void cargarCuentaUsuario(){
        try {
            usuario.setCuenta(cuentaDAO_.obtenerCuentaPorUsuarioId(usuario.getIdusuario()));
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public boolean isCredencialesIncorrectas() {
        return credencialesIncorrectas;
    }

    public void setCredencialesIncorrectas(boolean credencialesIncorrectas) {
        this.credencialesIncorrectas = credencialesIncorrectas;
    }

}

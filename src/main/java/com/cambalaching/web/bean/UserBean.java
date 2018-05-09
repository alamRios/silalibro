/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.web.bean;

import com.cambalaching.model.dao.impl.jdbc.CiudadDAOImplJDBC;
import com.cambalaching.model.dao.impl.jdbc.UserDAOImplJDBC;
import com.cambalaching.model.dto.ClienteDTO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
/**
 *
 * @author ariosa1500
 */
@ManagedBean
@ViewScoped
public class UserBean {
    private UserDAOImplJDBC userDAO_; 
    private CiudadDAOImplJDBC ciudadDAO_; 
    
    
    /* LOGIN VARIABLES */
    private String user_email; 
    private String user_pass; 
    
    /* REGISTRO VARIABLES */
    private List<SelectItem> ciudades; 
    private ClienteDTO clienteNuevo; 
    private String pass; 
    
    public UserBean(){
    }
    
    @PostConstruct
    public void setup()  {
        userDAO_ = new UserDAOImplJDBC(); 
        ciudadDAO_ = new CiudadDAOImplJDBC(); 
        
        user_email = ""; 
        user_pass = ""; 
        clienteNuevo = new ClienteDTO(); 
        
        try{
            ciudades = ciudadDAO_.obtenerCiudades_DDL(); 
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error al obtener ciudades",
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void verificarIngreso(){
        try{
            int idusuario = userDAO_.login(user_email,user_pass); 
            if(idusuario != 0){
                FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().put("idusuario", idusuario);
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.getFlash().setKeepMessages(true);
                context.redirect(context.getRequestContextPath() + "/inicio/index.xhtml");
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                        "Email o contraseña incorrectos",
                        "Favor de verificar");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void registrarNuevo(){
        try{
            // Validar caracteres especiales, digito, una mayúscula y una minúscula
            if(userDAO_.registrarCliente(clienteNuevo,pass)){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Registro terminado",
                        "Ya puedes iniciar");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else
                throw new Exception("No se ha podido registrar usuario");
                
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void cerrarSesion(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/cambalaching");
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public List<SelectItem> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<SelectItem> ciudades) {
        this.ciudades = ciudades;
    }

    public ClienteDTO getClienteNuevo() {
        return clienteNuevo;
    }

    public void setClienteNuevo(ClienteDTO clienteNuevo) {
        this.clienteNuevo = clienteNuevo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}

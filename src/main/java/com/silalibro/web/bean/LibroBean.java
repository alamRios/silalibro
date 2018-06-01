/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.web.bean;
import com.silalibro.web.bean.*;
import com.silalibro.dto.LibrosDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author booz
 */
@ManagedBean
public class LibroBean {
    private LibrosDTO libroNuevo; 
    public void init(){
    libroNuevo = new LibrosDTO();
    }
    public void registrarLibro(){
        try{
            libroNuevo.setSku(sku);
            if(libro_.registrarArticulo(libroNuevo)){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "El libro guardado correctamente",
                        "Sólo espera a que sea aceptado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                throw new Exception("No se ha podido registrar el libro");
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}

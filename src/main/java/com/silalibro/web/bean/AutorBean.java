/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.web.bean;

import com.silalibro.dao.AutorDAO;
import com.silalibro.dto.AutorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author arios
 */
@ManagedBean
@ViewScoped
public class AutorBean {
    private List<AutorDTO> autores; 
    private AutorDTO autorNuevo; 
    private AutorDAO autorDAO_; 
    
    @PostConstruct
    public void init(){
        autorNuevo = new AutorDTO(); 
        autorDAO_ = new AutorDAO(); 
        autores = new ArrayList<>(); 
    }
    
    public void cargarAutores(){
        try{
            autores = autorDAO_.obtenerAutores(); 
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public AutorDTO getAutorNuevo() {
        return autorNuevo;
    }

    public void setAutorNuevo(AutorDTO autorNuevo) {
        this.autorNuevo = autorNuevo;
    }

    public AutorDAO getAutorDAO_() {
        return autorDAO_;
    }

    public void setAutorDAO_(AutorDAO autorDAO_) {
        this.autorDAO_ = autorDAO_;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores;
    }
    
}

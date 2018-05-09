/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.web.bean;

import com.cambalaching.model.dao.impl.jdbc.ArticuloDAOImplJDBC;
import com.cambalaching.model.dao.impl.jdbc.CategoriaDAOImplJDBC;
import com.cambalaching.model.dto.ArticuloDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author ariosa1500
 */
@ManagedBean
@ViewScoped
public class ArticuloBean {
    private CategoriaDAOImplJDBC categoriaDAO_; 
    private ArticuloDAOImplJDBC articuloDAO_; 
    
    /*  ARTICULOS NUEVOS  */
    private List<SelectItem> categorias; 
    private ArticuloDTO articuloNuevo; 
    
    /* ARTICULOS PARA INTERCAMBIOS */
    private List<ArticuloDTO> articulosAprobados; 
    
    @PostConstruct
    public void init(){
        try{
            Integer idusuario = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("idusuario");
            if(idusuario == null){
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.getFlash().setKeepMessages(true);
                context.redirect(context.getRequestContextPath() + "/");
            } else{
                articuloDAO_ = new ArticuloDAOImplJDBC(); 
                categoriaDAO_ = new CategoriaDAOImplJDBC(); 
                articuloNuevo = new ArticuloDTO();
                articulosAprobados = new ArrayList<>(); 

                try{
                    categorias = categoriaDAO_.obtenerCategorias_DDL(); 
                }catch(Exception ex){
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                            "Ha ocurrido un error",
                            ex.toString());
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void obtenerArticulosAprobados(){
        try{
            articulosAprobados = articuloDAO_.obtenerArticulosAprobadosOtrosClientes(
                    (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("idusuario")); 
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void registrarArticulo(){
        try{
            Integer idusuario = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("idusuario");
            articuloNuevo.setCliente(idusuario);
            if(articuloDAO_.registrarArticulo(articuloNuevo)){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Articulo guardado correctamente",
                        "Sólo espera a que sea aceptado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                throw new Exception("No se ha podido registrar articulo");
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<SelectItem> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<SelectItem> categorias) {
        this.categorias = categorias;
    }

    public ArticuloDTO getArticuloNuevo() {
        return articuloNuevo;
    }

    public void setArticuloNuevo(ArticuloDTO articuloNuevo) {
        this.articuloNuevo = articuloNuevo;
    }    

    public List<ArticuloDTO> getArticulosAprobados() {
        return articulosAprobados;
    }

    public void setArticulosAprobados(List<ArticuloDTO> articulosAprobados) {
        this.articulosAprobados = articulosAprobados;
    }
    
}

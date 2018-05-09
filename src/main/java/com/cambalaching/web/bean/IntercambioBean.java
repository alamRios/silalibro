/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.web.bean;

import com.cambalaching.model.dao.impl.jdbc.ArticuloDAOImplJDBC;
import com.cambalaching.model.dao.impl.jdbc.IntercambioDAOImplJDBC;
import com.cambalaching.model.dto.ArticuloDTO;
import com.cambalaching.model.dto.IntercambioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ariosa1500
 */
@ManagedBean
@ViewScoped
public class IntercambioBean {
    ArticuloDAOImplJDBC articuloDAO_;
    IntercambioDAOImplJDBC intercambioDAO_; 
    
    private int idArticuloSeleccionado; 
    private ArticuloDTO articuloSeleccionado; 
    private ArticuloDTO articuloSeleccionadoOferta; 

    private List<IntercambioDTO> intercambiosEnProceso;
    
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
                intercambioDAO_ = new IntercambioDAOImplJDBC(); 
                intercambiosEnProceso = new ArrayList<>();
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void solicitarIntercambio(){
        try{
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.getFlash().setKeepMessages(true);
            context.redirect(context.getRequestContextPath() + "/intercambio/solicitud.xhtml?idarticulo="+articuloSeleccionado.getId());
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void prepararDatosArticulo(){
        try{
            articuloSeleccionado = articuloDAO_.obtenerArticuloPorId(idArticuloSeleccionado); 
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void registrarIntercambio(){
        try{
            if(intercambioDAO_.solicitarIntercambio(articuloSeleccionado, articuloSeleccionadoOferta)){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Intercambio registrado",
                    "El intercambio ya fue registrado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                throw new Exception("Error al solicitar intercambio");
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void obtenerIntercambiosCliente(){
        try{
            intercambiosEnProceso = intercambioDAO_.obtenerIntercambiosEnProceso(
            (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("idusuario"));
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public ArticuloDTO getArticuloSeleccionado() {
        return articuloSeleccionado;
    }

    public void setArticuloSeleccionado(ArticuloDTO articuloSeleccionado) {
        this.articuloSeleccionado = articuloSeleccionado;
    }
    
    public int getIdArticuloSeleccionado() {
        return idArticuloSeleccionado;
    }

    public void setIdArticuloSeleccionado(int idArticuloSeleccionado) {
        this.idArticuloSeleccionado = idArticuloSeleccionado;
    }

    public ArticuloDTO getArticuloSeleccionadoOferta() {
        return articuloSeleccionadoOferta;
    }

    public void setArticuloSeleccionadoOferta(ArticuloDTO articuloSeleccionadoOferta) {
        this.articuloSeleccionadoOferta = articuloSeleccionadoOferta;
    }

    public List<IntercambioDTO> getIntercambiosEnProceso() {
        return intercambiosEnProceso;
    }

    public void setIntercambiosEnProceso(List<IntercambioDTO> intercambiosEnProceso) {
        this.intercambiosEnProceso = intercambiosEnProceso;
    }
    
}

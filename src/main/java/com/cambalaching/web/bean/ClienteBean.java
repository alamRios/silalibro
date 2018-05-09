/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.web.bean;

import com.cambalaching.model.dao.impl.jdbc.ArticuloDAOImplJDBC;
import com.cambalaching.model.dao.impl.jdbc.ClienteDAOImplJDBC;
import com.cambalaching.model.dto.ArticuloDTO;
import com.cambalaching.model.dto.ClienteDTO;
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
public class ClienteBean {
    private ClienteDAOImplJDBC clienteDAO_; 
    private ArticuloDAOImplJDBC articuloDAO_; 
    
    private ClienteDTO cliente; 
    private List<ArticuloDTO> articulosAprobados; 
    
    /* APROBADOS */
    
    private ArticuloDTO articuloSeleccionado; 
    
    @PostConstruct
    public void init()  {
        try{
            Integer idusuario = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("idusuario");
            if(idusuario == null){
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.getFlash().setKeepMessages(true);
                context.redirect(context.getRequestContextPath() + "/");
            } else{
                clienteDAO_ = new ClienteDAOImplJDBC(); 
                articuloDAO_ = new ArticuloDAOImplJDBC(); 
                cliente = clienteDAO_.obtenerClientePorId(idusuario); 
                articuloSeleccionado = new ArticuloDTO(); 
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
            articulosAprobados = articuloDAO_.obtenerArticulosAprobados(
                    (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("idusuario"));
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ArticuloDTO> getArticulosAprobados() {
        return articulosAprobados;
    }

    public void setArticulosAprobados(List<ArticuloDTO> articulosAprobados) {
        this.articulosAprobados = articulosAprobados;
    }

    public ArticuloDTO getArticuloSeleccionado() {
        return articuloSeleccionado;
    }

    public void setArticuloSeleccionado(ArticuloDTO articuloSeleccionado) {
        this.articuloSeleccionado = articuloSeleccionado;
    }
}

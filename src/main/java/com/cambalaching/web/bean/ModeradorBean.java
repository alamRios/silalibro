/*
 * CPC SISTEMAS
 */
package com.cambalaching.web.bean;

import com.cambalaching.model.dao.impl.jdbc.ArticuloDAOImplJDBC;
import com.cambalaching.model.dto.ArticuloDTO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;


/**
 *
 * @author alam.rios
 */
@ManagedBean
@ViewScoped
public class ModeradorBean {
    private ArticuloDAOImplJDBC articuloDAO_; 
    
    /*  MODERADOR  */
    private List<ArticuloDTO> articulosPorAprobar; 
    private ArticuloDTO articuloSeleccionado; 
    
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
                articuloSeleccionado = new ArticuloDTO(); 
                articulosPorAprobar = articuloDAO_.obtenerArticulosPorAprobar(); 
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void aprobarArticulo(){
        try{
            if(articuloDAO_.aprobarArticulo(articuloSeleccionado)){
                articulosPorAprobar = articuloDAO_.obtenerArticulosPorAprobar();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Articulo actualizado correctamente",
                    "Se ha aprobado el articulo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                throw new Exception ("No ha sido posible aprobar el articulo");
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void eliminarArticulo(){
        try{
            if(articuloDAO_.eliminarArticulo(articuloSeleccionado)){
                articulosPorAprobar = articuloDAO_.obtenerArticulosPorAprobar();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Articulo actualizado correctamente",
                    "Se ha eliminado el articulo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                throw new Exception ("No ha sido posible aprobar el articulo");
            }
        }catch(Exception ex){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<ArticuloDTO> getArticulosPorAprobar() {
        return articulosPorAprobar;
    }

    public void setArticulosPorAprobar(List<ArticuloDTO> articulosPorAprobar) {
        this.articulosPorAprobar = articulosPorAprobar;
    }    
    
    public void onRowSelect(SelectEvent event){
        articuloSeleccionado = ((ArticuloDTO)event.getObject());
    }

    public ArticuloDTO getArticuloSeleccionado() {
        return articuloSeleccionado;
    }

    public void setArticuloSeleccionado(ArticuloDTO articuloSeleccionado) {
        this.articuloSeleccionado = articuloSeleccionado;
    }
    
    
}

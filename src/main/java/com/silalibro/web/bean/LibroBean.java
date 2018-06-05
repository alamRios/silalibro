package com.silalibro.web.bean;
import com.silalibro.dao.LibroDAO;
import com.silalibro.dto.LibroDTO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author booz
 */
@ManagedBean
@ViewScoped
public class LibroBean {
    private LibroDTO libroNuevo; 
    private LibroDAO libroDAO_;
    
    @PostConstruct
    public void init(){
        libroNuevo = new LibroDTO();
        libroDAO_ = new LibroDAO(); 
    }
    
    public void registrarLibro(){
        try{
            if(libroDAO_.registrarLibro(libroNuevo)){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Libro guardado correctamente","");
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

    public LibroDTO getLibroNuevo() {
        return libroNuevo;
    }

    public void setLibroNuevo(LibroDTO libroNuevo) {
        this.libroNuevo = libroNuevo;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.web.bean;

import com.silalibro.dao.CuentaDAO;
import com.silalibro.dao.LibroDAO;
import com.silalibro.dao.UsuarioDAO;
import com.silalibro.dto.LibroDTO;
import com.silalibro.dto.UsuarioDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private LibroDAO libroDAO_;

    /* GLOBAL VARIABLES */
    private String mensaje;
    private String categoria;
    private UsuarioDTO usuario, nvousuario;
    private List<LibroDTO> librosDisponibles;
    private List<LibroDTO> librosDisponiblesCategoria;
    private LibroDTO libroEnConsulta;

    /* LOGIN VARIABLES */
    private String correo_usr;
    private String passwd_usr;
    private boolean credencialesIncorrectas;

    /* REGISTRO VARIABLES */
    private String passwd_nvo;

    public UserBean() {
    }

    @PostConstruct
    public void setup() {
        categoria = "";
        mensaje = "Bienvenido a Silalibro";
        credencialesIncorrectas = false;
        usuarioDAO_ = new UsuarioDAO();
        nvousuario = new UsuarioDTO();
        cuentaDAO_ = new CuentaDAO();
        libroDAO_ = new LibroDAO();
        librosDisponibles = new ArrayList<>();
        librosDisponiblesCategoria = new ArrayList<>();

        validarUsuarioLogueado();

        cargarLibrosDisponibles();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public StreamedContent getPortadaLibro() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String rutaLibro = context.getExternalContext().getRequestParameterMap().get("rutaLibro");
            String tituloLibro = context.getExternalContext().getRequestParameterMap().get("tituloLibro");
            return new DefaultStreamedContent(new FileInputStream(new File(rutaLibro, tituloLibro + ".png")));
        }
    }

    public void cargarLibrosDisponibles() {
        try {
            librosDisponibles = libroDAO_.obtenerLibrosDisponibles();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void cargarLibrosDisponiblesCategoria() {
        try {
            librosDisponiblesCategoria = libroDAO_.obtenerLibrosDisponiblesCategoria(categoria);
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void consultarCategoria(String categoria_) {
        categoria = categoria_;
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.getFlash().setKeepMessages(true);
            context.redirect(context.getRequestContextPath() + "/index_1.xhtml");
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void consultarLibroSeleccionado(LibroDTO libro) {
        libroEnConsulta = libro;
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.getFlash().setKeepMessages(true);
            context.redirect(context.getRequestContextPath() + "/informacion/libro.xhtml");
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
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
            usuario.setCuenta(cuentaDAO_.obtenerCuentaPorUsuarioId(usuario.getIdusuario()));
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

    public void cargarCuentaUsuario() {
        try {
            usuario.setCuenta(cuentaDAO_.obtenerCuentaPorUsuarioId(usuario.getIdusuario()));
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error al cargar la cuenta del usuario",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void agregarUsuario() {
        try {
            usuarioDAO_.create(nvousuario, passwd_nvo);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Ha ocurrido un error",
                    ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void rentaDeLibro() {
        
        double saldo, precio;
        saldo = usuario.getCuenta().getTotalCuenta();
        precio = libroEnConsulta.getP_renta();
        if (saldo >= precio) {
            try {
                cuentaDAO_.cargoPorRenta(usuario.getIdusuario(), libroEnConsulta.getP_renta(), libroEnConsulta.getIdlibro());
                cargarCuentaUsuario();
                FacesContext.getCurrentInstance().getExternalContext().redirect("confirmacion.xhtml");
            } catch (Exception ex) {
                ex.printStackTrace();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Error al momento de rentar",
                        ex.toString());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No tienes suficiente saldo"));
        }
    }

    public UsuarioDTO getNvousuario() {
        return nvousuario;
    }

    public void setNvousuario(UsuarioDTO nvousuario) {
        this.nvousuario = nvousuario;
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

    public String getPasswd_nvo() {
        return passwd_nvo;
    }

    public void setPasswd_nvo(String passwd_nvo) {
        this.passwd_nvo = passwd_nvo;
    }

    public List<LibroDTO> getLibrosDisponibles() {
        return librosDisponibles;
    }

    public void setLibrosDisponibles(List<LibroDTO> librosDisponibles) {
        this.librosDisponibles = librosDisponibles;
    }

    public LibroDTO getLibroEnConsulta() {
        return libroEnConsulta;
    }

    public void setLibroEnConsulta(LibroDTO libroEnConsulta) {
        this.libroEnConsulta = libroEnConsulta;
    }

    public List<LibroDTO> getLibrosDisponiblesCategoria() {
        return librosDisponiblesCategoria;
    }

    public void setLibrosDisponiblesCategoria(List<LibroDTO> librosDisponiblesCategoria) {
        this.librosDisponiblesCategoria = librosDisponiblesCategoria;
    }

    public void validarUsuarioLogueado() {
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

}

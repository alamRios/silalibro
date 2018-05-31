/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dto;

/**
 *
 * @author boozh
 */
public class UsuarioDTO {
    private int idusuario; 
    private String nombreUsuario;
    private boolean administrador; 
    
    public UsuarioDTO(String nombreUsuario, boolean administrador){
        this.nombreUsuario = nombreUsuario; 
        this.administrador = administrador; 
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}

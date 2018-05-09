/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dto;

import java.io.Serializable;

/**
 *
 * @author ariosa1500
 */
public class ClienteDTO implements Serializable{
    private String email; 
    private String nombre; 
    private String apodo;
    private int ciudad;
    private boolean moderador; 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }
    
    public void setModerador(boolean moderador){
        this.moderador = moderador; 
    }
    
    public boolean isModerador(){
        return moderador; 
    }
}

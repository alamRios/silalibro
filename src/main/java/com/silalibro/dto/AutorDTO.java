/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dto;

/**
 *
 * @author arios
 */
public class AutorDTO {
    private String nombre; 
    private String apellidoPaterno; 
    private String appellidoMaterno; 
    private String nombrePais; 
    private int idautor;

    public int getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }
    private int idPais; 

    public String getNombreCompleto(){
        return nombre + " "+ apellidoPaterno+ " "+ appellidoMaterno;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getAppellidoMaterno() {
        return appellidoMaterno;
    }

    public void setAppellidoMaterno(String appellidoMaterno) {
        this.appellidoMaterno = appellidoMaterno;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
}

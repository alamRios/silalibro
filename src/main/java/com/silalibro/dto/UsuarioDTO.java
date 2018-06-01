/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dto;

import java.util.Date;

/**
 *
 * @author boozh
 */
public class UsuarioDTO {
    private int idusuario; 
    private String nombre;
    private String apellidoPaterno; 
    private String apellidoMaterno;
    private String email; 
    private boolean administrador; 
    private DireccionDTO direccion; 
    private Date fechaNacimiento; 
    private CuentaDTO cuenta; 
    
    public UsuarioDTO(){
        this.idusuario = 0; 
        this.nombre = ""; 
        this.apellidoPaterno = ""; 
        this.apellidoMaterno = ""; 
        this.email = ""; 
        this.administrador = false;
        this.direccion = new DireccionDTO(); 
        this.fechaNacimiento = new Date(); 
        this.cuenta = new CuentaDTO(); 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}

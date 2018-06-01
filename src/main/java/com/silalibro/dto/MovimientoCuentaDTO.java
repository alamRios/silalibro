/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dto;

import java.util.Date;

/**
 *
 * @author arios
 */
public class MovimientoCuentaDTO {
    private double monto; 
    private boolean cargo; 
    private Date fechaRegistro; 
    private String folioTransaccion; 

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isCargo() {
        return cargo;
    }

    public void setCargo(boolean cargo) {
        this.cargo = cargo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFolioTransaccion() {
        return folioTransaccion;
    }

    public void setFolioTransaccion(String folioTransaccion) {
        this.folioTransaccion = folioTransaccion;
    }
    
    
}

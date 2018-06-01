/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arios
 */
public class CuentaDTO {
    public double totalCuenta; 
    public List<MovimientoCuentaDTO> movimientos; 
    
    public CuentaDTO(){
        totalCuenta = 0; 
        movimientos = new ArrayList<>(); 
    }

    public double getTotalCuenta() {
        return totalCuenta;
    }

    public void setTotalCuenta(double totalCuenta) {
        this.totalCuenta = totalCuenta;
    }

    public List<MovimientoCuentaDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoCuentaDTO> movimientos) {
        this.movimientos = movimientos;
    }
    
}

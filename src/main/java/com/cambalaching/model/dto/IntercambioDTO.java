/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dto;

import java.sql.Date;

/**
 *
 * @author ariosa1500
 */
public class IntercambioDTO {
    private ArticuloDTO articuloSolicitado; 
    private ArticuloDTO articuloOfrecido; 
    private Date fechaSolicitud; 

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    public ArticuloDTO getArticuloSolicitado() {
        return articuloSolicitado;
    }

    public void setArticuloSolicitado(ArticuloDTO articuloSolicitado) {
        this.articuloSolicitado = articuloSolicitado;
    }

    public ArticuloDTO getArticuloOfrecido() {
        return articuloOfrecido;
    }

    public void setArticuloOfrecido(ArticuloDTO articuloOfrecido) {
        this.articuloOfrecido = articuloOfrecido;
    }
    
    
}

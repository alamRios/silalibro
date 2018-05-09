/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao;

import com.cambalaching.model.dto.ArticuloDTO;
import com.cambalaching.model.dto.IntercambioDTO;
import java.util.List;

/**
 *
 * @author ariosa1500
 */
public interface IntercambioDAO {
    public boolean solicitarIntercambio(ArticuloDTO articuloSolicitado, ArticuloDTO articuloOfrecido) throws Exception; 
    public List<IntercambioDTO> obtenerIntercambiosEnProceso(int idcliente) throws Exception; 
}

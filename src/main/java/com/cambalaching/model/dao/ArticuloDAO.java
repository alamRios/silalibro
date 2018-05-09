/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao;

import com.cambalaching.model.dto.ArticuloDTO;
import java.util.List;

/**
 *
 * @author ariosa1500
 */
public interface ArticuloDAO {
    public boolean registrarArticulo(ArticuloDTO articulo) throws Exception; 
    public List<ArticuloDTO> obtenerArticulosPorAprobar() throws Exception;
    public boolean aprobarArticulo(ArticuloDTO articuloSeleccionado) throws Exception;
    public boolean eliminarArticulo(ArticuloDTO articulo) throws Exception;
    /**
     * Obtiene articulos aprobados de un cliente en específico
     * @param idcliente
     * @return
     * @throws Exception 
     */
    public List<ArticuloDTO> obtenerArticulosAprobados(int idcliente) throws Exception; 
    /**
     * Obtiene artículos aprobados de todos los clientes
     * @param idcliente
     * @return
     * @throws Exception 
     */
    public List<ArticuloDTO> obtenerArticulosAprobadosOtrosClientes(int idcliente) throws Exception; 
    public ArticuloDTO obtenerArticuloPorId(int idarticulo) throws Exception; 
}

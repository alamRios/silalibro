/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao;

import com.cambalaching.model.dto.ClienteDTO;

/**
 *
 * @author ariosa1500
 */
public interface ClienteDAO {
    public ClienteDTO obtenerClientePorId(int idcliente) throws Exception; 
}

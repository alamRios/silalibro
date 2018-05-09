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
public interface UserDAO {
    public int login(String user_mail, String user_pass) throws Exception; 
    public boolean registrarCliente(ClienteDTO clienteNuevo, String pass) throws Exception; 
}

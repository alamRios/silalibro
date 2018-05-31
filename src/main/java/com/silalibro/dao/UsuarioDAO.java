/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.UsuarioDTO;

/**
 *
 * @author boozh
 */
public class UsuarioDAO {
    
    public UsuarioDTO iniciarSesion(String correo, String contra){
        UsuarioDTO usuario = null; 
        usuario = new UsuarioDTO("AlamRios",true);
        return usuario; 
    }
}

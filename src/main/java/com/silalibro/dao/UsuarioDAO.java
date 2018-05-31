/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.UsuarioDTO;
import com.silalibro.utils.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author boozh
 */
public class UsuarioDAO {
    private final String SQL_SELECT_USUARIO_CORREO_CONTRA = "select * from usuario where usuario_email like ? and usuario_pass like ?"; 
        
    public UsuarioDTO iniciarSesion(String correo, String contra) throws Exception{
        UsuarioDTO usuario = null; 
        Connection con = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_SELECT_USUARIO_CORREO_CONTRA); 
            st.setString(1, correo);
            st.setString(2, contra);
            rs = st.executeQuery(); 
            if(rs.next()){
                usuario = new UsuarioDTO(); 
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setEmail(rs.getString("usuario_email"));
                usuario.setNombre(rs.getString("usuario_nombre"));
                usuario.setApellidoPaterno(rs.getString("usuario_apellidoPaterno"));
                usuario.setApellidoMaterno(rs.getString("usuario_apellidoMaterno"));
                usuario.setAdministrador(rs.getBoolean("usuario_administrador"));
            }
        }catch(Exception ex){
            throw ex; 
        }finally{
            try{
                if(con != null)
                    con.close();
                if(st != null)
                    st.close();
                if(rs != null)
                    rs.close();
            }catch(Exception ex){
                throw ex; 
            }
        }
        return usuario; 
    }
}

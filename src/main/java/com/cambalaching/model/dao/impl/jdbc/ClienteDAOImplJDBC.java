/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao.impl.jdbc;

import com.cambalaching.model.dao.ClienteDAO;
import com.cambalaching.model.dto.ClienteDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ariosa1500
 */
public class ClienteDAOImplJDBC implements ClienteDAO{
    
    private String SQL_OBTENER_CLIENTE_POR_ID = "SELECT "
            + "cliente_nombre, "
            + "cliente_alias, "
            + "cliente_ciudad, "
            + "cliente_correo, "
            + "cliente_moderador_bo "
            + "FROM cambalachingdb.cliente "
            + "WHERE idcliente = ? ";
    
    @Override
    public ClienteDTO obtenerClientePorId(int idcliente) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_CLIENTE_POR_ID);
            st.setInt(1,idcliente);

            rs = st.executeQuery();
            if(rs.next()){
                ClienteDTO cliente = new ClienteDTO(); 
                cliente.setNombre(rs.getString("cliente_nombre"));
                cliente.setApodo(rs.getString("cliente_alias"));
                cliente.setCiudad(rs.getInt("cliente_ciudad"));
                cliente.setEmail(rs.getString("cliente_correo"));
                cliente.setModerador(rs.getBoolean("cliente_moderador_bo"));
                
                return cliente; 
            }
            else
                throw new Exception("Cliente no encontrado con id: "+idcliente);
        }catch(Exception ex){
            throw ex; 
        }finally{
            try{
                if(conn != null)
                    conn.close();
                if(st != null)
                    st.close();
                if(rs != null)
                    rs.close();
            }catch(Exception ex){
                throw ex; 
            }
        }
    }
    
}

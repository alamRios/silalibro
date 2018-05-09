/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao.impl.jdbc;

import com.cambalaching.model.dao.UserDAO;
import com.cambalaching.model.dto.ClienteDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ariosa1500
 */
public class UserDAOImplJDBC implements UserDAO{
    private String SQL_LOGIN = "SELECT idcliente "
            + "FROM cliente where cliente_correo like ? and cliente_contrasena like ? "; 
    
    private String SQL_REGISTRAR_CLIENTE = "insert into "
            + "cliente(cliente_nombre, cliente_alias, cliente_contrasena,"
            + " cliente_ciudad, cliente_correo, cliente_moderador_bo) "
            + "values (?,?,?,?,?,0)"; 

    @Override
    public int login(String user_mail, String user_pass) throws Exception{
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_LOGIN);
            st.setString(1,user_mail);
            st.setString(2,user_pass);

            rs = st.executeQuery();
            if(rs.next())
                return rs.getInt("idcliente");
            else
                return 0; 
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

    @Override
    public boolean registrarCliente(ClienteDTO clienteNuevo, String pass) throws Exception{
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_REGISTRAR_CLIENTE);
            st.setString(1,clienteNuevo.getNombre());
            st.setString(2,clienteNuevo.getApodo());
            st.setString(3, pass);
            st.setInt(4,clienteNuevo.getCiudad());
            st.setString(5,clienteNuevo.getEmail());
            
            return st.executeUpdate()>0;
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

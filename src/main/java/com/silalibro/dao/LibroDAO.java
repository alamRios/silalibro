/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.LibrosDTO;
import com.silalibro.utils.Conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author boozh
 */
public class LibroDAO {
    private final String SQL_SELECT_LIBRO = "select * from libro where titulo like ?"; 
    private final String SQL_SELECT_REGISTRAR_LIBRO = "INSERT INTO libros (libro_sku,libro_titulo,libro_idautor,librocol) VALUES (?,?,?,?);"; 
        
    public LibrosDTO registrarlibros(String sku, String titulo, String idautor, String librocol) throws Exception{
        LibrosDTO libro = null; 
        Connection con = null; 
        PreparedStatement st = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/db";
            Class.forName(driver);
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_SELECT_REGISTRAR_LIBRO); 
            st.setString(1, sku);
            st.setString(2, titulo);
            st.setString(3, idautor);
            st.setString(4, librocol);
            st.executeUpdate(); 
            
        }catch(Exception ex){
            throw ex; 
        }finally{
            try{
                if(con != null)
                    con.close();
                if(st != null)
                    st.close();
                
            }catch(Exception ex){
                throw ex; 
            }
        }
        return libro; 
    }
}


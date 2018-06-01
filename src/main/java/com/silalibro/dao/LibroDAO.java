/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.LibroDTO;
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
        
    public LibroDTO registrarlibros(LibroDTO libro) throws Exception{
        LibroDTO libro_ = null; 
        Connection con = null; 
        PreparedStatement st = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_SELECT_REGISTRAR_LIBRO); 
            st.setString(1, libro.getSku());
            st.setInt(2, libro.getIdautor());
            st.setString(3, libro.getTitulo());
            st.setString(4, libro.getLibrocol());
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


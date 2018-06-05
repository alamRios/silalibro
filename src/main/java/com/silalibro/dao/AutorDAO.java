/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.AutorDTO;
import com.silalibro.utils.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arios
 */
public class AutorDAO {
    private final String SQL_OBTENER_AUTORES = 
            "select * from autor join pais on autor_idpais = idpais"; 
    private String SQL_INSERT_AUTOR = 
            "insert into autor(autor_nombre,autor_apellidoPaterno,autor_apellidoMaterno,autor_idpais) values (?,?,?,?)";
    private String SQL_UPDATE_AUTOR;
    
    public List<AutorDTO> obtenerAutores() throws Exception{
        List<AutorDTO> autores = new ArrayList<>(); 
        Connection con = null; 
        PreparedStatement st = null;
        ResultSet rs = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_OBTENER_AUTORES); 
            rs = st.executeQuery(SQL_OBTENER_AUTORES); 
            
            while(rs.next()){
                AutorDTO autor = new AutorDTO(); 
                autor.setApellidoPaterno(rs.getString("autor_apellidoPaterno"));
                autor.setAppellidoMaterno(rs.getString("autor_apellidoMaterno"));
                autor.setNombre(rs.getString("autor_nombre"));
                autor.setIdPais(rs.getInt("autor_idpais"));
                autor.setNombrePais(rs.getString("pais_nombre"));
                autores.add(autor);
            }
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
        return autores; 
    }

    public boolean insertarOActualizarAutor(AutorDTO autor) throws Exception{
        PreparedStatement st = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Conection.obtenerConeccion();
            if(autor.getIdautor() == 0)
                st = con.prepareStatement(SQL_INSERT_AUTOR);
            else
                st = con.prepareStatement(SQL_UPDATE_AUTOR);
            st.setString(1,autor.getNombre());
            st.setString(2,autor.getApellidoPaterno());
            st.setString(3,autor.getAppellidoMaterno());
            st.setInt(4,autor.getIdPais());
            return st.executeUpdate() != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }
}

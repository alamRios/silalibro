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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arios
 */
public class AutorDAO {
    private final String SQL_OBTENER_AUTORES = 
            "select * from autor join pais on autor_idpais = idpais"; 
    
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.PaisDTO;
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
public class PaisDAO {
    private final String SQL_OBTENER_PAISES = 
            "select * from pais"; 
    
    public List<PaisDTO> obtenerPaises() throws Exception{
        List<PaisDTO> paises = new ArrayList<>(); 
        Connection con = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_OBTENER_PAISES); 
            rs = st.executeQuery(); 
            while(rs.next()){
                PaisDTO pais = new PaisDTO(); 
                pais.setIdpais(rs.getInt("idpais"));
                pais.setNombrePais(rs.getString("pais_nombre"));
                paises.add(pais);
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
        return paises; 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao.impl.jdbc;

import com.cambalaching.model.dao.CategoriaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author ariosa1500
 */
public class CategoriaDAOImplJDBC implements CategoriaDAO{

    private String SQL_OBTENER_CATEGORIA_DDL = "select idinteres, interes_nombre from interes where interes_activo_bo = 1 "; 
    
    @Override
    public List<SelectItem> obtenerCategorias_DDL() throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        List<SelectItem> list = new ArrayList<SelectItem>(); 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_CATEGORIA_DDL);

            rs = st.executeQuery();
            while(rs.next()){
                SelectItem si = new SelectItem(); 
                si.setLabel(rs.getString("interes_nombre"));
                si.setValue(rs.getInt("idinteres"));
                list.add(si);
            }
            return list; 
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

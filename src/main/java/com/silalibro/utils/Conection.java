/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author boozh
 */
public class Conection {
    private static final String url = "jdbc:mysql://localhost/"; 
    private static final String base = "silalibroDB?characterEncoding=UTF8";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "root";
    //private static final String password = "y3y0mysql";
    //private static final String password = "root";
    private static final String password = "HQDJfnrad1-3";
    
    public static Connection obtenerConeccion() throws Exception{
        Connection con = null; 
        try {
          Class.forName(driver).newInstance();
          con = DriverManager.getConnection(url+base,userName,password);
          return con; 
        } catch (SQLException e) {
            throw e; 
        } catch (Exception e){
            throw e;
        }
    }
}

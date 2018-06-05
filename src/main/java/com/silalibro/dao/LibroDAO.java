/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.LibroDTO;
import com.silalibro.utils.Conection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author boozh
 */
public class LibroDAO {
    private final String SQL_SELECT_REGISTRAR_LIBRO_PORTADA = "SELECT librocol from libro;";
    private final String SQL_SELECT_ID = "SELECT * from libro where idlibro=?;";
    private final String SQL_INSERT_REGISTRAR_LIBRO = "INSERT INTO libro (libro_sku,libro_titulo,libro_idautor, librocol) VALUES (?,?,?,?);"; 
        
    public boolean registrarLibro(LibroDTO libro) throws Exception{
        Connection con = null; 
        PreparedStatement st = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_INSERT_REGISTRAR_LIBRO); 
            st.setString(1, libro.getSku());
            st.setString(2, libro.getTitulo());
            st.setInt(3, libro.getIdautor());
            try(InputStream input = libro.getLibrocol().getInputStream()){
            String imgpath = "/resources/portadas/"+libro.getTitulo()+"/";
            if (!Files.exists(Paths.get(imgpath)))
                                Files.createDirectories(Paths.get(imgpath));
            imgpath+=libro.getTitulo()+".jpg";
            File portada= new File(imgpath);
           /* try {                    
                                FileUtils.copyInputStreamToFile(input, portada);
                            } catch (Exception e) {
                                throw e; 
                            }*/
            //FileInputStream portada_ = new FileInputStream(portada);
            st.setBinaryStream(0, input,(int)portada.length() );
           // st.setString(4,imgpath);
            st.executeUpdate();
           /* }catch(Exception ex){
                            throw ex; 
           
*/        
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
        return true; 
    }
     public boolean LibroPorId(int idlibro) throws Exception{
        Connection con = null; 
        PreparedStatement st = null;
        ResultSet rs = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_SELECT_ID); 
            st.setInt(1, idlibro);
            rs = st.executeQuery();
            if(rs.next()){
            LibroDTO libro = new LibroDTO(); 
                FileInputStream fis = new FileInputStream(rs.getString("articulo_foto_ruta")+".png");
                    StreamedContent sc = new DefaultStreamedContent(fis);
            }
        }catch(Exception ex){
            throw ex; 
        }
        
     return true;
     }
    
}


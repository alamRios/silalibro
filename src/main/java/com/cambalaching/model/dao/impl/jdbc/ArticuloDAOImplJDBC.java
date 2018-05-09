/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao.impl.jdbc;

import com.cambalaching.model.dao.ArticuloDAO;
import com.cambalaching.model.dto.ArticuloDTO;
import com.cambalaching.model.dto.ClienteDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ariosa1500
 */
public class ArticuloDAOImplJDBC implements ArticuloDAO{

    private String SQL_REGISTRAR_ARTICULO = "insert into articulo"
            + "(articulo_cliente, articulo_nombre, articulo_descripcion,"
            + " articulo_categoria, articulo_condiciones, articulo_tiempo,"
            + " articulo_fecha_registro, articulo_estatus) "
            + "values(?,?,?,?,?,?,now(),1)"; 
    
    private String SQL_REGISTRAR_FOTO_ARTICULO = "insert into articulo_foto"
            + "(articulo_foto_ruta, articulo_foto_articuloid) values (?,?)"; 
    
    private String SQL_OBTENER_ARTICULOS_POR_APROBAR = "select "+
                "idarticulo, "+
                "articulo_cliente," +
                "articulo_nombre," +
                "articulo_descripcion," +
                "articulo_categoria," +
                "articulo_condiciones," +
                "articulo_tiempo," +
                "articulo_fecha_registro," +
                "cliente_nombre," +
                "cliente_alias," +
                "cliente_ciudad," +
                "cliente_correo," +
                "cliente_moderador_bo," +
                "interes_nombre" +
            " from articulo " +
                " inner join cliente " +
                    " on articulo_cliente = idcliente" +
                " inner join interes" +
                    " on articulo_categoria = idinteres" +
            " where articulo_estatus = 1"; 
    
    private String SQL_OBTENER_ARTICULOS_APROBADOS = "select "+
                "idarticulo, "+
                "articulo_cliente," +
                "articulo_nombre," +
                "articulo_descripcion," +
                "articulo_categoria," +
                "articulo_condiciones," +
                "articulo_tiempo," +
                "articulo_fecha_registro," +
                "cliente_nombre," +
                "cliente_alias," +
                "cliente_ciudad," +
                "cliente_correo," +
                "cliente_moderador_bo," +
                "interes_nombre," +
                "articulo_foto_ruta" +
            " from articulo " +
                " inner join cliente " +
                    " on articulo_cliente = idcliente" +
                " inner join interes" +
                    " on articulo_categoria = idinteres" +
                " inner join articulo_foto" +
                    " on idarticulo = articulo_foto_articuloid" +
            " where articulo_estatus = 2 and articulo_cliente = ?"; 
    
    private String SQL_OBTENER_TODOS_ARTICULOS_APROBADOS = "select "+
                "idarticulo, "+
                "articulo_cliente," +
                "articulo_nombre," +
                "articulo_descripcion," +
                "articulo_categoria," +
                "articulo_condiciones," +
                "articulo_tiempo," +
                "articulo_fecha_registro," +
                "cliente_nombre," +
                "cliente_alias," +
                "cliente_ciudad," +
                "cliente_correo," +
                "cliente_moderador_bo," +
                "interes_nombre," +
                "articulo_foto_ruta" +
            " from articulo " +
                " inner join cliente " +
                    " on articulo_cliente = idcliente" +
                " inner join interes" +
                    " on articulo_categoria = idinteres" +
                " inner join articulo_foto" +
                    " on idarticulo = articulo_foto_articuloid" +
            " where articulo_estatus = 2 and articulo_cliente <> ?"; 
    
    private String SQL_APROBAR_ARTICULO_POR_ID = "update articulo "
            + "set articulo_estatus = 2 "
            + "where idarticulo = ?";
    
    private String SQL_ELIMINAR_ARTICULO_POR_ID = "update articulo "
            + "set articulo_estatus = 3 "
            + "where idarticulo = ?";
    
    private String SQL_OBTENER_ARTICULO_POR_ID = "select "+
                "idarticulo, "+
                "articulo_cliente," +
                "articulo_nombre," +
                "articulo_descripcion," +
                "articulo_categoria," +
                "articulo_condiciones," +
                "articulo_tiempo," +
                "articulo_fecha_registro," +
                "cliente_nombre," +
                "cliente_alias," +
                "cliente_ciudad," +
                "cliente_correo," +
                "cliente_moderador_bo," +
                "interes_nombre," +
                "articulo_foto_ruta" +
            " from articulo " +
                " inner join cliente " +
                    " on articulo_cliente = idcliente" +
                " inner join interes" +
                    " on articulo_categoria = idinteres" +
                " inner join articulo_foto" +
                    " on idarticulo = articulo_foto_articuloid" +
            " where idarticulo = ?"; 
    
    @Override
    public boolean registrarArticulo(ArticuloDTO articulo) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_REGISTRAR_ARTICULO);
            st.setInt(1,articulo.getCliente());
            st.setString(2,articulo.getNombre());
            st.setString(3,articulo.getDescripcion());
            st.setInt(4,articulo.getCategoria());
            st.setInt(5,articulo.getDesgaste());
            st.setInt(6,articulo.getUso());
            
            if(st.executeUpdate()>0){
                try(ResultSet rs = st.getGeneratedKeys()){
                    if(rs.next()){
                        try(InputStream input = articulo.getFoto().getInputStream()){
                            String imgpath = "/resources/articulos/"+articulo.getCliente()+"/";
                            if (!Files.exists(Paths.get(imgpath)))
                                Files.createDirectories(Paths.get(imgpath));
                            imgpath += rs.getLong(1)+".png";
                            File destFile = new File(imgpath);

                            //use org.apache.commons.io.FileUtils to copy the File
                            try {                    
                                FileUtils.copyInputStreamToFile(input, destFile);
                            } catch (Exception e) {
                                throw e; 
                            }
                            st = conn.prepareStatement(SQL_REGISTRAR_FOTO_ARTICULO);
                            st.setString(1, imgpath+rs.getLong(1));
                            st.setInt(2,(int)rs.getLong(1));
                            
                            return st.executeUpdate()>0; 
                        }catch(Exception ex){
                            throw ex; 
                        }
                    }else{
                        throw new Exception("No se ha podido obtener idarticulo");
                    }
                }catch(Exception ex){
                    throw ex;
                }
            }else{
                throw new Exception("No se ha podido registrar articulo");
            }
        }catch(Exception ex){
            throw ex; 
        }finally{
            try{
                if(conn != null)
                    conn.close();
                if(st != null)
                    st.close();
            }catch(Exception ex){
                throw ex; 
            }
        }
    }

    @Override
    public List<ArticuloDTO> obtenerArticulosPorAprobar() throws Exception{
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        List<ArticuloDTO> list = new ArrayList<>(); 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_ARTICULOS_POR_APROBAR);
            rs = st.executeQuery();
            
            while(rs.next()){
                ArticuloDTO adto = new ArticuloDTO(); 
                adto.setNombre(rs.getString("articulo_nombre"));
                adto.setDescripcion(rs.getString("articulo_descripcion"));
                adto.setCategoria(rs.getInt("articulo_categoria"));
                adto.setCategoriaNombre(rs.getString("interes_nombre"));
                adto.setDesgaste(rs.getInt("articulo_condiciones"));
                adto.setUso(rs.getInt("articulo_tiempo"));
                adto.setFechaRegistro(rs.getDate("articulo_fecha_registro"));
                adto.setCliente(rs.getInt("articulo_cliente"));
                adto.setId(rs.getInt("idarticulo"));
                    ClienteDTO clienteArticulo = new ClienteDTO(); 
                    clienteArticulo.setNombre(rs.getString("cliente_nombre"));
                    clienteArticulo.setApodo(rs.getString("cliente_alias"));
                    clienteArticulo.setCiudad(rs.getInt("cliente_ciudad"));
                    clienteArticulo.setEmail(rs.getString("cliente_correo"));
                    clienteArticulo.setModerador(rs.getBoolean("cliente_moderador_bo"));
                adto.setCliente_DTO(clienteArticulo);
                list.add(adto);
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

    @Override
    public boolean aprobarArticulo(ArticuloDTO articuloSeleccionado) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_APROBAR_ARTICULO_POR_ID);
            st.setInt(1,articuloSeleccionado.getId());
            
            return st.executeUpdate()>0; 
        }catch(Exception ex){
            throw ex; 
        }finally{
            try{
                if(conn != null)
                    conn.close();
                if(st != null)
                    st.close();
            }catch(Exception ex){
                throw ex; 
            }
        }
    }

    @Override
    public boolean eliminarArticulo(ArticuloDTO articulo) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_ELIMINAR_ARTICULO_POR_ID);
            st.setInt(1,articulo.getId());
            
            return st.executeUpdate()>0; 
        }catch(Exception ex){
            throw ex; 
        }finally{
            try{
                if(conn != null)
                    conn.close();
                if(st != null)
                    st.close();
            }catch(Exception ex){
                throw ex; 
            }
        }
    }

    @Override
    public List<ArticuloDTO> obtenerArticulosAprobados(int idcliente) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        List<ArticuloDTO> list = new ArrayList<>(); 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_ARTICULOS_APROBADOS);
            st.setInt(1, idcliente);
            rs = st.executeQuery();
            
            while(rs.next()){
                ArticuloDTO adto = new ArticuloDTO(); 
                adto.setNombre(rs.getString("articulo_nombre"));
                adto.setDescripcion(rs.getString("articulo_descripcion"));
                adto.setCategoria(rs.getInt("articulo_categoria"));
                adto.setCategoriaNombre(rs.getString("interes_nombre"));
                adto.setDesgaste(rs.getInt("articulo_condiciones"));
                adto.setUso(rs.getInt("articulo_tiempo"));
                adto.setFechaRegistro(rs.getDate("articulo_fecha_registro"));
                adto.setCliente(rs.getInt("articulo_cliente"));
                adto.setId(rs.getInt("idarticulo"));
                    FileInputStream fis = new FileInputStream(rs.getString("articulo_foto_ruta")+".png");
                    StreamedContent sc = new DefaultStreamedContent(fis);
                adto.setFotoStream(sc);
                    ClienteDTO clienteArticulo = new ClienteDTO(); 
                    clienteArticulo.setNombre(rs.getString("cliente_nombre"));
                    clienteArticulo.setApodo(rs.getString("cliente_alias"));
                    clienteArticulo.setCiudad(rs.getInt("cliente_ciudad"));
                    clienteArticulo.setEmail(rs.getString("cliente_correo"));
                    clienteArticulo.setModerador(rs.getBoolean("cliente_moderador_bo"));
                adto.setCliente_DTO(clienteArticulo);
                list.add(adto);
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

    @Override
    public List<ArticuloDTO> obtenerArticulosAprobadosOtrosClientes(int idcliente) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        List<ArticuloDTO> list = new ArrayList<>(); 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_TODOS_ARTICULOS_APROBADOS);
            st.setInt(1, idcliente);
            rs = st.executeQuery();
            
            while(rs.next()){
                ArticuloDTO adto = new ArticuloDTO(); 
                adto.setNombre(rs.getString("articulo_nombre"));
                adto.setDescripcion(rs.getString("articulo_descripcion"));
                adto.setCategoria(rs.getInt("articulo_categoria"));
                adto.setCategoriaNombre(rs.getString("interes_nombre"));
                adto.setDesgaste(rs.getInt("articulo_condiciones"));
                adto.setUso(rs.getInt("articulo_tiempo"));
                adto.setFechaRegistro(rs.getDate("articulo_fecha_registro"));
                adto.setCliente(rs.getInt("articulo_cliente"));
                adto.setId(rs.getInt("idarticulo"));
                    FileInputStream fis = new FileInputStream(rs.getString("articulo_foto_ruta")+".png");
                    StreamedContent sc = new DefaultStreamedContent(fis);
                adto.setFotoStream(sc);
                    ClienteDTO clienteArticulo = new ClienteDTO(); 
                    clienteArticulo.setNombre(rs.getString("cliente_nombre"));
                    clienteArticulo.setApodo(rs.getString("cliente_alias"));
                    clienteArticulo.setCiudad(rs.getInt("cliente_ciudad"));
                    clienteArticulo.setEmail(rs.getString("cliente_correo"));
                    clienteArticulo.setModerador(rs.getBoolean("cliente_moderador_bo"));
                adto.setCliente_DTO(clienteArticulo);
                list.add(adto);
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

    @Override
    public ArticuloDTO obtenerArticuloPorId(int idarticulo) throws Exception {
       Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_ARTICULO_POR_ID);
            st.setInt(1, idarticulo);
            rs = st.executeQuery();
            
            if(rs.next()){
                ArticuloDTO adto = new ArticuloDTO(); 
                adto.setNombre(rs.getString("articulo_nombre"));
                adto.setDescripcion(rs.getString("articulo_descripcion"));
                adto.setCategoria(rs.getInt("articulo_categoria"));
                adto.setCategoriaNombre(rs.getString("interes_nombre"));
                adto.setDesgaste(rs.getInt("articulo_condiciones"));
                adto.setUso(rs.getInt("articulo_tiempo"));
                adto.setFechaRegistro(rs.getDate("articulo_fecha_registro"));
                adto.setCliente(rs.getInt("articulo_cliente"));
                adto.setId(rs.getInt("idarticulo"));
                    FileInputStream fis = new FileInputStream(rs.getString("articulo_foto_ruta")+".png");
                    StreamedContent sc = new DefaultStreamedContent(fis);
                adto.setFotoStream(sc);
                    ClienteDTO clienteArticulo = new ClienteDTO(); 
                    clienteArticulo.setNombre(rs.getString("cliente_nombre"));
                    clienteArticulo.setApodo(rs.getString("cliente_alias"));
                    clienteArticulo.setCiudad(rs.getInt("cliente_ciudad"));
                    clienteArticulo.setEmail(rs.getString("cliente_correo"));
                    clienteArticulo.setModerador(rs.getBoolean("cliente_moderador_bo"));
                adto.setCliente_DTO(clienteArticulo);
                return adto;
            }else{
                throw new Exception("Articulo no encontrado con id: "+idarticulo); 
            }
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

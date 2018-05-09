/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao.impl.jdbc;

import com.cambalaching.model.dao.IntercambioDAO;
import com.cambalaching.model.dto.ArticuloDTO;
import com.cambalaching.model.dto.ClienteDTO;
import com.cambalaching.model.dto.IntercambioDTO;
import java.io.File;
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

/**
 *
 * @author ariosa1500
 */
public class IntercambioDAOImplJDBC implements IntercambioDAO{
    
    private String SQL_REGISTRAR_INTERCAMBIO = "insert into intercambio"
            + "(intercambio_cliente_solicita, intercambio_cliente_recibe,"
            + " intercambio_estatus, intercambioc_fecha_solicitud, intercambio_conformidad1) " +
                "values(?,?,1,now(),1)"; 
    
    private String SQL_REGISTRAR_INTERCAMBIO_ARTICULOS = 
            "insert into intercambio_articulos(intercambio_articulos_intercambio,intercambio_articulos_articulo) "
            + "values (?,?),(?,?)"; 
    
    private String SQL_OBTENER_INTERCAMBIOS_EN_PROCESO = "select * " +
        "from intercambio " +
            "inner join intercambio_articulos" +
                "on intercambio_articulos_intercambio = idintercambio" +
            "inner join articulo" +
                "on intercambio_articulos_articulo = idarticulo" +
        "where intercambio_estatus = 1 and (intercambio_cliente_solicita = ? or intercambio_cliente_recibe = ?)";
    
    @Override
    public boolean solicitarIntercambio(ArticuloDTO articuloSolicitado, ArticuloDTO articuloOfrecido) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_REGISTRAR_INTERCAMBIO);
            st.setInt(1,articuloOfrecido.getCliente());
            st.setInt(2,articuloSolicitado.getCliente());
            
            if(st.executeUpdate()>0){
                try(ResultSet rs = st.getGeneratedKeys()){
                    if(rs.next()){
                            st = conn.prepareStatement(SQL_REGISTRAR_INTERCAMBIO_ARTICULOS);
                            
                            st.setInt(1, (int)rs.getLong(1));
                            st.setInt(2,articuloOfrecido.getId());
                            st.setInt(3, (int)rs.getLong(1));
                            st.setInt(4,articuloSolicitado.getId());
                            
                            return st.executeUpdate()>0; 
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
    public List<IntercambioDTO> obtenerIntercambiosEnProceso(int idcliente) throws Exception {
        Connection conn = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        List<IntercambioDTO> list = new ArrayList<>(); 
        try{
            String driver = "com.mysql.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost/cambalachingdb";
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl, "root", "");

            st = conn.prepareStatement(SQL_OBTENER_INTERCAMBIOS_EN_PROCESO);
            rs = st.executeQuery();
            
            while(rs.next()){
                IntercambioDTO idto = new IntercambioDTO(); 
                idto.setFechaSolicitud(rs.getDate("intercambio_"));
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
                idto.setArticuloOfrecido(adto); // El primer articulo registrado es el ofrecido
                if(rs.next()){
                    idto = new IntercambioDTO(); 
                    adto = new ArticuloDTO(); 
                    adto.setNombre(rs.getString("articulo_nombre"));
                    adto.setDescripcion(rs.getString("articulo_descripcion"));
                    adto.setCategoria(rs.getInt("articulo_categoria"));
                    adto.setCategoriaNombre(rs.getString("interes_nombre"));
                    adto.setDesgaste(rs.getInt("articulo_condiciones"));
                    adto.setUso(rs.getInt("articulo_tiempo"));
                    adto.setFechaRegistro(rs.getDate("articulo_fecha_registro"));
                    adto.setCliente(rs.getInt("articulo_cliente"));
                    adto.setId(rs.getInt("idarticulo"));
                        clienteArticulo = new ClienteDTO(); 
                        clienteArticulo.setNombre(rs.getString("cliente_nombre"));
                        clienteArticulo.setApodo(rs.getString("cliente_alias"));
                        clienteArticulo.setCiudad(rs.getInt("cliente_ciudad"));
                        clienteArticulo.setEmail(rs.getString("cliente_correo"));
                        clienteArticulo.setModerador(rs.getBoolean("cliente_moderador_bo"));
                    adto.setCliente_DTO(clienteArticulo);
                    idto.setArticuloSolicitado(adto);
                }
                list.add(idto); 
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

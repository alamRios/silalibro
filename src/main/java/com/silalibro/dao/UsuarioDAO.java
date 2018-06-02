/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.DireccionDTO;
import com.silalibro.dto.UsuarioDTO;
import com.silalibro.utils.Conection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author boozh
 */
public class UsuarioDAO {
    private final String SQL_SELECT_USUARIO_CORREO_CONTRA = "select * from usuario join direccion on usuario.idDireccion = direccion.iddireccion where usuario_email like ? and usuario_pass like ?"; 
    
    private static final String SQL_INSERT_DIR = "INSERT INTO direccion(direccion_calle, direccion_numeroExterior,"
            + "direccion_numeroInterior, direccion_cp, direccion_colonia, direccion_municipio, direccion_estado)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_USUARIO = "INSERT INTO usuario (usuario_email, usuario_pass, usuario_nombre, "
            + "usuario_apellidoMaterno, usuario_apellidoPaterno, usuario_fechaNacimiento, idDireccion,"
            + "usuario_administrador) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public UsuarioDTO iniciarSesion(String correo, String contra) throws Exception{
        UsuarioDTO usuario = null; 
        Connection con = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_SELECT_USUARIO_CORREO_CONTRA); 
            st.setString(1, correo);
            st.setString(2, contra);
            rs = st.executeQuery(); 
            if(rs.next()){
                usuario = new UsuarioDTO(); 
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setEmail(rs.getString("usuario_email"));
                usuario.setNombre(rs.getString("usuario_nombre"));
                usuario.setApellidoPaterno(rs.getString("usuario_apellidoPaterno"));
                usuario.setApellidoMaterno(rs.getString("usuario_apellidoMaterno"));
                usuario.setAdministrador(rs.getBoolean("usuario_administrador"));
                usuario.setFechaNacimiento(rs.getDate("usuario_fechaNacimiento"));
                DireccionDTO direccion = new DireccionDTO();
                direccion.setCalle(rs.getString("direccion_calle"));
                direccion.setNumeroExterior(rs.getInt("direccion_numeroExterior"));
                direccion.setNumeroInterior(rs.getInt("direccion_numeroInterior"));
                direccion.setCP(rs.getString("direccion_cp"));
                direccion.setColonia(rs.getString("direccion_colonia"));
                direccion.setMunicipio(rs.getString("direccion_municipio"));
                direccion.setEstado(rs.getString("direccion_estado"));
                usuario.setDireccion(direccion);
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
        return usuario; 
    }
    
    public void create(UsuarioDTO nvousuario, String password) throws Exception {
        PreparedStatement st = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Conection.obtenerConeccion();
            st = con.prepareStatement(SQL_INSERT_DIR);
            st.setString(1, nvousuario.getDireccion().getCalle());
            st.setInt(2, nvousuario.getDireccion().getNumeroExterior());
            st.setInt(3, nvousuario.getDireccion().getNumeroInterior());
            st.setString(4, nvousuario.getDireccion().getCP());
            st.setString(5, nvousuario.getDireccion().getColonia());
            st.setString(6, nvousuario.getDireccion().getMunicipio());
            st.setString(7, nvousuario.getDireccion().getEstado());
            st.executeUpdate();

            st = con.prepareStatement(SQL_INSERT_USUARIO);
            st.setString(1, nvousuario.getEmail());
            st.setString(2, password);
            st.setString(3, nvousuario.getNombre());
            st.setString(4, nvousuario.getApellidoMaterno());
            st.setString(5, nvousuario.getApellidoPaterno());
            st.setDate(6,new Date(nvousuario.getFechaNacimiento().getTime()));
            st.setInt(7, 1);
            st.setBoolean(8, nvousuario.isAdministrador());
            st.executeUpdate();
        } catch (SQLException ex) {
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

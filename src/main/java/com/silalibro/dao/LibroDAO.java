/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.AutorDTO;
import com.silalibro.dto.LibroDTO;
import com.silalibro.utils.Conection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author boozh
 */
public class LibroDAO {

    private final String SQL_SELECT_ID = "SELECT * from libro where idlibro=?;";
    private final String SQL_SELECT_LIBROS_DISPONIBLES = "select * from libro join autor on libro_idautor = idautor join pais on autor_idpais = idpais;";
    private final String SQL_SELECT_CATEGORIA = "SELECT * from libro where libro_categoria=?;";
    private final String SQL_INSERT_REGISTRAR_LIBRO = "INSERT INTO libro (libro_sku,libro_titulo,libro_idautor, libro_categoria, librocol) VALUES (?,?,?,?,?);";

    public boolean registrarLibro(LibroDTO libro) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conection.obtenerConeccion();
            st = con.prepareStatement(SQL_INSERT_REGISTRAR_LIBRO);
            st.setString(1, libro.getSku());
            st.setString(2, libro.getTitulo());
            st.setInt(3, libro.getIdautor());
            st.setString(4, libro.getCategoria());
            String imgpath = "resources/portadas/"+libro.getTitulo().trim()+".png";
            st.setString(5, imgpath);
            if (st.executeUpdate() > 0) {
                try (InputStream input = libro.getLibrocol().getInputStream()) {
                    File destFile = new File(imgpath);
                    try {
                        FileUtils.copyInputStreamToFile(input, destFile);
                    } catch (Exception e) {
                        throw e;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            throw ex;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }

            } catch (Exception ex) {
                throw ex;
            }
        }
        return true;
    }

    public boolean LibroPorId(int idlibro) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = Conection.obtenerConeccion();
            st = con.prepareStatement(SQL_SELECT_ID);
            st.setInt(1, idlibro);
            rs = st.executeQuery();
            if (rs.next()) {
                LibroDTO libro = new LibroDTO();
                InputStream is = rs.getBinaryStream(1);
                OutputStream os = new FileOutputStream(new File("Test.jpg"));
                is.close();
                os.close();
            }
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    public boolean LibroPorCategoria(String Categoria) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = Conection.obtenerConeccion();
            st = con.prepareStatement(SQL_SELECT_CATEGORIA);
            st.setString(1, Categoria);
            rs = st.executeQuery();
            if (rs.next()) {
                LibroDTO libro = new LibroDTO();
                InputStream is = rs.getBinaryStream(1);
                OutputStream os = new FileOutputStream(new File("Test.jpg"));
                is.close();
                os.close();
            }
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    public List<LibroDTO> obtenerLibrosDisponibles() throws Exception {
        List<LibroDTO> libros = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = Conection.obtenerConeccion();
            st = con.prepareStatement(SQL_SELECT_LIBROS_DISPONIBLES);
            rs = st.executeQuery();
            while (rs.next()) {
                LibroDTO libro = new LibroDTO();
                AutorDTO autor = new AutorDTO();
                autor.setNombrePais(rs.getString("pais_nombre"));
                autor.setNombre(rs.getString("autor_nombre"));
                autor.setApellidoPaterno(rs.getString("autor_apellidoPaterno"));
                autor.setAppellidoMaterno(rs.getString("autor_apellidoMaterno"));
                libro.setAutor(autor);
                libro.setRutaLibro(rs.getString("librocol"));
                libro.setSku(rs.getString("libro_sku"));
                libro.setTitulo(rs.getString("libro_titulo"));

                libros.add(libro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return libros;
    }

}

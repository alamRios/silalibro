package com.silalibro.dto;

import javax.servlet.http.Part;

/**
 *
 * @author boozh
 */
public class LibroDTO {

    private int idlibro;
    private String titulo;
    private String sku;
    private int idautor;
    private Part librocol;
    private AutorDTO autor;
    private String rutaLibro;
    private String categoria;

    public LibroDTO() {
        this.idlibro = 0;
        this.titulo = "";
        this.sku = "";
        this.idautor = 0;
        this.categoria = "";
        autor = new AutorDTO();
    }

    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LibroDTO(String titulo) {
        this.titulo = titulo;
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public Part getLibrocol() {
        return librocol;
    }

    public void setLibrocol(Part librocol) {
        this.librocol = librocol;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public String getRutaLibro() {
        return rutaLibro;
    }

    public void setRutaLibro(String rutaLibro) {
        this.rutaLibro = rutaLibro;
    }

    @Override
    public String toString() {
        return "LibroDTO{" + "idlibro=" + idlibro + ", titulo=" + titulo + ", sku=" + sku + ", idautor=" + idautor + ", librocol=" + librocol + ", autor=" + autor + '}';
    }
}

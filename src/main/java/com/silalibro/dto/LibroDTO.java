
package com.silalibro.dto;

import com.silalibro.dto.LibroDTO.*;

/**
 *
 * @author boozh
 */
public class LibroDTO {
    private int idlibro; 
    private String titulo;
    private String sku; 
    private int idautor;
    private String librocol ; 
    private AutorDTO autor; 
    
    public LibroDTO(){
        this.idlibro = 0; 
        this.titulo = ""; 
        this.sku = ""; 
        this.librocol = "";
        autor = new AutorDTO(); 
    }
    
    public LibroDTO(String titulo){
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

    public Integer getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public String getLibrocol() {
        return librocol;
    }

    public void setLibrocol(String librocol) {
        this.librocol = librocol;
    }

    @Override
    public String toString() {
        return "Libros1{" + "idlibro=" + idlibro + ", titulo=" + titulo + ", sku=" + sku + ", idautor=" + idautor + ", librocol=" + librocol + '}';
    }


   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dto;

import java.io.File;
import java.sql.Date;
import javax.servlet.http.Part;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ariosa1500
 */
public class ArticuloDTO {
    private int id; 
    private String nombre; 
    private String descripcion; 
    private int desgaste; 
    private int uso; 
    private int categoria; 
    private String categoriaNombre;
    private Part foto; 
    private int cliente; 
    private ClienteDTO cliente_DTO; 
    private Date fechaRegistro; 
    private StreamedContent fotoStream; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDesgaste() {
        return desgaste;
    }

    public void setDesgaste(int desgaste) {
        this.desgaste = desgaste;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public ClienteDTO getCliente_DTO() {
        return cliente_DTO;
    }

    public void setCliente_DTO(ClienteDTO cliente_DTO) {
        this.cliente_DTO = cliente_DTO;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public StreamedContent getFotoStream() {
        return fotoStream;
    }

    public void setFotoStream(StreamedContent fotoStream) {
        this.fotoStream = fotoStream;
    }
    
    
}

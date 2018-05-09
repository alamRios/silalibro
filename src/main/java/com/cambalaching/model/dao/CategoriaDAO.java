/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cambalaching.model.dao;

import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author ariosa1500
 */
public interface CategoriaDAO {
    public List<SelectItem> obtenerCategorias_DDL() throws Exception; 
}

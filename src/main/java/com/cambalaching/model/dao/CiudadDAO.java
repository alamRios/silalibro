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
public interface CiudadDAO {
    public List<SelectItem> obtenerCiudades_DDL() throws Exception; 
}

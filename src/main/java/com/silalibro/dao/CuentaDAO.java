/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silalibro.dao;

import com.silalibro.dto.CuentaDTO;
import com.silalibro.dto.MovimientoCuentaDTO;
import com.silalibro.utils.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author arios
 */
public class CuentaDAO {
    private final String SQL_CUENTA_USUARIO_POR_ID = "select * from movimiento_cuenta where movimiento_cuenta_idusuario = ?"; 
    public CuentaDTO obtenerCuentaPorUsuarioId(int usuarioid) throws Exception{
        CuentaDTO cuenta = new CuentaDTO(); 
        Connection con = null; 
        PreparedStatement st = null; 
        ResultSet rs = null; 
        try{
            con = Conection.obtenerConeccion(); 
            st = con.prepareStatement(SQL_CUENTA_USUARIO_POR_ID); 
            st.setInt(1, usuarioid);
            rs = st.executeQuery(); 
            while(rs.next()){
                MovimientoCuentaDTO movimiento = new MovimientoCuentaDTO();
                movimiento.setMonto(rs.getDouble("movimiento_cuenta_monto"));
                movimiento.setCargo(rs.getBoolean("movimiento_cuenta_cargo"));
                movimiento.setFolioTransaccion(rs.getString("movimiento_cuenta_folioTransaccion"));
                movimiento.setFechaRegistro(rs.getDate("movimiento_cuenta_fecha"));
                if(movimiento.isCargo())
                    cuenta.totalCuenta -= movimiento.getMonto(); 
                else
                    cuenta.totalCuenta += movimiento.getMonto(); 
                cuenta.movimientos.add(movimiento);
            }
        }catch(Exception ex){
            ex.printStackTrace();
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
        return cuenta; 
    }
}

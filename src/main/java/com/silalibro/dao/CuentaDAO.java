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
    private final String SQL_CARGO_RENTA = "INSERT INTO movimiento_cuenta "
            + "(movimiento_cuenta_monto, movimiento_cuenta_cargo, movimiento_cuenta_fecha, "
            + "movimiento_cuenta_folioTransaccion, movimiento_cuenta_idusuario) "
            + "VALUES (?, b'1', NOW(), FLOOR(RAND() * 500) + 5, ?);";
    
    private final String SQL_INSERT_TABLA_RENTA = "INSERT INTO renta"
            + "(renta_idusuario, renta_fechaRegistro, renta_movimientoCuentaid, "
            + "renta_montoTotal) VALUES (?, NOW(), (SELECT idmovimiento_cuenta "
            + "from movimiento_cuenta where movimiento_cuenta_idusuario = ? and "
            + "movimiento_cuenta_monto = ? and movimiento_cuenta_cargo = b'1'), ?);";
    
    private final String SQL_INSERT_RENTALIBRO = "INSERT INTO rentaventa_libro"
            + "(rentaventa_libro_idlibro, rentaventa_libro_idrentaventa, rentaventa_libro_monto)"
            + "VALUES (?, (SELECT idrenta from renta where renta_idusuario = ? and "
            + "renta_montoTotal = ?), ?);";
    
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
    
    public void cargoPorRenta(int usuarioid, double monto, int libroid) throws Exception{ 
        Connection con = null; 
        PreparedStatement st = null; 
        try{
            con = Conection.obtenerConeccion(); 
            //insert a movimiento_cuenta como un cargo por la renta
            st = con.prepareStatement(SQL_CARGO_RENTA);
            st.setDouble(1, monto);
            st.setInt(2, usuarioid);
            st.executeUpdate();
            
            //insert a renta 
            st = con.prepareStatement(SQL_INSERT_TABLA_RENTA);
            st.setInt(1, usuarioid);
            st.setInt(2, usuarioid);
            st.setDouble(3, monto);
            st.setDouble(4, monto);
            st.executeUpdate();
            
            //insert a rentaventa_libro
            st = con.prepareStatement(SQL_INSERT_RENTALIBRO);
            st.setInt(1, libroid);
            st.setInt(2, usuarioid);
            st.setDouble(3, monto);
            st.setDouble(4, monto);
            st.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
            throw ex; 
        }finally{
            try{
                if(con != null)
                    con.close();
                if(st != null)
                    st.close();
            }catch(Exception ex){
                throw ex; 
            }
        }
    }
}

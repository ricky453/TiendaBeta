/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class ControladorVenta {
    
    Conexion cn ;
    ResultSet rs;
    PreparedStatement ps=null;
    
    public void Agregar(Venta venta){
        
    }
    public Venta ObtenerVenta(){
        Venta ventas = null;
        
        return ventas;
    }
    public void ActualizarInventario(DetalleVenta[] detalles){
        
    }
    public int ObtenerIdVenta() throws ErrorTienda{
        int IdVenta=0;
        try {
            cn=new Conexion();
       rs=null;
        rs = cn.st.executeQuery("SELECT count(IdVenta) FROM Venta");
        
        while(rs.next()){
            IdVenta = rs.getInt(1);
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorVenta/ObtenerIdVenta", ex.getMessage());
        } 
        try {
            cn.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IdVenta+1;
    }
    
}

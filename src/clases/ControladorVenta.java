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
    
    public void Agregar(Venta venta) throws ErrorTienda{
       cn = new Conexion();
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Fecha = sdf.format(venta.getFecha());
            cn.st.execute("INSERT INTO Venta(IdVenta,IdSucursal,TipoVenta,IdTipoPrecio,Cliente,Fecha,IVA "
                    + ",TotalGravado,Total,Direccion,Giro,NIT,NRC,NDocumento) VALUES ('"+venta.getIdVenta()+"','"+venta.getIdSucursal()+"', "
                            + "'"+venta.getIdTipoVenta()+"','"+venta.getIdPrecio()+"','"+venta.getCliente()+"','"+Fecha+"','"+venta.getIVA()+"', "
                                    + "'"+venta.getTotalGravado()+"','"+venta.getTotal()+"','"+venta.getDireccion()+"','"+venta.getGiro()+"', "
                                            + "'"+venta.getNIT()+"','"+venta.getNRC()+"','"+venta.getNomDocumento()+"')");
        } catch (Exception e) {
            throw new ErrorTienda("ControladorVenta Agregar", e.getMessage());
        }
        
        
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

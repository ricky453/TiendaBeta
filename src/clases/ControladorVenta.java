/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jose
 */
public class ControladorVenta {
    
    static Conexion cn ;
    static ResultSet rs;
    PreparedStatement ps=null;
    
    
    
    public boolean Agregar(Venta venta,Object[][] detalles) throws ErrorTienda{
       cn = new Conexion();
        try {
            System.err.println("Detalles ventas "+venta.getArticulos().size());
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String Fecha = sdf.format(venta.getFecha());
            cn.st.execute("INSERT INTO venta(IdVenta,IdSucursal,TipoVenta,IdTipoPrecio,Cliente,Fecha,IVA "
                    + ",TotalGravado,Total,Direccion,Giro,NIT,NRC,NDocumento,PAC,Utilidad) VALUES ('"+venta.getIdVenta()+"','"+venta.getIdSucursal()+"', "
                            + "'"+venta.getIdTipoVenta()+"','"+venta.getIdPrecio()+"','"+venta.getCliente()+"','"+Fecha+"','"+venta.getIVA()+"', "
                                    + "'"+venta.getTotalGravado()+"','"+venta.getTotal()+"','"+venta.getDireccion()+"','"+venta.getGiro()+"', "
                                            + "'"+venta.getNIT()+"',"+venta.getNRC()+","+venta.getNomDocumento()+","+venta.getPAC()+","+venta.getUtilidad()+")");
        
            cn.conexion.close();
            ActualizarInventario(detalles, venta.getIdSucursal());
            cn = new Conexion();
            try {
                for(int x=0;x<detalles.length;x++){
                    cn.st.execute("INSERT INTO detalleventa(IdVenta,CodBarra,Cantidad,PrecioUnitario) VALUES('"+detalles[x][0]+"', "
                            + "'"+detalles[x][1]+"','"+detalles[x][2]+"','"+detalles[x][3]+"')");
                }
                return true;
            } catch (Exception e) {
            }
            
            cn.conexion.close();
            
        } catch (Exception e) {
            throw new ErrorTienda("ControladorVenta Agregar", e.getMessage());
        }
        
        return false;
    }
    
    public static ArrayList<Venta> ObtenerVenta(int id) throws ErrorTienda{
        ArrayList<Object> ventas=new ArrayList<Object>();
        
        
        cn=new Conexion();
        
        try {
            rs=cn.st.executeQuery("SELECT producto.CodBarra, producto.Nombre, detalleventa.Cantidad,detalleventa.PrecioUnitario FROM producto INNER JOIN detalleventa ON producto.CodBarra=detalleventa.CodBarra WHERE detalleventa.IdVenta='"+id+"'");
            
            while (rs.next()) {
                ventas.add(rs.getString(1));
                ventas.add(rs.getString(2));
                ventas.add(rs.getString(3));
                ventas.add(rs.getString(4));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Venta> miventa=(ArrayList) ventas;
        
        return miventa;
    }
    public void ActualizarInventario(Object[][] detalles,int sucursal) throws ErrorTienda{
        cn = new Conexion();
        try {
            clases.Producto pr;
        for(int x =0;x<detalles.length;x++){
          
            pr = ControladorProducto.Obtener(String.valueOf(detalles[x][1]),sucursal);
          int cantidad = pr.getInventario();
            
            int cantidad2= Integer.parseInt(String.valueOf(detalles[x][2]));
            
            System.out.println("Cantidad en la bd "+cantidad+" Cantidad exigida "+cantidad2);
            cn.st.execute("UPDATE inventario SET Cantidad='"+(cantidad-cantidad2)+"' WHERE IdSucursal='"+sucursal+"' AND CodBarra='"+detalles[x][1]+"'");
        }    
        } catch (SQLException e) {
        throw new ErrorTienda("Controlador Venta catualizar inventario", e.getMessage());
        }
    }
    public int ObtenerIdVenta() throws ErrorTienda{
        int IdVenta=0;
        try {
            cn=new Conexion();
       rs=null;
        rs = cn.st.executeQuery("SELECT count(IdVenta) FROM venta");
        
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
    
    
    public static ArrayList<Venta> obteniendoVentas(String fecha) throws ErrorTienda{
        ArrayList<Object> ventas=new ArrayList<Object>();
        cn=new Conexion();
        try {
            
                rs=cn.st.executeQuery("SELECT venta.IdVenta, sucursal.Nombre, venta.Cliente, venta.TipoVenta, venta.Fecha FROM sucursal INNER JOIN venta  ON venta.IdSucursal=sucursal.IdSucursal WHERE venta.Fecha LIKE '"+fecha+"%' and TipoVenta!='B'");
            
                
            
            
            
            while (rs.next()) {
                ventas.add(rs.getString(1));
                ventas.add(rs.getString(2));
                ventas.add(rs.getString(3));
                ventas.add(rs.getString(4));
                ventas.add(rs.getString(5));
                
            }
            
            
        } catch (SQLException ex) {
            throw new ErrorTienda("Class ControladorVenta/obtiendoVentas",ex.getMessage());
        }
        
        ArrayList<Venta> miventas=(ArrayList) ventas;
        return miventas; 
    }
    public ArrayList<Object> VentasBorrador(String filtro,int idSucursal) throws ErrorTienda, SQLException{
        ArrayList<Object> ventasBorrador=new ArrayList<Object>();
        cn=new Conexion();
        System.out.println("DENDTRO DE CONTROLADOR");
        if(filtro.equals("TODAS")){
            System.out.println("DENTRO DE TODAS LAS OPCIONES DE BUSQUEDA DENDTRO DE CONTROLADOR");
            rs= (cn.st.executeQuery("SELECT IdVenta,Fecha,Total FROM venta WHERE TipoVenta='B';"));
        }else{
            rs= (cn.st.executeQuery("SELECT IdVenta,Fecha,Total FROM venta WHERE TipoVenta='B' AND IdSucursal = '"+idSucursal+"';"));
        }
        int fila=0;
        while(rs.next()){
            
            ventasBorrador.add(rs.getString(1));
            ventasBorrador.add(rs.getString(2));
            ventasBorrador.add(rs.getString(3));
        }
        
        return ventasBorrador;
    }
    
    
    
}

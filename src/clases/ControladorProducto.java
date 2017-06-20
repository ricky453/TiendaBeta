/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kike
 */
public class ControladorProducto {
    
    static Conexion cn;
    static ResultSet rs;
    private static boolean cambio;

    public static boolean isCambio() {
        return cambio;
    }

    public static void setCambio(boolean cambio) {
        ControladorProducto.cambio = cambio;
    }

   
   
    public static void Agregar(Producto pr) throws ErrorTienda{
        
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO Producto(CodBarra,Costo,Nombre) VALUES('"+pr.getCodBarra()+"','"+pr.getCosto()+"','"+pr.getNombre()+"')");
            cn.st.executeUpdate("INSERT INTO Inventario(IdSucursal,CodBarra,Cantidad) VALUES('"+pr.getCodBarra()+"','"+pr.getCodBarra()+"','"+pr.getInventario()+"')");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Agregar",e.getMessage());
        }
           
    }
    
    public static void Modificar(Producto pr) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.execute("UPDATE producto SET Nombre='"+pr.getNombre()+"',Costo='"+pr.getCosto()+"' WHERE CodBarra='"+pr.getCodBarra()+"'");
            cn.st.execute("UPDATE inventario SET IdSucursal='"+pr.getIdSucursal()+"',CodBarra='"+pr.getCodBarra()+"',Cantidad='"+pr.getInventario()+"' WHERE IdSucursal='"+pr.getIdSucursal()+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Modificar",e.getMessage());
        }
    }
    
    public static void Eliminar(Producto pr) throws ErrorTienda{
        try {
            
            String [] matriz=new String[4];
            String [] matriz2=new String[4];
            rs=cn.st.executeQuery("SELECT * FROM DetalleCompra WHERE CodBarra='"+pr.getCodBarra()+"'");
            
            while (rs.next()) {
                matriz[0]=rs.getString(1);
                matriz[1]=rs.getString(2);
                matriz[2]=rs.getString(3);
                matriz[3]=rs.getString(4);
                
            }
            
            rs=cn.st.executeQuery("SELECT * FROM DetalleVenta WHERE CodBarra='"+pr.getCodBarra()+"'");
            
            while (rs.next()) {
                matriz2[0]=rs.getString(1);
                matriz2[1]=rs.getString(2);
                matriz2[2]=rs.getString(3);
                matriz2[3]=rs.getString(4);
                
            }
            
            if (matriz[0] != null || matriz2[1] != null) {
                setCambio(true);
            }else{
                cn.st.executeUpdate("DELETE FROM inventario WHERE CodBarra='"+pr.getCodBarra()+"'");
                cn.st.executeUpdate("DELETE FROM producto WHERE CodBarra='"+pr.getCodBarra()+"'");
                setCambio(false);
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Eliminar",e.getMessage());
        }
    }
    
    
    public static ArrayList<Producto> Buscar(String buscar) throws ErrorTienda{
        ArrayList<Object> producto = new ArrayList<Object>();
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT DISTINCT producto.CodBarra,producto.nombre,inventario.Cantidad,producto.Costo, inventario.IdSucursal FROM producto INNER JOIN inventario ON producto.CodBarra=inventario.CodBarra WHERE producto.nombre LIKE '%"+buscar+"%' OR producto.CodBarra LIKE'%"+buscar+"%'");
            
                while (rs.next()) {
                    producto.add(rs.getString(1));
                    producto.add(rs.getString(2));
                    producto.add(rs.getString(3));
                    producto.add(rs.getString(4));
                    producto.add(rs.getString(5));
                }
            
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Buscar",e.getMessage());
        }
        
        ArrayList<Producto> productos=(ArrayList) producto;
        
        return productos;
    }
    
    
    public static Producto Obtener(String CodBarra,int idSucursal) throws ErrorTienda{
        Producto miproducto=new Producto();
        
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT Inventario.IdSucursal, Inventario.Cantidad, Inventario.CodBarra,Producto.Nombre,Producto.Costo FROM Inventario,Producto WHERE  Inventario.CodBarra='"+CodBarra+"' AND Inventario.IdSucursal="+idSucursal+" AND Producto.CodBarra=Inventario.CodBarra;");
            while (rs.next()) {
                miproducto.setIdSucursal(Integer.parseInt(rs.getString(1)));
                miproducto.setCodBarra(rs.getString(3));
                miproducto.setNombre(rs.getString(4));
                miproducto.setInventario(Integer.parseInt(rs.getString(2)));
                miproducto.setCosto(Double.parseDouble(rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Obtener",e.getMessage());
        }
        return miproducto;
    }
    
    
}

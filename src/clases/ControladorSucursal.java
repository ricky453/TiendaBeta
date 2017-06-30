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
public class ControladorSucursal {
    
    static Conexion cn;
    static ResultSet rs;
    private static boolean cambio;
    
    
    public static int ObtenerIdSucursal(Object nombre) throws ErrorTienda{
        cn = new Conexion();
        int id=0;
        try {
            rs = cn.st.executeQuery("SELECT IdSucursal FROM Sucursal WHERE Nombre= '"+nombre+"'");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return id;
    
    }
    public static int ObtenerIdMax() throws ErrorTienda{
        int IdSucursal=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT MAX(IdSucursal) FROM Sucursal");
        
            while(rs.next()){
                IdSucursal = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorSucursal/ObtenerIdSucursal", ex.getMessage());
        } 
        return IdSucursal;
    
    }
    public static ArrayList<Sucursal> buscarSucursal(String buscar) throws ErrorTienda{
        ArrayList<Object> sucursal = new ArrayList<Object>();
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT DISTINCT IdSucursal,Nombre, Direccion, Telefono FROM Sucursal WHERE Nombre LIKE '%"+buscar+"%'");
            
                while (rs.next()) {
                    sucursal.add(rs.getString(1));
                    sucursal.add(rs.getString(2));
                    sucursal.add(rs.getString(3));
                    sucursal.add(rs.getString(4));
                }
            
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorSucursal/Buscar",e.getMessage());
        }
        
        ArrayList<Sucursal> sucursales=(ArrayList) sucursal;
        
        return sucursales;
    }
    
    
    
    
    public static void modificarSucursal(Sucursal sc) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.execute("UPDATE Sucursal SET Nombre='"+sc.getNombre()+"',Direccion='"+sc.getDireccion()+"',Telefono='"+sc.getTelefono()+"' WHERE IdSucursal='"+sc.getIdSucursal()+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorSucursal/Modificar",e.getMessage());
        }
    }
    
    public static void eliminarSucursal(Sucursal sc) throws ErrorTienda{
        try {
            
            String [] matriz=new String[3];
            String [] matriz2=new String[5];
            String [] matriz3=new String[10];
            
            rs=cn.st.executeQuery("SELECT * FROM Inventario WHERE IdSucursal='"+sc.getIdSucursal()+"'");
            
            while (rs.next()) {
                matriz[0]=rs.getString(1);
                matriz[1]=rs.getString(2);
                matriz[2]=rs.getString(3);
                
            }
            
            rs=cn.st.executeQuery("SELECT * FROM DetalleVenta WHERE IdSucursal='"+sc.getIdSucursal()+"'");
            
            while (rs.next()) {
                matriz2[0]=rs.getString(1);
                matriz2[1]=rs.getString(2);
                matriz2[2]=rs.getString(3);
                matriz2[3]=rs.getString(4);
                matriz2[4]=rs.getString(5);
            }
            
            rs=cn.st.executeQuery("SELECT * FROM Compra WHERE IdSucursal='"+sc.getIdSucursal()+"'");
            
            while (rs.next()) {
                matriz2[0]=rs.getString(1);
                matriz2[1]=rs.getString(2);
                matriz2[2]=rs.getString(3);
                matriz2[3]=rs.getString(4);
                matriz2[4]=rs.getString(5);
                matriz2[5]=rs.getString(6);
                matriz2[6]=rs.getString(7);
                matriz2[7]=rs.getString(8);
                matriz2[8]=rs.getString(9);
                matriz2[9]=rs.getString(10);
            }
            
            if (matriz[0] != null || matriz2[4] != null || matriz3[2] != null) {
                setCambio(true);
            }else{
                cn.st.executeUpdate("DELETE FROM Sucursal WHERE IdSucursal='"+sc.getIdSucursal()+"'");
                setCambio(false);
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorSucursal/Eliminar",e.getMessage());
        }
    }
    
    
    public static void agregarSucursal(Sucursal sc) throws ErrorTienda{
         try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO Sucursal(IdSucursal,Nombre,Direccion, Telefono) VALUES('"+sc.getIdSucursal()+"','"+sc.getNombre()+"','"+sc.getDireccion()+"','"+sc.getTelefono()+"')");
            } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorSucursal/Agregar",e.getMessage());
        }
    }
    
    public static Sucursal obtenerSucursal(int sc) throws ErrorTienda{
        Sucursal misucursal=new Sucursal();
        
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT IdSucursal,Nombre,Direccion, Telefono FROM Sucursal WHERE IdSucursal='"+sc+"'");
            while (rs.next()) {
                misucursal.setIdSucursal(Integer.parseInt(rs.getString(1)));
                misucursal.setNombre(rs.getString(2));
                misucursal.setDireccion(rs.getString(3));
                misucursal.setTelefono(rs.getString(4));
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorSucursal/Obtener",e.getMessage());
        }
        return misucursal;
    }
    
    
    public static ArrayList<Sucursal> obtener() throws ErrorTienda{
        ArrayList<Object> sucursal = new ArrayList<Object>();
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT IdSucursal,Nombre,Direccion,Telefono FROM Sucursal ");
            
                while (rs.next()) {
                    sucursal.add(rs.getString(1));
                    sucursal.add(rs.getString(2));
                    sucursal.add(rs.getString(3));
                    sucursal.add(rs.getString(4));
                }
            
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorSucursal/Buscar",e.getMessage());
        }
        
        ArrayList<Sucursal> sucursales=(ArrayList) sucursal;
        
        return sucursales;
    }
    
    
    
    public static boolean isCambio() {
        return cambio;
    }

    public static void setCambio(boolean cambio) {
        ControladorSucursal.cambio = cambio;
    }
    
    
}

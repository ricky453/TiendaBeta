/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class ControladorTipoPrecio {
    
    static Conexion cn ;
    static ResultSet rs;
    
    public static void AgregarTipoPrecio(TipoPrecio tp) throws ErrorTienda{
        cn=new Conexion();
        try {
            cn.st.executeUpdate("INSERT INTO TipoPrecio(IdTipoPrecio,Nombre,Utilidad) VALUES('"+tp.getIdTipoPrecio()+"','"+tp.getNombre()+"','"+tp.getUtilidad()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTipoPrecio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void EliminarTipoPrecio(int idTipoPrecio) throws ErrorTienda{
        
        try {
            cn.st.executeUpdate("DELETE FROM TipoPrecio WHERE IdTipoPrecio='"+idTipoPrecio+"'");
        } catch (Exception e) {
            throw new ErrorTienda("Class ControladorTipoPrecio/Eliminar", e.getMessage());
        }
    }
    public static void ModificarTipoPrecio( TipoPrecio cambios) throws ErrorTienda{
          try {
            cn=new Conexion();
            cn.st.execute("UPDATE TipoPrecio SET Nombre='"+cambios.getNombre()+"',Utilidad='"+cambios.getUtilidad()+"' WHERE IdTipoPrecio='"+cambios.getIdTipoPrecio()+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorTipoPrecio/Modificar",e.getMessage());
        }
        
    }
    public TipoPrecio Buscar (int idTipoPrecio){
        TipoPrecio precio = new TipoPrecio();
        return precio;
        
    }
    public static ArrayList<TipoPrecio> ObtenerTodos() throws ErrorTienda{
        ArrayList<Object> precios = new ArrayList<Object>();
        cn = new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT * FROM TipoPrecio");
            while(rs.next()){
            precios.add(rs.getString(1));
            precios.add(rs.getString(2));
            precios.add(rs.getString(3));
            }
            
        } catch (Exception e) {
        }
        ArrayList<TipoPrecio> misPrecios = (ArrayList) precios;
        return misPrecios;
    }
    
     public static int ObtenerIdMax() throws ErrorTienda{
        int IdTipoPrecio=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT MAX(IdTipoPrecio) FROM TipoPrecio");
        
            while(rs.next()){
                IdTipoPrecio = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorSucursal/ObtenerIdTipoPrecio", ex.getMessage());
        } 
        return IdTipoPrecio;
    
    }
   
    
    
    
}

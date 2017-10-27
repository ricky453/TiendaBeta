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
 * @author oscar
 */
public class ControladorUsuario {
    static Conexion cn;
    static ResultSet rs;
    private static boolean cambio;

    public static boolean isCambio() {
        return cambio;
    }

    public static void setCambio(boolean cambio) {
        ControladorUsuario.cambio = cambio;
    }
    
    
    public static void Agregar(Usuario pv)throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO usuario(IdUsuario, Login, Clave, Rol) VALUES('"+pv.getIdUsuario()+"','"+pv.getUsuario()+"','"+pv.getClave()+"','"+pv.getRol()+"')");
            
        } catch (SQLException ex) {
            throw new ErrorTienda("Class ControladorUsuario/Agregar", ex.getMessage());
        }
    }
    
    public static String ObtenerPass(String id)throws ErrorTienda{
        String Usuario="";   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT Clave FROM usuario WHERE Login='"+id+"';");
            while(rs.next()){
                Usuario = rs.getString(1);
            }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorUsuario/ObtenerPass", ex.getMessage());
        } 
        return Usuario;
    
    }
    
    public static String obtenerRol(String id)throws ErrorTienda{
        String rol="";   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT Rol FROM usuario WHERE Login='"+id+"';");
            while(rs.next()){
                rol = rs.getString(1);
            }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorUsuario/ObtenerRol", ex.getMessage());
        } 
        return rol;
    
    }    
    public static int ObtenerIdUsuario()throws ErrorTienda{
        int Usuario=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT max(IdUsuario) FROM usuario");
        
            while(rs.next()){
                Usuario = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorUsuario/ObtenerIdUsuario", ex.getMessage());
        } 
        return Usuario;
    
    }

}

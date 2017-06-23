/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.ControladorProducto.cn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class ControladorParametro {
    static Conexion cn;
    static ResultSet rs;
    
    public static Parametro Obtener(int idpa) throws ErrorTienda {
    Parametro pa=new Parametro();
    
    cn = new Conexion();
    try {
            rs=cn.st.executeQuery("SELECT IdParametro,Nombre,Valor FROM Parametro WHERE IdParametro='"+idpa+"'");
            while (rs.next()) {
                pa.setIdParametro(Integer.parseInt(rs.getString(1)));
                pa.setNombre(rs.getString(2));
                pa.setValor(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorParametro/Obtener",e.getMessage());
        }
    return pa;
    
    }
    
     
    public static void Modificar(Parametro pa) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.execute("UPDATE Parametro SET Nombre='"+pa.getNombre()+"',Valor='"+pa.getValor()+"' WHERE IdParametro='"+pa.getIdParametro()+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorParametro/Modificar",e.getMessage());
        }
    }

    public static ArrayList<Parametro> Obtener() throws ErrorTienda{
        ArrayList<Object> parametro = new ArrayList<Object>();
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT IdParametro,Nombre,Valor FROM Parametro");
            
                while (rs.next()) {
                   parametro.add(rs.getString(1));
                   parametro.add(rs.getString(2));
                   parametro.add(rs.getString(3));
                   
                }
            
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorParametro/Obtener",e.getMessage());
        }
        
        ArrayList<Parametro> parametros=(ArrayList) parametro;
        
        return parametros;
    }
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */

public class PropiedadesVentas {
    ResultSet rs;
    
    
    Conexion cn ;
    
    private boolean estado;
    
    public void ObtenerEstado() throws ErrorTienda{
        try {
            
            cn = new Conexion();
            rs=cn.st.executeQuery("SELECT Propiedad FROM configuracion WHERE Atributo='frmCambio'");
        } catch (SQLException ex) {
           throw new ErrorTienda("Porpiedades Ventas Obtener estado)",ex.getMessage());
        }
        try {
            while(rs.next()){
                estado = (Boolean.valueOf(rs.getString(1)));
            }
            
            cn.conexion.close();
        } catch (SQLException ex) {
           throw new ErrorTienda("Porpiedades Ventas Obtener estado conversion)",ex.getMessage());
        }
        
       
    }
    public void cambiarEstado() throws ErrorTienda{
        try {
            cn = new Conexion();
            cn.st.executeUpdate("UPDATE configuracion SET Propiedad='"+estado+"' WHERE Atributo='frmCambio'");
             cn.conexion.close();
        } catch (SQLException ex) {
            throw new ErrorTienda("Porpiedades Ventas Cambiar Estado)",ex.getMessage());
        }
       
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sergio
 */
public class ControladorBitacora {
    static Conexion cn;
    static ResultSet rs;
    public static void Agregar(Bitacora bitacora) throws ErrorTienda{
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Fecha = sdf.format(bitacora.getFecha());
            cn.st.executeUpdate("INSERT INTO bitacora VALUES('"+bitacora.getIdUsuario()+"', '"+bitacora.getFecha()+"', '"+bitacora.getAccion()+"');");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorBitacora/Agregar", e.getMessage());
        }
    }
    
    public static void ObenerBitacoras(String consulta) throws ErrorTienda{
        try {
            rs = null;
            rs = cn.st.executeQuery("SELECT `bitacora`.`IdUsuario`, `usuario`.`Login`, `bitacora`.`Fecha`, `bitacora`.`Accion`\n" +
                                    "FROM `bitacora`\n" +
                                    "LEFT JOIN `usuario` ON `bitacora`.`IdUsuario` = `usuario`.`IdUsuario`\n" +
                                    "WHERE ((`bitacora`.`IdUsuario` ='"+consulta+"') OR (`bitacora`.`Fecha` ='"+consulta+"'))");
            
        } catch (Exception e) {
        }
    
    
    }
}

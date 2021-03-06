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
 * @author sergio
 */
public class ControladorBitacora {
    static Conexion cn;
    static ResultSet rs;
    public static void Agregar(Bitacora bitacora) throws ErrorTienda{
        cn = new Conexion();
        try {
            //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String Fecha = sdf.format(bitacora.getFecha());
            //System.out.println("INSERT INTO `bitacora`(`IdUsuario`, `Fecha`, `Accion`) VALUES ('"+bitacora.getIdUsuario()+"', '"+bitacora.getFecha()+"', '"+bitacora.getAccion()+"');");
            cn.st.executeUpdate("INSERT INTO `bitacora`(`IdUsuario`, `Fecha`, `Accion`) VALUES ('"+bitacora.getIdUsuario()+"', '"+bitacora.getFecha()+"', '"+bitacora.getAccion()+"');");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorBitacora/Agregar", e.getMessage());
        }
    }
    
    public static ArrayList<Bitacora> ObenterBitacoras(String consulta) throws ErrorTienda{
        ArrayList<Object> bitacora = new ArrayList<Object>();
        cn = new Conexion();
        try {

            rs = cn.st.executeQuery("SELECT `bitacora`.`IdUsuario`, `usuario`.`Login`, `bitacora`.`Fecha`, `bitacora`.`Accion` FROM `bitacora` LEFT JOIN `usuario` ON `bitacora`.`IdUsuario` = `usuario`.`IdUsuario`\n WHERE ((`usuario`.`Login` ='"+consulta+"') OR (`bitacora`.`Fecha` ='"+consulta+"'))");
            
            while (rs.next()) {
                bitacora.add(rs.getString(1));
                bitacora.add(rs.getString(2));
                bitacora.add(rs.getString(3));
                bitacora.add(rs.getString(4));
            }
        } catch (SQLException e) {
            throw new ErrorTienda("ContrladorBitacora/Obtener", e.getMessage());
        }
        ArrayList<Bitacora> bit = (ArrayList) bitacora;
        return bit;
    }
}

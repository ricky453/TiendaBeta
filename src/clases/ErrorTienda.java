/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class ErrorTienda extends Exception {
    

    public ErrorTienda(String ClaseMetodo,String mensajeError){
        super(ClaseMetodo+mensajeError);
        JOptionPane.showMessageDialog(null, ClaseMetodo+"{------}"+mensajeError);
    }
    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class PropiedadesVentas {
    Properties propiedades = new Properties();
    private boolean estado;
    
    public void CargarDatos(){
        try {
            propiedades.load(new FileInputStream("Ventas.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropiedadesVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Cambio(){
        estado = Boolean.valueOf(propiedades.getProperty("frmCambio"));
    }
    public void Modificar(String estado){
        try {
            propiedades.setProperty("frmCambio", estado);
            propiedades.store(new FileOutputStream("Ventas.properties"), "");
        } catch (IOException ex) {
            Logger.getLogger(PropiedadesVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isEstado() {
        return estado;
    }

    

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author sergio
 */
public class Bitacora {
    private int IdUsuario;
    private String Fecha;
    private String Accion; 

    public Bitacora() {
    }
    
    public Bitacora(int IdUsuario, String Fecha, String Accion) {
        this.IdUsuario = IdUsuario;
        this.Fecha = Fecha;
        this.Accion = Accion;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Bitacora {
    private int IdUsuario;
    private Date Fecha;
    private String Accion; 

    public Bitacora() {
    }
    
    public Bitacora(int IdUsuario, Date Fecha, String Accion) {
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

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }
    
}

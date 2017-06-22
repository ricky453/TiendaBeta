/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author jose
 */
public class TipoPrecio {
    private int idTipoPrecio ;
    private String nombre;
    private double utilidad;

    public int getIdTipoPrecio() {
        return idTipoPrecio;
    }

    public void setIdTipoPrecio(int idTipoPrecio) {
        this.idTipoPrecio = idTipoPrecio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }
    
}

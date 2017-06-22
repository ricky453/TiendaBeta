/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.DecimalFormat;

/**
 *
 * @author jose
 */
public class DetalleVenta {
    private Producto producto;
    private double Cantidad;
    private double PrecioUnitario;
    DecimalFormat decimal = new DecimalFormat("0.00");
    
    public void Nuevo(String Cod, int Cantidad){
        
    }
    public double CalcularPrecio(double utilidad){
        double nuevoPrecio=0,precioFinal;
        nuevoPrecio=((this.PrecioUnitario))/utilidad;
        precioFinal=Math.round(nuevoPrecio*100.0)/100.0;
        return Double.parseDouble(decimal.format(precioFinal));
    }
    public double CalcularPrecioDetalle(){
        double nuevoPrecio=0;
        nuevoPrecio=(this.PrecioUnitario*this.Cantidad);
        return Double.parseDouble(decimal.format(nuevoPrecio));
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }
    
    
}

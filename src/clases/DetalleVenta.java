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
    private String codBarra;
    DecimalFormat decimal = new DecimalFormat("0.00");
    
    public void Nuevo(String Cod, int Cantidad){
        this.Cantidad=Cantidad;
        this.codBarra=Cod;
        
    }
    public double CalcularPrecio(double utilidad){  //Para un producto:jabones
        //La utilidad es respecto al tipo precio
        double nuevoPrecio=0;
        nuevoPrecio=((this.PrecioUnitario))/utilidad; //precio de venta
        
        return Double.parseDouble(decimal.format(nuevoPrecio));
    }
    
    public double CalcularPrecioDetalle(){  //Para un conjunto de productos del mismo tipo: muchos jabones
        double nuevoPrecio=0;
        nuevoPrecio=(this.PrecioUnitario*this.Cantidad);
        return Double.parseDouble(decimal.format(nuevoPrecio));
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
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
        this.Cantidad =  Cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }
    
    
}

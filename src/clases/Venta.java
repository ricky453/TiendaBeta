/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jose
 */
public class Venta {
    private int IdVenta;//
    
    private int IdSucursal;//
    private int IdPrecio;//
    private Date Fecha;//
    private double IVA=0;//
    private double TotalGravado=0;//
    private double Total=0;//
    private double PAC=0;//
    private double utilidad;//
    private String Cliente="";//
    private String Direccion="";//
    private String Giro="";//
    private String NIT="";//
    private int NRC;//
    private int NomDocumento;//
    private char IdTipoVenta;//
    
    private ArrayList<Object> Articulos = new ArrayList<>();
    DecimalFormat decf = new DecimalFormat("0.0000");
    
    public void AgregarItem(DetalleVenta item){
        
        Articulos.add(this.IdVenta);
        Articulos.add(item.getCodBarra());
        Articulos.add(item.getCantidad());
        Articulos.add(item.getPrecioUnitario());
        System.out.println("Agregar nuevo item");
    }
    
    public void CalcularTotal(){
        this.Total=this.TotalGravado+this.IVA;
        
    }
    public double CalcularIVA(){
        double conImpuesto =0;
        conImpuesto =this.TotalGravado*1.13;
        this.IVA=conImpuesto-TotalGravado;
        
        return conImpuesto;
    }

    public double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }
    
    public void CalcularPAC(){
        double pac;
        pac=Double.parseDouble(decf.format(this.Total*(1.75/100)));
        this.PAC= pac;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public double getPAC() {
        return PAC;
    }

    public void setPAC(double PAC) {
        this.PAC = PAC;
    }
    
    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IsSucursal) {
        this.IdSucursal = IsSucursal;
    }

    public int getIdPrecio() {
        return IdPrecio;
    }

    public void setIdPrecio(int IdPrecio) {
        this.IdPrecio = IdPrecio;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTotalGravado() {
        return TotalGravado;
    }

    public void setTotalGravado(double TotalGravado) {
        this.TotalGravado = TotalGravado;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getGiro() {
        return Giro;
    }

    public void setGiro(String Giro) {
        this.Giro = Giro;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public int getNRC() {
        return NRC;
    }

    public void setNRC(int NRC) {
        this.NRC = NRC;
    }

    public int getNomDocumento() {
        return NomDocumento;
    }

    public void setNomDocumento(int NomDocumento) {
        this.NomDocumento = NomDocumento;
    }

    public char getIdTipoVenta() {
        return IdTipoVenta;
    }

    public void setIdTipoVenta(char IdTipoVenta) {
        this.IdTipoVenta = IdTipoVenta;
    }

    public ArrayList<Object> getArticulos() {
        return Articulos;
    }

   
    
    
}

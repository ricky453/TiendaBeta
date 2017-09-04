package clases;

import java.util.Date;
import java.util.ArrayList;

public class Compra {
    private int IdCompra;
    private Date fecha;
    private Proveedor PROVEEDOR;
    private int IdSucursal;
    private char TipoCompra;
    private String NumDocumento;
    private double SubTotal;
    private double IVA;
    private double percepcion;
    private double Total;
    private ArrayList<DetalleCompra> ARTICULOS;
    private int IdUsuario;

    public Compra() {
    }

    public int getIdCompra() {
        return IdCompra;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public void setIdCompra(int IdCompra) {
        this.IdCompra = IdCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Proveedor getPROVEEDOR() {
        return PROVEEDOR;
    }

    public void setPROVEEDOR(Proveedor PROVEEDOR) {
        this.PROVEEDOR = PROVEEDOR;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public char getTipoCompra() {
        return TipoCompra;
    }

    public void setTipoCompra(char TipoCompra) {
        this.TipoCompra = TipoCompra;
    }

    public String getNumDocumento() {
        return NumDocumento;
    }

    public void setNumDocumento(String NumDocumento) {
        this.NumDocumento = NumDocumento;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(double percepcion) {
        this.percepcion = percepcion;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public ArrayList<DetalleCompra> getARTICULOS() {
        return ARTICULOS;
    }

    public void setARTICULOS(ArrayList<DetalleCompra> ARTICULOS) {
        this.ARTICULOS = ARTICULOS;
    }
    
    public void AgregarItem(DetalleCompra dc) throws ErrorTienda{
        try{       
            this.ARTICULOS.add(dc);
            this.CalcularTotal();
        }catch(Exception ex) {
            throw new ErrorTienda("Class Compra/AgregarItem", ex.getMessage());
        }
    }
    
    public void CalcularTotal() throws ErrorTienda {
        double total=0;
        try{
            for(DetalleCompra dc: this.ARTICULOS){
                total=total+(dc.getCantidad()*dc.getCostoUnitario());
            }
            this.Total = Math.round(total*100.0)/100.0;
        }catch(ArithmeticException ex){
            throw new ErrorTienda("Class Compra/CalcularTotal", ex.getMessage());  
        }
    }
    
    public void CalcularIVA() throws ErrorTienda{
        double total = 0;
        try{
            for(DetalleCompra dc: this.ARTICULOS){
                total=total+(dc.getCantidad()*dc.getCostoUnitario());
            }
            total = total * getIVA();
            this.IVA = Math.round(total*100.0)/100.0;
            
        }catch(ArithmeticException ex){
            throw new ErrorTienda("Class Compra/CalcularTotal", ex.getMessage());  
        }
        
    }
    
    public void CalcularPercepcion() throws ErrorTienda{
        double total = 0;
        try{
            for(DetalleCompra dc: this.ARTICULOS){
                total=total+(dc.getCantidad()*dc.getCostoUnitario());
            }
            total = total * getPercepcion();
            this.percepcion = Math.round(total*100.0)/100.0;
            
        }catch(ArithmeticException ex){
            throw new ErrorTienda("Class Compra/CalcularTotal", ex.getMessage());  
        }
    }
}

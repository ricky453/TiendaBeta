package clases;

public class DetalleCompra {
    private int IdCompra;
    private Producto PRODUCTO;
    private double Cantidad;
    private double CostoUnitario;

    public DetalleCompra() {
    }

    public int getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(int IdCompra) {
        this.IdCompra = IdCompra;
    }

    public Producto getPRODUCTO() {
        return PRODUCTO;
    }

    public void setPRODUCTO(Producto PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getCostoUnitario() {
        return CostoUnitario;
    }

    public void setCostoUnitario(double CostoUnitario) {
        this.CostoUnitario = CostoUnitario;
    }
    
}

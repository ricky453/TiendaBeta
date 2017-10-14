package clases;
public class Producto {
    
    private String CodBarra="";
    private String nombre="";
    private double costo;
    public int Inventario=0;
    public int IdSucursal=0;

    public Producto() {
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }
    
    public String getCodBarra() {
        return CodBarra;
    }

    public void setCodBarra(String CodBarra) {
        this.CodBarra = CodBarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    public int getInventario() {
        return Inventario;
    }

    public void setInventario(int Inventario) {
        this.Inventario = Inventario;
    }
    
}

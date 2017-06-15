package clases;
public class ControladorCompra {
    static Conexion cn;
    public static void Agregar(Compra compra) throws ErrorTienda{
        try {
            cn.st.executeUpdate("INSERT INTO compra VALUES ('"+compra.getIdCompra()+"', '"+compra.getFecha()+"', '"+compra.getPROVEEDOR().getIdProveedor()
            +"', '"+compra.getIdSucursal()+"', '"+compra.getTipoCompra()+"', '"+compra.getNumDocumento()+"', '"+compra.getSubTotal()+"', '"+compra.getIVA()
            +"', '"+compra.getPercepcion()+"', '"+compra.getTotal()+"');");
            
            
        } catch (Exception e) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", e.getMessage());
        }
    }
}

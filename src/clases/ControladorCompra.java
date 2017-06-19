package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorCompra {
    static Conexion cn;
    public static void Agregar(Compra compra) throws ErrorTienda{
        try {
            cn.st.executeUpdate("INSERT INTO compra VALUES ('"+compra.getIdCompra()+"', '"+compra.getFecha()+"', '"+compra.getPROVEEDOR().getIdProveedor()
            +"', '"+compra.getIdSucursal()+"', '"+compra.getTipoCompra()+"', '"+compra.getNumDocumento()+"', '"+compra.getSubTotal()+"', '"+compra.getIVA()
            +"', '"+compra.getPercepcion()+"', '"+compra.getTotal()+"');");
            
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", e.getMessage());
        }
    }
    public static ArrayList<DetalleCompra> ObtenerCompra() throws ErrorTienda{
        ArrayList<Object> dc= new ArrayList<Object>();
        ResultSet rs;
        try {
            rs = cn.st.executeQuery("SELECT * FROM detalleCompra");
            while (rs.next()) {
                dc.add(rs.getString(1));
                dc.add(rs.getString(2));
                dc.add(rs.getString(3));
 
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", e.getMessage());
        }
        ArrayList<DetalleCompra> detalleCompra = (ArrayList) dc;
        return detalleCompra;
    }
    
    public static void ActualizarInventario(Object[][] dc) throws ErrorTienda{
        cn = new Conexion();
        try {
            Producto pr;
            for (int i = 0; i < dc.length; i++) {
                pr = ControladorProducto.Obtener(String.valueOf(dc[i][0]));
                int cantidad, cantidad2;
                cantidad = pr.getInventario();
                cantidad2 = (Integer) dc[i][2];
                cn.st.executeUpdate("UPDATE inventario SET cantidad = '"+(cantidad+cantidad2)+"' WHERE CodBarra = '"+dc[i][0]+"';");
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", e.getMessage());
        }
    }
}

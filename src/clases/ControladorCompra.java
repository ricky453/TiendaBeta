package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    
    public static void ActualizarPrecioPromedioProducto(Object[][] dc) throws ErrorTienda{
        int CantidadActual=0;
        DecimalFormat decimal = new DecimalFormat("#.####");
        
        try {
            for (int i = 0; i < dc.length; i++) {
                double actualizarPrecio=0.0;
            
                ResultSet rsCantidad = null;
                rsCantidad = cn.st.executeQuery("SELECT Inventario FROM productos WHERE CodBarra='"+dc[i][0]+"';");
                
                CantidadActual=0;
                while(rsCantidad.next()){
                    CantidadActual = rsCantidad.getInt(1);
                }

                //Obtener el precio actual
                double PrecioActual=0;

                ResultSet rsPrecio = null;
                rsPrecio = cn.st.executeQuery("SELECT Costo FROM Productos WHERE CodBarra='"+dc[i][0]+"';");

                while(rsPrecio.next()){
                    PrecioActual = rsPrecio.getDouble(1);


                }
                
                actualizarPrecio = CantidadActual * PrecioActual;
                actualizarPrecio = actualizarPrecio + ( Integer.parseInt(dc[i][2].toString()) * Double.parseDouble(dc[i][3].toString()) );
                actualizarPrecio = actualizarPrecio / (Integer.parseInt(dc[i][2].toString())+CantidadActual);
                cn.st.executeUpdate("UPDATE Productos SET Costo='"+decimal.format(actualizarPrecio)+"' WHERE CodBarra='"+dc[i][0]+"';");
                
            }
        
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ActualizarPrecioPromedioProducto", ex.getMessage());
        }
    }
    
    public static int ObtenerIdCompra() throws ErrorTienda {
       cn =new Conexion();
        int IdCompra=0;
        try {
        ResultSet rsIdCompra = null;
        rsIdCompra = cn.st.executeQuery("SELECT COUNT(*) FROM Compra");
        
        while(rsIdCompra.next()){
            IdCompra = rsIdCompra.getInt("count(*)");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ObtenerIdCompra", ex.getMessage());
        } 
        return IdCompra;
    }
    
    
    
    }


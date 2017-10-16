package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ControladorCompra {
    static Conexion cn;
    public static void Agregar(Compra compra, Object[][] detalleCompra) throws ErrorTienda{
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Fecha = sdf.format(compra.getFecha());
            cn.st.executeUpdate("INSERT INTO compra VALUES ('"+compra.getIdCompra()+"', '"+Fecha+"', '"+compra.getPROVEEDOR().getIdProveedor()
            +"', '"+compra.getIdSucursal()+"', '"+compra.getTipoCompra()+"', '"+compra.getNumDocumento()+"', '"+compra.getSubTotal()+"', '"+compra.getIVA()
            +"', '"+compra.getPercepcion()+"', '"+compra.getTotal()+"');");
            for(int x=0;x<detalleCompra.length;x++){
                   cn.st.execute("INSERT INTO detallecompra VALUES('"
                           +detalleCompra[x][0]+"','"+detalleCompra[x][1]+"','"+detalleCompra[x][2]+"','"+detalleCompra[x][3]+"', '"+compra.getIdSucursal()+"');");
                   
            }
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", e.getMessage());
        }
    }
    public static ArrayList<DetalleCompra> ObtenerCompra(int id) throws ErrorTienda{
        ArrayList<Object> dc= new ArrayList<Object>();
        cn=new Conexion(); 
        ResultSet rs;
        try {
            rs = null;
            rs = cn.st.executeQuery("SELECT `detallecompra`.`IdCompra`, `producto`.`Nombre`, `detallecompra`.`Cantidad`, `detallecompra`.`CostoUnitario`\n" +
                  "FROM `producto`\n" +
                  "    LEFT JOIN `detallecompra` ON `detallecompra`.`CodBarra` = `producto`.`CodBarra` WHERE detallecompra.IdCompra='"+id+"'");
            while (rs.next()) {
                dc.add(rs.getString(1));
                dc.add(rs.getString(2));
                dc.add(rs.getString(3));
                dc.add(rs.getString(4));
 
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorCompra/ObtenerComprar", e.getMessage());
        }
        ArrayList<DetalleCompra> detalleCompra = (ArrayList) dc;
        return detalleCompra;
    }
    
    public static void ActualizarInventario(Object[][] dc, int IdSucursal) throws ErrorTienda{
        cn = new Conexion();
        ResultSet rs;
        try {
            Producto producto = new Producto();
            for (int i = 0; i < dc.length; i++) {
                rs = cn.st.executeQuery("SELECT * FROM inventario WHERE IdSucursal = '"+IdSucursal+"' AND CodBarra = '"+dc[i][1]+"';");
                //System.out.println(rs.first()+", "+rs.next());
                if (rs != null && rs.next()){
                        
                        producto.setCodBarra(rs.getString("CodBarra"));
                        producto.setIdSucursal(rs.getInt(2));
                        producto.setInventario(rs.getInt(3));

                        cn.st.executeUpdate("UPDATE inventario SET cantidad = '"+((int) dc[i][2]+producto.getInventario())+"' WHERE CodBarra = '"+dc[i][1]+"' AND IdSucursal='"+IdSucursal+"';");
                        System.out.println("UPDATE inventario SET cantidad = '"+((int) dc[i][2]+producto.getInventario())+"' WHERE CodBarra = '"+dc[i][1]+"' AND IdSucursal='"+IdSucursal+"';");
                    
                    
                }else{
                    cn.st.executeUpdate("INSERT INTO inventario VALUES('"+dc[i][1]+"', '"+IdSucursal+"', '"+dc[i][2]+"')");
                }
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorCompra/ActualizarInventario", e.getMessage());
        }
    }
    
    public static void ActualizarPrecioPromedioProducto(Object[][] dc) throws ErrorTienda{
        int CantidadActual=0;
        DecimalFormat decimal = new DecimalFormat("#.####");
        System.out.println(dc.length);
        try {
            for (int i = 0; i < dc.length; i++) {
                double actualizarPrecio=0.0;
            
                ResultSet rsCantidad = null;
                rsCantidad = cn.st.executeQuery("SELECT Cantidad FROM inventario WHERE CodBarra='"+dc[i][0]+"';");
                
                CantidadActual=0;
                int j=1;
                while(rsCantidad.next()){
                    CantidadActual = CantidadActual + rsCantidad.getInt(j);
                    j++;
                }

                //Obtener el precio actual
                double PrecioActual=0;

                ResultSet rsPrecio = null;
                rsPrecio = cn.st.executeQuery("SELECT Costo FROM producto WHERE CodBarra='"+dc[i][0]+"';");

                while(rsPrecio.next()){
                    PrecioActual = rsPrecio.getDouble(1);


                }
                
                actualizarPrecio = CantidadActual * PrecioActual;
                actualizarPrecio = actualizarPrecio + ( Integer.parseInt(dc[i][2].toString()) * Double.parseDouble(dc[i][3].toString()) );
                actualizarPrecio = actualizarPrecio / (Integer.parseInt(dc[i][2].toString())+CantidadActual);
                cn.st.executeUpdate("UPDATE producto SET Costo='"+decimal.format(actualizarPrecio)+"' WHERE CodBarra='"+dc[i][0]+"';");
                
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
        rsIdCompra = cn.st.executeQuery("SELECT COUNT(*) FROM compra");
        
        while(rsIdCompra.next()){
            IdCompra = rsIdCompra.getInt("count(*)");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ObtenerIdCompra", ex.getMessage());
        } 
        return IdCompra;
    }
        public static ArrayList<Compra> obteniendoCompras(String fecha) throws ErrorTienda{
        ArrayList<Object> compras=new ArrayList<Object>();
        cn=new Conexion();
        try {
            ResultSet rs;
            rs=cn.st.executeQuery("SELECT compra.IdCompra, sucursal.Nombre, compra.IdProveedor, compra.TipoCompra, compra.Fecha FROM sucursal INNER JOIN compra ON compra.IdSucursal=sucursal.IdSucursal WHERE compra.Fecha LIKE '"+fecha+"%'");
            
            while (rs.next()) {
                compras.add(rs.getString(1));
                compras.add(rs.getString(2));
                compras.add(rs.getString(3));
                compras.add(rs.getString(4));
                compras.add(rs.getString(5));
                
            }
            
            
        } catch (SQLException ex) {
            throw new ErrorTienda("Class ControladorVenta/obtiendoVentas",ex.getMessage());
        }
        
        ArrayList<Compra> micompras=(ArrayList) compras;
        return micompras; 
    }
    
    
    }


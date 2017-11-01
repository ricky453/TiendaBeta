
package ticket;

import clases.Venta;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class UsoTicket {
    
    private static PrinterOptions p = new PrinterOptions();
    
    public static void imprimir() {
        
        //metodo para imprimir
        feedPrinter(p.finalCommandSet().getBytes());

    }
    
    public static void borradoInicializacion(){
        p.resetAll();
        p.initialize();
    }
    
    public static void cabecera(){
        p.feedBack((byte) 2);
        p.chooseFont(1);
        p.color(0);
        p.alignLeft();
        p.setText("\tTienda ABC");
        p.newLine();
        p.setText("\tFinal calle principal santa ana");
        p.newLine();
       
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosTicket(Object sucursal, Date fecha){
        p.alignLeft();
        p.setText("Num. Ticket:" + "1");
        p.newLine();
        p.setText("Sucursal "+sucursal);
        p.newLine();
        p.setText("Factura");
        p.newLine();
        p.setText("Fecha de venta: "+fecha);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosVentaFactura(String venta[][],String total,int filas){
        double totalInterno=0;
        p.setText("Descripción \tCantidad\tPrecio\t$Total");
        p.newLine();
        
        for (int i = 0; i < filas; i++) {
            p.setText(venta[i][0]+" "+venta[i][1]+" "+venta[i][2]+" "+venta[i][3]);
            totalInterno +=Double.parseDouble(venta[i][3]);
            p.newLine();
        }
        p.newLine();
        p.setText("Total: " + total+" ");
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosVentaCreditoFiscal(String venta[][],String subtotal,String iva,String total,int filas){
        
        p.setText("Descripción \tCantidad\tPrecio\t$Total");
        p.newLine();
        
        for (int i = 0; i < filas; i++) {
            p.setText(venta[i][0]+" "+venta[i][1]+" "+venta[i][2]+" "+venta[i][3]);
            
            p.newLine();
        }
        p.newLine();
       
        p.setText("SubTotal: $" + subtotal);
        p.newLine();
        p.setText("Iva: $"+iva);
        p.newLine();
        p.setText("Total: $"+total);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosClienteFactura(){
        p.alignLeft();
        p.setText(" - Detalles Cliente- ");
        p.newLine();
        p.alignLeft();
        p.addLineSeperator();
        p.newLine();

        p.setText("Cliente: " + "el cliente");
        p.newLine();
        p.setText("Direccion: "+"la direccion");
   
        p.addLineSeperator();
        p.newLine();
        p.setText("Tenga un buen dia!!!");
        p.newLine();
        p.addLineSeperator();
        p.feed((byte) 3);
        
        p.finit();
    }
    
    public static void datosVendedor(){
        p.alignLeft();
        p.setText("\t - Detalles Vendedor - ");
        p.newLine();
        p.setText("Id vendedor: "+"id");
        p.newLine();
        p.setText("Vendedor: "+"login");
        p.newLine();
        p.setText("Rol: "+"rolito");
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.finit();
        
    }
    
    public static void datosClienteCreditoFiscal(){
        p.alignLeft();
        p.setText(" - Detalles Cliente- ");
        p.newLine();
        p.alignLeft();
        p.addLineSeperator();
        p.newLine();

        p.setText("Cliente: " + "el cliente");
        p.newLine();
        p.setText("Dirección: "+"la direccion");
        p.newLine();
        p.setText("Giro"+"giro men");
        p.newLine();
        p.setText("NRC: "+"giro men");
        p.newLine();
        p.setText("NIT: "+"nitmen");
        p.newLine();
        p.setText("N° Documento"+"documento bitchs");
        p.newLine();
   
        p.addLineSeperator();
        p.newLine();
        p.setText("Tenga un buen dia!!!");
        p.newLine();
        p.addLineSeperator();
        p.feed((byte) 3);
        
        p.finit();
    }
    
    private static boolean feedPrinter(byte[] b) {
        try {
            AttributeSet attrSet = new HashPrintServiceAttributeSet(new PrinterName("3nstar", null)); //EPSON TM-U220 ReceiptE4

            DocPrintJob job = PrintServiceLookup.lookupPrintServices(null, attrSet)[0].createPrintJob();
         
   //PrintServiceLookup.lookupDefaultPrintService().createPrintJob();  

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(b, flavor, null);

            job.print(doc, null);

        } catch (javax.print.PrintException pex) {
            System.out.println("Printer Error " + pex.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}

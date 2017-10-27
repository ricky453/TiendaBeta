
package ticket;

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
    
    public static void datosTicket(){
        p.alignLeft();
        p.setText("Num. Ticket:" + "1");
        p.newLine();
        p.setText("Sucursal"+"suco");
        p.newLine();
        p.setText("Factura");
        p.newLine();
        p.setText("Fecha de venta: "+"fuking fecha");
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosVentaFactura(){
        p.setText("Descripción \tCantidad\tPrecio\t$Total");
        p.newLine();
        
        for (int i = 0; i < 4; i++) {
            p.setText("os"+"3"+"5"+"89");
            p.newLine();
        }
        p.newLine();
       
        p.setText("Total: $" + "cuando nos vemos?");
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosVentaCreditoFiscal(){
        p.setText("Descripción \tCantidad\tPrecio\t$Total");
        p.newLine();
        
        for (int i = 0; i < 4; i++) {
            p.setText("os"+"3"+"5"+"89");
            p.newLine();
        }
        p.newLine();
       
        p.setText("SubTotal: $" + "venta subtotal");
        p.newLine();
        p.setText("Iva: $"+"venta iva");
        p.newLine();
        p.setText("Total: $"+"venta total");
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
        p.setText("Dirección: "+"la direccion");
   
        p.addLineSeperator();
        p.newLine();
        p.setText("Tenga un buen dia!!!");
        p.newLine();
        p.addLineSeperator();
        p.feed((byte) 3);
        
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

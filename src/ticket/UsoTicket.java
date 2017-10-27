
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
    
    public static void imprimiendo(String[] args) {
        

PrinterOptions p = new PrinterOptions();

        p.resetAll();
        p.initialize();
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


//metodo para imprimir

        feedPrinter(p.finalCommandSet().getBytes());



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

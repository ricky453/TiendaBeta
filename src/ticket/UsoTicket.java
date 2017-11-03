
package ticket;

import clases.Venta;
import java.text.DecimalFormat;
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
    private static DecimalFormat decimal=new DecimalFormat("#.##");
    
    public static void imprimir() {
        
        //metodo para imprimir
        feedPrinter(p.finalCommandSet().getBytes());

    }
    
    public static void borradoInicializacion(){
        p.resetAll();
        p.initialize();
    }
    
    public static void cabecera(){
        p.feedBack((byte)2);
        p.chooseFont(1);
        p.color(0);
        p.alignLeft();
        p.setText("\tTIENDA ABC");
        p.newLine();
        p.setText("  Final Calle Principal Santa Ana");
        p.newLine();
       
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosTicket(Object sucursal, String fecha,String tipoVenta){
        p.alignLeft();
        p.newLine();
        p.setText("Sucursal "+sucursal);
        p.newLine();
        p.setText("Tipo de venta: "+tipoVenta);
        p.newLine();
        p.setText("Fecha de venta: "+fecha);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosVentaFactura(String venta[][],String total,int filas){
        
        p.setText("Descripcion\tCantidad   Precio+Iva");
        p.newLine();
        
        for (int i = 0; i < filas; i++) {
            
            if (venta[i][0].length()>15) {
                String tempo1,tempo2;
                int largoCantidad=venta[i][1].length();
                tempo1=venta[i][0].substring(0, 14);
                tempo2=venta[i][0].substring(14);
                
                
                for (int j = tempo2.length(); j <= 15; j++) {
                    tempo2=tempo2+" ";
                }
                venta[i][0]=tempo1+"\n"+tempo2;
                
                for (int j = largoCantidad; j <=5; j++) {
                    venta[i][1]=venta[i][1]+" ";
                }
                
                String precioIva=decimal.format(Double.parseDouble(venta[i][2])*1.13);
                
                p.setText(venta[i][0]+"\t  "+venta[i][1]+"   "+precioIva);
                p.newLine();
                
                
            }else{
                int largo=venta[i][0].length();
                int largoCantidad=venta[i][1].length();
                
                for (int j = largo; j <= 15; j++) {
                    venta[i][0]=venta[i][0]+" ";
                }
                
                for (int j = largoCantidad; j <=5; j++) {
                    venta[i][1]=venta[i][1]+" ";
                }
                
                p.setText(venta[i][0]+"\t  "+venta[i][1]+"   "+venta[i][2]);
                p.newLine();
            }
            
            
        }
        p.newLine();
        p.setText("Total: " + total);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
    }
    
    public static void datosVentaCreditoFiscal(String venta[][],String subtotal,String iva,String total,int filas){
        
        p.setText("Descripcion\tCantidad   Precio");
        p.newLine();
        
        for (int i = 0; i < filas; i++) {
            
            if (venta[i][0].length()>15) {
                String tempo1,tempo2;
                int largoCantidad=venta[i][1].length();
                tempo1=venta[i][0].substring(0, 14);
                tempo2=venta[i][0].substring(14);
                
                
                for (int j = tempo2.length(); j <= 15; j++) {
                    tempo2=tempo2+" ";
                }
                venta[i][0]=tempo1+"\n"+tempo2;
                
                for (int j = largoCantidad; j <=5; j++) {
                    venta[i][1]=venta[i][1]+" ";
                }
                
                p.setText(venta[i][0]+"\t  "+venta[i][1]+"   "+venta[i][2]);
                p.newLine();
                
                
            }else{
                int largo=venta[i][0].length();
                int largoCantidad=venta[i][1].length();
                
                for (int j = largo; j <= 15; j++) {
                    venta[i][0]=venta[i][0]+" ";
                }
                
                for (int j = largoCantidad; j <=5; j++) {
                    venta[i][1]=venta[i][1]+" ";
                }
                
                p.setText(venta[i][0]+"\t  "+venta[i][1]+"   "+venta[i][2]);
                p.newLine();
            }
            
            
        }
        p.newLine();
       
        p.setText("SubTotal: " + subtotal);
        p.newLine();
        p.setText("Iva: "+iva);
        p.newLine();
        p.setText("Total: "+total);
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
    
    public static void datosVendedor(int id,String login,String rol){
        p.alignLeft();
        p.setText("\t - Detalles Vendedor - ");
        p.newLine();
        p.setText("Id vendedor: "+id);
        p.newLine();
        p.setText("Vendedor: "+login);
        p.newLine();
        p.setText("Rol: "+rol);
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

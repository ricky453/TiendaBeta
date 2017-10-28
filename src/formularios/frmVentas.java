/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorProducto;
import clases.ControladorSucursal;
import clases.ControladorTipoPrecio;
import clases.ControladorVenta;
import clases.DetalleVenta;
import clases.ErrorTienda;
import clases.Producto;
import clases.Sucursal;
import clases.TipoPrecio;
import clases.Venta;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import ticket.UsoTicket;

/**
 *
 * @author Ricky
 */
public class frmVentas extends javax.swing.JFrame {

    boolean estadoMenu;
    JTableHeader tHeadVentas;
    DefaultTableModel modeloVentas;
    ArrayList<Sucursal> sucursales = new ArrayList();
    ArrayList<TipoPrecio> precio = new ArrayList();
    Iterator<Sucursal> Iterador;
    Object miSucursal[][],misPrecios[][] ;
    DecimalFormat decimal =new DecimalFormat("#.##");
    double subTotales,utilidad,CostoGravado;
    
    ControladorVenta cv;
    ControladorProducto cp;
    Producto miProducto;
    Venta venta = new Venta();
    DetalleVenta dv = new DetalleVenta();
    ControladorTipoPrecio precios;
    Date fecha = new Date();
    
    
    public frmVentas() throws ErrorTienda {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        tHeadVentas = tblProductosVender.getTableHeader();
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraSuperior.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
        modeloVentas = (DefaultTableModel) tblProductosVender.getModel();
        
        
        ObtenerIdVenta();
        CargarSucursales();
        
        tipoPrecios();
        
        txtCodigoBarraVender.requestFocus();
        tipoVentaSeleccion(false);
        dtcFecha.setDate(fecha);
        
    }
    //METODO GENERAL PARA ENVIAR MENSAJES POR NOTIFICAICON DE FRMNOTIFICACION
    public void mensajeNotificacion(String mensaje, String tipo){
        if(tipo.equals("Error")){
        frmNotificacion not = new frmNotificacion();
        not.Mensaje(mensaje);
        not.setVisible(true);
        not.lblIcono.setIcon(new ImageIcon(getClass().getResource("/iconos/Error.png")));
        //not.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminar.png")));
        }else if(tipo == "Ok"){
        frmNotificacion not = new frmNotificacion();
        not.Mensaje(mensaje);
        not.setVisible(true);
        not.lblIcono.setIcon(new ImageIcon(getClass().getResource("/iconos/Ok.png")));
        }else if(tipo == "Adv"){
        frmNotificacion not = new frmNotificacion();
        not.Mensaje(mensaje);
        not.setVisible(true);
        not.lblIcono.setIcon(new ImageIcon(getClass().getResource("/iconos/Adv.png")));
        }
    }
    //OBTENER EL NUEVO ID DE VENTA ACTUALIZADO
    public void ObtenerIdVenta() throws ErrorTienda {
        int idVenta=0;
        
        try {
            cv = new ControladorVenta();
            idVenta = cv.ObtenerIdVenta();
        } catch (ErrorTienda ex) {
            throw new ErrorTienda("Formulario ventas <--> Falló al obtener el producto",ex.getMessage());
        }
        txtIdVenta.setText(""+idVenta);
    }
    //OBETENER PRODUCTO CON CODIGO DE BARRA COMO PARAMETRO
    public void ObtenerProducto(String CodBarra) throws ErrorTienda{
        //Comprobar que el espacio de codigo de barra no esteb vacio 
        if(!txtCodigoBarraVender.getText().isEmpty()){
            
            try {
                int idSucursal = Integer.valueOf(String.valueOf(miSucursal[cmbSucursalVenta.getSelectedIndex()][0]));
                 miProducto = ControladorProducto.Obtener(CodBarra,idSucursal);
            } catch (ErrorTienda ex) {
                throw new ErrorTienda("ObtenerProducto error", ex.getMessage());
            }
            //VERIFICAR SI LA SUCURSAL SELECCIONADA CONSTA DE ESE PRODUCTO
            if(miProducto.getIdSucursal()!=0){
               // Verificar si dicho codigo de barra esta almacenado en la bd
            if(miProducto.getInventario()==0){
                
                mensajeNotificacion("Producto Agotado", "Adv");
                txtCodigoBarraVender.requestFocus();
                txtCodigoBarraVender.selectAll();
            }else{
                txtNombreProductoVender.setText(miProducto.getNombre());
                     txtCantidadVender.requestFocus();
                     txtCantidadVender.selectAll();
                
            } 
            }else{
                mensajeNotificacion("No existe en esta sucursal","Adv");
                txtCodigoBarraVender.requestFocus();
                txtCodigoBarraVender.selectAll();
            }
            
            
        }else{
            mensajeNotificacion("El código de barra está vacío.", "Error");
            txtCodigoBarraVender.requestFocus();
            
            
        }
    }
    //CARGAR PRODUCTO EN LA TABLA
    public void cargarTabla() throws ErrorTienda{
        
        if(VerificarTabla()==false){
            int contador=0;
        int fila=modeloVentas.getRowCount();
        double costoUnitario=0,totalDetalle=0;
        listas(false);
        modeloVentas.addRow(new Object []{"","","","",""});
        modeloVentas.setValueAt(miProducto.getCodBarra(),fila, 0);
        modeloVentas.setValueAt(miProducto.getNombre(),fila, 1);
        modeloVentas.setValueAt(txtCantidadVender.getText(),fila, 2);
        //Verificar el tipo de precia que se usara en la venta
        while(contador <misPrecios.length){
            if(misPrecios[contador][1].equals(""+cmbTipoPrecio.getSelectedItem())){
                
                utilidad = (Double.parseDouble(String.valueOf(misPrecios[contador][2]))/(-100))+1;
                
                dv.setPrecioUnitario(miProducto.getCosto());
                
                
                
                //VERIFICAR EL TIPO DE VENTA
                costoUnitario=dv.CalcularPrecio(utilidad);
                modeloVentas.setValueAt(decimal.format(costoUnitario),fila, 3);
            
        }
        contador++;
        }
        dv.setPrecioUnitario(costoUnitario);
        dv.setCantidad(Double.parseDouble(txtCantidadVender.getText()));
        totalDetalle=dv.CalcularPrecioDetalle();
        double subTot=costoUnitario*Double.parseDouble(txtCantidadVender.getText());
        if(cmbTipoVenta.getSelectedIndex()==0){
            modeloVentas.setValueAt(decimal.format(subTot*1.13),fila, 4);
        }else{
            modeloVentas.setValueAt(decimal.format(subTot),fila, 4);
        }
        
        
        if(cmbTipoVenta.getSelectedIndex()==1){
        SumarSubTotales();
            
        venta.setTotalGravado(subTotales);
        venta.CalcularIVA();
        venta.CalcularTotal();
        
        txtIVA.setText("$ "+decimal.format(venta.getIVA()));
        txtSumas.setText("$"+decimal.format(subTotales));
        txtTotalventa.setText("$ "+decimal.format(venta.getTotal()));
        }else{
          SumarSubTotales();
        txtTotalventa.setText("$ "+decimal.format(subTotales));  
        }
        }
        
        limpiar("p");
    }
    //CALCULAR SUMA DE SUB TOTALES
    public void SumarSubTotales(){
        int filas = modeloVentas.getRowCount();
        subTotales=0;
        for(int i=0;i<filas;i++){
            subTotales+=Double.parseDouble(String.valueOf(modeloVentas.getValueAt(i, 4)));
        }
        
    }
    //OBTENER TODAS LAS SUCUARSALES
    public void CargarSucursales() throws ErrorTienda{
        sucursales = ControladorSucursal.obtener();
        miSucursal = new Object[sucursales.size()/4][4];
        
        int contador=0,fila=0;
        Iterator<Sucursal> iterador= sucursales.iterator();
        String temporal="";
        while (iterador.hasNext()){
            miSucursal[fila][0]=iterador.next();
            miSucursal[fila][1]=iterador.next();
            miSucursal[fila][2]=iterador.next();
            miSucursal[fila][3]=iterador.next();
            cmbSucursalVenta.addItem(""+miSucursal[fila][1]);
            fila++;
        }
       
    }
    //CALCULAR PAC
    
    //CARGAR LOS TIPOS DE PRECIO
    public void tipoPrecios() throws ErrorTienda{
        
        precio = ControladorTipoPrecio.ObtenerTodos();
        
        if(precio.size()!=0){
            misPrecios = new Object[precio.size()/3][3];
            int contador=0,fila=0;
        Iterator<TipoPrecio> iterador= precio.iterator();
                while(iterador.hasNext()){
                    misPrecios[fila][0]=iterador.next();
                    misPrecios[fila][1]=iterador.next();
                    misPrecios[fila][2]=iterador.next();
                    cmbTipoPrecio.addItem(""+misPrecios[fila][1]);
                    fila++;
                }
       
                cmbTipoPrecio.setSelectedIndex(1);
        }else{
            mensajeNotificacion("Aún no hay tipos de precio definidos", "Error");
            txtCodigoBarraVender.setEnabled(false);
            txtNombreProductoVender.setText("NO HAY TIPOS DE PRECIO, ES NECESARIO AGREGAR");
            txtNombreProductoVender.setForeground(Color.red);
            
        
            
        }
        
        
    }
    //LIMPIA EL FORMULARIO PERO COMO PARAMETRO SE ENVIA SI LIMPIA TODO O SOLO UNA PARTE DE EL, SIRVE PARA CANCELAR Y PRA CUANDO SE AGREGA UNA NUEVA TUPLA
    // A LA TABLA
    public void limpiar(String condicion){
        txtCantidadVender.setText("1");
        txtNombreProductoVender.setText("");
        txtCodigoBarraVender.setText("");
        txtCodigoBarraVender.requestFocus();
        
        if(condicion.equals("todo")){
            modeloVentas.setRowCount(0);
        txtTotalventa.setText("$ 0.00");
        txtIVA.setText("$ 0.00");
        txtSumas.setText("$ 0.00");
        txtClienteVenta.setText("");
        txtDireccionVenta.setText("");
        txtGiro.setText("");
        txtNRCVenta.setText("");
        txtNITVenta.setText("");
        txtNDocumento.setText("");
        dtcFecha.setDate(fecha);
        
        }
    }
    //ELIMINAR PRODUCTO SELECCIONADO DE LA TABLA
    public void EliminarProducto(){
        modeloVentas.removeRow(tblProductosVender.getSelectedRow());
    }
    //SIRVE PARA MOSTRAR U OCULTAR LOS ESPACIOS DE TEXTO QUE DEGLOSAN LOS DOS TIPOS DE VENTAS DISPONIBLES
    public void tipoVentaSeleccion(boolean estado){
        txtIVA.setVisible(estado);
          txtSumas.setVisible(estado);
          lblIVA.setVisible(estado);
          lblSumas.setVisible(estado);
          txtGiro.setVisible(estado);
          txtNRCVenta.setVisible(estado);
          txtNITVenta.setVisible(estado);
          txtNDocumento.setVisible(estado);
          lblGiro.setVisible(estado);
          lblNRC.setVisible(estado);
          lblNIT.setVisible(estado);
          lblDOC.setVisible(estado);
    }
    //ESTADO DE LAS LISTAS PLEGABLES
    public void listas (boolean estado){
        cmbSucursalVenta.setEnabled(estado);
        cmbTipoPrecio.setEnabled(estado);
        cmbTipoVenta.setEnabled(estado);
    }
    //GUARDAR, AGREGA LA VENTA A LA BASE DE DATOS
    public void guardar() throws ErrorTienda{
        char idTipoVenta;
        double totGravado;
        if(cmbTipoVenta.getSelectedIndex()==0){
            idTipoVenta='F';
        }else{
            idTipoVenta='C';
        }
        
        venta.setIdVenta(Integer.parseInt(txtIdVenta.getText()));
        venta.setIdPrecio(Integer.parseInt(String.valueOf(misPrecios[cmbTipoPrecio.getSelectedIndex()][0])));
        venta.setIdTipoVenta(idTipoVenta);
        venta.setIdSucursal(Integer.parseInt(String.valueOf(miSucursal[cmbSucursalVenta.getSelectedIndex()][0])));
        if(idTipoVenta=='F'){
            totGravado=Double.parseDouble(txtTotalventa.getText().substring(1))/1.13;
            
            venta.setTotalGravado(Double.parseDouble(decimal.format(totGravado)));
            venta.setIVA(Double.parseDouble(decimal.format(Double.parseDouble(txtTotalventa.getText().substring(1))-totGravado)));
            System.out.println("1");
            
            
            
        }else{
            totGravado=Double.parseDouble(txtSumas.getText().substring(1));
            venta.setTotalGravado(Double.parseDouble(txtSumas.getText().substring(1)));
            venta.setIVA(Double.parseDouble(txtIVA.getText().substring(1)));
            venta.setGiro(txtGiro.getText().toUpperCase());
            venta.setNIT(txtNITVenta.getText());
            venta.setNRC(Integer.parseInt(txtNRCVenta.getText()));
            venta.setNomDocumento(Integer.parseInt(txtNDocumento.getText()));
            

            
        }
        venta.setUtilidad(Double.parseDouble(decimal.format((totGravado-this.CostoGravado))));
        venta.setTotal(Double.parseDouble(txtTotalventa.getText().substring(1)));
        venta.setFecha(dtcFecha.getDate());
        venta.setCliente(txtClienteVenta.getText().toUpperCase());
        venta.setDireccion(txtDireccionVenta.getText().toUpperCase());
        venta.CalcularPAC();
        
        System.out.println("2");
        Object sucursal=cmbSucursalVenta.getSelectedItem();
        System.out.println("3");
        String producto=txtNombreProductoVender.getText();
        String total=txtTotalventa.getText();
        String subtotal=txtSumas.getText();
        String iva=txtIVA.getText();
        int filas=modeloVentas.getRowCount();
        
        String detalles[][] = new String[modeloVentas.getRowCount()][4];
            System.out.println("pereza");
            for(int y=0; y<modeloVentas.getRowCount();y++){
                detalles[y][0]=modeloVentas.getValueAt(y, 1).toString();
                detalles[y][1]=modeloVentas.getValueAt(y, 2).toString();
                detalles[y][2]=modeloVentas.getValueAt(y, 3).toString();
                detalles[y][3]=modeloVentas.getValueAt(y, 4).toString();
                
            }
        
        if (idTipoVenta=='F') {
            
            for (int i =1; i < modeloVentas.getRowCount(); i++) {
                
            }
            
            
            //Imprimir
            UsoTicket.borradoInicializacion();
            UsoTicket.cabecera();
            UsoTicket.datosTicket(sucursal,venta.getFecha());
            
            
            UsoTicket.datosVentaFactura(detalles,total,filas);
            
            UsoTicket.datosVendedor();
            UsoTicket.imprimir();
        }else{
            //Imprimir
            UsoTicket.borradoInicializacion();
            UsoTicket.cabecera();
            UsoTicket.datosTicket(sucursal,venta.getFecha());
            UsoTicket.datosVentaCreditoFiscal(detalles,subtotal,iva,total,filas);
            UsoTicket.datosVendedor();
            UsoTicket.imprimir();
        }
        
        
        
        Object DetallesVenta[][] = new Object[modeloVentas.getRowCount()][4];
        
        for(int y=0; y<modeloVentas.getRowCount();y++){
            DetallesVenta[y][0]=txtIdVenta.getText();
            DetallesVenta[y][1]=modeloVentas.getValueAt(y, 0);
            DetallesVenta[y][2]=modeloVentas.getValueAt(y, 2);
            DetallesVenta[y][3]=modeloVentas.getValueAt(y, 3);
        }
        
        if(cv.Agregar(venta,DetallesVenta)){
            mensajeNotificacion("¡Venta realizada!", "Ok");
            
            
        }else{
            mensajeNotificacion("¡Venta NO realizada!", "Error");
        }
        
    }
    public void CalcularCostoGravado(){
        
        for(int fila=0;fila<modeloVentas.getRowCount();fila++){
            double CostoProducto = Double.parseDouble(String.valueOf(modeloVentas.getValueAt(fila, 3)))*utilidad;
            int ProductoCantidad = Integer.parseInt(String.valueOf(modeloVentas.getValueAt(fila, 2)));
            this.CostoGravado+=CostoProducto*ProductoCantidad;
            
        }
        System.out.println("pereza men bitch");
        
    }
    //COMPROBAR QUE UN PRODUCTO SELECCIONADO ESTE O NO AGREGADO ANTRERIROMENTE A LA TABLA DE PRODUCTOS
    public boolean VerificarTabla() throws ErrorTienda{
        if(modeloVentas.getRowCount()!=0){// VERIFICA TUPLAS EXISTENTES EN LA TABLA DE PRODUCTOS DEL FORM
            
       int idSucursal = Integer.valueOf(String.valueOf(miSucursal[cmbSucursalVenta.getSelectedIndex()][0]));// OBTIENE EL ID DE LA SUCURSAL
       
       miProducto = ControladorProducto.Obtener(txtCodigoBarraVender.getText(),idSucursal);//OBTIENE LOS DATOS DEL PRODUCTO SOLICITADO
       
       for(int x=0;x<modeloVentas.getRowCount();x++){// RECORRE LA TABLA DE PRODUCTOS DEL FORM 
           
           if(txtCodigoBarraVender.getText().equals(modeloVentas.getValueAt(x, 0))){//VERIFICANDO SI ESTE YA EXISTE EN ESTA
               
               int cantidad = Integer.parseInt(txtCantidadVender.getText())+ Integer.parseInt(String.valueOf(modeloVentas.getValueAt(x, 2)));
               modeloVentas.setValueAt(cantidad, x, 2);
               double precioUnitario = Double.parseDouble(String.valueOf(modeloVentas.getValueAt(x, 3)));
               double subTotal = precioUnitario*cantidad;
               modeloVentas.setValueAt(decimal.format(subTotal), x, 4);
               if(cmbTipoVenta.getSelectedIndex()==0){
                   SumarSubTotales();
                   txtTotalventa.setText("$ "+decimal.format(subTotales));
               }else{
                   SumarSubTotales();
            System.out.println("sub totales "+subTotales);
        venta.setTotalGravado(subTotales);
        venta.CalcularIVA();
        venta.CalcularTotal();
        
        txtIVA.setText("$ "+decimal.format(venta.getIVA()));
        txtSumas.setText("$"+subTotales);
        txtTotalventa.setText("$ "+decimal.format(venta.getTotal()));
               }
               return true;
           }
       }
        }
       return false;
    }
    //VALIDAR QUE LOS CAMPOS ESTEN CORRECTAMENTE LLENADOS
    public boolean validar(int tipoVenta){
        int mensaje=0;
        if(modeloVentas.getRowCount()==0){
            mensajeNotificacion("No hay productos seleccionados", "Error");
            txtCodigoBarraVender.requestFocus();
            return false;
        }else{
            if(txtClienteVenta.getText().isEmpty()){
                    mensajeNotificacion("Falta llenar campos", "Adv");
                    lblCliente.setForeground(Color.red);
                    mensaje = 1;
                }
            if(txtDireccionVenta.getText().isEmpty()){
                    
                    lblDireccion.setForeground(Color.red);
                    mensaje = 1;
                
            }
            System.out.println("Tipo venta en metodo "+tipoVenta);
            if(tipoVenta==1){
                System.out.println("Tipo venta en metodo gasa"+tipoVenta);
              
              if(txtGiro.getText().isEmpty()){
                    
                    lblGiro.setForeground(Color.red);
                    mensaje = 1;
               }
              if(txtNRCVenta.getText().isEmpty()){
                  lblNRC.setForeground(Color.red);
                    mensaje = 1;
              }
              if(txtNITVenta.getText().isEmpty()){
                  lblNIT.setForeground(Color.red);
                    mensaje = 1;
              }
              if(txtNDocumento.getText().isEmpty()){
                  lblDOC.setForeground(Color.red);
                    mensaje = 1;
              }
                
            }
            if(mensaje ==1){
                mensajeNotificacion("Falta llenar algunos campos", "Adv");
                return false;
            }
        }
        return true;
    }
    public void colorLabels(){
        lblCliente.setForeground(Color.BLACK);
        lblDireccion.setForeground(Color.BLACK);
        lblGiro.setForeground(Color.BLACK);
        lblNRC.setForeground(Color.BLACK);
        lblNIT.setForeground(Color.BLACK);
        lblDOC.setForeground(Color.BLACK);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jpnMenu = new javax.swing.JPanel();
        lblSucursales = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        lblVentas = new javax.swing.JLabel();
        lblParametro = new javax.swing.JLabel();
        lblCompras = new javax.swing.JLabel();
        lblTipoPrecio = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblBotonCerrar = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jpnBarraSuperior1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblBotonCerrar1 = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        jpnAgregarCompra = new javax.swing.JPanel();
        lblIDVenta = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        cmbSucursalVenta = new javax.swing.JComboBox<>();
        cmbTipoVenta = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        dtcFecha = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtNombreProductoVender = new javax.swing.JTextField();
        txtCantidadVender = new javax.swing.JTextField();
        btnAgregarProductoVenta = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        lblNRC = new javax.swing.JLabel();
        txtCodigoBarraVender = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        lblGiro = new javax.swing.JLabel();
        txtNRCVenta = new javax.swing.JTextField();
        txtGiro = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txtClienteVenta = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccionVenta = new javax.swing.JTextField();
        lblDOC = new javax.swing.JLabel();
        txtNITVenta = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosVender = new javax.swing.JTable();
        btnVender = new javax.swing.JButton();
        txtTotalventa = new javax.swing.JTextField();
        lblSumas = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnCancelarVenta1 = new javax.swing.JButton();
        txtIVA = new javax.swing.JTextField();
        txtSumas = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        txtNDocumento = new javax.swing.JTextField();
        lblNIT = new javax.swing.JLabel();
        cmbTipoPrecio = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        lblMasIva = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnMenu.setPreferredSize(new java.awt.Dimension(80, 304));
        jpnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnMenuMouseExited(evt);
            }
        });
        jpnMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSucursales.setBackground(new java.awt.Color(0, 0, 0));
        lblSucursales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSucursales.setForeground(new java.awt.Color(255, 255, 255));
        lblSucursales.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Sucursales.png"))); // NOI18N
        lblSucursales.setText("Sucursales");
        lblSucursales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSucursalesMouseClicked(evt);
            }
        });
        jpnMenu.add(lblSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 140, 50));

        lblProveedores.setBackground(new java.awt.Color(0, 0, 0));
        lblProveedores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProveedores.setForeground(new java.awt.Color(255, 255, 255));
        lblProveedores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Proveedores.png"))); // NOI18N
        lblProveedores.setText("Proveedores");
        lblProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProveedoresMouseClicked(evt);
            }
        });
        jpnMenu.add(lblProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, 50));

        lblProductos.setBackground(new java.awt.Color(0, 0, 0));
        lblProductos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProductos.setForeground(new java.awt.Color(255, 255, 255));
        lblProductos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Productos.png"))); // NOI18N
        lblProductos.setText("Productos");
        lblProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProductosMouseEntered(evt);
            }
        });
        jpnMenu.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, 50));

        lblVentas.setBackground(new java.awt.Color(0, 0, 0));
        lblVentas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVentas.setForeground(new java.awt.Color(255, 255, 255));
        lblVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/EVentas.png"))); // NOI18N
        lblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentasMouseClicked(evt);
            }
        });
        jpnMenu.add(lblVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        lblParametro.setBackground(new java.awt.Color(0, 0, 0));
        lblParametro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblParametro.setForeground(new java.awt.Color(255, 255, 255));
        lblParametro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblParametro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Parametro.png"))); // NOI18N
        lblParametro.setText("Parametro");
        lblParametro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblParametro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblParametroMouseClicked(evt);
            }
        });
        jpnMenu.add(lblParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, 50));

        lblCompras.setBackground(new java.awt.Color(0, 0, 0));
        lblCompras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCompras.setForeground(new java.awt.Color(255, 255, 255));
        lblCompras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Compras.png"))); // NOI18N
        lblCompras.setText("Compras");
        lblCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComprasMouseClicked(evt);
            }
        });
        jpnMenu.add(lblCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 50));

        lblTipoPrecio.setBackground(new java.awt.Color(0, 0, 0));
        lblTipoPrecio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipoPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoPrecio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/tipoPrecio.png"))); // NOI18N
        lblTipoPrecio.setText("Tipo Precio");
        lblTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTipoPrecioMouseClicked(evt);
            }
        });
        jpnMenu.add(lblTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 140, 50));

        getContentPane().add(jpnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -360, 140, 360));

        jpnBarraSuperior.setBackground(new java.awt.Color(102, 0, 0));
        jpnBarraSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpnBarraSuperiorMouseDragged(evt);
            }
        });
        jpnBarraSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnBarraSuperiorMousePressed(evt);
            }
        });
        jpnBarraSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBotonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/exit32.png"))); // NOI18N
        lblBotonCerrar.setToolTipText("Salir");
        lblBotonCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBotonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotonCerrarMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblBotonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 30, 55));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Agregar una Venta:");
        jpnBarraSuperior.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, 30));

        jpnBarraSuperior1.setBackground(new java.awt.Color(102, 0, 0));
        jpnBarraSuperior1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpnBarraSuperior1MouseDragged(evt);
            }
        });
        jpnBarraSuperior1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnBarraSuperior1MousePressed(evt);
            }
        });
        jpnBarraSuperior1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setToolTipText("");
        jpnBarraSuperior1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 10, 60));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setToolTipText("");
        jpnBarraSuperior1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 30, 60));

        lblBotonCerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/exit32.png"))); // NOI18N
        lblBotonCerrar1.setToolTipText("Salir");
        lblBotonCerrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBotonCerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotonCerrar1MouseClicked(evt);
            }
        });
        jpnBarraSuperior1.add(lblBotonCerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 30, 55));

        menu.setBackground(new java.awt.Color(85, 0, 0));
        menu.setFont(new java.awt.Font("Trajan Pro", 1, 16)); // NOI18N
        menu.setForeground(new java.awt.Color(255, 255, 255));
        menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu.setText("Menú");
        menu.setToolTipText("Menú");
        menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu.setIconTextGap(-5);
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuMouseExited(evt);
            }
        });
        jpnBarraSuperior1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 55));

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Home.png"))); // NOI18N
        home.setToolTipText("Ir a Home");
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jpnBarraSuperior1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 70, 55));

        jpnBarraSuperior.add(jpnBarraSuperior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnAgregarCompra.setBackground(new java.awt.Color(0, 0, 0));
        jpnAgregarCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIDVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIDVenta.setForeground(new java.awt.Color(240, 240, 240));
        lblIDVenta.setText("ID Venta");
        jpnAgregarCompra.add(lblIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 40));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 20, 60));

        cmbSucursalVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSucursalVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalVentaActionPerformed(evt);
            }
        });
        jpnAgregarCompra.add(cmbSucursalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 160, 30));

        cmbTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura", "Cŕedito Fiscal", "Borrador" }));
        cmbTipoVenta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoVentaItemStateChanged(evt);
            }
        });
        jpnAgregarCompra.add(cmbTipoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 150, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(240, 240, 240));
        jLabel37.setText("Fecha:");
        jpnAgregarCompra.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, 30));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(240, 240, 240));
        jLabel39.setText("Tipo de venta:");
        jpnAgregarCompra.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, -1, 30));

        txtIdVenta.setEditable(false);
        txtIdVenta.setBackground(new java.awt.Color(254, 254, 254));
        jpnAgregarCompra.add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 17, 120, 30));

        dtcFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtcFechaFocusLost(evt);
            }
        });
        jpnAgregarCompra.add(dtcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 150, 30));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(240, 240, 240));
        jLabel40.setText("Sucursal:");
        jpnAgregarCompra.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, 30));

        getContentPane().add(jpnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 60));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 10, 210));

        txtNombreProductoVender.setEditable(false);
        txtNombreProductoVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtNombreProductoVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 350, 40));

        txtCantidadVender.setText("  1");
        txtCantidadVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCantidadVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVenderKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVenderKeyPressed(evt);
            }
        });
        getContentPane().add(txtCantidadVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 110, 40));

        btnAgregarProductoVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarProductoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/agregar2.png"))); // NOI18N
        btnAgregarProductoVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProductoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarProductoVentaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarProductoVentaMouseEntered(evt);
            }
        });
        btnAgregarProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarProductoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 110, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Producto");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        lblNRC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNRC.setText("NRC:");
        getContentPane().add(lblNRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, -1, -1));

        txtCodigoBarraVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarraVenderKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarraVenderKeyPressed(evt);
            }
        });
        getContentPane().add(txtCodigoBarraVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, 40));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1200, 10));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Cantidad");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, -1));

        lblGiro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGiro.setText("Giro");
        getContentPane().add(lblGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, -1, -1));

        txtNRCVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNRCVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtNRCVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 140, 170, 30));

        txtGiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiroKeyTyped(evt);
            }
        });
        getContentPane().add(txtGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 400, 30));

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCliente.setText("Cliente");
        getContentPane().add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, -1, -1));

        txtClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteVentaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteVentaKeyPressed(evt);
            }
        });
        getContentPane().add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 400, 30));

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireccion.setText("Dirección:");
        getContentPane().add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, -1, -1));

        txtDireccionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtDireccionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 400, 30));

        lblDOC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDOC.setText("N° Documento");
        getContentPane().add(lblDOC, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 250, -1, -1));

        txtNITVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNITVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtNITVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 180, 30));

        tblProductosVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod Barra", "Producto", "Cantidad", "Precio Unitario $", "Sub Total $"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosVender.getTableHeader().setReorderingAllowed(false);
        tblProductosVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProductosVenderKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductosVender);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 1040, 190));

        btnVender.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/vender.png"))); // NOI18N
        btnVender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVenderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVenderMouseExited(evt);
            }
        });
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        getContentPane().add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, 110, 30));

        txtTotalventa.setEditable(false);
        txtTotalventa.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalventa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtTotalventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 550, 120, 40));

        lblSumas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSumas.setText("Sumas");
        getContentPane().add(lblSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Tipo de Precio");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        btnCancelarVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/cancelar.png"))); // NOI18N
        btnCancelarVenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVenta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarVenta1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarVenta1MouseEntered(evt);
            }
        });
        btnCancelarVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVenta1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarVenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 650, 110, 30));

        txtIVA.setEditable(false);
        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 550, 120, 40));

        txtSumas.setEditable(false);
        txtSumas.setBackground(new java.awt.Color(255, 255, 255));
        txtSumas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSumas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, 120, 40));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 3, 0));
        jLabel38.setText("Total");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 560, -1, 20));

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIVA.setText("13% IVA");
        getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, -1, -1));

        txtNDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNDocumentoKeyTyped(evt);
            }
        });
        getContentPane().add(txtNDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 180, 30));

        lblNIT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNIT.setText("NIT:");
        getContentPane().add(lblNIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 190, -1, -1));

        cmbTipoPrecio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbTipoPrecio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoPrecioItemStateChanged(evt);
            }
        });
        cmbTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(cmbTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 170, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Código de Barra");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblMasIva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMasIva.setForeground(new java.awt.Color(255, 0, 0));
        lblMasIva.setText("+ IVA");
        getContentPane().add(lblMasIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 330, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVenderKeyPressed
        char c = evt.getKeyChar();
        
        if(c == (char) KeyEvent.VK_ENTER){
            try {
                cargarTabla();
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCantidadVenderKeyPressed

    private void txtCantidadVenderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVenderKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {

            if (c != (char) KeyEvent.VK_BEGIN) {
                if (c != (char) KeyEvent.VK_BACK_SPACE) {
                    if (c != (char) KeyEvent.VK_DELETE) {
                        if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                            
                            mensajeNotificacion("¡Error! Solo números.", "Error");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_txtCantidadVenderKeyTyped

    private void btnAgregarProductoVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoVentaMouseEntered
        btnAgregarProductoVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/agregar2B.png")));
    }//GEN-LAST:event_btnAgregarProductoVentaMouseEntered

    private void btnAgregarProductoVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoVentaMouseExited
        btnAgregarProductoVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/agregar2.png")));
    }//GEN-LAST:event_btnAgregarProductoVentaMouseExited

    private void btnAgregarProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoVentaActionPerformed
         if(txtNombreProductoVender.getText().isEmpty()){
            mensajeNotificacion("Seleccione un producto", "Adv");
            
        }else{
             if(txtCantidadVender.getText().isEmpty()){
            mensajeNotificacion("Ingrese una cantidad", "Adv");
        }else{
                 try {
                     cargarTabla();
                 } catch (ErrorTienda ex) {
                     Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
         }
        
    }//GEN-LAST:event_btnAgregarProductoVentaActionPerformed

    private void txtCodigoBarraVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraVenderKeyPressed
        char c=evt.getKeyChar();      
         
         
         if (c == (char) KeyEvent.VK_ENTER) {
            try {
                ObtenerProducto(txtCodigoBarraVender.getText());
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
             
           
        }
        
    }//GEN-LAST:event_txtCodigoBarraVenderKeyPressed

    private void txtCodigoBarraVenderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraVenderKeyTyped

        char c = evt.getKeyChar();
        if(txtCodigoBarraVender.getText().length()>=13){
            evt.consume();
        }else{
            if (c < '0' || c > '9') {

                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                                evt.consume();
                                mensajeNotificacion("¡Error! Solo números.", "Error");
                            }
                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_txtCodigoBarraVenderKeyTyped

    private void tblProductosVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosVenderKeyPressed
        char c = evt.getKeyChar();
        if(c == (char) KeyEvent.VK_DELETE){
            EliminarProducto();
             if(cmbTipoVenta.getSelectedIndex()==0){
                   SumarSubTotales();
                   txtTotalventa.setText("$ "+decimal.format(subTotales));
               }else{
                   SumarSubTotales();
            System.out.println("sub totales "+subTotales);
        venta.setTotalGravado(subTotales);
        venta.CalcularIVA();
        venta.CalcularTotal();
        
        txtIVA.setText("$ "+decimal.format(venta.getIVA()));
        txtSumas.setText("$"+subTotales);
        txtTotalventa.setText("$ "+decimal.format(venta.getTotal()));
               }
             txtCodigoBarraVender.requestFocus();
            
        }
    }//GEN-LAST:event_tblProductosVenderKeyPressed

    private void btnVenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVenderMouseEntered
        btnVender.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/venderB.png")));
    }//GEN-LAST:event_btnVenderMouseEntered

    private void btnVenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVenderMouseExited
        btnVender.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/vender.png")));
    }//GEN-LAST:event_btnVenderMouseExited

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        try {
            System.err.println("Tipo venta "+cmbTipoVenta.getSelectedIndex());
            if(validar(cmbTipoVenta.getSelectedIndex())){
                CalcularCostoGravado();
                guardar();
               
               frmCalcularCambio cc = new frmCalcularCambio();
               cc.txtTotalaPagar.setText(txtTotalventa.getText());
               cc.setVisible(true);
                limpiar("todo");
                ObtenerIdVenta();
                listas(true);
                cmbSucursalVenta.setSelectedIndex(0);
                cmbTipoPrecio.setSelectedIndex(1);
                cmbTipoVenta.setSelectedIndex(0);
                
            }
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void cmbTipoVentaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoVentaItemStateChanged
       if(cmbTipoVenta.getSelectedIndex() == 0)
       {
           tipoVentaSeleccion(false);
           limpiar("todo");
           lblMasIva.setVisible(true);
       }else{

           tipoVentaSeleccion(true);
           limpiar("todo");
           lblMasIva.setVisible(false);
       }
       colorLabels();
    }//GEN-LAST:event_cmbTipoVentaItemStateChanged

    private void btnCancelarVenta1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVenta1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarVenta1MouseEntered

    private void btnCancelarVenta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVenta1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarVenta1MouseExited

    private void btnCancelarVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVenta1ActionPerformed
        colorLabels();
        limpiar("todo");
        cmbSucursalVenta.setSelectedIndex(0);
        cmbTipoVenta.setSelectedIndex(0);
        cmbTipoPrecio.setSelectedIndex(1);
        listas(true);
    }//GEN-LAST:event_btnCancelarVenta1ActionPerformed

    private void lblBotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrarMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void lblSucursalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSucursalesMouseClicked
        frmSucursales sc = new frmSucursales();
        sc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblSucursalesMouseClicked

    private void lblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedoresMouseClicked
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblProveedoresMouseClicked

    private void lblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMouseClicked
        frmProductos pd = new frmProductos();
        pd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblProductosMouseClicked

    private void lblProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMouseEntered

    }//GEN-LAST:event_lblProductosMouseEntered

    private void lblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentasMouseClicked
        try {
            frmVentas vt = new frmVentas();
            vt.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVentasMouseClicked

    private void lblParametroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParametroMouseClicked
        frmParametro pt = new frmParametro();
        pt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblParametroMouseClicked

    private void lblComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComprasMouseClicked
        frmCompras cm = new frmCompras();
        cm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblComprasMouseClicked

    private void jpnMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMenuMouseExited

    }//GEN-LAST:event_jpnMenuMouseExited

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        if(estadoMenu==true){
            Animacion.Animacion.subir(55, -360, 1, 2, jpnMenu);
            estadoMenu=false;
            Border empty;
            empty = BorderFactory.createEmptyBorder();
            menu.setBorder(empty);
            menu.setText("Menu");
            txtCodigoBarraVender.requestFocus();
        }else{
            Animacion.Animacion.bajar(-360, 55, 1, 2, jpnMenu);
            estadoMenu=true;
            Border raisedbevel;
            raisedbevel = BorderFactory.createRaisedBevelBorder();
            menu.setBorder(raisedbevel);
            menu.setText("Cerrar");
            txtCantidadVender.requestFocus();
            
        }
    }//GEN-LAST:event_menuMouseClicked

    private void menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseExited

    }//GEN-LAST:event_menuMouseExited

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_homeMouseClicked

    private void lblBotonCerrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrar1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrar1MouseClicked

    private void jpnBarraSuperior1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperior1MouseDragged

    }//GEN-LAST:event_jpnBarraSuperior1MouseDragged

    private void jpnBarraSuperior1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperior1MousePressed

    }//GEN-LAST:event_jpnBarraSuperior1MousePressed

    private void cmbSucursalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalVentaActionPerformed
        limpiar("todo");
        colorLabels();
    }//GEN-LAST:event_cmbSucursalVentaActionPerformed

    private void cmbTipoPrecioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPrecioItemStateChanged
        txtCodigoBarraVender.requestFocus();
        
    }//GEN-LAST:event_cmbTipoPrecioItemStateChanged

    private void txtClienteVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteVentaKeyPressed

    private void txtClienteVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteVentaKeyTyped
        char c;
        c =  evt.getKeyChar();
        
       if( c < (char) 'a' || c > (char) 'z'){
            if( c < (char) 'A' || c > (char) 'Z'){
                if(c != (char) KeyEvent.VK_SPACE){
            if(c != (char) KeyEvent.VK_ENTER){
            evt.consume();
            }else{
                
            }
            }
        }
       }else{
            lblCliente.setForeground(Color.BLACK);
           
        }
       if (Character.isLowerCase(c)) {
                String cadena=(""+c).toUpperCase();
                c=cadena.charAt(0);
                evt.setKeyChar(c);
            }
       if(c ==  KeyEvent.VK_ENTER){
            txtDireccionVenta.requestFocus();
            }
    }//GEN-LAST:event_txtClienteVentaKeyTyped

    private void txtDireccionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionVentaKeyTyped
         char c;
        c =  evt.getKeyChar();
        
        if( c < (char) 'a' || c > (char) 'z'){
            if( c < (char) 'A' || c > (char) 'Z'){
            if(c < '0' || c > '9'){
                if(c != (char) KeyEvent.VK_SPACE){
                 if(c != (char) KeyEvent.VK_ENTER){
                    evt.consume();
                }   
                }
            }
            }
        }else{
            lblDireccion.setForeground(Color.BLACK);
            
        }
        if (Character.isLowerCase(c)) {
                String cadena=(""+c).toUpperCase();
                c=cadena.charAt(0);
                evt.setKeyChar(c);
            }
        if(c ==  KeyEvent.VK_ENTER){
            txtGiro.requestFocus();
            }
    }//GEN-LAST:event_txtDireccionVentaKeyTyped

    private void txtGiroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiroKeyTyped
        char c;
        c =  evt.getKeyChar();
        
        if( c < (char) 'a' || c > (char) 'z'){
            if( c < (char) 'A' || c > (char) 'Z'){
            if(c < '0' || c > '9'){
                if(c != (char) KeyEvent.VK_SPACE){
                 if(c != (char) KeyEvent.VK_ENTER){
                    evt.consume();
                }   
                }
            }
            }
        }else{
            lblGiro.setForeground(Color.BLACK);
            
        }
        if (Character.isLowerCase(c)) {
                String cadena=(""+c).toUpperCase();
                c=cadena.charAt(0);
                evt.setKeyChar(c);
            }
        if(c ==  KeyEvent.VK_ENTER){
            txtNRCVenta.requestFocus();
            }
    }//GEN-LAST:event_txtGiroKeyTyped

    private void txtNRCVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNRCVentaKeyTyped
        char c;
        c =  evt.getKeyChar();
        if(txtNRCVenta.getText().length()>=7){
            evt.consume();
        }
        
            if(c < '0' || c > '9'){
                
                 if(c != (char) KeyEvent.VK_ENTER){
                    evt.consume();
                }   
                
            
        }else{
            lblNRC.setForeground(Color.BLACK);
            
        }
        if(c ==  KeyEvent.VK_ENTER){
            txtNITVenta.requestFocus();
            }
    }//GEN-LAST:event_txtNRCVentaKeyTyped

    private void txtNITVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNITVentaKeyTyped
        char c;
        c =  evt.getKeyChar();
        if(txtNITVenta.getText().length()>=14){
            evt.consume();
        }
        
            if(c < '0' || c > '9'){
                
                 if(c != (char) KeyEvent.VK_ENTER){
                    evt.consume();
                }   
                
            
        }else{
            lblNIT.setForeground(Color.BLACK);
            
        }
        if(c ==  KeyEvent.VK_ENTER){
            txtNDocumento.requestFocus();
            }
    }//GEN-LAST:event_txtNITVentaKeyTyped

    private void txtNDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDocumentoKeyTyped
        char c;
        c =  evt.getKeyChar();
        
        
            if(c < '0' || c > '9'){
                
                 if(c != (char) KeyEvent.VK_ENTER){
                    evt.consume();
                }   
                
            
        }else{
            lblDOC.setForeground(Color.BLACK);
            
        }
        
    }//GEN-LAST:event_txtNDocumentoKeyTyped

    private void lblTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTipoPrecioMouseClicked
        frmTipoPrecio tp = new frmTipoPrecio();
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblTipoPrecioMouseClicked

    private void dtcFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtcFechaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dtcFechaFocusLost

    private void cmbTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoPrecioActionPerformed
        
    }//GEN-LAST:event_cmbTipoPrecioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmVentas().setVisible(true);
                } catch (ErrorTienda ex) {
                    Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProductoVenta;
    private javax.swing.JButton btnCancelarVenta1;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> cmbSucursalVenta;
    private javax.swing.JComboBox<String> cmbTipoPrecio;
    private javax.swing.JComboBox<String> cmbTipoVenta;
    private com.toedter.calendar.JDateChooser dtcFecha;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPanel jpnAgregarCompra;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnBarraSuperior1;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblBotonCerrar1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblDOC;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblGiro;
    private javax.swing.JLabel lblIDVenta;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblMasIva;
    private javax.swing.JLabel lblNIT;
    private javax.swing.JLabel lblNRC;
    private javax.swing.JLabel lblParametro;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblSucursales;
    private javax.swing.JLabel lblSumas;
    private javax.swing.JLabel lblTipoPrecio;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JLabel menu;
    private javax.swing.JTable tblProductosVender;
    private javax.swing.JTextField txtCantidadVender;
    private javax.swing.JTextField txtClienteVenta;
    public static javax.swing.JTextField txtCodigoBarraVender;
    private javax.swing.JTextField txtDireccionVenta;
    private javax.swing.JTextField txtGiro;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNDocumento;
    private javax.swing.JTextField txtNITVenta;
    private javax.swing.JTextField txtNRCVenta;
    private javax.swing.JTextField txtNombreProductoVender;
    private javax.swing.JTextField txtSumas;
    private javax.swing.JTextField txtTotalventa;
    // End of variables declaration//GEN-END:variables
}

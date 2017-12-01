/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Bitacora;
import clases.ControladorBitacora;
import clases.ControladorSucursal;
import clases.ControladorUsuario;
import clases.ControladorVenta;
import clases.ErrorTienda;
import clases.Producto;
import clases.Sucursal;
import clases.Usuario;
import clases.Venta;
import facadeshop.Diseño;
import static formularios.frmLogin.txtUser;
import static formularios.frmVentas.lblUser1;
import formulariosReportes.frmComprasReportes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import ticket.UsoTicket;

/**
 *
 * @author jose-lopez
 */
public class frmVentasBorrador extends javax.swing.JFrame {

    /**
     * Creates new form frmVentasBorrador
     */
    JTableHeader ventas, detalles;
    DefaultTableModel modeloVentas, modeloDetalles;
    ArrayList<Sucursal> sucursales = new ArrayList();
    Object miSucursal[][];
    ResultSet rs = null;
    ControladorVenta cv = new ControladorVenta();
    int contador=0,clicks=0,idVenta=0,idSucursal=0;
    String rol, password;
    double total=0,subTotal=0,iva=0,CostoGravado=0,Utilidad=0;
    DecimalFormat decimal =new DecimalFormat("#.##");
    Venta venta = new Venta();
    Producto miProducto;
    Object Utilidades[];
    ArrayList misDetalles = new ArrayList();
    Date fecha = new Date();
    
    
    public frmVentasBorrador() throws ErrorTienda, SQLException {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        modeloVentas = (DefaultTableModel) tblVentas.getModel();
        modeloDetalles = (DefaultTableModel) tblDetallesVenta.getModel();
        
        ventas = tblVentas.getTableHeader();
        detalles=tblDetallesVenta.getTableHeader();
        CargarSucursales();
        CargarVentasBorrador("TODAS",0);
        tblVentas.setSelectionBackground(Color.DARK_GRAY);
        tblVentas.setSelectionForeground(Color.WHITE);
        dtcFecha.setDateFormatString("dd-MM-yyyy");
        dtcFecha.setDate(fecha);
        obtenerUsuario();
        
        CabeceraTablas();
    }
    
        //agregar una bitacora
    public void AgregarBitacora(String Accion) throws ErrorTienda{
        Date date = new Date();
        Bitacora bitacora = new Bitacora();
        bitacora.setIdUsuario(ControladorUsuario.ObtenerIdUser(lblUser1.getText()));
        DateFormat hora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        bitacora.setFecha(hora.format(date));
        bitacora.setAccion(Accion);
        ControladorBitacora.Agregar(bitacora);
    }
    
    
    public void CabeceraTablas(){
        
        
        
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        ventas.setBackground(Color.BLACK);
        ventas.setForeground(Color.WHITE);
        ventas.setFont(fuente);
        detalles.setBackground(Color.BLACK);
        detalles.setForeground(Color.WHITE);
        detalles.setFont(fuente);
    }
    
    public void CargarVentasBorrador(String criterio, int idSucursal) throws ErrorTienda, SQLException{
        ArrayList<Object> misVentas= new ArrayList<>();
        if(criterio.equals("TODAS")){
            misVentas= cv.VentasBorrador("TODAS", idSucursal);
        }else{
            misVentas= cv.VentasBorrador("PorSucursal", idSucursal);
            Utilidades = new Object[misVentas.size()/5];
        }
        modeloVentas.setRowCount(0);
        
        Iterator<Object> iterador = misVentas.iterator();
        int fila=0;
        
        while(iterador.hasNext()){
            modeloVentas.addRow(new Object[]{"","","",""});
            modeloVentas.setValueAt(iterador.next(), fila, 1);
            modeloVentas.setValueAt(iterador.next(), fila, 0);
            modeloVentas.setValueAt(iterador.next(), fila, 2);
            modeloVentas.setValueAt(iterador.next(), fila, 3);
            if(!criterio.equals("TODAS")){
                Utilidades[fila]=iterador.next();
            }
            fila++;
        }
        
       
    }
    
    
    public void EliminarDetalle(int idVenta) throws ErrorTienda{
        try {
            Object codBarras[][];
            
            
            
            ArrayList<Venta> misventas=ControladorVenta.ObtenerVenta(idVenta);
            codBarras = new Object[misventas.size()/4][2];
            
            Iterator iterador=misventas.iterator();
            int i=0;
            
            while(iterador.hasNext()){
                
                codBarras[i][0]=iterador.next();
                iterador.next();
                codBarras[i][1]=iterador.next();
                iterador.next();
                i++;
                
            }
            
            
                
                for(int y =0;y<codBarras.length;y++){
                    for(int x=0;x<modeloDetalles.getRowCount();x++){
                    if(codBarras[y][0].equals(modeloDetalles.getValueAt(x, 0))){
                    
                        double cantidadActual = Double.parseDouble(modeloDetalles.getValueAt(x, 2).toString());
                        double resta = cantidadActual-Double.parseDouble(codBarras[y][1].toString());
                        
                        if(resta==0){
                            modeloDetalles.removeRow(x);
                        }else{
                            modeloDetalles.setValueAt(resta, x, 2);
                        }
                        
                    }
                }
            }
            
            
            
            
            
        } catch (ErrorTienda ex) {
            throw new ErrorTienda("Eliminar ", ex.getMessage());
        }
        
        if(modeloDetalles.getRowCount()==0){
            txtIdVenta.setText("");
        }
    }
    //public void CalcularCostoGravado
    public void CargarSucursales() throws ErrorTienda{
        sucursales = ControladorSucursal.obtener();
        miSucursal = new Object[sucursales.size()/4][4];
        
        int fila=0;
        Iterator<Sucursal> iterador= sucursales.iterator();
        
        cmbSucursales.addItem("TODAS");
        while (iterador.hasNext()){
            miSucursal[fila][0]=iterador.next();
            miSucursal[fila][1]=iterador.next();
            miSucursal[fila][2]=iterador.next();
            miSucursal[fila][3]=iterador.next();
            cmbSucursales.addItem(""+miSucursal[fila][1]);
            fila++;
        }
        if(cmbSucursales.getItemCount()==2){
            cmbSucursales.setSelectedIndex(1);
        }
       
    }
        //CAMBIAR CONTRASEÑA DEL USUARIO
    public void comprobarPass(){
        try {
            password = ControladorUsuario.ObtenerPass(Diseño.user);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(password.equals(pwdAntigua.getText())){
            comprobarNuevaPass();
        }else{
            pwdAntigua.requestFocus();
            mensajeNotificacion("¡Contraseña actual errónea!", "Error");
        }
    }
    
    public void comprobarNuevaPass(){
        if(pwdNueva.getText().equals(pwdNueva2.getText())){
            cambiarPassword();
        }else{
            pwdNueva.requestFocus();
            mensajeNotificacion("¡Contraseñas no coinciden!", "Error");
        }
    }
    
    public void cambiarPassword(){
        Usuario user = new Usuario();
        user.setClave(pwdNueva.getText());
        user.setUsuario(Diseño.user);
        try {

            ControladorUsuario.ModificarPass(user);
            mensajeNotificacion("¡Contraseña cambiada correctamente!", "Ok");
            pwdAntigua.setText("");
            pwdNueva.setText("");
            pwdNueva2.setText("");
            Animacion.Animacion.subir(55, -195, 1, 2, jpnPass);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void cargarDetalles(int IdVenta){
        boolean encontrado=false;
        Object[] fila=new Object[5];
        
        if(modeloDetalles.getRowCount()==0){
            try {
                ObtenerIdVenta();
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
            }
             txtIdVenta.setText(""+idVenta);
        }
        
        try {
            ArrayList<Venta> misventas=ControladorVenta.ObtenerVenta(IdVenta);
           
            Iterator iterador=misventas.iterator();
            while (iterador.hasNext()) {
                encontrado=false;
                fila[0]=iterador.next();
                fila[1]=iterador.next();
                fila[2]=iterador.next();
                fila[3]=iterador.next();
                
                if(modeloDetalles.getRowCount()==0){
                
                    modeloDetalles.addRow(fila);
                
                }else{
                    double cantidadActual,nuevaCantidad,suma;
                    for(int x =0;x<modeloDetalles.getRowCount();x++){
                        
                         if(fila[0].toString().equals(modeloDetalles.getValueAt(x, 0))){
                             cantidadActual=Double.parseDouble(modeloDetalles.getValueAt(x, 2).toString());
                             nuevaCantidad = Double.parseDouble(fila[2].toString());
                             suma = cantidadActual+nuevaCantidad;
                             modeloDetalles.setValueAt(suma, x, 2);
                             encontrado =true;
                         }
                    }
                    
                    if(encontrado==false){
                        modeloDetalles.addRow(fila);
                        
                    }
                }
                
                
                
                
            }
            ///////////////////////////////////////////////////////////////
           
            
            
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentasDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CalcularIVA_SubTotal(){
        
        
        subTotal=total/1.13;
        
        iva=total-subTotal;
        
        if(jrbCredito.isSelected()){
        txtSumas.setText("$ "+decimal.format(subTotal));
        txtIVA.setText("$ "+decimal.format(iva));
        }
        
    }
    
    public void EliminarVentasBorrador(){
        
        for(int x =0;x<modeloVentas.getRowCount();x++){
            String valorActual = (modeloVentas.getValueAt(x, 0)).toString();
                if(valorActual.substring(0, 1).equals("ↂ")){
                    
                try {
                    cv.EliminarVentasBorrador(modeloVentas.getValueAt(x,1).toString());
                } catch (ErrorTienda ex) {
                    Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        }
    }
    public void Cancelar(){
        
        
        txtTotal.setText("");
        txtIVA.setText("");
        txtSumas.setText("");
        txtClienteVenta.setText("");
        txtDireccionVenta.setText("");
        txtGiro.setText("");
        txtNDocumento.setText("");
        txtNITVenta.setText("");
        txtNRCVenta.setText("");
        jrbFactura.setSelected(true);
        cmbSucursales.setSelectedIndex(0);
        Utilidad=0;
        subTotal=0;
        total=0;
        
        misDetalles.removeAll(misDetalles);
        txtIdVenta.setText("");
    }
    public void CrearNuevaVenta() throws ErrorTienda{
        
        
        char idTipoVenta;
        if(jrbFactura.isSelected()){
            idTipoVenta='F';
        }else{
            idTipoVenta='C';
        }
        venta.setIdVenta(idVenta);
        venta.setIdPrecio(2);
        venta.setIdTipoVenta(idTipoVenta);
        venta.setIdSucursal(idSucursal);
        venta.setTotal(Double.parseDouble(decimal.format(total)));
        venta.setIVA(Double.parseDouble(decimal.format(iva)));
        venta.setTotalGravado(Double.parseDouble(decimal.format(subTotal)));
        venta.setCliente(txtClienteVenta.getText());
        venta.setDireccion(txtDireccionVenta.getText());
        if(jrbCredito.isSelected()){
            venta.setGiro(txtGiro.getText());
            if(!txtNDocumento.getText().isEmpty()){
                venta.setNomDocumento(Integer.parseInt(txtNDocumento.getText()));
            }
            if(!txtNRCVenta.getText().isEmpty()){
                venta.setNRC(Integer.parseInt(txtNRCVenta.getText()));
            }
            
            venta.setNIT(txtNITVenta.getText());
            
        }
        venta.setUtilidad(Double.parseDouble(decimal.format(Utilidad)));
        venta.setFecha(dtcFecha.getDate());
        venta.CalcularPAC();
        Object DetallesVenta[][] = new Object[modeloDetalles.getRowCount()][4];
        
        for(int y=0; y<modeloDetalles.getRowCount();y++){
            DetallesVenta[y][0]=txtIdVenta.getText();
            DetallesVenta[y][1]=modeloDetalles.getValueAt(y, 0);
            DetallesVenta[y][2]=modeloDetalles.getValueAt(y, 2);
            DetallesVenta[y][3]=modeloDetalles.getValueAt(y, 3);
        }
        
        //mi parte
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yy HH:mm:ss",Locale.getDefault());
        Object sucursal=cmbSucursales.getSelectedItem();
        String fecha=formato.format(dtcFecha.getDate());
        
        String totalT=decimal.format(total);
        String subtotalT=decimal.format(subTotal);
        String ivaT=decimal.format(iva);
        int filas=modeloDetalles.getRowCount();
        
        String detalles[][] = new String[modeloDetalles.getRowCount()][4];
        
            for(int y=0; y<modeloDetalles.getRowCount();y++){
                detalles[y][0]=modeloDetalles.getValueAt(y, 1).toString();
                detalles[y][1]=modeloDetalles.getValueAt(y, 2).toString();
                detalles[y][2]=modeloDetalles.getValueAt(y, 3).toString();
                double cantidad=Double.parseDouble(modeloDetalles.getValueAt(y, 2).toString());
                double precio=Double.parseDouble(modeloDetalles.getValueAt(y, 2).toString());
                detalles[y][3]=decimal.format(cantidad*precio);
            
            }
            
            String login=lblUser1.getText();
            String rol=lblRolUsuario.getText();
            int id=ControladorUsuario.ObtenerIdUsuario();
        
        if (idTipoVenta=='F') { 
            //Imprimir
            UsoTicket.borradoInicializacion();
            UsoTicket.cabecera();
            UsoTicket.datosTicket(sucursal,fecha,"Factura");
            
            
            UsoTicket.datosVentaFactura(detalles,totalT,filas);
            
            UsoTicket.datosVendedor(id,login,rol);
            UsoTicket.imprimir();
            
        }else{
            //Imprimir
            UsoTicket.borradoInicializacion();
            UsoTicket.cabecera();
            UsoTicket.datosTicket(sucursal,fecha,"Credito Fiscal");
            UsoTicket.datosVentaCreditoFiscal(detalles,subtotalT,ivaT,totalT,filas);
            UsoTicket.datosVendedor(id,login,rol);
            UsoTicket.imprimir();
        }
        
        if(cv.Agregar(venta,DetallesVenta,"BORRADOR")){
            mensajeNotificacion("¡Venta realizada!", "Ok");
            AgregarBitacora("Realizó la venta que tiene como ID: "+txtIdVenta.getText()+" por "+txtTotal.getText());
            
            
            
        }else{
            mensajeNotificacion("¡Venta NO realizada!", "Error");
        }
        
        
    }
    public void CalcularUtilidad(String operacion,int fila){
        if(operacion.equals("SUMA")){
            Utilidad+=Double.parseDouble((Utilidades[fila]).toString());
        }else{
            Utilidad-=Double.parseDouble((Utilidades[fila]).toString());
        }
    }
    public void ObtenerIdVenta() throws ErrorTienda {
        
        
        try {
            cv = new ControladorVenta();
            idVenta = cv.ObtenerIdVenta();
        } catch (ErrorTienda ex) {
            throw new ErrorTienda("Formulario ventas <--> Falló al obtener el producto",ex.getMessage());
        }
       
    }
    public void tipoVentaSeleccion(boolean estado){
        
          
          txtGiro.setVisible(estado);
          txtNRCVenta.setVisible(estado);
          txtNITVenta.setVisible(estado);
          txtNDocumento.setVisible(estado);
          lblGiro.setVisible(estado);
          lblNRC.setVisible(estado);
          lblNIT.setVisible(estado);
          lblDOC.setVisible(estado);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        frmDatosFinales = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblNRC = new javax.swing.JLabel();
        lblGiro = new javax.swing.JLabel();
        txtNRCVenta = new javax.swing.JTextField();
        txtGiro = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txtClienteVenta = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccionVenta = new javax.swing.JTextField();
        lblDOC = new javax.swing.JLabel();
        txtNITVenta = new javax.swing.JTextField();
        txtNDocumento = new javax.swing.JTextField();
        lblNIT = new javax.swing.JLabel();
        btnTerminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jpnPass = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        pwdAntigua = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        pwdNueva = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        pwdNueva2 = new javax.swing.JPasswordField();
        btnAtrasPwd = new javax.swing.JLabel();
        btnCambiarPwd = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jpnUser = new javax.swing.JPanel();
        lblRolUsuario = new javax.swing.JLabel();
        lblCambiarPwd = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetallesVenta = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cmbSucursales = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        dtcFecha = new com.toedter.calendar.JDateChooser();
        jpnlConsolidarComo = new javax.swing.JPanel();
        jrbCredito = new javax.swing.JRadioButton();
        jrbFactura = new javax.swing.JRadioButton();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        lblVender = new javax.swing.JLabel();
        lblVentasBorrador = new javax.swing.JLabel();
        lblDetallesVentas = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        jpnWhite = new javax.swing.JPanel();
        lblUser1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblAgregarUsuario = new javax.swing.JLabel();
        jpnBarraMenu = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        jpnSubMenu = new javax.swing.JPanel();
        btnCompras = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnTipoPrecio = new javax.swing.JButton();
        btnParametro = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnSucursales = new javax.swing.JButton();
        btnDetalleCompras = new javax.swing.JButton();
        btnDetalleVentas = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnBitacoras = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSumas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        chbMarcarTodas = new javax.swing.JCheckBox();

        frmDatosFinales.setTitle("Terminando");
        frmDatosFinales.setAlwaysOnTop(true);
        frmDatosFinales.setUndecorated(true);
        frmDatosFinales.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ultimos datos requeridos");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        frmDatosFinales.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 40));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNRC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNRC.setText("NRC:");
        jPanel5.add(lblNRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        lblGiro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGiro.setText("Giro:");
        jPanel5.add(lblGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 100, -1));

        txtNRCVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNRCVentaKeyTyped(evt);
            }
        });
        jPanel5.add(txtNRCVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 150, 30));

        txtGiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiroKeyTyped(evt);
            }
        });
        jPanel5.add(txtGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 330, 30));

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCliente.setText("Cliente:");
        jPanel5.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        txtClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteVentaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteVentaKeyPressed(evt);
            }
        });
        jPanel5.add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 30));

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireccion.setText("Dirección:");
        jPanel5.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, -1));

        txtDireccionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionVentaKeyTyped(evt);
            }
        });
        jPanel5.add(txtDireccionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 330, 30));

        lblDOC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDOC.setText("N° Documento");
        jPanel5.add(lblDOC, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        txtNITVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNITVentaKeyTyped(evt);
            }
        });
        jPanel5.add(txtNITVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 150, 30));

        txtNDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNDocumentoKeyTyped(evt);
            }
        });
        jPanel5.add(txtNDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 150, 30));

        lblNIT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNIT.setText("NIT:");
        jPanel5.add(lblNIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        btnTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/terminar.png"))); // NOI18N
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnTerminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 100, 30));

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/atras.png"))); // NOI18N
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel5.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 100, 30));

        frmDatosFinales.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 540, 240));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas Borrador");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        jpnPass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 0));
        jLabel14.setText("Repita nueva contraseña:");
        jpnPass.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, -1));

        pwdAntigua.setBackground(new java.awt.Color(240, 240, 240));
        pwdAntigua.setBorder(null);
        pwdAntigua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwdAntiguaFocusGained(evt);
            }
        });
        pwdAntigua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdAntiguaActionPerformed(evt);
            }
        });
        jpnPass.add(pwdAntigua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 35, 190, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel15.setText("Contraseña antigua:");
        jpnPass.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        pwdNueva.setBackground(new java.awt.Color(240, 240, 240));
        pwdNueva.setBorder(null);
        pwdNueva.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwdNuevaFocusGained(evt);
            }
        });
        pwdNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdNuevaActionPerformed(evt);
            }
        });
        jpnPass.add(pwdNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, 190, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 0));
        jLabel7.setText("Nueva contraseña:");
        jpnPass.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

        pwdNueva2.setBackground(new java.awt.Color(240, 240, 240));
        pwdNueva2.setBorder(null);
        pwdNueva2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwdNueva2FocusGained(evt);
            }
        });
        jpnPass.add(pwdNueva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 190, -1));

        btnAtrasPwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Atras2.png"))); // NOI18N
        btnAtrasPwd.setToolTipText("Volver atrás.");
        btnAtrasPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtrasPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasPwdMouseClicked(evt);
            }
        });
        jpnPass.add(btnAtrasPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 30, 30));

        btnCambiarPwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Aceptar.png"))); // NOI18N
        btnCambiarPwd.setToolTipText("Cambiar Contraseña.");
        btnCambiarPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambiarPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCambiarPwdMouseClicked(evt);
            }
        });
        jpnPass.add(btnCambiarPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 155, 30, 30));

        jSeparator6.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, 10));

        jSeparator7.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 190, 10));

        jSeparator4.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 190, 10));

        getContentPane().add(jpnPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, -195, 230, 190));

        jpnUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        jpnUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnUserMouseExited(evt);
            }
        });
        jpnUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRolUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        lblRolUsuario.setForeground(new java.awt.Color(102, 0, 0));
        lblRolUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRolUsuario.setText("Nombres + Apellidos");
        jpnUser.add(lblRolUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 230, 30));

        lblCambiarPwd.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblCambiarPwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/login/pin.png"))); // NOI18N
        lblCambiarPwd.setText("Cambiar contraseña");
        lblCambiarPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCambiarPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCambiarPwdMouseClicked(evt);
            }
        });
        jpnUser.add(lblCambiarPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 180, 20));

        lblCerrarSesion.setBackground(new java.awt.Color(0, 0, 0));
        lblCerrarSesion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/login/usuario.png"))); // NOI18N
        lblCerrarSesion.setText("Cerrar sesión");
        lblCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarSesionMouseClicked(evt);
            }
        });
        jpnUser.add(lblCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 110, 20));

        getContentPane().add(jpnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 55, 230, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ventas:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, 40));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 1010, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Detalle de la nueva venta:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 200, 40));

        tblDetallesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodBarra", "Producto", "Cantidad", "Precio Unitario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetallesVenta.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblDetallesVenta);
        if (tblDetallesVenta.getColumnModel().getColumnCount() > 0) {
            tblDetallesVenta.getColumnModel().getColumn(0).setResizable(false);
            tblDetallesVenta.getColumnModel().getColumn(0).setPreferredWidth(85);
            tblDetallesVenta.getColumnModel().getColumn(1).setResizable(false);
            tblDetallesVenta.getColumnModel().getColumn(1).setPreferredWidth(270);
            tblDetallesVenta.getColumnModel().getColumn(2).setResizable(false);
            tblDetallesVenta.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblDetallesVenta.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 560, 310));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbSucursales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSucursalesItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 5, 320, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sucursal:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(240, 240, 240));
        jLabel37.setText("Fecha:");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, -1, 40));

        dtcFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtcFechaFocusLost(evt);
            }
        });
        jPanel1.add(dtcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 150, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 1010, 40));

        jpnlConsolidarComo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consolidar como:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpnlConsolidarComo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(jrbCredito);
        jrbCredito.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jrbCredito.setText("Credito Fiscal");
        jrbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCreditoActionPerformed(evt);
            }
        });
        jpnlConsolidarComo.add(jrbCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        buttonGroup1.add(jrbFactura);
        jrbFactura.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jrbFactura.setSelected(true);
        jrbFactura.setText("Factura");
        jrbFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbFacturaActionPerformed(evt);
            }
        });
        jpnlConsolidarComo.add(jrbFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/continuar.png"))); // NOI18N
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        jpnlConsolidarComo.add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 110, 30));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/cancelar.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jpnlConsolidarComo.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 110, 30));

        getContentPane().add(jpnlConsolidarComo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, 460, 130));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Sucursal", "ID Venta", "Fecha", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblVentas.getTableHeader().setReorderingAllowed(false);
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        tblVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVentasKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblVentas);
        if (tblVentas.getColumnModel().getColumnCount() > 0) {
            tblVentas.getColumnModel().getColumn(0).setResizable(false);
            tblVentas.getColumnModel().getColumn(1).setResizable(false);
            tblVentas.getColumnModel().getColumn(2).setResizable(false);
            tblVentas.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 410, 310));

        lblVender.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblVender.setForeground(new java.awt.Color(153, 153, 153));
        lblVender.setText("Vender");
        lblVender.setToolTipText("Realizar una venta.");
        lblVender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVenderMouseClicked(evt);
            }
        });
        getContentPane().add(lblVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, 50));

        lblVentasBorrador.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblVentasBorrador.setText("Ventas Borrador");
        lblVentasBorrador.setToolTipText("");
        lblVentasBorrador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblVentasBorrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentasBorradorMouseClicked(evt);
            }
        });
        getContentPane().add(lblVentasBorrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, 50));

        lblDetallesVentas.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblDetallesVentas.setForeground(new java.awt.Color(153, 153, 153));
        lblDetallesVentas.setText("Detalles de Ventas");
        lblDetallesVentas.setToolTipText("Ver los detalles de ventas realizadas.");
        lblDetallesVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDetallesVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDetallesVentasMouseClicked(evt);
            }
        });
        getContentPane().add(lblDetallesVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, -1, 50));

        jpnBarraSuperior.setBackground(new java.awt.Color(0, 0, 0));
        jpnBarraSuperior.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        jpnWhite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnWhiteMouseClicked(evt);
            }
        });
        jpnWhite.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUser1.setBackground(new java.awt.Color(0, 0, 0));
        lblUser1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblUser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/configb.png"))); // NOI18N
        lblUser1.setText("USER");
        lblUser1.setToolTipText("Configuración");
        lblUser1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUser1MouseClicked(evt);
            }
        });
        jpnWhite.add(lblUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jpnBarraSuperior.add(jpnWhite, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 130, 60));

        lblUser.setBackground(new java.awt.Color(222, 222, 222));
        lblUser.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(204, 204, 204));
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/config.png"))); // NOI18N
        lblUser.setText("USER");
        lblUser.setToolTipText("Configuración");
        lblUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 130, 50));

        lblLogo.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        lblLogo.setText("iShop 3.0");
        lblLogo.setToolTipText("");
        jpnBarraSuperior.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 50));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Consolidar Ventas Borrador");
        jpnBarraSuperior.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, 40));

        lblAgregarUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/agus.png"))); // NOI18N
        lblAgregarUsuario.setToolTipText("Agregar Usuario");
        lblAgregarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAgregarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarUsuarioMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblAgregarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 60, 50));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnBarraMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnBarraMenu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jpnBarraMenuPropertyChange(evt);
            }
        });
        jpnBarraMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setToolTipText("Inicio");
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnBarraMenu.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        lblMenu.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Atras.png"))); // NOI18N
        lblMenu.setText("Volver a Home");
        lblMenu.setToolTipText("");
        lblMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuMouseClicked(evt);
            }
        });
        jpnBarraMenu.add(lblMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 15, 180, 50));

        jpnSubMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnSubMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnSubMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/compras.png"))); // NOI18N
        btnCompras.setBorderPainted(false);
        btnCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComprasMouseExited(evt);
            }
        });
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 10, 180, 40));

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/ventas.png"))); // NOI18N
        btnVentas.setBorderPainted(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnSubMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 110, 180, 40));

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/productos.png"))); // NOI18N
        btnProductos.setBorderPainted(false);
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProductosMouseExited(evt);
            }
        });
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 210, 180, 40));

        btnTipoPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/tipoprecio.png"))); // NOI18N
        btnTipoPrecio.setBorderPainted(false);
        btnTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTipoPrecioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTipoPrecioMouseExited(evt);
            }
        });
        btnTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoPrecioActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 410, 180, 40));

        btnParametro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/parametro.png"))); // NOI18N
        btnParametro.setBorderPainted(false);
        btnParametro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParametro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnParametroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnParametroMouseExited(evt);
            }
        });
        btnParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametroActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 360, 180, 40));

        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/proveedores.png"))); // NOI18N
        btnProveedores.setBorderPainted(false);
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseExited(evt);
            }
        });
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 260, 180, 40));

        btnSucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/sucursales.png"))); // NOI18N
        btnSucursales.setBorderPainted(false);
        btnSucursales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSucursalesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSucursalesMouseExited(evt);
            }
        });
        btnSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSucursalesActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 310, 180, 40));

        btnDetalleCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/det.compras.png"))); // NOI18N
        btnDetalleCompras.setBorderPainted(false);
        btnDetalleCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalleCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetalleComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetalleComprasMouseExited(evt);
            }
        });
        btnDetalleCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleComprasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnDetalleCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 60, 180, 40));

        btnDetalleVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/det.ventas.png"))); // NOI18N
        btnDetalleVentas.setBorderPainted(false);
        btnDetalleVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalleVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetalleVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetalleVentasMouseExited(evt);
            }
        });
        btnDetalleVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleVentasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnDetalleVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 160, 180, 40));

        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/reportes.png"))); // NOI18N
        btnReportes.setBorderPainted(false);
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportesMouseExited(evt);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 460, 180, 40));

        btnBitacoras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/bitacoras.png"))); // NOI18N
        btnBitacoras.setBorderPainted(false);
        btnBitacoras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBitacoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBitacorasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBitacorasMouseExited(evt);
            }
        });
        btnBitacoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitacorasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnBitacoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 510, 180, 40));

        jpnBarraMenu.add(jpnSubMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 77, 190, 560));

        getContentPane().add(jpnBarraMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 650));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 10, 380));

        jLabel1.setText("ID Venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, -1, -1));

        txtIdVenta.setEditable(false);
        getContentPane().add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 170, 160, 20));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 100, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Total");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        txtIVA.setEditable(false);
        txtIVA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPanel2.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 100, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("IVA");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        txtSumas.setEditable(false);
        txtSumas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPanel2.add(txtSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Sub Total");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, 430, 120));

        chbMarcarTodas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chbMarcarTodas.setText("Marcar todas");
        chbMarcarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMarcarTodasActionPerformed(evt);
            }
        });
        getContentPane().add(chbMarcarTodas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    public void obtenerUsuario(){
        lblUser.setText(Diseño.user);
        lblUser1.setText(Diseño.user);
        jpnUser.setVisible(false);
        jpnWhite.setVisible(false);
        obtenerRol();
    }
    public void obtenerRol(){
        try {
            rol = ControladorUsuario.obtenerRol(txtUser.getText());
            if(rol.equals("A")){
                lblRolUsuario.setText("ADMINISTRADOR");
                jpnSubMenu.setVisible(true);
                lblDetallesVentas.setVisible(true);
                lblMenu.setVisible(true);
            }
            else if(rol.equals("V")){
                lblRolUsuario.setText("VENDEDOR");
                jpnSubMenu.setVisible(false);
                lblDetallesVentas.setVisible(false);
                lblMenu.setVisible(false);
            }
            else if(rol.equals("C")){
                lblRolUsuario.setText("COMPRADOR");
            }
            
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    private void cmbSucursalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSucursalesItemStateChanged
        txtTotal.setText("$");
        txtIVA.setText("$");
        txtSumas.setText("$");
        chbMarcarTodas.setSelected(false);
        if(cmbSucursales.getSelectedIndex()==0){
            
            modeloDetalles.setRowCount(0);
            try {
                CargarVentasBorrador("TODAS",0);
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            tblDetallesVenta.setFocusable(false);
            
            modeloDetalles.setRowCount(0);
            try {
            if(contador==0){
                
                CargarVentasBorrador("sucursal", Integer.parseInt(String.valueOf(miSucursal[cmbSucursales.getSelectedIndex()-1][0])));
            }else{
                contador=-1;
            }
            contador++;
                
            
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_cmbSucursalesItemStateChanged

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        
        
        
        if(cmbSucursales.getSelectedIndex()==0){//CONDICIONAL PARA SABER SI SE PUEDE MARCAR O NO DEPENDIENDO SI SE HA SELECCIONADO UNA SUCURSAL
            
            //cargarDetalles(Integer.parseInt((tblVentas.getValueAt(tblVentas.getSelectedRow(), 1)).toString()));
        }else{
            
            if(evt.getClickCount()==2){
                
//             if(!frmConsolidar.isVisible()){
//                //frmConsolidar.setSize(300, 250);
//                //frmConsolidar.setVisible(true);
//                 Estado(false);//Habilita los demas componentes de la interfas, en este caso los bloquea con false
                 
//             }
                
            String valorActual = (modeloVentas.getValueAt(tblVentas.getSelectedRow(), 0)).toString();
                if(!valorActual.substring(0, 1).equals("ↂ")){
                    idSucursal= Integer.parseInt(valorActual);
                    modeloVentas.setValueAt("ↂ "+valorActual,tblVentas.getSelectedRow(), 0);
                    //Agregar(modeloVentas.getValueAt(tblVentas.getSelectedRow(), 1), modeloVentas.getValueAt(tblVentas.getSelectedRow(), 2), modeloVentas.getValueAt(tblVentas.getSelectedRow(), 3));
                    total+=Double.parseDouble(String.valueOf(modeloVentas.getValueAt(tblVentas.getSelectedRow(), 3)));
                    txtTotal.setText("$ "+decimal.format(total));
                    CalcularUtilidad("SUMA",tblVentas.getSelectedRow());
                    CalcularIVA_SubTotal();
                    //AgregarDetalles();
                    cargarDetalles(Integer.parseInt((tblVentas.getValueAt(tblVentas.getSelectedRow(), 1)).toString()));
                }else{
                    idSucursal= Integer.parseInt(valorActual.substring(2));
                }
                    
            
        }
            
                
             
            
            
            
        }
            
        
        
    }//GEN-LAST:event_tblVentasMouseClicked

    private void lblVenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVenderMouseClicked
        try {
            frmVentas vn = new frmVentas();
            vn.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVenderMouseClicked

    private void lblVentasBorradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentasBorradorMouseClicked

    }//GEN-LAST:event_lblVentasBorradorMouseClicked

    private void lblDetallesVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDetallesVentasMouseClicked
        frmVentasDetalle vd = new frmVentasDetalle();
        vd.setVisible(true);
        this.setVisible(false);
        
        
    }//GEN-LAST:event_lblDetallesVentasMouseClicked

    private void lblUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser1MouseClicked
        
        
    }//GEN-LAST:event_lblUser1MouseClicked

    private void jpnWhiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnWhiteMouseClicked
        jpnWhite.setVisible(false);
        jpnUser.setVisible(false);
    }//GEN-LAST:event_jpnWhiteMouseClicked

    private void lblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMouseClicked
        obtenerRol();
        if(jpnUser.isVisible()){
            jpnUser.setVisible(false);
            jpnWhite.setVisible(false);
        }
        else if(!jpnUser.isVisible()){
            jpnUser.setVisible(true);
            jpnWhite.setVisible(true);
        }
    }//GEN-LAST:event_lblUserMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void jpnBarraMenuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpnBarraMenuPropertyChange

    }//GEN-LAST:event_jpnBarraMenuPropertyChange

    private void lblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblMenuMouseClicked

    private void jrbCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCreditoActionPerformed
        if(total!=0){
            CalcularIVA_SubTotal();
        }
        
    }//GEN-LAST:event_jrbCreditoActionPerformed

    private void tblVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentasKeyPressed
       if(cmbSucursales.getSelectedIndex()!=0){
            char c = evt.getKeyChar();
        if(c == (char) KeyEvent.VK_DELETE){
            
            String valorActual = (modeloVentas.getValueAt(tblVentas.getSelectedRow(), 0)).toString();
            
                if(valorActual.substring(0, 1).equals("ↂ")){
                    modeloVentas.setValueAt(valorActual.substring(2), tblVentas.getSelectedRow(), 0);
                    total-=Double.parseDouble(String.valueOf(modeloVentas.getValueAt(tblVentas.getSelectedRow(), 3)));
                    txtTotal.setText("$ "+decimal.format(total));
                    
                    CalcularUtilidad("RESTA",tblVentas.getSelectedRow());
                try {
                    EliminarDetalle(Integer.parseInt((tblVentas.getValueAt(tblVentas.getSelectedRow(), 1)).toString()));
                    if(chbMarcarTodas.isSelected()){
                        chbMarcarTodas.setSelected(false);
                    }
                } catch (ErrorTienda ex) {
                    Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                    CalcularIVA_SubTotal();
                }
        }
       }
        
    }//GEN-LAST:event_tblVentasKeyPressed

    private void jrbFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbFacturaActionPerformed
        txtIVA.setText("$");
        txtSumas.setText("$");
    }//GEN-LAST:event_jrbFacturaActionPerformed

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

    private void txtClienteVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteVentaKeyPressed

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

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        
        
        if(modeloDetalles.getRowCount()>0){
            if(jrbCredito.isSelected()){
            tipoVentaSeleccion(true);
        }else{
               
            tipoVentaSeleccion(false);
                
        }
            if(!frmDatosFinales.isVisible()){
                frmDatosFinales.setSize(540, 285);
                frmDatosFinales.setVisible(true);
                frmDatosFinales.setLocationRelativeTo(null);
                Bloqueo(false);
            }
        
        
        }else{
             mensajeNotificacion("No hay ventas seleccionadas", "Adv");
        }
    }//GEN-LAST:event_btnContinuarActionPerformed
    public void Bloqueo(boolean estado){
        jrbFactura.setEnabled(estado);
        jrbCredito.setEnabled(estado);
        btnCancelar.setEnabled(estado);
    }
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        frmDatosFinales.setVisible(false);
        Bloqueo(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        try {
            CrearNuevaVenta();
            EliminarVentasBorrador();
            frmDatosFinales.setVisible(false);
            Cancelar();
            Bloqueo(true);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void dtcFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtcFechaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dtcFechaFocusLost

    private void btnComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);
    }//GEN-LAST:event_btnComprasMouseEntered

    private void btnComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseExited
        /*  ---- Animación compras, volver posición anterior ----  */
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnCompras);
    }//GEN-LAST:event_btnComprasMouseExited

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        frmCompras co = new frmCompras();
        co.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);
    }//GEN-LAST:event_btnProductosMouseEntered

    private void btnProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProductos);
    }//GEN-LAST:event_btnProductosMouseExited

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        frmProductos pro = new frmProductos();
        pro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnTipoPrecioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnTipoPrecio);
    }//GEN-LAST:event_btnTipoPrecioMouseEntered

    private void btnTipoPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnTipoPrecio);
    }//GEN-LAST:event_btnTipoPrecioMouseExited

    private void btnTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoPrecioActionPerformed
        frmTipoPrecio tp = new frmTipoPrecio();
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTipoPrecioActionPerformed

    private void btnParametroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnParametro);
    }//GEN-LAST:event_btnParametroMouseEntered

    private void btnParametroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnParametro);
    }//GEN-LAST:event_btnParametroMouseExited

    private void btnParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametroActionPerformed
        frmParametro pa = new frmParametro();
        pa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnParametroActionPerformed

    private void btnProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);
    }//GEN-LAST:event_btnProveedoresMouseEntered

    private void btnProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProveedores);
    }//GEN-LAST:event_btnProveedoresMouseExited

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        frmProveedores pr = new frmProveedores();
        pr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnSucursalesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnSucursales);
    }//GEN-LAST:event_btnSucursalesMouseEntered

    private void btnSucursalesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnSucursales);
    }//GEN-LAST:event_btnSucursalesMouseExited

    private void btnSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalesActionPerformed
        frmSucursales su = new frmSucursales();
        su.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSucursalesActionPerformed

    private void btnDetalleComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleComprasMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnDetalleCompras);
    }//GEN-LAST:event_btnDetalleComprasMouseEntered

    private void btnDetalleComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleComprasMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnDetalleCompras);
    }//GEN-LAST:event_btnDetalleComprasMouseExited

    private void btnDetalleComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleComprasActionPerformed
        frmComprasDetalle cd = new frmComprasDetalle();
        cd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDetalleComprasActionPerformed

    private void btnDetalleVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentasMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnDetalleVentas);
    }//GEN-LAST:event_btnDetalleVentasMouseEntered

    private void btnDetalleVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentasMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnDetalleVentas);
    }//GEN-LAST:event_btnDetalleVentasMouseExited

    private void btnDetalleVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleVentasActionPerformed
        frmVentasDetalle vd = new frmVentasDetalle();
        vd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDetalleVentasActionPerformed

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnReportes);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnReportes);
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        frmComprasReportes cr = new frmComprasReportes();
        cr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnBitacorasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBitacorasMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnBitacoras);
    }//GEN-LAST:event_btnBitacorasMouseEntered

    private void btnBitacorasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBitacorasMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnBitacoras);
    }//GEN-LAST:event_btnBitacorasMouseExited

    private void btnBitacorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBitacorasActionPerformed
        frmBitacoras bi = new frmBitacoras();
        bi.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBitacorasActionPerformed

    private void pwdAntiguaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdAntiguaFocusGained
        pwdAntigua.selectAll();
    }//GEN-LAST:event_pwdAntiguaFocusGained

    private void pwdAntiguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdAntiguaActionPerformed
        pwdNueva.requestFocus();
    }//GEN-LAST:event_pwdAntiguaActionPerformed

    private void pwdNuevaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdNuevaFocusGained
        pwdNueva.selectAll();
    }//GEN-LAST:event_pwdNuevaFocusGained

    private void pwdNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdNuevaActionPerformed
        pwdNueva2.requestFocus();
    }//GEN-LAST:event_pwdNuevaActionPerformed

    private void pwdNueva2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdNueva2FocusGained
        pwdNueva2.selectAll();
    }//GEN-LAST:event_pwdNueva2FocusGained

    private void btnAtrasPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasPwdMouseClicked
        Animacion.Animacion.subir(55, -195, 1, 2, jpnPass);
    }//GEN-LAST:event_btnAtrasPwdMouseClicked

    private void btnCambiarPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiarPwdMouseClicked
        comprobarPass();
    }//GEN-LAST:event_btnCambiarPwdMouseClicked

    private void lblCambiarPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarPwdMouseClicked
        jpnPass.setVisible(true);
        Animacion.Animacion.bajar(-195, 55, 1, 2, jpnPass);
        pwdAntigua.requestFocus();
    }//GEN-LAST:event_lblCambiarPwdMouseClicked

    private void lblCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarSesionMouseClicked
        frmLogin lg = new frmLogin();
        lg.setVisible(true);
        this.setVisible(false);
        Bitacora bitacora = new Bitacora();
        Date date = new Date();
        SimpleDateFormat hora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        bitacora.setAccion("Cerró sesión.");
        bitacora.setFecha(hora.format(date));
        try {
            bitacora.setIdUsuario(ControladorUsuario.ObtenerIdUser(lblUser1.getText()));
            ControladorBitacora.Agregar(bitacora);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensajeNotificacion("¡Has cerrado sesión!", "Error");
    }//GEN-LAST:event_lblCerrarSesionMouseClicked

    private void jpnUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnUserMouseExited
        //jpnUser.setVisible(false);
        //jpnWhite.setVisible(false);
    }//GEN-LAST:event_jpnUserMouseExited

    private void lblAgregarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarUsuarioMouseClicked
        frmRegistrarUsuario ru = new frmRegistrarUsuario();
        ru.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblAgregarUsuarioMouseClicked

    private void chbMarcarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMarcarTodasActionPerformed
        if(modeloVentas.getRowCount()==0||cmbSucursales.getSelectedIndex()==0){
            chbMarcarTodas.setSelected(false);
        }else{
            if(chbMarcarTodas.isSelected()){
        for(int x =0; x<modeloVentas.getRowCount();x++){
            String valorActual = (modeloVentas.getValueAt(x, 0)).toString();
                if(!valorActual.substring(0, 1).equals("ↂ")){
                    idSucursal= Integer.parseInt(valorActual);
                    modeloVentas.setValueAt("ↂ "+valorActual,x, 0);
                    //Agregar(modeloVentas.getValueAt(tblVentas.getSelectedRow(), 1), modeloVentas.getValueAt(tblVentas.getSelectedRow(), 2), modeloVentas.getValueAt(tblVentas.getSelectedRow(), 3));
                    total+=Double.parseDouble(String.valueOf(modeloVentas.getValueAt(x, 3)));
                    txtTotal.setText("$ "+decimal.format(total));
                    CalcularUtilidad("SUMA",x);
                    CalcularIVA_SubTotal();
                    //AgregarDetalles();
                    cargarDetalles(Integer.parseInt((tblVentas.getValueAt(x, 1)).toString()));
                }else{
                    idSucursal= Integer.parseInt(valorActual.substring(2));
                }
                    
        }    
        }
        else{
            for(int x=0;x<modeloVentas.getRowCount();x++){
                String valorActual = (modeloVentas.getValueAt(x, 0)).toString();
            
                if(valorActual.substring(0, 1).equals("ↂ")){
                    modeloVentas.setValueAt(valorActual.substring(2), x, 0);
                    total-=Double.parseDouble(String.valueOf(modeloVentas.getValueAt(x, 3)));
                    txtTotal.setText("$ "+decimal.format(total));
                    
                    CalcularUtilidad("RESTA",x);
                try {
                    EliminarDetalle(Integer.parseInt((tblVentas.getValueAt(x, 1)).toString()));
                } catch (ErrorTienda ex) {
                    Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                    CalcularIVA_SubTotal();
                }
            }
            
            
        }
        }
                
        
        
    }//GEN-LAST:event_chbMarcarTodasActionPerformed

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
            java.util.logging.Logger.getLogger(frmVentasBorrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVentasBorrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVentasBorrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVentasBorrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmVentasBorrador().setVisible(true);
                } catch (ErrorTienda ex) {
                    Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(frmVentasBorrador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAtrasPwd;
    private javax.swing.JButton btnBitacoras;
    private javax.swing.JLabel btnCambiarPwd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnDetalleCompras;
    private javax.swing.JButton btnDetalleVentas;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JButton btnTipoPrecio;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVolver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbMarcarTodas;
    private javax.swing.JComboBox<String> cmbSucursales;
    private com.toedter.calendar.JDateChooser dtcFecha;
    private javax.swing.JFrame frmDatosFinales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnPass;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
    private javax.swing.JPanel jpnlConsolidarComo;
    private javax.swing.JRadioButton jrbCredito;
    private javax.swing.JRadioButton jrbFactura;
    private javax.swing.JLabel lblAgregarUsuario;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDOC;
    private javax.swing.JLabel lblDetallesVentas;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblGiro;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblNIT;
    private javax.swing.JLabel lblNRC;
    private javax.swing.JLabel lblRolUsuario;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JLabel lblVender;
    private javax.swing.JLabel lblVentasBorrador;
    private javax.swing.JPasswordField pwdAntigua;
    private javax.swing.JPasswordField pwdNueva;
    private javax.swing.JPasswordField pwdNueva2;
    private javax.swing.JTable tblDetallesVenta;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtClienteVenta;
    private javax.swing.JTextField txtDireccionVenta;
    private javax.swing.JTextField txtGiro;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNDocumento;
    private javax.swing.JTextField txtNITVenta;
    private javax.swing.JTextField txtNRCVenta;
    private javax.swing.JTextField txtSumas;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

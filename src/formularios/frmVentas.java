/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorProducto;
import clases.ControladorSucursal;
import clases.ControladorTipoPrecio;
import clases.ControladorUsuario;
import clases.ControladorVenta;
import clases.DetalleVenta;
import clases.ErrorTienda;
import clases.Producto;
import clases.PropiedadesVentas;
import clases.Sucursal;
import clases.TipoPrecio;
import clases.Usuario;
import clases.Venta;
import facadeshop.Diseño;
import static formularios.frmComprasDetalle.lblUser;
import static formularios.frmComprasDetalle.lblUser1;
import static formularios.frmLogin.txtUser;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
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
    String rol, password;
    ControladorVenta cv;
    ControladorProducto cp;
    Producto miProducto;
    Venta venta = new Venta();
    DetalleVenta dv = new DetalleVenta();
    ControladorTipoPrecio precios;
    Date fecha = new Date();
     boolean calcularCambio;
    
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
        dtcFecha.setDateFormatString("dd-MM-yyyy");
        dtcFecha.setDate(fecha);
        obtenerUsuario();
        HabilitarCalcularCambio();
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
    public void HabilitarCalcularCambio(){
        PropiedadesVentas pv = new PropiedadesVentas();
        pv.CargarDatos();
        pv.Cambio();
        calcularCambio=pv.isEstado();
        if(calcularCambio){
            rdbCalcularTotal.setSelected(true);
        }else{
            rdbCalcularTotal.setSelected(false);
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
        SimpleDateFormat formato;
        char idTipoVenta;
        double totGravado;
        if(cmbTipoVenta.getSelectedIndex()==0){
            idTipoVenta='F';
        }else if(cmbTipoVenta.getSelectedIndex()==1){
            idTipoVenta='C';
        }else{
            idTipoVenta='B';
        }
        
        venta.setIdVenta(Integer.parseInt(txtIdVenta.getText()));
        venta.setIdPrecio(Integer.parseInt(String.valueOf(misPrecios[cmbTipoPrecio.getSelectedIndex()][0])));
        venta.setIdTipoVenta(idTipoVenta);
        venta.setIdSucursal(Integer.parseInt(String.valueOf(miSucursal[cmbSucursalVenta.getSelectedIndex()][0])));
        if(idTipoVenta=='F' || idTipoVenta=='B'){
            totGravado=Double.parseDouble(txtTotalventa.getText().substring(1))/1.13;
            
            venta.setTotalGravado(Double.parseDouble(decimal.format(totGravado)));
            venta.setIVA(Double.parseDouble(decimal.format(Double.parseDouble(txtTotalventa.getText().substring(1))-totGravado)));
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
        formato=new SimpleDateFormat("dd/MM/yy HH:mm:ss",Locale.getDefault());
        String fecha=formato.format(venta.getFecha());
        venta.setCliente(txtClienteVenta.getText().toUpperCase());
        venta.setDireccion(txtDireccionVenta.getText().toUpperCase());
        venta.CalcularPAC();
        
        
        Object sucursal=cmbSucursalVenta.getSelectedItem();
        
        String producto=txtNombreProductoVender.getText();
        String total=txtTotalventa.getText();
        String subtotal=txtSumas.getText();
        String iva=txtIVA.getText();
        int filas=modeloVentas.getRowCount();
        
        String detalles[][] = new String[modeloVentas.getRowCount()][5];
        
            for(int y=0; y<modeloVentas.getRowCount();y++){
                detalles[y][0]=modeloVentas.getValueAt(y, 1).toString();
                detalles[y][1]=modeloVentas.getValueAt(y, 2).toString();
                detalles[y][2]=modeloVentas.getValueAt(y, 3).toString();
                detalles[y][3]=modeloVentas.getValueAt(y, 4).toString();
                
            }
            
            String login=lblUser1.getText();
            String rol=lblRolUsuario.getText();
            int id=ControladorUsuario.ObtenerIdUsuario();
        
        if (idTipoVenta=='F') { 
            //Imprimir
            UsoTicket.borradoInicializacion();
            UsoTicket.cabecera();
            UsoTicket.datosTicket(sucursal,fecha,"Factura");
            
            
            UsoTicket.datosVentaFactura(detalles,total,filas);
            
            UsoTicket.datosVendedor(id,login,rol);
            UsoTicket.imprimir();
        }else{
            //Imprimir
            UsoTicket.borradoInicializacion();
            UsoTicket.cabecera();
            UsoTicket.datosTicket(sucursal,fecha,"Credito Fiscal");
            UsoTicket.datosVentaCreditoFiscal(detalles,subtotal,iva,total,filas);
            UsoTicket.datosVendedor(id,login,rol);
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
               if(cmbTipoVenta.getSelectedIndex()==0 || cmbTipoVenta.getSelectedIndex()==2){
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
            if(tipoVenta==0){
                if(txtClienteVenta.getText().isEmpty()){
                    mensajeNotificacion("Falta llenar campos", "Adv");
                    lblCliente.setForeground(Color.red);
                    mensaje = 1;
                }
            if(txtDireccionVenta.getText().isEmpty()){
                    
                    lblDireccion.setForeground(Color.red);
                    mensaje = 1;
                
            }
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
        jpnPass = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pwdAntigua = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        pwdNueva = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        pwdNueva2 = new javax.swing.JPasswordField();
        btnAtrasPwd = new javax.swing.JLabel();
        btnCambiarPwd = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jpnUser = new javax.swing.JPanel();
        lblRolUsuario = new javax.swing.JLabel();
        lblCambiarPwd = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
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
        txtTotalventa = new javax.swing.JTextField();
        btnVender = new javax.swing.JButton();
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
        jpnBarraSuperior = new javax.swing.JPanel();
        jpnWhite = new javax.swing.JPanel();
        lblUser1 = new javax.swing.JLabel();
        lblBotonCerrar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jpnBarraMenu = new javax.swing.JPanel();
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
        btnHome = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        lblVentasBorrador = new javax.swing.JLabel();
        lblVender = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lblDetallesVentas = new javax.swing.JLabel();
        rdbCalcularTotal = new javax.swing.JRadioButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        jpnPass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Repita nueva contraseña:");
        jpnPass.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

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
        jpnPass.add(pwdAntigua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel4.setText("Contraseña antigua:");
        jpnPass.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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
        jpnPass.add(pwdNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 0));
        jLabel7.setText("Nueva contraseña:");
        jpnPass.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        pwdNueva2.setBackground(new java.awt.Color(240, 240, 240));
        pwdNueva2.setBorder(null);
        pwdNueva2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwdNueva2FocusGained(evt);
            }
        });
        jpnPass.add(pwdNueva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, -1));

        btnAtrasPwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Atras1Negro.png"))); // NOI18N
        btnAtrasPwd.setToolTipText("Volver atrás.");
        btnAtrasPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtrasPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasPwdMouseClicked(evt);
            }
        });
        jpnPass.add(btnAtrasPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 30, 30));

        btnCambiarPwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Aceptar1Negro.png"))); // NOI18N
        btnCambiarPwd.setToolTipText("Cambiar Contraseña.");
        btnCambiarPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambiarPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCambiarPwdMouseClicked(evt);
            }
        });
        jpnPass.add(btnCambiarPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 155, 30, 30));

        jSeparator1.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 145, 200, 10));

        jSeparator2.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 200, 10));

        jSeparator4.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 95, 200, 10));

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

        jpnAgregarCompra.setBackground(new java.awt.Color(0, 0, 0));
        jpnAgregarCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIDVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIDVenta.setForeground(new java.awt.Color(240, 240, 240));
        lblIDVenta.setText("ID Venta");
        jpnAgregarCompra.add(lblIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 20, 60));

        cmbSucursalVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSucursalVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalVentaActionPerformed(evt);
            }
        });
        jpnAgregarCompra.add(cmbSucursalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 5, 160, 30));

        cmbTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura", "Cŕedito Fiscal", "Borrador" }));
        cmbTipoVenta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoVentaItemStateChanged(evt);
            }
        });
        jpnAgregarCompra.add(cmbTipoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 5, 150, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(240, 240, 240));
        jLabel37.setText("Fecha:");
        jpnAgregarCompra.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, 40));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(240, 240, 240));
        jLabel39.setText("Tipo de venta:");
        jpnAgregarCompra.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, -1, 40));

        txtIdVenta.setEditable(false);
        txtIdVenta.setBackground(new java.awt.Color(254, 254, 254));
        jpnAgregarCompra.add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 5, 120, 30));

        dtcFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtcFechaFocusLost(evt);
            }
        });
        jpnAgregarCompra.add(dtcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 5, 150, 30));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(240, 240, 240));
        jLabel40.setText("Sucursal:");
        jpnAgregarCompra.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, 40));

        getContentPane().add(jpnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 1010, 40));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 10, 210));

        txtNombreProductoVender.setEditable(false);
        txtNombreProductoVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtNombreProductoVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 350, 40));

        txtCantidadVender.setText("  1");
        txtCantidadVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCantidadVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVenderKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVenderKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidadVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 60, 40));

        btnAgregarProductoVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarProductoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/agregar2.png"))); // NOI18N
        btnAgregarProductoVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProductoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarProductoVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarProductoVentaMouseExited(evt);
            }
        });
        btnAgregarProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarProductoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 110, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Producto");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));

        lblNRC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNRC.setText("NRC:");
        getContentPane().add(lblNRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, -1, -1));

        txtCodigoBarraVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarraVenderKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarraVenderKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoBarraVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 170, 40));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 1010, 10));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Cantidad");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, -1, -1));

        lblGiro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGiro.setText("Giro:");
        getContentPane().add(lblGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 100, -1));

        txtNRCVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNRCVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtNRCVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 190, 150, 30));

        txtGiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiroKeyTyped(evt);
            }
        });
        getContentPane().add(txtGiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, 330, 30));

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCliente.setText("Cliente:");
        getContentPane().add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 130, -1));

        txtClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 330, 30));

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireccion.setText("Dirección:");
        getContentPane().add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 130, -1));

        txtDireccionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtDireccionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 330, 30));

        lblDOC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDOC.setText("N° Documento");
        getContentPane().add(lblDOC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, -1, -1));

        txtNITVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNITVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtNITVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 250, 150, 30));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 970, 190));

        txtTotalventa.setEditable(false);
        txtTotalventa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtTotalventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 590, 120, 40));

        btnVender.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/vender.png"))); // NOI18N
        btnVender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVenderMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVenderMouseEntered(evt);
            }
        });
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        getContentPane().add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 650, 110, 30));

        lblSumas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSumas.setText("Sumas");
        getContentPane().add(lblSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 600, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Tipo de Precio");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, -1, -1));

        btnCancelarVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/cancelar.png"))); // NOI18N
        btnCancelarVenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVenta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarVenta1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarVenta1MouseExited(evt);
            }
        });
        btnCancelarVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVenta1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarVenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 650, 110, 30));

        txtIVA.setEditable(false);
        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 590, 120, 40));

        txtSumas.setEditable(false);
        txtSumas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSumas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 590, 120, 40));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 3, 0));
        jLabel38.setText("Total");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 600, -1, 20));

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIVA.setText("13% IVA");
        getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 600, -1, -1));

        txtNDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNDocumentoKeyTyped(evt);
            }
        });
        getContentPane().add(txtNDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 310, 150, 30));

        lblNIT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNIT.setText("NIT:");
        getContentPane().add(lblNIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 230, -1, -1));

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
        getContentPane().add(cmbTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 170, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Código de Barra");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        lblMasIva.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lblMasIva.setForeground(new java.awt.Color(255, 0, 0));
        lblMasIva.setText("+ IVA");
        getContentPane().add(lblMasIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 370, -1, 20));

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

        lblBotonCerrar.setBackground(new java.awt.Color(102, 0, 0));
        lblBotonCerrar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        lblBotonCerrar.setForeground(new java.awt.Color(102, 0, 0));
        lblBotonCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBotonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/exit32.png"))); // NOI18N
        lblBotonCerrar.setToolTipText("Salir");
        lblBotonCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBotonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotonCerrarMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblBotonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 40, 50));

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
        jpnBarraSuperior.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 50));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnBarraMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnBarraMenu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jpnBarraMenuPropertyChange(evt);
            }
        });
        jpnBarraMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnSubMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnSubMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnSubMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/compras.png"))); // NOI18N
        btnCompras.setBorderPainted(false);
        btnCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComprasMouseClicked(evt);
            }
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
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVentasMouseExited(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 110, 180, 40));

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/productos.png"))); // NOI18N
        btnProductos.setBorderPainted(false);
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTipoPrecioMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnParametroMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseExited(evt);
            }
        });
        jpnSubMenu.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 260, 180, 40));

        btnSucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/sucursales.png"))); // NOI18N
        btnSucursales.setBorderPainted(false);
        btnSucursales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSucursalesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSucursalesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSucursalesMouseExited(evt);
            }
        });
        jpnSubMenu.add(btnSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 310, 180, 40));

        btnDetalleCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/det.compras.png"))); // NOI18N
        btnDetalleCompras.setBorderPainted(false);
        btnDetalleCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalleCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetalleComprasMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetalleVentasMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBitacorasMouseClicked(evt);
            }
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

        getContentPane().add(jpnBarraMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 650));

        lblVentasBorrador.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblVentasBorrador.setForeground(new java.awt.Color(153, 153, 153));
        lblVentasBorrador.setText("Ventas Borrador");
        lblVentasBorrador.setToolTipText("Ver las ventas en borrador disponibles.");
        lblVentasBorrador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVentasBorrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentasBorradorMouseClicked(evt);
            }
        });
        getContentPane().add(lblVentasBorrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, 50));

        lblVender.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblVender.setText("Vender");
        lblVender.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblVender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVenderMouseClicked(evt);
            }
        });
        getContentPane().add(lblVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, 50));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

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

        rdbCalcularTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdbCalcularTotal.setSelected(true);
        rdbCalcularTotal.setText("Habilitar Calcular Total");
        rdbCalcularTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbCalcularTotalActionPerformed(evt);
            }
        });
        getContentPane().add(rdbCalcularTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 660, -1, -1));

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
               if(calcularCambio){
                   cc.txtTotalaPagar.setText(txtTotalventa.getText());
                   System.err.println("Hola");
                   cc.setVisible(true);
               }
               
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
           txtClienteVenta.setVisible(!false);
           txtDireccionVenta.setVisible(!false);
           lblCliente.setVisible(!false);
           lblDireccion.setVisible(!false);
           cmbTipoPrecio.setSelectedIndex(1);
       }else if(cmbTipoVenta.getSelectedIndex() == 1){
           
           tipoVentaSeleccion(true);
           limpiar("todo");
           txtClienteVenta.setVisible(!false);
           txtDireccionVenta.setVisible(!false);
           lblCliente.setVisible(!false);
           lblDireccion.setVisible(!false);
           lblMasIva.setVisible(false);
           cmbTipoPrecio.setSelectedIndex(1);
       }
       else{
           tipoVentaSeleccion(false);
           limpiar("todo");
           lblMasIva.setVisible(true);
           txtClienteVenta.setVisible(false);
           txtDireccionVenta.setVisible(false);
           lblCliente.setVisible(false);
           lblDireccion.setVisible(false);
           cmbTipoPrecio.setSelectedIndex(0);
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

    private void dtcFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtcFechaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dtcFechaFocusLost

    private void cmbTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoPrecioActionPerformed
        
    }//GEN-LAST:event_cmbTipoPrecioActionPerformed

    private void lblUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser1MouseClicked
        jpnWhite.setVisible(false);
        jpnUser.setVisible(false);
    }//GEN-LAST:event_lblUser1MouseClicked

    private void jpnWhiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnWhiteMouseClicked
        jpnWhite.setVisible(false);
        jpnUser.setVisible(false);
    }//GEN-LAST:event_jpnWhiteMouseClicked

    private void lblBotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrarMouseClicked

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

    private void btnComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseClicked
        frmCompras cm = new frmCompras();
        cm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnComprasMouseClicked

    private void btnComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseEntered
        /*  ---- Animación compras, mover ----  */
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);
    }//GEN-LAST:event_btnComprasMouseEntered

    private void btnComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseExited
        /*  ---- Animación compras, volver posición anterior ----  */
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnCompras);
    }//GEN-LAST:event_btnComprasMouseExited

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed

    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked
        try {
            frmVentas vt = new frmVentas();
            vt.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentasMouseClicked

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnVentas);
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnVentas);
    }//GEN-LAST:event_btnVentasMouseExited

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed

    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked
        frmProductos pd = new frmProductos();
        pd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);
    }//GEN-LAST:event_btnProductosMouseEntered

    private void btnProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProductos);
    }//GEN-LAST:event_btnProductosMouseExited

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed

    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseClicked
        frmTipoPrecio tp = new frmTipoPrecio();
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTipoPrecioMouseClicked

    private void btnTipoPrecioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnTipoPrecio);
    }//GEN-LAST:event_btnTipoPrecioMouseEntered

    private void btnTipoPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnTipoPrecio);
    }//GEN-LAST:event_btnTipoPrecioMouseExited

    private void btnTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipoPrecioActionPerformed

    private void btnParametroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseClicked

        frmParametro pr = new frmParametro();
        pr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnParametroMouseClicked

    private void btnParametroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnParametro);
    }//GEN-LAST:event_btnParametroMouseEntered

    private void btnParametroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnParametro);
    }//GEN-LAST:event_btnParametroMouseExited

    private void btnParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnParametroActionPerformed

    private void btnProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseClicked
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProveedoresMouseClicked

    private void btnProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);
    }//GEN-LAST:event_btnProveedoresMouseEntered

    private void btnProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProveedores);
    }//GEN-LAST:event_btnProveedoresMouseExited

    private void btnSucursalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseClicked
        frmSucursales su = new frmSucursales();
        su.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSucursalesMouseClicked

    private void btnSucursalesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnSucursales);
    }//GEN-LAST:event_btnSucursalesMouseEntered

    private void btnSucursalesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnSucursales);
    }//GEN-LAST:event_btnSucursalesMouseExited

    private void btnDetalleComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleComprasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetalleComprasMouseClicked

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

    private void btnDetalleVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentasMouseClicked

    }//GEN-LAST:event_btnDetalleVentasMouseClicked

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

    private void btnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesMouseClicked

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnReportes);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnReportes);
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnBitacorasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBitacorasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBitacorasMouseClicked

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

    private void jpnBarraMenuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpnBarraMenuPropertyChange

    }//GEN-LAST:event_jpnBarraMenuPropertyChange

    private void lblVentasBorradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentasBorradorMouseClicked
        try {
            frmVentasBorrador vb = new frmVentasBorrador();
            vb.setVisible(true);
            this.setVisible(false);
            lblVentasBorrador.setForeground(java.awt.Color.black);
            lblVender.setForeground(java.awt.Color.lightGray);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVentasBorradorMouseClicked

    private void lblVenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVenderMouseClicked
        // TODO add your handling code here:
        lblVender.setForeground(java.awt.Color.black);
        lblVentasBorrador.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblVenderMouseClicked

    private void lblDetallesVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDetallesVentasMouseClicked
        frmVentasDetalle vd = new frmVentasDetalle();
        vd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblDetallesVentasMouseClicked

    private void lblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblMenuMouseClicked

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
        mensajeNotificacion("¡Has cerrado sesión!", "Error");
    }//GEN-LAST:event_lblCerrarSesionMouseClicked

    private void jpnUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnUserMouseExited
        //jpnUser.setVisible(false);
        //jpnWhite.setVisible(false);
    }//GEN-LAST:event_jpnUserMouseExited

    private void rdbCalcularTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbCalcularTotalActionPerformed
        PropiedadesVentas pv = new PropiedadesVentas();
        if(rdbCalcularTotal.isSelected()){
            pv.Modificar("true");
            HabilitarCalcularCambio();
        }else{
            pv.Modificar("false");
            HabilitarCalcularCambio();
        }
    }//GEN-LAST:event_rdbCalcularTotalActionPerformed

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
    private javax.swing.JLabel btnAtrasPwd;
    private javax.swing.JButton btnBitacoras;
    private javax.swing.JLabel btnCambiarPwd;
    private javax.swing.JButton btnCancelarVenta1;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnDetalleCompras;
    private javax.swing.JButton btnDetalleVentas;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnTipoPrecio;
    private javax.swing.JButton btnVender;
    private javax.swing.JButton btnVentas;
    private javax.swing.JComboBox<String> cmbSucursalVenta;
    private javax.swing.JComboBox<String> cmbTipoPrecio;
    private javax.swing.JComboBox<String> cmbTipoVenta;
    private com.toedter.calendar.JDateChooser dtcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPanel jpnAgregarCompra;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnPass;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
    public static javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDOC;
    private javax.swing.JLabel lblDetallesVentas;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblGiro;
    private javax.swing.JLabel lblIDVenta;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMasIva;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblNIT;
    private javax.swing.JLabel lblNRC;
    private javax.swing.JLabel lblRolUsuario;
    private javax.swing.JLabel lblSumas;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JLabel lblVender;
    private javax.swing.JLabel lblVentasBorrador;
    private javax.swing.JPasswordField pwdAntigua;
    private javax.swing.JPasswordField pwdNueva;
    private javax.swing.JPasswordField pwdNueva2;
    private javax.swing.JRadioButton rdbCalcularTotal;
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

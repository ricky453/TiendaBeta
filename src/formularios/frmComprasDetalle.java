/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Compra;
import clases.ControladorCompra;
import clases.ControladorUsuario;
import clases.ControladorVenta;
import clases.DetalleCompra;
import clases.ErrorTienda;
import facadeshop.Diseño;
import static formularios.frmCompras.lblUser;
import static formularios.frmCompras.lblUser1;
import static formularios.frmLogin.txtUser;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Ricky
 */
public class frmComprasDetalle extends javax.swing.JFrame {

    boolean estadoMenu;
    JTableHeader tHeadVentas;
    public DefaultTableModel modeloDCompras = new DefaultTableModel();
    public int seleccion;
    double subTotales;
    String rol;
    Date fdate=new Date();
    DecimalFormat decimal = new DecimalFormat("0.00");
    
    public frmComprasDetalle() {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        tHeadVentas = tblCompras.getTableHeader();
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraSuperior.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
        jpnSubMenu1.setVisible(false);
        modeloDCompras = (DefaultTableModel) tblComprasDetalladas.getModel();
        obtenerUsuario();
        jdcFecha.setDateFormatString("dd-MM-yyyy");
        jdcFecha.setDate(fdate);
    }
    
    public void obtenerUsuario(){
        lblUser.setText(Diseño.user);
        lblUser1.setText(Diseño.user);
        jpnUser.setVisible(false);
        jpnWhite.setVisible(false);
        obtenerRol();
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
    public void obtenerRol(){
        try {
            rol = ControladorUsuario.obtenerRol(txtUser.getText());
            if(rol.equals("A")){
                lblRolUsuario.setText("ADMINISTRADOR");
            }
            else if(rol.equals("V")){
                lblRolUsuario.setText("VENDEDOR");}
            else if(rol.equals("C")){
                lblRolUsuario.setText("COMPRADOR");

            }
            
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   //---------------------------Llenar tabla de Detalle Compra----------------------------------------
      /*  public void actualizarTablaDetalleCompra(){
            modeloDCompras.setRowCount(0);
            
            ArrayList<DetalleCompra> listaDetalleCompra=new ArrayList();
            Object fila[]=new Object[5];
            
        
            try {
            listaDetalleCompra=ControladorCompra.ObtenerCompra();
            String[] nombreDetalleCompra = new String []{"IdCompra","Producto","Cantidad","Costo Unitario"};
            modeloDCompras.setColumnIdentifiers(nombreDetalleCompra);
            Iterator<DetalleCompra> dc=listaDetalleCompra.iterator();
                while(dc.hasNext()){
                    fila[0]= dc.next();
                    fila[1]= dc.next();
                    fila[2]= dc.next();
                    fila[3]= dc.next();
                    modeloDCompras.addRow(fila);
                    tblCompras.setModel(modeloDCompras);
                }
            }
            
         catch (ErrorTienda ex) {
             Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
         }
    } */
        public void SumarSubTotales(){
        int filas = modeloDCompras.getRowCount();
        subTotales=0;
        for(int i=0;i<filas;i++){
            subTotales+=Double.parseDouble(String.valueOf(modeloDCompras.getValueAt(i, 3)))*Double.parseDouble(String.valueOf(modeloDCompras.getValueAt(i, 2)));
        }
        
    }
        public void limpiar(){
            txtIdCompra.setText("");
            txtTipoCompra.setText("");
            txtSucursal.setText("");
            txtFecha.setText("");
            txtSumas.setText("");
            txtIVA.setText("");
            txtTotalCompra.setText("");
            modeloDCompras.setRowCount(0);
            tblComprasDetalladas.removeAll();
        }
    
     public void estableciendoDatos(int id){
        Object[] fila=new Object[4];
        
        modeloDCompras=new DefaultTableModel();
        String[] campos = {"IdCompra","Producto","Cantidad","Costo Unitario"};
        
        System.out.println(id);
        
        try {
            ArrayList<DetalleCompra> miscompras=ControladorCompra.ObtenerCompra(id);
            modeloDCompras.setColumnIdentifiers(campos);
            Iterator iterador=miscompras.iterator();
            
            while (iterador.hasNext()) {
                fila[0]=iterador.next();
                fila[1]=iterador.next();
                fila[2]=iterador.next();
                fila[3]=iterador.next();
                
                modeloDCompras.addRow(fila);
                tblComprasDetalladas.setModel(modeloDCompras);
            }
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmComprasDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frmComprasDetalladas = new javax.swing.JFrame();
        jLabel11 = new javax.swing.JLabel();
        txtIdCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTipoCompra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblComprasDetalladas = new javax.swing.JTable();
        lblSumas = new javax.swing.JLabel();
        txtSumas = new javax.swing.JTextField();
        lblIVA = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtTotalCompra = new javax.swing.JTextField();
        jpnBarraSuperior2 = new javax.swing.JPanel();
        lblBotonCerrar1 = new javax.swing.JLabel();
        lblLogo2 = new javax.swing.JLabel();
        jpnBarraMenu1 = new javax.swing.JPanel();
        lblMenu1 = new javax.swing.JLabel();
        jpnSubMenu1 = new javax.swing.JPanel();
        btnCompras1 = new javax.swing.JButton();
        btnVentas1 = new javax.swing.JButton();
        btnProductos1 = new javax.swing.JButton();
        btnTipoPrecio1 = new javax.swing.JButton();
        btnParametro1 = new javax.swing.JButton();
        btnProveedores1 = new javax.swing.JButton();
        btnSucursales1 = new javax.swing.JButton();
        btnDetalleCompras1 = new javax.swing.JButton();
        btnDetalleVentas1 = new javax.swing.JButton();
        btnReportes1 = new javax.swing.JButton();
        btnBitacoras1 = new javax.swing.JButton();
        btnHome1 = new javax.swing.JLabel();
        lblComprar1 = new javax.swing.JLabel();
        lblDetallesCompras1 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jpnUser = new javax.swing.JPanel();
        lblRolUsuario = new javax.swing.JLabel();
        lblCambiarPwd = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        jpnWhite = new javax.swing.JPanel();
        lblUser1 = new javax.swing.JLabel();
        lblBotonCerrar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompras = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnDetalles = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        lblDetallesCompras = new javax.swing.JLabel();
        lblComprar = new javax.swing.JLabel();
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
        jSeparator9 = new javax.swing.JSeparator();

        frmComprasDetalladas.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        frmComprasDetalladas.setMinimumSize(new java.awt.Dimension(1200, 700));
        frmComprasDetalladas.setUndecorated(true);
        frmComprasDetalladas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Id de la compra:");
        frmComprasDetalladas.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 130, 30));

        txtIdCompra.setEditable(false);
        txtIdCompra.setText(" ");
        txtIdCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCompraActionPerformed(evt);
            }
        });
        frmComprasDetalladas.getContentPane().add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 50, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Fue una compra con:");
        frmComprasDetalladas.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 170, 30));

        txtTipoCompra.setEditable(false);
        txtTipoCompra.setText(" ");
        txtTipoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoCompraActionPerformed(evt);
            }
        });
        frmComprasDetalladas.getContentPane().add(txtTipoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 170, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Sucursal:");
        frmComprasDetalladas.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 70, 30));

        txtSucursal.setEditable(false);
        txtSucursal.setText(" ");
        txtSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSucursalActionPerformed(evt);
            }
        });
        frmComprasDetalladas.getContentPane().add(txtSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 240, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Fecha:");
        frmComprasDetalladas.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 260, 50, 30));

        txtFecha.setEditable(false);
        txtFecha.setText(" ");
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        frmComprasDetalladas.getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 190, 30));

        tblComprasDetalladas =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblComprasDetalladas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Precio Unitario $", "Sub total $"
            }
        ));
        tblComprasDetalladas.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblComprasDetalladas);

        frmComprasDetalladas.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 960, 220));

        lblSumas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSumas.setText("Sumas");
        frmComprasDetalladas.getContentPane().add(lblSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 570, -1, 30));

        txtSumas.setEditable(false);
        txtSumas.setBackground(new java.awt.Color(255, 255, 255));
        txtSumas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSumas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frmComprasDetalladas.getContentPane().add(txtSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 570, 120, 30));

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIVA.setText("13% IVA");
        frmComprasDetalladas.getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 570, -1, 30));

        txtIVA.setEditable(false);
        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frmComprasDetalladas.getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 570, 120, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 3, 0));
        jLabel38.setText("Total");
        frmComprasDetalladas.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 570, -1, 30));

        txtTotalCompra.setEditable(false);
        txtTotalCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalCompra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frmComprasDetalladas.getContentPane().add(txtTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 570, 120, 30));

        jpnBarraSuperior2.setBackground(new java.awt.Color(0, 0, 0));
        jpnBarraSuperior2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnBarraSuperior2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpnBarraSuperior2MouseDragged(evt);
            }
        });
        jpnBarraSuperior2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnBarraSuperior2MousePressed(evt);
            }
        });
        jpnBarraSuperior2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBotonCerrar1.setBackground(new java.awt.Color(102, 0, 0));
        lblBotonCerrar1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        lblBotonCerrar1.setForeground(new java.awt.Color(102, 0, 0));
        lblBotonCerrar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBotonCerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/exit32.png"))); // NOI18N
        lblBotonCerrar1.setToolTipText("Salir");
        lblBotonCerrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBotonCerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotonCerrar1MouseClicked(evt);
            }
        });
        jpnBarraSuperior2.add(lblBotonCerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 40, 50));

        lblLogo2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblLogo2.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        lblLogo2.setText("iShop 3.0");
        lblLogo2.setToolTipText("");
        jpnBarraSuperior2.add(lblLogo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 50));

        frmComprasDetalladas.getContentPane().add(jpnBarraSuperior2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnBarraMenu1.setBackground(new java.awt.Color(102, 0, 0));
        jpnBarraMenu1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jpnBarraMenu1PropertyChange(evt);
            }
        });
        jpnBarraMenu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMenu1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblMenu1.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Menu.png"))); // NOI18N
        lblMenu1.setText("Menu");
        jpnBarraMenu1.add(lblMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 15, 170, 50));

        jpnSubMenu1.setBackground(new java.awt.Color(102, 0, 0));
        jpnSubMenu1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnSubMenu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCompras1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/compras.png"))); // NOI18N
        btnCompras1.setBorderPainted(false);
        btnCompras1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCompras1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCompras1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCompras1MouseExited(evt);
            }
        });
        btnCompras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompras1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnCompras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 10, 180, 40));

        btnVentas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/ventas.png"))); // NOI18N
        btnVentas1.setBorderPainted(false);
        btnVentas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVentas1MouseExited(evt);
            }
        });
        btnVentas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 110, 180, 40));

        btnProductos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/productos.png"))); // NOI18N
        btnProductos1.setBorderPainted(false);
        btnProductos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductos1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProductos1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProductos1MouseExited(evt);
            }
        });
        btnProductos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 210, 180, 40));

        btnTipoPrecio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/tipoprecio.png"))); // NOI18N
        btnTipoPrecio1.setBorderPainted(false);
        btnTipoPrecio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTipoPrecio1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTipoPrecio1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTipoPrecio1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTipoPrecio1MouseExited(evt);
            }
        });
        btnTipoPrecio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoPrecio1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnTipoPrecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 410, 180, 40));

        btnParametro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/parametro.png"))); // NOI18N
        btnParametro1.setBorderPainted(false);
        btnParametro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParametro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnParametro1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnParametro1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnParametro1MouseExited(evt);
            }
        });
        btnParametro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametro1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnParametro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 360, 180, 40));

        btnProveedores1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/proveedores.png"))); // NOI18N
        btnProveedores1.setBorderPainted(false);
        btnProveedores1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProveedores1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProveedores1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProveedores1MouseExited(evt);
            }
        });
        jpnSubMenu1.add(btnProveedores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 260, 180, 40));

        btnSucursales1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/sucursales.png"))); // NOI18N
        btnSucursales1.setBorderPainted(false);
        btnSucursales1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSucursales1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSucursales1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSucursales1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSucursales1MouseExited(evt);
            }
        });
        jpnSubMenu1.add(btnSucursales1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 310, 180, 40));

        btnDetalleCompras1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/det.compras.png"))); // NOI18N
        btnDetalleCompras1.setBorderPainted(false);
        btnDetalleCompras1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalleCompras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetalleCompras1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetalleCompras1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetalleCompras1MouseExited(evt);
            }
        });
        btnDetalleCompras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleCompras1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnDetalleCompras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 60, 180, 40));

        btnDetalleVentas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/det.ventas.png"))); // NOI18N
        btnDetalleVentas1.setBorderPainted(false);
        btnDetalleVentas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalleVentas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetalleVentas1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetalleVentas1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetalleVentas1MouseExited(evt);
            }
        });
        btnDetalleVentas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleVentas1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnDetalleVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 160, 180, 40));

        btnReportes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/reportes.png"))); // NOI18N
        btnReportes1.setBorderPainted(false);
        btnReportes1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportes1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportes1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportes1MouseExited(evt);
            }
        });
        btnReportes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportes1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnReportes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 460, 180, 40));

        btnBitacoras1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/bitacoras.png"))); // NOI18N
        btnBitacoras1.setBorderPainted(false);
        btnBitacoras1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBitacoras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBitacoras1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBitacoras1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBitacoras1MouseExited(evt);
            }
        });
        btnBitacoras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitacoras1ActionPerformed(evt);
            }
        });
        jpnSubMenu1.add(btnBitacoras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 510, 180, 40));

        jpnBarraMenu1.add(jpnSubMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 77, 190, 560));

        btnHome1.setToolTipText("Inicio");
        btnHome1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnBarraMenu1.add(btnHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        frmComprasDetalladas.getContentPane().add(jpnBarraMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 650));

        lblComprar1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblComprar1.setForeground(new java.awt.Color(153, 153, 153));
        lblComprar1.setText("Comprar");
        lblComprar1.setToolTipText("Realizar una compra.");
        lblComprar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblComprar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComprar1MouseClicked(evt);
            }
        });
        frmComprasDetalladas.getContentPane().add(lblComprar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 50));

        lblDetallesCompras1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblDetallesCompras1.setForeground(new java.awt.Color(153, 153, 153));
        lblDetallesCompras1.setText("Detalles de Compras");
        lblDetallesCompras1.setToolTipText("Ver detalles de las compras realizadas.");
        lblDetallesCompras1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDetallesCompras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDetallesCompras1MouseClicked(evt);
            }
        });
        frmComprasDetalladas.getContentPane().add(lblDetallesCompras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, 50));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(102, 0, 0));
        frmComprasDetalladas.getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jpnUser.add(lblCambiarPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 130, 20));

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

        tblCompras =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idCompra", "Sucursal", "Proveedor", "TipoCompra", "Fecha"
            }
        ));
        tblCompras.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCompras);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 920, 320));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Lista de las Compras:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, -1, -1));

        btnDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/detalles2.png"))); // NOI18N
        btnDetalles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetallesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetallesMouseEntered(evt);
            }
        });
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        getContentPane().add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 640, 110, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Fecha:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, 30));

        jdcFecha.setDateFormatString("yyyy-dd-MM");
        getContentPane().add(jdcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 200, 30));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 110, 30));

        lblDetallesCompras.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblDetallesCompras.setForeground(new java.awt.Color(51, 51, 51));
        lblDetallesCompras.setText("Detalles de Compras");
        lblDetallesCompras.setToolTipText("");
        lblDetallesCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDetallesCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDetallesComprasMouseClicked(evt);
            }
        });
        getContentPane().add(lblDetallesCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, 50));

        lblComprar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblComprar.setForeground(new java.awt.Color(153, 153, 153));
        lblComprar.setText("Comprar");
        lblComprar.setToolTipText("Realizar una compra.");
        lblComprar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblComprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComprarMouseClicked(evt);
            }
        });
        getContentPane().add(lblComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 50));

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

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnDetallesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseExited
        btnDetalles.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/detalles2.png")));
    }//GEN-LAST:event_btnDetallesMouseExited

    private void btnDetallesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseEntered
        btnDetalles.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/detalles2B.png")));
    }//GEN-LAST:event_btnDetallesMouseEntered

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        if(tblCompras.getSelectedRow()!=-1){
            
            this.hide();
            frmComprasDetalladas.show();
            frmComprasDetalladas.setLocation(this.getLocation());
            limpiar();
            seleccion=tblCompras.getSelectedRow();
            
            if ((tblCompras.getValueAt(seleccion, 4).toString()).equals("F")) {
                    
                    System.out.println(Integer.parseInt(tblCompras.getValueAt(seleccion, 0).toString()));
                    txtIdCompra.setText(tblCompras.getValueAt(seleccion, 0).toString());
                    txtFecha.setText((tblCompras.getValueAt(seleccion, 4).toString()));
                    txtSucursal.setText((tblCompras.getValueAt(seleccion, 1).toString()));
                    txtTipoCompra.setText("Factura");
                    
                    estableciendoDatos(Integer.parseInt(tblCompras.getValueAt(seleccion, 0).toString()));
                    SumarSubTotales();
                    lblSumas.setVisible(false);
                    txtSumas.setVisible(false);
                    lblIVA.setVisible(false);
                    txtIVA.setVisible(false);
                    txtTotalCompra.setText(""+decimal.format(subTotales));
                  
                
            }else{
                txtIdCompra.setText(tblCompras.getValueAt(seleccion, 0).toString());
                txtFecha.setText((tblCompras.getValueAt(seleccion, 4).toString()));
                txtSucursal.setText((tblCompras.getValueAt(seleccion, 1).toString()));
                txtTipoCompra.setText("Crédito Fiscal");
                
                estableciendoDatos(Integer.parseInt(tblCompras.getValueAt(seleccion, 0).toString()));
                SumarSubTotales();
                
                txtSumas.setText(""+decimal.format(subTotales));
                double iva=subTotales*0.13;
                txtIVA.setText(""+decimal.format(iva));
                double total=iva+subTotales;
                txtTotalCompra.setText(""+decimal.format(total));
            }
            
            
            
            
            
        }else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado en la tabla");
        }
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");

        String fecha=sd.format(jdcFecha.getDate());

        DefaultTableModel modeloDetalles = new DefaultTableModel();
        ArrayList<Compra> compras = new ArrayList();
        Object[] fila = new Object[5];

        if (fecha.equals("")) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fecha");
        }else{
            String[] campos = {"IdCompra", "Sucursal", "ID Proveedor", "Tipo de Compra","Fecha"};
            modeloDetalles.setColumnIdentifiers(campos);
            try {

                compras=ControladorCompra.obteniendoCompras(fecha);
                Object x ;
                Iterator iterador=compras.iterator();
                while (iterador.hasNext()) {
                    fila[0]=iterador.next();
                    fila[1]=iterador.next();
                    fila[2]=iterador.next();
                    x=iterador.next();
                    if (x.equals("F")) {
                        fila[3]="Factura";
                    }else if(x.equals("C")){
                        fila[3]="Crédito Fiscal";
                    }else{
                        fila[3]="Libre";
                    }
                    fila[4]=iterador.next();

                    modeloDetalles.addRow(fila);
                    tblCompras.setModel(modeloDetalles);
                }

            } catch (ErrorTienda ex) {
                Logger.getLogger(frmVentasDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCompraActionPerformed

    private void txtTipoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoCompraActionPerformed

    private void txtSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSucursalActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void lblDetallesComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDetallesComprasMouseClicked

        lblDetallesCompras.setForeground(java.awt.Color.black);
        lblComprar.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblDetallesComprasMouseClicked

    private void lblComprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComprarMouseClicked
        frmCompras cd = new frmCompras();
        cd.setVisible(true);
        this.setVisible(false);
        lblComprar.setForeground(java.awt.Color.black);
        lblDetallesCompras.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblComprarMouseClicked

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

    private void lblBotonCerrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrar1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrar1MouseClicked

    private void jpnBarraSuperior2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperior2MouseDragged

    }//GEN-LAST:event_jpnBarraSuperior2MouseDragged

    private void jpnBarraSuperior2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperior2MousePressed

    }//GEN-LAST:event_jpnBarraSuperior2MousePressed

    private void btnCompras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompras1MouseClicked
        frmCompras cm = new frmCompras();
        cm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCompras1MouseClicked

    private void btnCompras1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompras1MouseEntered
        /*  ---- Animación compras, mover ----  */
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);
    }//GEN-LAST:event_btnCompras1MouseEntered

    private void btnCompras1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompras1MouseExited
        /*  ---- Animación compras, volver posición anterior ----  */
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnCompras);
    }//GEN-LAST:event_btnCompras1MouseExited

    private void btnCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompras1ActionPerformed

    }//GEN-LAST:event_btnCompras1ActionPerformed

    private void btnVentas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseClicked
        try {
            frmVentas vt = new frmVentas();
            vt.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentas1MouseClicked

    private void btnVentas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnVentas);
    }//GEN-LAST:event_btnVentas1MouseEntered

    private void btnVentas1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnVentas);
    }//GEN-LAST:event_btnVentas1MouseExited

    private void btnVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas1ActionPerformed

    }//GEN-LAST:event_btnVentas1ActionPerformed

    private void btnProductos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductos1MouseClicked
        frmProductos pd = new frmProductos();
        pd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProductos1MouseClicked

    private void btnProductos1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductos1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);
    }//GEN-LAST:event_btnProductos1MouseEntered

    private void btnProductos1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductos1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProductos);
    }//GEN-LAST:event_btnProductos1MouseExited

    private void btnProductos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos1ActionPerformed

    }//GEN-LAST:event_btnProductos1ActionPerformed

    private void btnTipoPrecio1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecio1MouseClicked
        frmTipoPrecio tp = new frmTipoPrecio();
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTipoPrecio1MouseClicked

    private void btnTipoPrecio1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecio1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnTipoPrecio);
    }//GEN-LAST:event_btnTipoPrecio1MouseEntered

    private void btnTipoPrecio1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecio1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnTipoPrecio);
    }//GEN-LAST:event_btnTipoPrecio1MouseExited

    private void btnTipoPrecio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoPrecio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipoPrecio1ActionPerformed

    private void btnParametro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametro1MouseClicked

        frmParametro pr = new frmParametro();
        pr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnParametro1MouseClicked

    private void btnParametro1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametro1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnParametro);
    }//GEN-LAST:event_btnParametro1MouseEntered

    private void btnParametro1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametro1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnParametro);
    }//GEN-LAST:event_btnParametro1MouseExited

    private void btnParametro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametro1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnParametro1ActionPerformed

    private void btnProveedores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedores1MouseClicked
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProveedores1MouseClicked

    private void btnProveedores1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedores1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);
    }//GEN-LAST:event_btnProveedores1MouseEntered

    private void btnProveedores1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedores1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProveedores);
    }//GEN-LAST:event_btnProveedores1MouseExited

    private void btnSucursales1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursales1MouseClicked
        frmSucursales su = new frmSucursales();
        su.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSucursales1MouseClicked

    private void btnSucursales1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursales1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnSucursales);
    }//GEN-LAST:event_btnSucursales1MouseEntered

    private void btnSucursales1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursales1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnSucursales);
    }//GEN-LAST:event_btnSucursales1MouseExited

    private void btnDetalleCompras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleCompras1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetalleCompras1MouseClicked

    private void btnDetalleCompras1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleCompras1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnDetalleCompras);
    }//GEN-LAST:event_btnDetalleCompras1MouseEntered

    private void btnDetalleCompras1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleCompras1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnDetalleCompras);
    }//GEN-LAST:event_btnDetalleCompras1MouseExited

    private void btnDetalleCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleCompras1ActionPerformed
        frmComprasDetalle cd = new frmComprasDetalle();
        cd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDetalleCompras1ActionPerformed

    private void btnDetalleVentas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentas1MouseClicked

    }//GEN-LAST:event_btnDetalleVentas1MouseClicked

    private void btnDetalleVentas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentas1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnDetalleVentas);
    }//GEN-LAST:event_btnDetalleVentas1MouseEntered

    private void btnDetalleVentas1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentas1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnDetalleVentas);
    }//GEN-LAST:event_btnDetalleVentas1MouseExited

    private void btnDetalleVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleVentas1ActionPerformed
        frmVentasDetalle vd = new frmVentasDetalle();
        vd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDetalleVentas1ActionPerformed

    private void btnReportes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportes1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportes1MouseClicked

    private void btnReportes1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportes1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnReportes);
    }//GEN-LAST:event_btnReportes1MouseEntered

    private void btnReportes1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportes1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnReportes);
    }//GEN-LAST:event_btnReportes1MouseExited

    private void btnReportes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportes1ActionPerformed

    private void btnBitacoras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBitacoras1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBitacoras1MouseClicked

    private void btnBitacoras1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBitacoras1MouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnBitacoras);
    }//GEN-LAST:event_btnBitacoras1MouseEntered

    private void btnBitacoras1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBitacoras1MouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnBitacoras);
    }//GEN-LAST:event_btnBitacoras1MouseExited

    private void btnBitacoras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBitacoras1ActionPerformed
        frmBitacoras bi = new frmBitacoras();
        bi.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBitacoras1ActionPerformed

    private void jpnBarraMenu1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpnBarraMenu1PropertyChange

    }//GEN-LAST:event_jpnBarraMenu1PropertyChange

    private void lblComprar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComprar1MouseClicked
        frmCompras cd = new frmCompras();
        cd.setVisible(true);
        frmComprasDetalladas.dispose();
        lblComprar.setForeground(java.awt.Color.black);
        lblDetallesCompras.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblComprar1MouseClicked

    private void lblDetallesCompras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDetallesCompras1MouseClicked
        frmComprasDetalle cdd = new frmComprasDetalle();
        cdd.setVisible(true);
        frmComprasDetalladas.dispose();
        lblDetallesCompras.setForeground(java.awt.Color.black);
        lblComprar.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblDetallesCompras1MouseClicked

    private void lblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblMenuMouseClicked

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
            java.util.logging.Logger.getLogger(frmComprasDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmComprasDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmComprasDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmComprasDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmComprasDetalle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBitacoras;
    private javax.swing.JButton btnBitacoras1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnCompras1;
    private javax.swing.JButton btnDetalleCompras;
    private javax.swing.JButton btnDetalleCompras1;
    private javax.swing.JButton btnDetalleVentas;
    private javax.swing.JButton btnDetalleVentas1;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnHome1;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnParametro1;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProductos1;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReportes1;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnSucursales1;
    private javax.swing.JButton btnTipoPrecio;
    private javax.swing.JButton btnTipoPrecio1;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVentas1;
    private javax.swing.JFrame frmComprasDetalladas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraMenu1;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnBarraSuperior2;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnSubMenu1;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
    public static javax.swing.JLabel lblBotonCerrar;
    public static javax.swing.JLabel lblBotonCerrar1;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblComprar;
    private javax.swing.JLabel lblComprar1;
    private javax.swing.JLabel lblDetallesCompras;
    private javax.swing.JLabel lblDetallesCompras1;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMenu1;
    private javax.swing.JLabel lblRolUsuario;
    private javax.swing.JLabel lblSumas;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JTable tblCompras;
    public javax.swing.JTable tblComprasDetalladas;
    public javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIVA;
    public javax.swing.JTextField txtIdCompra;
    public javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtSumas;
    public javax.swing.JTextField txtTipoCompra;
    private javax.swing.JTextField txtTotalCompra;
    // End of variables declaration//GEN-END:variables
}

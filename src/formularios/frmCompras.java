/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import AppPackage.AnimationClass;
import clases.Compra;
import clases.ControladorCompra;
import clases.ControladorProducto;
import clases.ControladorProveedor;
import clases.ControladorSucursal;
import clases.DetalleCompra;
import clases.ErrorTienda;
import clases.Producto;
import clases.Proveedor;
import clases.Sucursal;
import clases.TipoPrecio;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Ricky
 */
public class frmCompras extends javax.swing.JFrame {

    Character s;
    boolean estadoMenu,estadoProd, exprod, encontrado;
    DecimalFormat decimalProductos = new DecimalFormat("0.0000");
    DefaultTableModel tablaModel= new DefaultTableModel();
    DecimalFormat decimal = new DecimalFormat("0.00");
    JTableHeader tHeadVentas;
    
    public frmCompras() {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        LlenarCompras();
        TableModel();
        tHeadVentas = tblCompra.getTableHeader();
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraSuperior.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpnMenu = new javax.swing.JPanel();
        lblSucursales = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        lblVentas = new javax.swing.JLabel();
        lblParametro = new javax.swing.JLabel();
        lblCompras = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        menu = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblBotonCerrar = new javax.swing.JLabel();
        jpnAgregarCompra = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lblnumdoc = new javax.swing.JLabel();
        cmbTipoCompra = new javax.swing.JComboBox<>();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        cmbSucursalCompra = new javax.swing.JComboBox<>();
        txtNumeroDoc = new javax.swing.JTextField();
        btnGuardarVenta = new javax.swing.JButton();
        txtIdCompra = new javax.swing.JTextField();
        cmbProveedor = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCompra = new javax.swing.JTable();
        txtPercepcion = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblIdCompra = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        lblPercepcion = new javax.swing.JLabel();
        lblCodBarraProd = new javax.swing.JLabel();
        lblNomProd = new javax.swing.JLabel();
        txtNomProd = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblCostoProd = new javax.swing.JLabel();
        txtCostoProd = new javax.swing.JTextField();
        btnCancelarVenta = new javax.swing.JButton();
        jSeparator37 = new javax.swing.JSeparator();
        lblTotal1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblIVA = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        btnDetalles = new javax.swing.JButton();
        txtCodBarraProd1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1200, 700));
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
        lblVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Ventas.png"))); // NOI18N
        lblVentas.setText("Ventas");
        lblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentasMouseClicked(evt);
            }
        });
        jpnMenu.add(lblVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 50));

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
        lblCompras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ECompras.png"))); // NOI18N
        lblCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComprasMouseClicked(evt);
            }
        });
        jpnMenu.add(lblCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        getContentPane().add(jpnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -360, 140, 316));

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
        jpnBarraSuperior.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 55));

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Home.png"))); // NOI18N
        home.setToolTipText("Ir a Home");
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 70, 55));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setToolTipText("");
        jpnBarraSuperior.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 30, 60));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setToolTipText("");
        jpnBarraSuperior.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 60, 60));

        lblBotonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/exit32.png"))); // NOI18N
        lblBotonCerrar.setToolTipText("Salir");
        lblBotonCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBotonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotonCerrarMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblBotonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 30, 55));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnAgregarCompra.setBackground(new java.awt.Color(0, 0, 0));
        jpnAgregarCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(240, 240, 240));
        jLabel33.setText("Tipo de compra:");
        jpnAgregarCompra.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 12, -1, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 20, 50));

        lblnumdoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnumdoc.setForeground(new java.awt.Color(240, 240, 240));
        lblnumdoc.setText("Número Documento:");
        jpnAgregarCompra.add(lblnumdoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 30));

        cmbTipoCompra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbTipoCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito Fiscal", "Factura", "Libre" }));
        cmbTipoCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbTipoCompra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCompraItemStateChanged(evt);
            }
        });
        jpnAgregarCompra.add(cmbTipoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 12, 150, 30));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 20, 50));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(240, 240, 240));
        jLabel35.setText("Sucursal:");
        jpnAgregarCompra.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 12, -1, 30));

        cmbSucursalCompra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSucursalCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sucursal1", "Sucursal2", "Sucursal3" }));
        jpnAgregarCompra.add(cmbSucursalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 12, 160, 30));

        txtNumeroDoc.setEditable(false);
        txtNumeroDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroDocKeyTyped(evt);
            }
        });
        jpnAgregarCompra.add(txtNumeroDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 180, 30));

        getContentPane().add(jpnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        btnGuardarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png"))); // NOI18N
        btnGuardarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarVentaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarVentaMouseEntered(evt);
            }
        });
        btnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 620, 110, 30));

        txtIdCompra.setEditable(false);
        getContentPane().add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 60, 30));

        getContentPane().add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 200, 30));

        tblCompra =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id producto", "Producto", "Cantidad", "Costo", "SubTotal"
            }
        ));
        tblCompra.getTableHeader().setReorderingAllowed(false);
        tblCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblCompraKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCompraKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblCompra);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 960, 200));

        txtPercepcion.setEditable(false);
        txtPercepcion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPercepcion.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(txtPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 100, 40));

        txtFecha.setEditable(false);
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 160, 30));

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha.setText("Fecha:");
        getContentPane().add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 60, 30));

        lblIdCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIdCompra.setText("Id Compra:");
        getContentPane().add(lblIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 80, 30));

        lblProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblProveedor.setText("Proveedor:");
        getContentPane().add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 90, 30));

        lblPercepcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPercepcion.setText("Percepción:");
        getContentPane().add(lblPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 90, 40));

        lblCodBarraProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCodBarraProd.setText("Cod Barra:");
        getContentPane().add(lblCodBarraProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 80, 30));

        lblNomProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNomProd.setText("Producto:");
        getContentPane().add(lblNomProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 70, 30));

        txtNomProd.setEditable(false);
        txtNomProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomProdKeyTyped(evt);
            }
        });
        getContentPane().add(txtNomProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 140, 30));

        lblCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCantidad.setText("Cantidad:");
        getContentPane().add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 70, 30));

        txtCantidad.setText("1");
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 40, 30));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1200, 10));

        lblCostoProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCostoProd.setText("Costo:");
        getContentPane().add(lblCostoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 220, 60, 30));

        txtCostoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoProdKeyTyped(evt);
            }
        });
        getContentPane().add(txtCostoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 220, 80, 30));

        btnCancelarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/cancelar.png"))); // NOI18N
        btnCancelarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarVentaMouseExited(evt);
            }
        });
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 620, 110, 30));

        jSeparator37.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1200, 10));

        lblTotal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotal1.setText("TOTAL:");
        getContentPane().add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 550, 50, 40));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 550, 100, 40));

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIVA.setText("IVA:");
        getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 600, 50, 40));

        txtIVA.setEditable(false);
        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIVA.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 600, 100, 40));

        btnDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/detalles2.png"))); // NOI18N
        btnDetalles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetallesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetallesMouseExited(evt);
            }
        });
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        getContentPane().add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 620, 110, 30));

        txtCodBarraProd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodBarraProd1KeyTyped(evt);
            }
        });
        getContentPane().add(txtCodBarraProd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 120, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void LlenarCompras(){        
        try {
            ArrayList<Sucursal> sucursal = ControladorSucursal.obtener();
            cmbSucursalCompra.removeAllItems();
            int idCompra;
            idCompra = ControladorCompra.ObtenerIdCompra();
            txtIdCompra.setText(String.valueOf(idCompra+1));
            txtNumeroDoc.setText(String.valueOf(idCompra+1));
            //GENERAR FECHA 
            Date utilDate=new Date();
            SimpleDateFormat fecha= new SimpleDateFormat("dd'/'MM'/'YYYY");
            txtFecha.setText(fecha.format(utilDate)); 
             //AGREGAR PROVEEDORES AL COMBO BOX
            Object vector1[] = new Object[4];
            if (cmbSucursalCompra.getItemCount()==0) {
                Iterator<Sucursal> iterador= sucursal.iterator();
                while(iterador.hasNext()){
                    vector1[0]=iterador.next();
                    vector1[1]=iterador.next();  
                    vector1[2]=iterador.next();
                    vector1[3]=iterador.next();
                    cmbSucursalCompra.addItem((String) vector1[1]);
                }
            } 
            ArrayList<Proveedor> proveedor = ControladorProveedor.Obtener();
            cmbProveedor.removeAllItems();
            Object vector[] = new Object[7];
            if (cmbProveedor.getItemCount() == 0) {
                Iterator<Proveedor> pro = proveedor.iterator();
                while (pro.hasNext()) {
                    vector[0] = pro.next();
                    vector[1] = pro.next();
                    vector[2] = pro.next();
                    vector[3] = pro.next();
                    vector[4] = pro.next();
                    vector[5] = pro.next();
                    vector[6] = pro.next();
                    cmbProveedor.addItem((String) vector[1]);
                }
            }
            
        } catch (Exception e) {
        }
        
    } 
    public void eliminarCompras(){
        int fila = tblCompra.getSelectedRow();
        if (fila != -1) {
            int opcion1=JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar el producto seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/iconos/pregunta.png"));
    
        if (opcion1 == 0) {
            tablaModel.removeRow(tblCompra.getSelectedRow());
        }
        }
    }
    public void TableModel(){
        String headers[] = {"Cod Barra","Producto","Cantidad","Costo","Subtotal"};
        tablaModel.setColumnIdentifiers(headers);
        tblCompra.setModel(tablaModel);
    }
    public void AgregarProductoCompras() throws ErrorTienda{
        Producto pr = new Producto();
        pr.setCodBarra(txtNumeroDoc.getText());
        pr.setNombre(txtNomProd.getText());
        pr.setInventario(Integer.parseInt(txtCantidad.getText()));
        pr.setCosto(Double.parseDouble(txtCostoProd.getText()));
        pr.setIdSucursal(ControladorSucursal.ObtenerIdSucursal(cmbSucursalCompra.getSelectedItem()));
        
        try {
            ControladorProducto.Agregar(pr);
        } catch (ErrorTienda ex) {

        }
        exprod=true;
        txtNomProd.setEditable(false);
        AgregarProductoTablaCompras();
        mensajeNotificacion("¡Producto agregado exitosamente!", "Ok");
    }
    public void AgregarProductoTablaCompras(){
        int TipoCompra = cmbTipoCompra.getSelectedIndex();
        if (tblCompra.getRowCount()>0) {
            int i = 0;
            while (encontrado==false&&i<tblCompra.getRowCount()) {
                encontrado = tblCompra.getValueAt(i, 0).equals(txtCodBarraProd1.getText());
                i++;
            }
        }


        if(encontrado == false){
            String fila[]  = new String[5];
            fila[0]=txtCodBarraProd1.getText();
            fila[1]=txtNomProd.getText();
            fila[2]=txtCantidad.getText();
            //System.out.println(decimalProductos.format(txtCostoProd.getText().toString()));
            if (TipoCompra != 0) {
                fila[3]=decimalProductos.format(Double.parseDouble(txtCostoProd.getText())+Double.parseDouble(txtCostoProd.getText())*0.13);
            }else{
                fila[3]=decimalProductos.format(Double.parseDouble(txtCostoProd.getText()));
            }
            fila[4]=String.valueOf(decimalProductos.format((Double.parseDouble(txtCantidad.getText()))*(Double.parseDouble(txtCostoProd.getText()))));
            tablaModel.addRow(fila);
            tblCompra.setModel(tablaModel);
        }else{
            boolean buscar=false;
            int j=0;
            int nuevaCantidad;
            double nuevoCosto;
            while (buscar==false) {
                buscar = tblCompra.getValueAt(j, 0).equals(txtCodBarraProd1.getText());
                j++;
            }
            nuevaCantidad = Integer.parseInt(txtCantidad.getText()) + Integer.parseInt(tblCompra.getValueAt(j-1, 2).toString());
            nuevoCosto = (Double.parseDouble(txtCostoProd.getText()) + Double.parseDouble(tblCompra.getValueAt(j-1, 3).toString()))/2;
            System.out.println(j);
            tablaModel.setValueAt(nuevaCantidad, j-1, 2);
            tablaModel.setValueAt(decimalProductos.format(nuevoCosto), j-1, 3);
            tablaModel.setValueAt(decimalProductos.format(nuevaCantidad*nuevoCosto), j-1, 4);
            tblCompra.setModel(tablaModel);
        }
        encontrado = false;
        //LIMPIAR LOS TXT
        txtCodBarraProd1.setText("");
        
        txtNomProd.setText("");
        txtCantidad.setText("1");
        txtCostoProd.setText("");
        txtCodBarraProd1.requestFocus();

        int filas = tablaModel.getRowCount();
        int iteraciones=0;
        double total=0;
        while(iteraciones<filas){
            total+= Double.parseDouble(String.valueOf(tablaModel.getValueAt(iteraciones, 4)));
            //System.out.println(tablaModel.getValueAt(iteraciones, 4));
            iteraciones++;
        }

        double totalFinal=Double.parseDouble(decimal.format(total));
        txtTotal.setText("$"+totalFinal);
        if (TipoCompra==0) {
            txtIVA.setText("$"+decimal.format(totalFinal*0.13));
            txtPercepcion.setText("$"+decimal.format(totalFinal*0.1));
        }
}
    
    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void btnGuardarVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarVentaMouseEntered
        btnGuardarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarVentaMouseEntered

    private void btnGuardarVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarVentaMouseExited
        btnGuardarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarVentaMouseExited

    private void btnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVentaActionPerformed
        ArrayList<Proveedor> Proveedor = new ArrayList();
        Object IdProveedor;
        Object[] llenarProveedor = new Object[6];
        ArrayList<DetalleCompra> Articulos = new ArrayList();
        DetalleCompra detalleCompra = new DetalleCompra();
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Compra compra = new Compra();
        ControladorProducto producto = new ControladorProducto();
        Proveedor proveedor = new Proveedor();
        int Tipocompra = cmbTipoCompra.getSelectedIndex();
        try {
            Proveedor = ControladorProveedor.Buscar(cmbProveedor.getSelectedItem().toString());
            Iterator<Proveedor> prov = Proveedor.iterator();
            IdProveedor = prov.next();                       
            if (tblCompra.getRowCount()>0) {
               String IDprov=IdProveedor.toString();
               int idSucursal = ControladorSucursal.ObtenerIdSucursal(cmbSucursalCompra.getSelectedItem());
                for (int i = 0; i < tblCompra.getRowCount(); i++) {
                    detalleCompra.setCostoUnitario(Double.parseDouble(decimal.format(Double.parseDouble(tblCompra.getValueAt(i, 3).toString()))));
                    detalleCompra.setCantidad(Integer.parseInt(tblCompra.getValueAt(i, 2).toString()));
                    detalleCompra.setPRODUCTO(ControladorProducto.Obtener(tblCompra.getValueAt(i, 0).toString(),idSucursal));
                    Articulos.add(detalleCompra);
                }
                while (prov.hasNext()) {
                    llenarProveedor[0] = prov.next();
                    llenarProveedor[1] = prov.next();
                    llenarProveedor[2] = prov.next();
                    llenarProveedor[3] = prov.next();
                    llenarProveedor[4] = prov.next();
                    llenarProveedor[5] = prov.next();
                    
                    
                    
                }    
                proveedor.setIdProveedor(Integer.parseInt(IdProveedor.toString()));
                proveedor.setNombre(llenarProveedor[0].toString());
                proveedor.setTelefono(llenarProveedor[1].toString());
                proveedor.setDireccion(llenarProveedor[2].toString());
                proveedor.setNIT(llenarProveedor[3].toString());
                proveedor.setEmail(llenarProveedor[4].toString());
                proveedor.setNRC(llenarProveedor[5].toString());

                String total="";

                for (int j = 1; j < txtTotal.getText().length(); j++) {
                    total = total + txtTotal.getText().charAt(j);
                }

                compra.setIdCompra(Integer.parseInt(txtIdCompra.getText()));
                compra.setPROVEEDOR(proveedor);
                switch (Tipocompra) {                    
                    case 0:                        
                        compra.setTipoCompra('C');                        
                        break;
                    case 1:
                        compra.setTipoCompra('F');
                        break;
                    case 2:
                        compra.setTipoCompra('L');
                        break;
                }
                compra.setIdSucursal(ControladorSucursal.ObtenerIdSucursal(cmbSucursalCompra.getSelectedItem()));
                compra.setFecha(formato.format(fechaActual));
                if (Tipocompra==0) {
                    compra.setPercepcion(Double.parseDouble(total)*0.1);
                    compra.setIVA(Double.parseDouble(total)*0.13);
                    compra.setTotal(Double.parseDouble(total)+(Double.parseDouble(total)*0.1)+(Double.parseDouble(total)*0.13));
                }else{
                    compra.setTotal(Double.parseDouble(total));
                }
                compra.setARTICULOS(Articulos);
                compra.setSubTotal(Double.parseDouble(total));
                compra.setNumDocumento(txtNumeroDoc.getText());
                System.out.println(txtNumeroDoc.getText()+"hfadfhgadsd");
                Object [][] detallesCompra;

                int filas = tablaModel.getRowCount();
                detallesCompra = new Object[filas][5];
                for(int x=0;x<filas;x++){
                    detallesCompra[x][0]=tablaModel.getValueAt(x, 0);
                    detallesCompra[x][1]=Integer.parseInt(txtIdCompra.getText());
                    detallesCompra[x][2]=Integer.parseInt(String.valueOf(tablaModel.getValueAt(x, 2)));
                    detallesCompra[x][3]=Double.parseDouble(String.valueOf(tablaModel.getValueAt(x, 3)));
                    detallesCompra[x][4]=ControladorSucursal.ObtenerIdSucursal(cmbSucursalCompra.getSelectedItem());
                }
                ControladorCompra.Agregar(compra,detallesCompra);
                ControladorCompra.ActualizarPrecioPromedioProducto(detallesCompra);
                ControladorCompra.ActualizarInventario(detallesCompra, ControladorSucursal.ObtenerIdSucursal(cmbSucursalCompra.getSelectedItem()));

                
                }
            mensajeNotificacion("Compra Agregada", "Ok");
            int idCompra;
            idCompra = ControladorCompra.ObtenerIdCompra();
            //limpiarCompra();
            txtIdCompra.setText(String.valueOf(idCompra+1));
            txtNumeroDoc.setText(String.valueOf(idCompra+1));
            tablaModel.setNumRows(0);
            txtTotal.setText("$");
            txtIVA.setText("");
            txtPercepcion.setText("");
            cmbSucursalCompra.setEnabled(true);
            
        } catch (ErrorTienda ex) {
            
        }
        
    }//GEN-LAST:event_btnGuardarVentaActionPerformed

    private void tblCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCompraKeyPressed
        char c=evt.getKeyChar();      
        int iteraciones=0;
         
         if (c == (char) KeyEvent.VK_DELETE) {
             eliminarCompras();
             int filas = tablaModel.getRowCount();
            
            double total=0;
            while(iteraciones<filas){
               total+=Double.parseDouble(String.valueOf(tablaModel.getValueAt(iteraciones, 4)));
               iteraciones++;}
            
            double totalFinal=Double.parseDouble(decimal.format(total));
            txtTotal.setText("$"+totalFinal);
            
            
        }
    }//GEN-LAST:event_tblCompraKeyPressed

    private void tblCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCompraKeyTyped

    private void txtNumeroDocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocKeyTyped
        

    }//GEN-LAST:event_txtNumeroDocKeyTyped

    private void txtNomProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomProdKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();

        if (ch == (char) KeyEvent.VK_ENTER) {
            
            txtCantidad.requestFocus();
            txtCantidad.selectAll();

        }
        int c=(int) evt.getKeyChar();
        char mayu=evt.getKeyChar();

        if ((c>=65 && c<=90) || (c>=97 && c<=122)  || (c==32) || (c >=48 && c<=57) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
            if (Character.isLowerCase(mayu)) {
                String cadena=(""+mayu).toUpperCase();
                mayu=cadena.charAt(0);
                evt.setKeyChar(mayu);
            }
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_txtNomProdKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        s = evt.getKeyChar();
        if (!Character.isDigit(s)) {
            getToolkit().beep();
            evt.consume();

        }
        if (s == KeyEvent.VK_ENTER && Integer.parseInt(txtCantidad.getText())>0) {

            txtCostoProd.requestFocus();
        }

    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCostoProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoProdKeyTyped
        // TODO add your handling code here:

        s = evt.getKeyChar();
        int p=(int) evt.getKeyChar();

        // double actualizarPrecio = ((((CantidadActual)*(PrecioActual))+((detalleCompra.get(i).getCantidad())*(detalleCompra.get(i).getCostoUnitario())))/((detalleCompra.get(i).getCantidad())+CantidadActual));
        if (!Character.isDigit(s) && s != KeyEvent.VK_PERIOD) {
            getToolkit().beep();
            evt.consume();
        }else{
            if (p==46) {
                String cadena=txtCostoProd.getText();
                int tamanio=cadena.length();
                for (int i = 0; i <= tamanio; i++) {
                    if (cadena.contains(".")) {
                        evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                        getToolkit().beep();
                        evt.consume();
                    }
                }
            }
        }
        if (s == KeyEvent.VK_ENTER) {
           if (txtCodBarraProd1.getText().equals("")||txtNomProd.getText().equals("")||txtCostoProd.getText().equals("")||txtCantidad.getText().equals("")) {
                mensajeNotificacion("Debe de rellenar todos los campos.", "Error");
            }else{
                if(Double.parseDouble(txtCostoProd.getText()) > 0){
                    if (exprod==false){
                        try {
                            AgregarProductoCompras();
                        } catch (ErrorTienda ex) {
                            
                        }
                    }else{
                       AgregarProductoTablaCompras();
                    }
                    cmbTipoCompra.setEnabled(false);
                }else{
                    mensajeNotificacion("El Costo debe ser mayor a 0.", "Error");
                }
            } 
        }

    }//GEN-LAST:event_txtCostoProdKeyTyped

    private void btnCancelarVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVentaMouseEntered
        btnCancelarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/cancelarB.png")));
    }//GEN-LAST:event_btnCancelarVentaMouseEntered

    private void btnCancelarVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVentaMouseExited
        btnCancelarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/cancelar.png")));
    }//GEN-LAST:event_btnCancelarVentaMouseExited

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed

        txtNumeroDoc.setText("");
        txtNomProd.setText("");
        txtCantidad.setText("1");
        txtCostoProd.setText("");

    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_homeMouseClicked

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        if(estadoMenu==true){
            Animacion.Animacion.subir(55, -316, 1, 2, jpnMenu);
            estadoMenu=false;
            Border empty;
            empty = BorderFactory.createEmptyBorder();
            menu.setBorder(empty);
            menu.setText("Menu");
        }else{
        Animacion.Animacion.bajar(-316, 55, 1, 2, jpnMenu);
        estadoMenu=true;
        Border raisedbevel;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        menu.setBorder(raisedbevel);
        menu.setText("Cerrar");
        }
    }//GEN-LAST:event_menuMouseClicked

    private void menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseExited

    }//GEN-LAST:event_menuMouseExited

    private void jpnMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMenuMouseExited

    }//GEN-LAST:event_jpnMenuMouseExited

    private void lblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMouseClicked
        frmProductos pd = new frmProductos();
        pd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblProductosMouseClicked

    private void lblProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductosMouseEntered
  
    }//GEN-LAST:event_lblProductosMouseEntered

    private void lblSucursalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSucursalesMouseClicked
        frmSucursales sc = new frmSucursales();
        sc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblSucursalesMouseClicked

    private void lblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentasMouseClicked
        try {
            frmVentas vt = new frmVentas();
            vt.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVentasMouseClicked

    private void lblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedoresMouseClicked
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblProveedoresMouseClicked

    private void cmbTipoCompraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCompraItemStateChanged
        if(cmbTipoCompra.getSelectedIndex()==0){
            lblnumdoc.setVisible(true);
            lblIVA.setVisible(true);
            txtNumeroDoc.setVisible(true);
            txtIVA.setVisible(true);
            lblPercepcion.setVisible(true);
            txtPercepcion.setVisible(true);
        }else{
            lblnumdoc.setVisible(false);
            txtNumeroDoc.setVisible(false);
            lblIVA.setVisible(false);
            txtIVA.setVisible(false);
            lblPercepcion.setVisible(false);
            txtPercepcion.setVisible(false);
        }
    }//GEN-LAST:event_cmbTipoCompraItemStateChanged

    private void lblParametroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParametroMouseClicked
        frmParametroModificar pt = new frmParametroModificar();
        pt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblParametroMouseClicked

    private void lblComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComprasMouseClicked
        frmCompras cm = new frmCompras();
        cm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblComprasMouseClicked

    private void lblBotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrarMouseClicked

    private void btnDetallesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseEntered
        btnDetalles.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/detalles2B.png")));
    }//GEN-LAST:event_btnDetallesMouseEntered

    private void btnDetallesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseExited
        btnDetalles.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/detalles2.png")));
    }//GEN-LAST:event_btnDetallesMouseExited

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        frmComprasDetalle cd = new frmComprasDetalle();
        cd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void txtCodBarraProd1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodBarraProd1KeyTyped
        int idSucursal=0;
        char ch = evt.getKeyChar();
        if(txtCodBarraProd1.getText().length()>=13){
           evt.consume();
        }else{
            if (ch < '0' || ch > '9') {

                if (ch != (char) KeyEvent.VK_BEGIN) {
                    if (ch != (char) KeyEvent.VK_BACK_SPACE) {
                        if (ch != (char) KeyEvent.VK_DELETE) {
                            if(ch != (char) KeyEvent.VK_ENTER){

                                evt.consume();
                                mensajeNotificacion("¡Error! Solo números.", "Error");

                            }
                        }
                    }
                }
            }   
        }
        char c = evt.getKeyChar();               
        if (c == (char) KeyEvent.VK_ENTER) {
            String codBarra=txtCodBarraProd1.getText();
            String producto;

            try {
                if (codBarra.equals("")) {
                    mensajeNotificacion("¡Ingrese un código de barras!", "Error");
                } else {
                    idSucursal = ControladorSucursal.ObtenerIdSucursal(cmbSucursalCompra.getSelectedItem());
                    ControladorProducto.Obtener(codBarra,idSucursal);
                    producto= ControladorProducto.Obtener(codBarra,1).getNombre();
                    //PARA SABER SI EXISTE O NO EXISTE UN PRODUCTO
                    if (producto==null || producto=="") {
                        txtCodBarraProd1.setEditable(true);
                        txtCodBarraProd1.requestFocus();                          
                        mensajeNotificacion("Ese producto no está, ¡Agregue!", "Adv");
                        exprod=false;
                    } else {
                        txtNomProd.setText(producto);
                        txtCantidad.requestFocus();
                        txtCantidad.selectAll();
                        exprod=true;
                    }
                }
            } catch (ErrorTienda ex) {

            }
                
        }
    }//GEN-LAST:event_txtCodBarraProd1KeyTyped

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
            java.util.logging.Logger.getLogger(frmCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox<String> cmbSucursalCompra;
    private javax.swing.JComboBox<String> cmbTipoCompra;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnAgregarCompra;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodBarraProd;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblCostoProd;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblIdCompra;
    private javax.swing.JLabel lblNomProd;
    private javax.swing.JLabel lblParametro;
    private javax.swing.JLabel lblPercepcion;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblSucursales;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JLabel lblnumdoc;
    private javax.swing.JLabel menu;
    private javax.swing.JTable tblCompra;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodBarraProd1;
    private javax.swing.JTextField txtCostoProd;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtIdCompra;
    private javax.swing.JTextField txtNomProd;
    private javax.swing.JTextField txtNumeroDoc;
    private javax.swing.JTextField txtPercepcion;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

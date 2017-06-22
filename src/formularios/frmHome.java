/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Conexion;
import clases.ErrorTienda;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ricky
 */
public final class frmHome extends javax.swing.JFrame {
    
    int x,y;
    boolean apagado;
    boolean ventas, compras, productos, proveedores, sucursales, inventario, parametro;

    public frmHome()  {
        initComponents();
        apagado2();
        Principal(true);
        Compras(false);
        Ventas(false);
        Productos(false);
        Proveedores(false);
        Sucursales(false);
        Parametro(false);     
        
    }

/*  ---- Visualización de imágenes en Menú ----  */
    public void Principal(boolean estado){
        jpnPrimero.setVisible(estado);
    }
    public void Compras(boolean estado){
        jpnSegundo.setVisible(estado);
    }
    public void Ventas(boolean estado){
        jpnTercero.setVisible(estado);
    }
    public void Productos(boolean estado){
        jpnCuarto.setVisible(estado);
    }
    public void Proveedores(boolean estado){
        jpnQuinto.setVisible(estado);
    }
    public void Sucursales(boolean estado){
        jpnSeptimo.setVisible(estado);
    }
    public void Parametro(boolean estado){
        jpnOctavo.setVisible(estado);
    }
    public void apagado(){
        apagado = true;
        jpnPrincipal.setVisible(false);  
    }
    public void apagado2(){
        pnlPortada.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnBarraSuperior = new javax.swing.JPanel();
        lblBotonCerrar = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jpnBarraMenu = new javax.swing.JPanel();
        lblMenu = new javax.swing.JLabel();
        jpnSubMenu = new javax.swing.JPanel();
        btnCompras = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnParametro = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnSucursales = new javax.swing.JButton();
        btnHome = new javax.swing.JLabel();
        jpnPrincipal = new javax.swing.JPanel();
        jpnPrimero = new javax.swing.JPanel();
        pnlPortada = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lblMitad = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        jpnSegundo = new javax.swing.JPanel();
        lblMitad2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl15 = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        lbl12 = new javax.swing.JLabel();
        lbl18 = new javax.swing.JLabel();
        lbl17 = new javax.swing.JLabel();
        lbl13 = new javax.swing.JLabel();
        lbl16 = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        jpnTercero = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbl19 = new javax.swing.JLabel();
        lbl20 = new javax.swing.JLabel();
        lbl26 = new javax.swing.JLabel();
        lbl27 = new javax.swing.JLabel();
        lbl28 = new javax.swing.JLabel();
        lbl29 = new javax.swing.JLabel();
        lbl30 = new javax.swing.JLabel();
        lbl36 = new javax.swing.JLabel();
        lblMitad3 = new javax.swing.JLabel();
        jpnCuarto = new javax.swing.JPanel();
        lblMitad4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl21 = new javax.swing.JLabel();
        lbl22 = new javax.swing.JLabel();
        lbl23 = new javax.swing.JLabel();
        lbl24 = new javax.swing.JLabel();
        lbl25 = new javax.swing.JLabel();
        lbl37 = new javax.swing.JLabel();
        lbl38 = new javax.swing.JLabel();
        lbl39 = new javax.swing.JLabel();
        jpnQuinto = new javax.swing.JPanel();
        lblMitad5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbl31 = new javax.swing.JLabel();
        lbl32 = new javax.swing.JLabel();
        lbl33 = new javax.swing.JLabel();
        lbl34 = new javax.swing.JLabel();
        lbl35 = new javax.swing.JLabel();
        lbl40 = new javax.swing.JLabel();
        lbl46 = new javax.swing.JLabel();
        lbl47 = new javax.swing.JLabel();
        jpnSeptimo = new javax.swing.JPanel();
        lblMitad7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbl51 = new javax.swing.JLabel();
        lbl52 = new javax.swing.JLabel();
        lbl53 = new javax.swing.JLabel();
        lbl54 = new javax.swing.JLabel();
        lbl55 = new javax.swing.JLabel();
        lbl56 = new javax.swing.JLabel();
        lbl57 = new javax.swing.JLabel();
        lbl58 = new javax.swing.JLabel();
        jpnOctavo = new javax.swing.JPanel();
        lblMitad8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbl64 = new javax.swing.JLabel();
        lbl65 = new javax.swing.JLabel();
        lbl66 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnBarraSuperior.setBackground(new java.awt.Color(0, 0, 0));
        jpnBarraSuperior.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
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
        jpnBarraSuperior.add(lblBotonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 30, 50));

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        lblLogo.setText("iShop 2.0");
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

        lblMenu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Menu.png"))); // NOI18N
        lblMenu.setText("Menu");
        jpnBarraMenu.add(lblMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 15, 170, 50));

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
        jpnSubMenu.add(btnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 20, 180, 40));

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
        jpnSubMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 70, 180, 40));

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
        jpnSubMenu.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 120, 180, 40));

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
        jpnSubMenu.add(btnParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 270, 180, 40));

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
        jpnSubMenu.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 170, 180, 40));

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
        jpnSubMenu.add(btnSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 220, 180, 40));

        jpnBarraMenu.add(jpnSubMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 77, 190, 330));

        btnHome.setToolTipText("Inicio");
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnBarraMenu.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        getContentPane().add(jpnBarraMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 650));

        jpnPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpnPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnPrimero.setBackground(new java.awt.Color(0, 0, 0));
        jpnPrimero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPortada.setBackground(new java.awt.Color(0, 0, 0));
        pnlPortada.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Desarrolladores", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlPortada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlPortada.add(lbl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 50, 50));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Gil Menjívar, Sergio Daniel");
        pnlPortada.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 250, -1));

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Alegría Arévalo, Ismael Enrique");
        pnlPortada.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, -1));

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Baños Lobos, Pedro Javier");
        pnlPortada.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, -1));

        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Barrientos Hernández, Ricardo Alberto");
        pnlPortada.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 250, -1));

        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setText("García López, José Armando");
        pnlPortada.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 250, -1));

        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("García Rodríguez, Oscar Arnoldo");
        pnlPortada.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 250, -1));

        jpnPrimero.add(pnlPortada, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 350, 160));

        lbl4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl4.setForeground(new java.awt.Color(255, 255, 255));
        lbl4.setText("iShop");
        jpnPrimero.add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 50, -1));

        lbl7.setBackground(new java.awt.Color(153, 153, 153));
        lbl7.setForeground(new java.awt.Color(102, 102, 102));
        lbl7.setText("Versión 2.0");
        lbl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl7MouseExited(evt);
            }
        });
        jpnPrimero.add(lbl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 70, -1));

        lbl5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl5.setForeground(new java.awt.Color(102, 102, 102));
        lbl5.setText("Te damos la bienvenida a tu");
        jpnPrimero.add(lbl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, -1, -1));

        lbl6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl6.setForeground(new java.awt.Color(102, 102, 102));
        lbl6.setText("nuevo sistema de Tienda.");
        jpnPrimero.add(lbl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, -1, 30));

        lblMitad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad6.jpg"))); // NOI18N
        jpnPrimero.add(lblMitad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 515, 640));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        jpnPrimero.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, -1, -1));
        jpnPrimero.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, -1));

        jpnPrincipal.add(jpnPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1070, 650));

        jpnSegundo.setBackground(new java.awt.Color(0, 0, 0));
        jpnSegundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad2.jpg"))); // NOI18N
        jpnSegundo.add(lblMitad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 515, 640));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl15.setForeground(new java.awt.Color(102, 102, 102));
        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setText("el sistema de Compras.");
        jPanel1.add(lbl15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl14.setForeground(new java.awt.Color(102, 102, 102));
        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setText("Usa esta opción para manejar");
        jPanel1.add(lbl14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl12.setForeground(new java.awt.Color(102, 102, 102));
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setText("Factura o Libre.");
        jPanel1.add(lbl12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl18.setForeground(new java.awt.Color(102, 102, 102));
        lbl18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl18.setText("tipos de venta: Crédito fiscal,");
        jPanel1.add(lbl18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl17.setForeground(new java.awt.Color(102, 102, 102));
        lbl17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl17.setText("Puedes escoger entre distintos");
        jPanel1.add(lbl17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl13.setForeground(new java.awt.Color(102, 102, 102));
        lbl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl13.setText("abastecer tu Tienda.");
        jPanel1.add(lbl13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl16.setForeground(new java.awt.Color(102, 102, 102));
        lbl16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl16.setText("Podrás realizar compras y ");
        jPanel1.add(lbl16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl11.setForeground(new java.awt.Color(255, 255, 255));
        lbl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Compras.png"))); // NOI18N
        lbl11.setText("Compras");
        jPanel1.add(lbl11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnSegundo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 210, 230));

        jpnPrincipal.add(jpnSegundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        jpnTercero.setBackground(new java.awt.Color(0, 0, 0));
        jpnTercero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl19.setForeground(new java.awt.Color(102, 102, 102));
        lbl19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl19.setText("el sistema de Ventas.");
        jPanel2.add(lbl19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl20.setForeground(new java.awt.Color(102, 102, 102));
        lbl20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl20.setText("Usa esta opción para manejar");
        jPanel2.add(lbl20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl26.setForeground(new java.awt.Color(102, 102, 102));
        lbl26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl26.setText("o Factura.");
        jPanel2.add(lbl26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl27.setForeground(new java.awt.Color(102, 102, 102));
        lbl27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl27.setText("tipos de venta: Crédito fiscal,");
        jPanel2.add(lbl27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl28.setForeground(new java.awt.Color(102, 102, 102));
        lbl28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl28.setText("Puedes escoger entre distintos");
        jPanel2.add(lbl28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl29.setForeground(new java.awt.Color(102, 102, 102));
        lbl29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl29.setText("que obtiene tu tienda.");
        jPanel2.add(lbl29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl30.setForeground(new java.awt.Color(102, 102, 102));
        lbl30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl30.setText("Podrás manejar los ingresos");
        jPanel2.add(lbl30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl36.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl36.setForeground(new java.awt.Color(255, 255, 255));
        lbl36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Ventas.png"))); // NOI18N
        lbl36.setText("Ventas");
        jPanel2.add(lbl36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnTercero.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 210, 230));

        lblMitad3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad3.jpg"))); // NOI18N
        lblMitad3.setText("jLabel2");
        jpnTercero.add(lblMitad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 515, 640));

        jpnPrincipal.add(jpnTercero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        jpnCuarto.setBackground(new java.awt.Color(0, 0, 0));
        jpnCuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad4.jpg"))); // NOI18N
        jpnCuarto.add(lblMitad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 515, 640));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl21.setForeground(new java.awt.Color(102, 102, 102));
        lbl21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl21.setText("agregar o eliminar Productos.");
        jPanel3.add(lbl21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl22.setForeground(new java.awt.Color(102, 102, 102));
        lbl22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl22.setText("Usa esta opción para modificar,");
        jPanel3.add(lbl22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl23.setForeground(new java.awt.Color(102, 102, 102));
        lbl23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl23.setText("de barra.");
        jPanel3.add(lbl23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl24.setForeground(new java.awt.Color(102, 102, 102));
        lbl24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl24.setText("agregados mediante un código");
        jPanel3.add(lbl24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl25.setForeground(new java.awt.Color(102, 102, 102));
        lbl25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl25.setText("Los productos que quieres serán");
        jPanel3.add(lbl25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl37.setForeground(new java.awt.Color(102, 102, 102));
        lbl37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl37.setText("de tu sistema de Tienda.");
        jPanel3.add(lbl37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl38.setForeground(new java.awt.Color(102, 102, 102));
        lbl38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl38.setText("Podrás manejar los productos ");
        jPanel3.add(lbl38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl39.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl39.setForeground(new java.awt.Color(255, 255, 255));
        lbl39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Productos.png"))); // NOI18N
        lbl39.setText("Productos");
        jPanel3.add(lbl39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnCuarto.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 210, 230));

        jpnPrincipal.add(jpnCuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        jpnQuinto.setBackground(new java.awt.Color(0, 0, 0));
        jpnQuinto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad5.jpg"))); // NOI18N
        jpnQuinto.add(lblMitad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 515, 640));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl31.setForeground(new java.awt.Color(102, 102, 102));
        lbl31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl31.setText("el sistema de Proveedores.");
        jPanel4.add(lbl31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl32.setForeground(new java.awt.Color(102, 102, 102));
        lbl32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl32.setText("Usa esta opción para manejar");
        jPanel4.add(lbl32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl33.setForeground(new java.awt.Color(102, 102, 102));
        lbl33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl33.setText("tu tienda.");
        jPanel4.add(lbl33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl34.setForeground(new java.awt.Color(102, 102, 102));
        lbl34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl34.setText("más abastecida podrá estar");
        jPanel4.add(lbl34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl35.setForeground(new java.awt.Color(102, 102, 102));
        lbl35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl35.setText("Entre más proveedores tengas");
        jPanel4.add(lbl35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl40.setForeground(new java.awt.Color(102, 102, 102));
        lbl40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl40.setText("¡Agrega a proveedores!");
        jPanel4.add(lbl40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl46.setForeground(new java.awt.Color(102, 102, 102));
        lbl46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl46.setText("¿Deseas más productos?");
        jPanel4.add(lbl46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl47.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl47.setForeground(new java.awt.Color(255, 255, 255));
        lbl47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Proveedores.png"))); // NOI18N
        lbl47.setText("Proveedores");
        jPanel4.add(lbl47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnQuinto.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 210, 230));

        jpnPrincipal.add(jpnQuinto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        jpnSeptimo.setBackground(new java.awt.Color(0, 0, 0));
        jpnSeptimo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad8.jpg"))); // NOI18N
        jpnSeptimo.add(lblMitad7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 515, 640));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl51.setForeground(new java.awt.Color(102, 102, 102));
        lbl51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl51.setText("el sistema de Sucursales.");
        jPanel6.add(lbl51, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl52.setForeground(new java.awt.Color(102, 102, 102));
        lbl52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl52.setText("Usa esta opción para manejar");
        jPanel6.add(lbl52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl53.setForeground(new java.awt.Color(102, 102, 102));
        lbl53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl53.setText("para tu tienda.");
        jPanel6.add(lbl53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl54.setForeground(new java.awt.Color(102, 102, 102));
        lbl54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl54.setText("agregando nuevas Sucursales");
        jPanel6.add(lbl54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl55.setForeground(new java.awt.Color(102, 102, 102));
        lbl55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl55.setText("Llega a distintas partes ");
        jPanel6.add(lbl55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl56.setForeground(new java.awt.Color(102, 102, 102));
        lbl56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl56.setText("seguimos siendo la misma.");
        jPanel6.add(lbl56, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl57.setForeground(new java.awt.Color(102, 102, 102));
        lbl57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl57.setText("Somos una red, y a su vez,");
        jPanel6.add(lbl57, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl58.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl58.setForeground(new java.awt.Color(255, 255, 255));
        lbl58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Sucursales.png"))); // NOI18N
        lbl58.setText("Sucursales");
        jPanel6.add(lbl58, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnSeptimo.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 210, 230));

        jpnPrincipal.add(jpnSeptimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        jpnOctavo.setBackground(new java.awt.Color(0, 0, 0));
        jpnOctavo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad7.JPG"))); // NOI18N
        jpnOctavo.add(lblMitad8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 515, 640));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl64.setForeground(new java.awt.Color(102, 102, 102));
        lbl64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl64.setText("a ser utilizado en la tienda.");
        jPanel7.add(lbl64, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl65.setForeground(new java.awt.Color(102, 102, 102));
        lbl65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl65.setText("Modifica el parámetro ");
        jPanel7.add(lbl65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl66.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl66.setForeground(new java.awt.Color(255, 255, 255));
        lbl66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Parametro.png"))); // NOI18N
        lbl66.setText("Parámetro");
        jPanel7.add(lbl66, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnOctavo.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 210, 230));

        jpnPrincipal.add(jpnOctavo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        getContentPane().add(jpnPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 1030, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblBotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrarMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void jpnBarraMenuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpnBarraMenuPropertyChange
        
    }//GEN-LAST:event_jpnBarraMenuPropertyChange

    private void lbl7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl7MouseEntered
        pnlPortada.setVisible(true);
    }//GEN-LAST:event_lbl7MouseEntered

    private void lbl7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl7MouseExited
        pnlPortada.setVisible(false);
    }//GEN-LAST:event_lbl7MouseExited

    private void btnComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);
        apagado2();
        frmCompras cm = new frmCompras();
        cm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnComprasMouseClicked

    private void btnComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseEntered
        /*  ---- Animación compras, mover ----  */
        if(!compras)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);
        Principal(false);
        Compras(true);
    }//GEN-LAST:event_btnComprasMouseEntered

    private void btnComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseExited
        /*  ---- Animación compras, volver posición anterior ----  */
        if(!compras)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnCompras);
        Principal(true);
        Compras(false);
    }//GEN-LAST:event_btnComprasMouseExited

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed

    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked
        try {
            apagado();
            apagado2();
            frmVentas vt = new frmVentas();
            vt.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentasMouseClicked

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
        if(!ventas)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnVentas);
        Principal(false);
        Ventas(true);
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseExited
        if(!ventas)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnVentas);
        Principal(true);
        Ventas(false);
    }//GEN-LAST:event_btnVentasMouseExited

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed

    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);
        apagado2();
        frmProductos pd = new frmProductos();
        pd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseEntered
        if(!productos)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);
        Principal(false);
        Productos(true);
    }//GEN-LAST:event_btnProductosMouseEntered

    private void btnProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseExited
        if(!productos)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProductos);
        Principal(true);
        Productos(false);
    }//GEN-LAST:event_btnProductosMouseExited

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed


    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnParametroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnParametro);
        apagado2();
        frmParametro pr = new frmParametro();
        pr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnParametroMouseClicked

    private void btnParametroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseEntered
        if(!parametro)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnParametro);
        Principal(false);
        Parametro(true);
    }//GEN-LAST:event_btnParametroMouseEntered

    private void btnParametroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnParametroMouseExited
        if(!parametro)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnParametro);
        Principal(true);
        Parametro(false);
    }//GEN-LAST:event_btnParametroMouseExited

    private void btnProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);
        apagado2();
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnProveedoresMouseClicked

    private void btnProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseEntered
        if(!proveedores)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);
        Principal(false);
        Proveedores(true);
    }//GEN-LAST:event_btnProveedoresMouseEntered

    private void btnProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseExited
        if(!proveedores)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProveedores);
        Principal(true);
        Proveedores(false);
    }//GEN-LAST:event_btnProveedoresMouseExited

    private void btnSucursalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseClicked
        frmSucursales su = new frmSucursales(); 
        su.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSucursalesMouseClicked

    private void btnSucursalesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseEntered
        if(!sucursales)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnSucursales);
        Principal(false);
        Sucursales(true);
    }//GEN-LAST:event_btnSucursalesMouseEntered

    private void btnSucursalesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSucursalesMouseExited
        if(!sucursales)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnSucursales);
        Principal(true);
        Sucursales(false);
    }//GEN-LAST:event_btnSucursalesMouseExited

    private void btnParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnParametroActionPerformed

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
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompras;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCuarto;
    private javax.swing.JPanel jpnOctavo;
    private javax.swing.JPanel jpnPrimero;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPanel jpnQuinto;
    private javax.swing.JPanel jpnSegundo;
    private javax.swing.JPanel jpnSeptimo;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnTercero;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl17;
    private javax.swing.JLabel lbl18;
    private javax.swing.JLabel lbl19;
    private javax.swing.JLabel lbl20;
    private javax.swing.JLabel lbl21;
    private javax.swing.JLabel lbl22;
    private javax.swing.JLabel lbl23;
    private javax.swing.JLabel lbl24;
    private javax.swing.JLabel lbl25;
    private javax.swing.JLabel lbl26;
    private javax.swing.JLabel lbl27;
    private javax.swing.JLabel lbl28;
    private javax.swing.JLabel lbl29;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl30;
    private javax.swing.JLabel lbl31;
    private javax.swing.JLabel lbl32;
    private javax.swing.JLabel lbl33;
    private javax.swing.JLabel lbl34;
    private javax.swing.JLabel lbl35;
    private javax.swing.JLabel lbl36;
    private javax.swing.JLabel lbl37;
    private javax.swing.JLabel lbl38;
    private javax.swing.JLabel lbl39;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl40;
    private javax.swing.JLabel lbl46;
    private javax.swing.JLabel lbl47;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl51;
    private javax.swing.JLabel lbl52;
    private javax.swing.JLabel lbl53;
    private javax.swing.JLabel lbl54;
    private javax.swing.JLabel lbl55;
    private javax.swing.JLabel lbl56;
    private javax.swing.JLabel lbl57;
    private javax.swing.JLabel lbl58;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl64;
    private javax.swing.JLabel lbl65;
    private javax.swing.JLabel lbl66;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMitad;
    private javax.swing.JLabel lblMitad2;
    private javax.swing.JLabel lblMitad3;
    private javax.swing.JLabel lblMitad4;
    private javax.swing.JLabel lblMitad5;
    private javax.swing.JLabel lblMitad7;
    private javax.swing.JLabel lblMitad8;
    private javax.swing.JPanel pnlPortada;
    // End of variables declaration//GEN-END:variables
}

/*
 **    **  ******  **      **  ******
 **    **  **  **  ****  ****  **
 ********  **  **  **  **  **  ****
 **    **  **  **  **      **  **
 **    **  ******  **      **  ******
 */
package formularios;

import clases.ControladorUsuario;
import clases.ErrorTienda;
import clases.Usuario;
import facadeshop.Diseño;
import static formularios.frmLogin.txtUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


/**
 *
 * @author Ricky
 */

public final class frmHome extends javax.swing.JFrame {
    
    int x,y;
    String nombres, apellidos, rol, password;
    boolean apagado;
    boolean ventas, compras, productos, proveedores, sucursales, inventario, parametro, tipoPrecio, detalleCompra, detalleVenta;

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
        TipoPrecio(false);
        DetalleCompra(false);
        DetalleVenta(false);
        jpnUser.setVisible(false);
        jpnWhite.setVisible(false);
        lblUser.setText(Diseño.user);
        lblUser1.setText(Diseño.user);
        jpnPass.setVisible(false);
    }
    public void obtenerRol(){
        try {
            rol = ControladorUsuario.obtenerRol(txtUser.getText());
            if(rol.equals("A"))
                lblRolUsuario.setText("ADMINISTRADOR");
            else if(rol.equals("V"))
                lblRolUsuario.setText("VENDEDOR");
            else if(rol.equals("C"))
                lblRolUsuario.setText("COMPRADOR");
            
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        public void mensajeNotificacion(String mensaje, String tipo){
        if(tipo.equals("Error")){
        frmNotificacion not = new frmNotificacion();
        not.Mensaje(mensaje);
        not.setVisible(true);
        not.lblIcono.setIcon(new ImageIcon(getClass().getResource("/iconos/Error.png")));
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
            Animacion.Animacion.subir(0, -185, 1, 2, jpnPass);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    public void TipoPrecio(boolean estado){
        jpnNoveno.setVisible(estado);
    }
    public void DetalleCompra(boolean estado){
        //jpnNoveno.setVisible(estado);
    }
    public void DetalleVenta(boolean estado){
        //jpnNoveno.setVisible(estado);
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
        jpnWhite = new javax.swing.JPanel();
        lblUser1 = new javax.swing.JLabel();
        lblBotonCerrar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblAgregarUsuario = new javax.swing.JLabel();
        jpnBarraMenu = new javax.swing.JPanel();
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
        btnHome = new javax.swing.JLabel();
        jpnPrincipal = new javax.swing.JPanel();
        jpnPrimero = new javax.swing.JPanel();
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
        jSeparator3 = new javax.swing.JSeparator();
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
        jpnUser = new javax.swing.JPanel();
        lblRolUsuario = new javax.swing.JLabel();
        lblCambiarPwd = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
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
        jpnNoveno = new javax.swing.JPanel();
        lblMitad6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbl43 = new javax.swing.JLabel();
        lbl44 = new javax.swing.JLabel();
        lbl45 = new javax.swing.JLabel();
        lbl48 = new javax.swing.JLabel();
        lbl49 = new javax.swing.JLabel();
        lbl50 = new javax.swing.JLabel();

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

        lblMenu.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

        getContentPane().add(jpnBarraMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 650));

        jpnPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpnPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnPrimero.setBackground(new java.awt.Color(0, 0, 0));
        jpnPrimero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jSeparator3.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 95, 200, 10));

        jpnPrimero.add(jpnPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, -185, 230, 190));

        pnlPortada.setBackground(new java.awt.Color(0, 0, 0));
        pnlPortada.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Desarrolladores", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Light", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlPortada.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        pnlPortada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlPortada.add(lbl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 50, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Gil Menjívar, Sergio Daniel");
        pnlPortada.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 250, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Alegría Arévalo, Ismael Enrique");
        pnlPortada.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Baños Lobos, Pedro Javier");
        pnlPortada.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Barrientos Hernández, Ricardo Alberto");
        pnlPortada.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 250, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setText("García López, José Armando");
        pnlPortada.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 250, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("García Rodríguez, Oscar Arnoldo");
        pnlPortada.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 250, -1));

        jpnPrimero.add(pnlPortada, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 350, 160));

        lbl4.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lbl4.setForeground(new java.awt.Color(255, 255, 255));
        lbl4.setText("iShop");
        jpnPrimero.add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 50, -1));

        lbl7.setBackground(new java.awt.Color(153, 153, 153));
        lbl7.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        lbl7.setForeground(new java.awt.Color(102, 102, 102));
        lbl7.setText("Versión 3.0");
        lbl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl7MouseExited(evt);
            }
        });
        jpnPrimero.add(lbl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 70, -1));

        lbl5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl5.setForeground(new java.awt.Color(102, 102, 102));
        lbl5.setText("Te damos la bienvenida a tu");
        jpnPrimero.add(lbl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, -1, -1));

        lbl6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl6.setForeground(new java.awt.Color(102, 102, 102));
        lbl6.setText("nuevo sistema de Tienda.");
        jpnPrimero.add(lbl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, -1, 30));

        lblMitad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad6.jpg"))); // NOI18N
        jpnPrimero.add(lblMitad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 515, 640));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        jpnPrimero.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, -1, -1));
        jpnPrimero.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, -1));

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

        jpnPrimero.add(jpnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 230, 110));

        jpnPrincipal.add(jpnPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1070, 650));

        jpnSegundo.setBackground(new java.awt.Color(0, 0, 0));
        jpnSegundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad2.jpg"))); // NOI18N
        jpnSegundo.add(lblMitad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 515, 640));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl15.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl15.setForeground(new java.awt.Color(102, 102, 102));
        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setText("el sistema de Compras.");
        jPanel1.add(lbl15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl14.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl14.setForeground(new java.awt.Color(102, 102, 102));
        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setText("Usa esta opción para manejar");
        jPanel1.add(lbl14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl12.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl12.setForeground(new java.awt.Color(102, 102, 102));
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setText("Factura o Libre.");
        jPanel1.add(lbl12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl18.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl18.setForeground(new java.awt.Color(102, 102, 102));
        lbl18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl18.setText("tipos de venta: Crédito fiscal,");
        jPanel1.add(lbl18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl17.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl17.setForeground(new java.awt.Color(102, 102, 102));
        lbl17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl17.setText("Puedes escoger entre distintos");
        jPanel1.add(lbl17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl13.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl13.setForeground(new java.awt.Color(102, 102, 102));
        lbl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl13.setText("abastecer tu Tienda.");
        jPanel1.add(lbl13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl16.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl16.setForeground(new java.awt.Color(102, 102, 102));
        lbl16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl16.setText("Podrás realizar compras y ");
        jPanel1.add(lbl16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl11.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

        lbl19.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl19.setForeground(new java.awt.Color(102, 102, 102));
        lbl19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl19.setText("el sistema de Ventas.");
        jPanel2.add(lbl19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl20.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl20.setForeground(new java.awt.Color(102, 102, 102));
        lbl20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl20.setText("Usa esta opción para manejar");
        jPanel2.add(lbl20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl26.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl26.setForeground(new java.awt.Color(102, 102, 102));
        lbl26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl26.setText("o Factura.");
        jPanel2.add(lbl26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl27.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl27.setForeground(new java.awt.Color(102, 102, 102));
        lbl27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl27.setText("tipos de venta: Crédito fiscal,");
        jPanel2.add(lbl27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl28.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl28.setForeground(new java.awt.Color(102, 102, 102));
        lbl28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl28.setText("Puedes escoger entre distintos");
        jPanel2.add(lbl28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl29.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl29.setForeground(new java.awt.Color(102, 102, 102));
        lbl29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl29.setText("que obtiene tu tienda.");
        jPanel2.add(lbl29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl30.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl30.setForeground(new java.awt.Color(102, 102, 102));
        lbl30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl30.setText("Podrás manejar los ingresos");
        jPanel2.add(lbl30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl36.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

        lbl21.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl21.setForeground(new java.awt.Color(102, 102, 102));
        lbl21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl21.setText("agregar o eliminar Productos.");
        jPanel3.add(lbl21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl22.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl22.setForeground(new java.awt.Color(102, 102, 102));
        lbl22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl22.setText("Usa esta opción para modificar,");
        jPanel3.add(lbl22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl23.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl23.setForeground(new java.awt.Color(102, 102, 102));
        lbl23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl23.setText("de barra.");
        jPanel3.add(lbl23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl24.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl24.setForeground(new java.awt.Color(102, 102, 102));
        lbl24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl24.setText("agregados mediante un código");
        jPanel3.add(lbl24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl25.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl25.setForeground(new java.awt.Color(102, 102, 102));
        lbl25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl25.setText("Los productos que quieres serán");
        jPanel3.add(lbl25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl37.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl37.setForeground(new java.awt.Color(102, 102, 102));
        lbl37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl37.setText("de tu sistema de Tienda.");
        jPanel3.add(lbl37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl38.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl38.setForeground(new java.awt.Color(102, 102, 102));
        lbl38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl38.setText("Podrás manejar los productos ");
        jPanel3.add(lbl38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl39.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

        lbl31.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl31.setForeground(new java.awt.Color(102, 102, 102));
        lbl31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl31.setText("el sistema de Proveedores.");
        jPanel4.add(lbl31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl32.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl32.setForeground(new java.awt.Color(102, 102, 102));
        lbl32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl32.setText("Usa esta opción para manejar");
        jPanel4.add(lbl32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl33.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl33.setForeground(new java.awt.Color(102, 102, 102));
        lbl33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl33.setText("tu tienda.");
        jPanel4.add(lbl33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl34.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl34.setForeground(new java.awt.Color(102, 102, 102));
        lbl34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl34.setText("más abastecida podrá estar");
        jPanel4.add(lbl34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl35.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl35.setForeground(new java.awt.Color(102, 102, 102));
        lbl35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl35.setText("Entre más proveedores tengas");
        jPanel4.add(lbl35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl40.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl40.setForeground(new java.awt.Color(102, 102, 102));
        lbl40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl40.setText("¡Agrega a proveedores!");
        jPanel4.add(lbl40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl46.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl46.setForeground(new java.awt.Color(102, 102, 102));
        lbl46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl46.setText("¿Deseas más productos?");
        jPanel4.add(lbl46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl47.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

        lbl51.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl51.setForeground(new java.awt.Color(102, 102, 102));
        lbl51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl51.setText("el sistema de Sucursales.");
        jPanel6.add(lbl51, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 210, 30));

        lbl52.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl52.setForeground(new java.awt.Color(102, 102, 102));
        lbl52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl52.setText("Usa esta opción para manejar");
        jPanel6.add(lbl52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, -1));

        lbl53.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl53.setForeground(new java.awt.Color(102, 102, 102));
        lbl53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl53.setText("para tu tienda.");
        jPanel6.add(lbl53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl54.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl54.setForeground(new java.awt.Color(102, 102, 102));
        lbl54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl54.setText("agregando nuevas Sucursales");
        jPanel6.add(lbl54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl55.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl55.setForeground(new java.awt.Color(102, 102, 102));
        lbl55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl55.setText("Llega a distintas partes ");
        jPanel6.add(lbl55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl56.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl56.setForeground(new java.awt.Color(102, 102, 102));
        lbl56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl56.setText("seguimos siendo la misma.");
        jPanel6.add(lbl56, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl57.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl57.setForeground(new java.awt.Color(102, 102, 102));
        lbl57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl57.setText("Somos una red, y a su vez,");
        jPanel6.add(lbl57, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl58.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

        lbl64.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl64.setForeground(new java.awt.Color(102, 102, 102));
        lbl64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl64.setText("a ser utilizado en la tienda.");
        jPanel7.add(lbl64, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl65.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl65.setForeground(new java.awt.Color(102, 102, 102));
        lbl65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl65.setText("Modifica el parámetro ");
        jPanel7.add(lbl65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl66.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lbl66.setForeground(new java.awt.Color(255, 255, 255));
        lbl66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Parametro.png"))); // NOI18N
        lbl66.setText("Parámetro");
        jPanel7.add(lbl66, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnOctavo.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 210, 230));

        jpnPrincipal.add(jpnOctavo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

        jpnNoveno.setBackground(new java.awt.Color(0, 0, 0));
        jpnNoveno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMitad6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/mitad9.jpg"))); // NOI18N
        jpnNoveno.add(lblMitad6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 515, 640));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl43.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl43.setForeground(new java.awt.Color(102, 102, 102));
        lbl43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl43.setText("también eliminar.");
        jPanel5.add(lbl43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 210, 30));

        lbl44.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl44.setForeground(new java.awt.Color(102, 102, 102));
        lbl44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl44.setText("la lista de tipo de precios y");
        jPanel5.add(lbl44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, 30));

        lbl45.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl45.setForeground(new java.awt.Color(102, 102, 102));
        lbl45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl45.setText("Podrás modificar valores, ver");
        jPanel5.add(lbl45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, -1));

        lbl48.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl48.setForeground(new java.awt.Color(102, 102, 102));
        lbl48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl48.setText("precio a manejar en la tienda.");
        jPanel5.add(lbl48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        lbl49.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        lbl49.setForeground(new java.awt.Color(102, 102, 102));
        lbl49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl49.setText("Puedes agregar los tipos de");
        jPanel5.add(lbl49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        lbl50.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lbl50.setForeground(new java.awt.Color(255, 255, 255));
        lbl50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/tipoPrecio.png"))); // NOI18N
        lbl50.setText("Tipo Precio");
        jPanel5.add(lbl50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, -1));

        jpnNoveno.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 210, 230));

        jpnPrincipal.add(jpnNoveno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1010, 650));

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

    private void btnTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseClicked
        frmTipoPrecio tp = new frmTipoPrecio(); 
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTipoPrecioMouseClicked

    private void btnTipoPrecioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseEntered
        if(!tipoPrecio)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnTipoPrecio);
        Principal(false);
        TipoPrecio(true);
    }//GEN-LAST:event_btnTipoPrecioMouseEntered

    private void btnTipoPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoPrecioMouseExited
        if(!tipoPrecio)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnTipoPrecio);
        Principal(true);
        TipoPrecio(false);
    }//GEN-LAST:event_btnTipoPrecioMouseExited

    private void btnTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipoPrecioActionPerformed

    private void lblAgregarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarUsuarioMouseClicked
        frmRegistrarUsuario ru = new frmRegistrarUsuario();
        ru.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblAgregarUsuarioMouseClicked

    private void btnDetalleComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleComprasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetalleComprasMouseClicked

    private void btnDetalleComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleComprasMouseEntered
        if(!detalleCompra)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnDetalleCompras);
        //Principal(false);
        //Compras(true);
    }//GEN-LAST:event_btnDetalleComprasMouseEntered

    private void btnDetalleComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleComprasMouseExited
        if(!detalleCompra)
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
        if(!detalleVenta)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnDetalleVentas);
    }//GEN-LAST:event_btnDetalleVentasMouseEntered

    private void btnDetalleVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetalleVentasMouseExited
               if(!detalleVenta)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnDetalleVentas);
    }//GEN-LAST:event_btnDetalleVentasMouseExited

    private void btnDetalleVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleVentasActionPerformed
        frmVentasDetalle vd = new frmVentasDetalle();
        vd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDetalleVentasActionPerformed

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

    private void jpnUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnUserMouseExited
        //jpnUser.setVisible(false);
        //jpnWhite.setVisible(false);
    }//GEN-LAST:event_jpnUserMouseExited

    private void lblUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser1MouseClicked
        jpnWhite.setVisible(false);
        jpnUser.setVisible(false);
        jpnPass.setVisible(false);
    }//GEN-LAST:event_lblUser1MouseClicked

    private void jpnWhiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnWhiteMouseClicked
        jpnWhite.setVisible(false);
        jpnUser.setVisible(false);
    }//GEN-LAST:event_jpnWhiteMouseClicked

    private void lblCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarSesionMouseClicked
        frmLogin lg = new frmLogin();
        lg.setVisible(true);
        this.setVisible(false);
        mensajeNotificacion("¡Has cerrado sesión!", "Error");
    }//GEN-LAST:event_lblCerrarSesionMouseClicked

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

    private void lblCambiarPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarPwdMouseClicked
        jpnPass.setVisible(true);
        Animacion.Animacion.bajar(-185, 0, 1, 2, jpnPass);
        pwdAntigua.requestFocus();
    }//GEN-LAST:event_lblCambiarPwdMouseClicked

    private void btnAtrasPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasPwdMouseClicked
        Animacion.Animacion.subir(0, -185, 1, 2, jpnPass);
    }//GEN-LAST:event_btnAtrasPwdMouseClicked

    private void pwdAntiguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdAntiguaActionPerformed
        pwdNueva.requestFocus();
    }//GEN-LAST:event_pwdAntiguaActionPerformed

    private void pwdNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdNuevaActionPerformed
        pwdNueva2.requestFocus();
    }//GEN-LAST:event_pwdNuevaActionPerformed

    private void pwdAntiguaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdAntiguaFocusGained
        pwdAntigua.selectAll();
    }//GEN-LAST:event_pwdAntiguaFocusGained

    private void pwdNuevaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdNuevaFocusGained
        pwdNueva.selectAll();
    }//GEN-LAST:event_pwdNuevaFocusGained

    private void pwdNueva2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdNueva2FocusGained
        pwdNueva2.selectAll();
    }//GEN-LAST:event_pwdNueva2FocusGained

    private void btnCambiarPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiarPwdMouseClicked
        comprobarPass();
    }//GEN-LAST:event_btnCambiarPwdMouseClicked

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
    private javax.swing.JLabel btnAtrasPwd;
    private javax.swing.JButton btnBitacoras;
    private javax.swing.JLabel btnCambiarPwd;
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
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCuarto;
    private javax.swing.JPanel jpnNoveno;
    private javax.swing.JPanel jpnOctavo;
    private javax.swing.JPanel jpnPass;
    private javax.swing.JPanel jpnPrimero;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPanel jpnQuinto;
    private javax.swing.JPanel jpnSegundo;
    private javax.swing.JPanel jpnSeptimo;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnTercero;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
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
    private javax.swing.JLabel lbl43;
    private javax.swing.JLabel lbl44;
    private javax.swing.JLabel lbl45;
    private javax.swing.JLabel lbl46;
    private javax.swing.JLabel lbl47;
    private javax.swing.JLabel lbl48;
    private javax.swing.JLabel lbl49;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl50;
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
    private javax.swing.JLabel lblAgregarUsuario;
    public static javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMitad;
    private javax.swing.JLabel lblMitad2;
    private javax.swing.JLabel lblMitad3;
    private javax.swing.JLabel lblMitad4;
    private javax.swing.JLabel lblMitad5;
    private javax.swing.JLabel lblMitad6;
    private javax.swing.JLabel lblMitad7;
    private javax.swing.JLabel lblMitad8;
    private javax.swing.JLabel lblRolUsuario;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JPanel pnlPortada;
    private javax.swing.JPasswordField pwdAntigua;
    private javax.swing.JPasswordField pwdNueva;
    private javax.swing.JPasswordField pwdNueva2;
    // End of variables declaration//GEN-END:variables
}

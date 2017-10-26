/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.*;
import facadeshop.Diseño;
import static formularios.frmComprasDetalle.lblUser;
import static formularios.frmComprasDetalle.lblUser1;
import static formularios.frmLogin.txtUser;
import static formularios.frmProveedores.tblProveedores;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Ricky
 */
public class frmProductos extends javax.swing.JFrame {

    boolean estadoMenu=false; 
    JTableHeader tHeadVentas;
    String rol;
    
    public frmProductos() {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        tHeadVentas = tblProductos.getTableHeader();
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraSuperior.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
        obtenerUsuario();
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
    //--------------------llenando tabla de productos----------------
    
    public void tablaBuscarProductos(){
        String codBarra=txtProductosBuscar.getText();
        DefaultTableModel modeloProductos = new DefaultTableModel();
        ArrayList<Producto> productos = new ArrayList();
        Object[] fila = new Object[5];
        Object[] sucursales=new Object[4];
        if (codBarra.equals("")) {
            mensajeNotificacion("No ha introducido un código o nombre.", "Adv");
            
        }else{
            String[] campos = {"CodBarra", "Nombre", "Inventario", "Costo","Sucursal"};
            try {
                Sucursal opcionSucu;
                
                
                productos = ControladorProducto.Buscar(codBarra);
                modeloProductos.setColumnIdentifiers(campos);
                Iterator<Producto> prod = productos.iterator();
                
                
                
                while (prod.hasNext()) {
                    
                    fila[0] = prod.next();
                    fila[1] = prod.next();
                    fila[2] = prod.next();
                    fila[3] = prod.next();
                    fila[4] = prod.next();
                    
                    opcionSucu= ControladorSucursal.obtenerSucursal(Integer.parseInt(fila[4].toString()));
                    
                    
                    fila[4]=opcionSucu.getNombre();
                    
                    
                    modeloProductos.addRow(fila);
                    tblProductos.setModel(modeloProductos);
                }
                
            
                
                if (tblProductos.getRowCount()==0) {
                    mensajeNotificacion("¡Error! Ese producto no existe.", "Error");
                }
            } catch (ErrorTienda ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }
    
    
    //------------------------Extrayendo datos para comboboxSucursal-------------------
    public void llenandoComboboxSucursal(){
        Object[] seleccion=new Object[4];
        frmProductosModificar frmPm=new frmProductosModificar();
        
        try {
            ArrayList<Sucursal> misucu=ControladorSucursal.obtener();
            Iterator iterador=misucu.iterator();
            while(iterador.hasNext()){
                seleccion[0]=iterador.next();
                System.out.println(seleccion[0]);
                frmPm.modeloModificarSucursal.addElement(iterador.next());
                
                seleccion[2]=iterador.next();
                seleccion[3]=iterador.next();
            }
            
            frmPm.cmbModificarSucursal.setModel(frmPm.modeloModificarSucursal);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        lblSucursales = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        lblVentas = new javax.swing.JLabel();
        lblParametro = new javax.swing.JLabel();
        lblCompras = new javax.swing.JLabel();
        lblTipoPrecio = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnBuscarProducto = new javax.swing.JButton();
        btnModificarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtProductosBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        jpnWhite = new javax.swing.JPanel();
        lblUser1 = new javax.swing.JLabel();
        lblBotonCerrar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jpnUser = new javax.swing.JPanel();
        lblRolUsuario = new javax.swing.JLabel();
        lblCambiarPwd = new javax.swing.JLabel();
        lblCerrarSesion = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
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
        lblListaProductos = new javax.swing.JLabel();
        lblAgregarProductos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 700));
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
        lblProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/EProductos.png"))); // NOI18N
        lblProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProductosMouseEntered(evt);
            }
        });
        jpnMenu.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

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
        jpnMenu.add(lblVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, 50));

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

        tblProductos =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de Barra", "Nombre", "Inventario", "Costo", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblProductosInputMethodTextChanged(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblProductosKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 890, 260));

        btnBuscarProducto.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/buscar.png"))); // NOI18N
        btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarProductoMouseExited(evt);
            }
        });
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 110, 30));

        btnModificarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/modificar.png"))); // NOI18N
        btnModificarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarProductoMouseExited(evt);
            }
        });
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 600, 110, 30));

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/eliminar.png"))); // NOI18N
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarProductoMouseExited(evt);
            }
        });
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 600, 110, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Listado de los Productos:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        txtProductosBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductosBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductosBuscarKeyTyped(evt);
            }
        });
        getContentPane().add(txtProductosBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 430, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Producto a buscar:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));

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

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

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

        lblListaProductos.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblListaProductos.setForeground(new java.awt.Color(51, 51, 51));
        lblListaProductos.setText("Productos");
        lblListaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblListaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListaProductosMouseClicked(evt);
            }
        });
        getContentPane().add(lblListaProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 50));

        lblAgregarProductos.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblAgregarProductos.setForeground(new java.awt.Color(153, 153, 153));
        lblAgregarProductos.setText("Agregar Productos");
        lblAgregarProductos.setToolTipText("Ver los detalles de compras realizadas.");
        lblAgregarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAgregarProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarProductosMouseClicked(evt);
            }
        });
        getContentPane().add(lblAgregarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked


    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblProductosInputMethodTextChanged

    }//GEN-LAST:event_tblProductosInputMethodTextChanged

    private void tblProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyTyped


    }//GEN-LAST:event_tblProductosKeyTyped

    private void btnBuscarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoMouseEntered
        btnBuscarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/buscarB.png")));
    }//GEN-LAST:event_btnBuscarProductoMouseEntered

    private void btnBuscarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoMouseExited
        btnBuscarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/buscar.png")));
    }//GEN-LAST:event_btnBuscarProductoMouseExited

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        tablaBuscarProductos();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnModificarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProductoMouseEntered
        btnModificarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/modificarB.png")));
    }//GEN-LAST:event_btnModificarProductoMouseEntered

    private void btnModificarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProductoMouseExited
        btnModificarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/modificar.png")));
    }//GEN-LAST:event_btnModificarProductoMouseExited

    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
        
        
        
        if(tblProductos.getSelectedRow()!=-1){
            int seleccion; 
            frmProductosModificar pm = new frmProductosModificar();
            pm.setVisible(true);
            this.setVisible(false);
            seleccion = tblProductos.getSelectedRow();
            pm.txtNuevoCodBarraProducto.setText(tblProductos.getValueAt(seleccion, 0).toString());
            pm.txtNuevoNombreProducto.setText((tblProductos.getValueAt(seleccion, 1).toString()));
            pm.txtNuevoCostoProducto.setText((tblProductos.getValueAt(seleccion, 3).toString()));
            pm.txtNuevoInventarioProducto.setText((tblProductos.getValueAt(seleccion, 2).toString()));
            
            
            pm.txtNuevoNombreProducto.requestFocus();
            pm.txtNuevoNombreProducto.selectAll();
        }else{
            mensajeNotificacion("¡Seleccione un dato de la tabla!", "Adv");
        }
    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void btnEliminarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProductoMouseEntered
        btnEliminarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminarB.png")));
    }//GEN-LAST:event_btnEliminarProductoMouseEntered

    private void btnEliminarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProductoMouseExited
        btnEliminarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminar.png")));
    }//GEN-LAST:event_btnEliminarProductoMouseExited

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int fila=tblProductos.getSelectedRow(); 
        if (tblProductos.isRowSelected(fila)) {
            
            if (fila>=0) {
                
            
            Producto eliminar=new Producto();
            ControladorProducto controlador=new ControladorProducto();
            DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
            

            String codBarra=tblProductos.getValueAt(fila, 0).toString();
            String nombre=tblProductos.getValueAt(fila, 1).toString();
            int inventario=Integer.parseInt(tblProductos.getValueAt(fila, 2).toString());
            double costo=Double.parseDouble(tblProductos.getValueAt(fila, 3).toString());

            eliminar.setCodBarra(codBarra);
            eliminar.setNombre(nombre);
            eliminar.setInventario(inventario);
            eliminar.setCosto(costo);


            try {
                ControladorProducto.Eliminar(eliminar);
                if (ControladorProducto.isCambio()) {
                    mensajeNotificacion("¡Error! Producto con registros vigentes.", "Error");
                }else{
                    modeloProductos.removeRow(fila);
                    txtProductosBuscar.setText("");
                    mensajeNotificacion("¡Producto eliminado exitosamente!", "Ok");
                }
                
            } catch (ErrorTienda ex) {
                
            }
            }
        }else{
            mensajeNotificacion("¡Seleccione un Producto de la tabla!", "Adv");
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void txtProductosBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosBuscarKeyPressed
        char c=evt.getKeyChar();    
        if (c == (char) KeyEvent.VK_ENTER) {
            tablaBuscarProductos();
        }
    }//GEN-LAST:event_txtProductosBuscarKeyPressed

    private void txtProductosBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosBuscarKeyTyped
        char mayu=evt.getKeyChar();      
         int c=(int) evt.getKeyChar();
        int limiteCaracteres=13;
        
         
         if ((c >=48 && c<=57) || (c>=65 && c<=90) || (c>=97 && c<=122) || (c==32) || (c==8) || (c== (char)KeyEvent.VK_ENTER)) {
             if (txtProductosBuscar.getText().length()==limiteCaracteres) {
                 getToolkit().beep();
                 evt.consume();
             }else if (Character.isLowerCase(mayu)) {
                     String cadena=(""+mayu).toUpperCase();
                     mayu=cadena.charAt(0);
                     evt.setKeyChar(mayu);
             }
        }else{
             evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
         }
    }//GEN-LAST:event_txtProductosBuscarKeyTyped

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
            Logger.getLogger(frmProductos.class.getName()).log(Level.SEVERE, null, ex);
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

    private void lblTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTipoPrecioMouseClicked
        frmTipoPrecio tp = new frmTipoPrecio();
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblTipoPrecioMouseClicked

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

    private void lblListaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListaProductosMouseClicked
        // TODO add your handling code here:
        lblListaProductos.setForeground(java.awt.Color.black);
        lblAgregarProductos.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblListaProductosMouseClicked

    private void lblAgregarProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarProductosMouseClicked
        frmProductosAgregar pa = new frmProductosAgregar();
        pa.setVisible(true);
        this.setVisible(false);
        lblAgregarProductos.setForeground(java.awt.Color.black);
        lblListaProductos.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblAgregarProductosMouseClicked

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
            java.util.logging.Logger.getLogger(frmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBitacoras;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnDetalleCompras;
    private javax.swing.JButton btnDetalleVentas;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnTipoPrecio;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
    private javax.swing.JLabel lblAgregarProductos;
    public static javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblListaProductos;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblParametro;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblRolUsuario;
    private javax.swing.JLabel lblSucursales;
    private javax.swing.JLabel lblTipoPrecio;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JLabel lblVentas;
    public javax.swing.JTable tblProductos;
    public javax.swing.JTextField txtProductosBuscar;
    // End of variables declaration//GEN-END:variables
}

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

        modeloDCompras = (DefaultTableModel) tblComprasDetalladas.getModel();
        obtenerUsuario();
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
            subTotales+=Double.parseDouble(String.valueOf(modeloDCompras.getValueAt(i, 3)));
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
            Logger.getLogger(frmVentasDetalladas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frmComprasDetalladas2 = new javax.swing.JFrame();
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
        jpnAgregarCompra1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jpnBarraSuperior1 = new javax.swing.JPanel();
        lblLogo1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnAtras1 = new javax.swing.JLabel();
        lblSumas = new javax.swing.JLabel();
        txtSumas = new javax.swing.JTextField();
        lblIVA = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtTotalCompra = new javax.swing.JTextField();
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
        jpnMenu = new javax.swing.JPanel();
        lblSucursales = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        lblVentas = new javax.swing.JLabel();
        lblMenuCerrar = new javax.swing.JLabel();
        lblParametro = new javax.swing.JLabel();
        lblCompras = new javax.swing.JLabel();
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
        jSeparator9 = new javax.swing.JSeparator();

        frmComprasDetalladas2.setMinimumSize(new java.awt.Dimension(1200, 700));
        frmComprasDetalladas2.setUndecorated(true);
        frmComprasDetalladas2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Id de la compra:");
        frmComprasDetalladas2.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 130, -1));

        txtIdCompra.setEditable(false);
        txtIdCompra.setText(" ");
        txtIdCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCompraActionPerformed(evt);
            }
        });
        frmComprasDetalladas2.getContentPane().add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 50, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Fue una compra con:");
        frmComprasDetalladas2.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 170, -1));

        txtTipoCompra.setEditable(false);
        txtTipoCompra.setText(" ");
        txtTipoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoCompraActionPerformed(evt);
            }
        });
        frmComprasDetalladas2.getContentPane().add(txtTipoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 170, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Sucursal:");
        frmComprasDetalladas2.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 70, -1));

        txtSucursal.setEditable(false);
        txtSucursal.setText(" ");
        txtSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSucursalActionPerformed(evt);
            }
        });
        frmComprasDetalladas2.getContentPane().add(txtSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 240, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Fecha:");
        frmComprasDetalladas2.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, 50, -1));

        txtFecha.setEditable(false);
        txtFecha.setText(" ");
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        frmComprasDetalladas2.getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, 190, -1));

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

        frmComprasDetalladas2.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 960, 220));

        jpnAgregarCompra1.setBackground(new java.awt.Color(0, 0, 0));
        jpnAgregarCompra1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(240, 240, 240));
        jLabel35.setText("Detalles de Compras:");
        jpnAgregarCompra1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, -1, 30));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 20, 50));

        frmComprasDetalladas2.getContentPane().add(jpnAgregarCompra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

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

        lblLogo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLogo1.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        lblLogo1.setToolTipText("");
        jpnBarraSuperior1.add(lblLogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 50, 50));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setToolTipText("");
        jpnBarraSuperior1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 60, 60));

        btnAtras1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Atras.png"))); // NOI18N
        btnAtras1.setToolTipText("Volver atrás");
        btnAtras1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtras1MouseClicked(evt);
            }
        });
        jpnBarraSuperior1.add(btnAtras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 50, 40));

        frmComprasDetalladas2.getContentPane().add(jpnBarraSuperior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        lblSumas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSumas.setText("Sumas");
        frmComprasDetalladas2.getContentPane().add(lblSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, -1, -1));

        txtSumas.setEditable(false);
        txtSumas.setBackground(new java.awt.Color(255, 255, 255));
        txtSumas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSumas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frmComprasDetalladas2.getContentPane().add(txtSumas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 120, 40));

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIVA.setText("13% IVA");
        frmComprasDetalladas2.getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, -1, -1));

        txtIVA.setEditable(false);
        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frmComprasDetalladas2.getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 120, 40));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 3, 0));
        jLabel38.setText("Total");
        frmComprasDetalladas2.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, -1, 20));

        txtTotalCompra.setEditable(false);
        txtTotalCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalCompra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frmComprasDetalladas2.getContentPane().add(txtTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 490, 120, 40));

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

        jpnMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnMenu.setPreferredSize(new java.awt.Dimension(80, 304));
        jpnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnMenuMouseExited(evt);
            }
        });
        jpnMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Sucursales.png"))); // NOI18N
        lblSucursales.setToolTipText("Sucursales");
        lblSucursales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSucursalesMouseClicked(evt);
            }
        });
        jpnMenu.add(lblSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 230, -1, 30));

        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Proveedores.png"))); // NOI18N
        lblProveedores.setToolTipText("Proveedores");
        lblProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProveedoresMouseClicked(evt);
            }
        });
        jpnMenu.add(lblProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 190, -1, 30));

        lblProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Productos.png"))); // NOI18N
        lblProductos.setToolTipText("Productos");
        lblProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProductosMouseEntered(evt);
            }
        });
        jpnMenu.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 150, -1, 30));

        lblVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Ventas.png"))); // NOI18N
        lblVentas.setToolTipText("Ventas");
        lblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentasMouseClicked(evt);
            }
        });
        jpnMenu.add(lblVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 110, -1, 30));

        lblMenuCerrar.setBackground(new java.awt.Color(0, 0, 0));
        lblMenuCerrar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblMenuCerrar.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenuCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Menu.png"))); // NOI18N
        lblMenuCerrar.setToolTipText("Cerrar");
        lblMenuCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuCerrarMouseClicked(evt);
            }
        });
        jpnMenu.add(lblMenuCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 55));

        lblParametro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Parametro.png"))); // NOI18N
        lblParametro.setToolTipText("Sucursales");
        lblParametro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblParametro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblParametroMouseClicked(evt);
            }
        });
        jpnMenu.add(lblParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 270, -1, 30));

        lblCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Compras.png"))); // NOI18N
        lblCompras.setToolTipText("Compras");
        lblCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblComprasMouseClicked(evt);
            }
        });
        jpnMenu.add(lblCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 70, -1, 30));

        getContentPane().add(jpnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -360, 80, 318));

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

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            Logger.getLogger(frmComprasDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblVentasMouseClicked

    private void lblMenuCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuCerrarMouseClicked
        if(estadoMenu==true){
            Animacion.Animacion.subir(0, -360, 1, 2, jpnMenu);
            estadoMenu=false;
        }
    }//GEN-LAST:event_lblMenuCerrarMouseClicked

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

    private void jpnMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMenuMouseExited

    }//GEN-LAST:event_jpnMenuMouseExited

    private void btnDetallesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseExited
        btnDetalles.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/detalles2.png")));
    }//GEN-LAST:event_btnDetallesMouseExited

    private void btnDetallesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseEntered
        btnDetalles.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/detalles2B.png")));
    }//GEN-LAST:event_btnDetallesMouseEntered

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        if(tblCompras.getSelectedRow()!=-1){
            
            this.hide();
            frmComprasDetalladas2.show();
            frmComprasDetalladas2.setLocation(this.getLocation());
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

    private void btnAtras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtras1MouseClicked
        frmComprasDetalladas2.dispose();
        this.show();
    }//GEN-LAST:event_btnAtras1MouseClicked

    private void jpnBarraSuperior1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperior1MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnBarraSuperior1MouseDragged

    private void jpnBarraSuperior1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperior1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnBarraSuperior1MousePressed

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
    private javax.swing.JLabel btnAtras1;
    private javax.swing.JButton btnBitacoras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnDetalleCompras;
    private javax.swing.JButton btnDetalleVentas;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnTipoPrecio;
    private javax.swing.JButton btnVentas;
    private javax.swing.JFrame frmComprasDetalladas2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpnAgregarCompra1;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnBarraSuperior1;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
    public static javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblComprar;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblDetallesCompras;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMenuCerrar;
    private javax.swing.JLabel lblParametro;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblRolUsuario;
    private javax.swing.JLabel lblSucursales;
    private javax.swing.JLabel lblSumas;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JLabel lblVentas;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorTipoPrecio;
import clases.ControladorUsuario;
import clases.ErrorTienda;
import clases.TipoPrecio;
import clases.Usuario;
import facadeshop.Diseño;
import formularios.frmCompras;
import formularios.frmHome;
import static formularios.frmLogin.txtUser;
import formularios.frmProductos;
import formularios.frmProveedores;
import static formularios.frmProveedores.lblUser;
import static formularios.frmProveedores.lblUser1;
import formularios.frmSucursales;
import formularios.frmVentas;
import formulariosReportes.frmComprasReportes;
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
public class frmTipoPrecio extends javax.swing.JFrame {

    boolean estadoMenu;
    JTableHeader tHeadVentas;
    public DefaultTableModel modelotipoprecio= new DefaultTableModel();
       frmTipoPrecioModificar ftp = new frmTipoPrecioModificar();
       String rol, password;

    public frmTipoPrecio() {
        initComponents();
        this.setSize(1200, 700);
        tHeadVentas = tblTipoPrecio.getTableHeader();
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraSuperior.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
        LlenarTabla();
        obtenerUsuario();
    }
    
     public void LlenarTabla(){
      modelotipoprecio.setRowCount(0);       
            ArrayList<TipoPrecio> lsttipoprecio=new ArrayList();
            Object fila[]=new Object[3];
        try {
            lsttipoprecio= ControladorTipoPrecio.ObtenerTodos();
            String [] encabezados= new String[]{"IdTipoPrecio","Nombre","Utilidad"};
            modelotipoprecio.setColumnIdentifiers(encabezados);
                    Iterator<TipoPrecio> tp=lsttipoprecio.iterator();
                    while(tp.hasNext()){
                    fila[0]= tp.next();
                    fila[1]= tp.next();
                    fila[2]= tp.next();
                    modelotipoprecio.addRow(fila);
                    tblTipoPrecio.setModel(modelotipoprecio);
                }        
        } catch (Exception e) {
        }
    }
    
     //Metodo para editar
    public void TrasladoDatos(){
        ftp.txtIDTipoPrecio.setEditable(false);
    ftp.txtIDTipoPrecio.setText(tblTipoPrecio.getValueAt(tblTipoPrecio.getSelectedRow(),0).toString());
    ftp.txtNombreTipo.setText(tblTipoPrecio.getValueAt(tblTipoPrecio.getSelectedRow(),1).toString());
    ftp.txtUtilidadTipoPrecio.setText(tblTipoPrecio.getValueAt(tblTipoPrecio.getSelectedRow(),2).toString());
    ftp.txtNombreTipo.requestFocus();
    ftp.txtNombreTipo.selectAll();
    ftp.nombre = tblTipoPrecio.getValueAt(tblTipoPrecio.getSelectedRow(), 1).toString();
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
                lblAgregarUsuario.setVisible(true);
            }
            else if(rol.equals("V")){
                lblRolUsuario.setText("VENDEDOR");
                lblAgregarUsuario.setVisible(false);}
            else if(rol.equals("C")){
                lblRolUsuario.setText("COMPRADOR");
                lblAgregarUsuario.setVisible(false);

            }
            
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTipoPrecio = new javax.swing.JTable();
        btnModificarTipoPrecio = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnEliminarTipoPrecio = new javax.swing.JButton();
        lblListaTipoPrecio = new javax.swing.JLabel();
        lblAgregarTipoPrecio = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jpnBarraSuperior = new javax.swing.JPanel();
        jpnWhite = new javax.swing.JPanel();
        lblUser1 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tipo Precio");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        jpnPass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Repita nueva contraseña:");
        jpnPass.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, -1));

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

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel4.setText("Contraseña antigua:");
        jpnPass.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

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

        jSeparator1.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, 10));

        jSeparator2.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(102, 0, 0));
        jpnPass.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 190, 10));

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

        tblTipoPrecio =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblTipoPrecio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdTipoPrecio", "Nombre", "Utilidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTipoPrecio.getTableHeader().setReorderingAllowed(false);
        tblTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoPrecioMouseClicked(evt);
            }
        });
        tblTipoPrecio.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblTipoPrecioInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tblTipoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblTipoPrecioKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblTipoPrecio);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 890, 260));

        btnModificarTipoPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/modificar.png"))); // NOI18N
        btnModificarTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarTipoPrecioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarTipoPrecioMouseExited(evt);
            }
        });
        btnModificarTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTipoPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificarTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 570, 110, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Listado de los tipos de Precio:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, 20));

        btnEliminarTipoPrecio.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarTipoPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarTipoPrecio.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarTipoPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/eliminar.png"))); // NOI18N
        btnEliminarTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarTipoPrecioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarTipoPrecioMouseExited(evt);
            }
        });
        btnEliminarTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTipoPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 570, 110, 30));

        lblListaTipoPrecio.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblListaTipoPrecio.setForeground(new java.awt.Color(51, 51, 51));
        lblListaTipoPrecio.setText("Tipo de Precio");
        lblListaTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblListaTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListaTipoPrecioMouseClicked(evt);
            }
        });
        getContentPane().add(lblListaTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 50));

        lblAgregarTipoPrecio.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblAgregarTipoPrecio.setForeground(new java.awt.Color(153, 153, 153));
        lblAgregarTipoPrecio.setText("Agregar Tipo de Precio");
        lblAgregarTipoPrecio.setToolTipText("Agregar un nuevo tipo de precio.");
        lblAgregarTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAgregarTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarTipoPrecioMouseClicked(evt);
            }
        });
        getContentPane().add(lblAgregarTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, 50));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(102, 0, 0));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 117, 1020, 10));

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
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
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
        btnTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnSubMenu.add(btnTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 410, 180, 40));

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

        btnHome.setToolTipText("Inicio");
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnBarraMenu.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        getContentPane().add(jpnBarraMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoPrecioMouseClicked

    }//GEN-LAST:event_tblTipoPrecioMouseClicked

    private void tblTipoPrecioInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblTipoPrecioInputMethodTextChanged

    }//GEN-LAST:event_tblTipoPrecioInputMethodTextChanged

    private void tblTipoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTipoPrecioKeyTyped

    }//GEN-LAST:event_tblTipoPrecioKeyTyped

    private void btnModificarTipoPrecioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarTipoPrecioMouseEntered
        btnModificarTipoPrecio.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/modificarB.png")));
    }//GEN-LAST:event_btnModificarTipoPrecioMouseEntered

    private void btnModificarTipoPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarTipoPrecioMouseExited
        btnModificarTipoPrecio.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/modificar.png")));
    }//GEN-LAST:event_btnModificarTipoPrecioMouseExited

    private void btnModificarTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTipoPrecioActionPerformed

        if(tblTipoPrecio.getSelectedRow()!=-1){
            int seleccion;
            frmTipoPrecioModificar tpm = new frmTipoPrecioModificar();
            tpm.setVisible(true);
            this.setVisible(false);
            TrasladoDatos();

        }else{
            mensajeNotificacion("¡Seleccione un dato de la tabla!", "Adv");
        }
    }//GEN-LAST:event_btnModificarTipoPrecioActionPerformed

    private void btnEliminarTipoPrecioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarTipoPrecioMouseEntered
        btnEliminarTipoPrecio.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminarB.png")));
    }//GEN-LAST:event_btnEliminarTipoPrecioMouseEntered

    private void btnEliminarTipoPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarTipoPrecioMouseExited
        btnEliminarTipoPrecio.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminar.png")));
    }//GEN-LAST:event_btnEliminarTipoPrecioMouseExited

    private void btnEliminarTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTipoPrecioActionPerformed
        
        
        if(tblTipoPrecio.getSelectedRow()!=-1){
            int seleccion;
            int id = Integer.parseInt(modelotipoprecio.getValueAt(tblTipoPrecio.getSelectedRow(), 0).toString()) ;
             try {
            ControladorTipoPrecio.EliminarTipoPrecio(id);
            mensajeNotificacion("Registro eliminado con exito","Ok");
            LlenarTabla();
        } catch (ErrorTienda ex) {
            
        }

        }else{
            mensajeNotificacion("¡Seleccione un dato de la tabla!", "Adv");
        }
       
        
        
    }//GEN-LAST:event_btnEliminarTipoPrecioActionPerformed

    private void lblListaTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListaTipoPrecioMouseClicked
        // TODO add your handling code here:
        lblListaTipoPrecio.setForeground(java.awt.Color.black);
        lblAgregarTipoPrecio.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblListaTipoPrecioMouseClicked

    private void lblAgregarTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarTipoPrecioMouseClicked
        frmTipoPrecioAgregar tpa = new frmTipoPrecioAgregar();
        tpa.setVisible(true);
        this.setVisible(false);
        lblAgregarTipoPrecio.setForeground(java.awt.Color.black);
        lblListaTipoPrecio.setForeground(java.awt.Color.lightGray);
    }//GEN-LAST:event_lblAgregarTipoPrecioMouseClicked

    private void lblUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser1MouseClicked
        jpnWhite.setVisible(false);
        jpnUser.setVisible(false);
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

    private void lblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblMenuMouseClicked

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

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnVentas);
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseExited
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnVentas);
    }//GEN-LAST:event_btnVentasMouseExited

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        try {
            frmVentas ve = new frmVentas();
            ve.setVisible(true);
            this.setVisible(false);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmBitacoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentasActionPerformed

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

    private void jpnBarraMenuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpnBarraMenuPropertyChange

    }//GEN-LAST:event_jpnBarraMenuPropertyChange

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

    private void lblAgregarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarUsuarioMouseClicked
        frmRegistrarUsuario ru = new frmRegistrarUsuario();
        ru.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblAgregarUsuarioMouseClicked

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
            java.util.logging.Logger.getLogger(frmTipoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTipoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTipoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTipoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new frmTipoPrecio().setVisible(true);
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
    private javax.swing.JButton btnEliminarTipoPrecio;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnModificarTipoPrecio;
    private javax.swing.JButton btnParametro;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSucursales;
    private javax.swing.JButton btnTipoPrecio;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnPass;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnUser;
    private javax.swing.JPanel jpnWhite;
    private javax.swing.JLabel lblAgregarTipoPrecio;
    private javax.swing.JLabel lblAgregarUsuario;
    private javax.swing.JLabel lblCambiarPwd;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblListaTipoPrecio;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblRolUsuario;
    public static javax.swing.JLabel lblUser;
    public static javax.swing.JLabel lblUser1;
    private javax.swing.JPasswordField pwdAntigua;
    private javax.swing.JPasswordField pwdNueva;
    private javax.swing.JPasswordField pwdNueva2;
    public javax.swing.JTable tblTipoPrecio;
    // End of variables declaration//GEN-END:variables
}

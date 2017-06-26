/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorSucursal;
import clases.ErrorTienda;
import clases.Proveedor;
import clases.Sucursal;
import static formularios.frmProveedores.tblProveedores;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Ricky
 */
public class frmSucursales extends javax.swing.JFrame {
    
    JTableHeader tHeadVentas;
    boolean estadoMenu;
    private TableRowSorter trsFiltro;
    public DefaultTableModel modeloSucursales= new DefaultTableModel();
    frmSucursalesModificar sum = new frmSucursalesModificar();
    
    public frmSucursales() {
        initComponents();
        actualizarTablaSucursal();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        tHeadVentas = tblSucursales.getTableHeader();
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
    
    //Obtener los datos para enviarlo a Modificar
    public void ObtenerDatos(){
        sum.txtIDSucursal.setText(tblSucursales.getValueAt(tblSucursales.getSelectedRow(), 0).toString());
        sum.txtNuevoNombreSucursal.setText(tblSucursales.getValueAt(tblSucursales.getSelectedRow(), 1).toString());
        sum.txtNuevoTelefonoSucursal.setText(tblSucursales.getValueAt(tblSucursales.getSelectedRow(), 3).toString());
        sum.txtNuevoDireccionSucursal.setText(tblSucursales.getValueAt(tblSucursales.getSelectedRow(), 2).toString());
        sum.txtNuevoNombreSucursal.selectAll();
        sum.txtNuevoNombreSucursal.requestFocus();
        sum.nombre = tblSucursales.getValueAt(tblSucursales.getSelectedRow(), 1).toString();
    }    
    
       //---------------------------Llenar tabla de proveedores----------------------------------------
    public void actualizarTablaSucursal(){
            modeloSucursales.setRowCount(0);
            
            ArrayList<Sucursal> listaSucursal=new ArrayList();
            Object fila[]=new Object[4];
            
        
            try {
            listaSucursal=ControladorSucursal.obtener();
            String[] nombreSucursal = new String []{"IdSucursal","Nombre","Direccion","Telefono"};
            modeloSucursales.setColumnIdentifiers(nombreSucursal);
            Iterator<Sucursal> prov=listaSucursal.iterator();
                while(prov.hasNext()){
                    fila[0]= prov.next();
                    fila[1]= prov.next();
                    fila[2]= prov.next();
                    fila[3]= prov.next();
                    modeloSucursales.addRow(fila);
                    tblSucursales.setModel(modeloSucursales);
                }
            }
            
         catch (ErrorTienda ex) {
             Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
            
        
         }
    } 
        
    public void EliminarSucursal(){
        int fila = tblSucursales.getSelectedRow(); 
        System.out.println(fila);
        if (tblSucursales.isRowSelected(fila)) {
            
            if (fila>=0) {
            int seleccion;
            seleccion = tblSucursales.getSelectedRow();
            DefaultTableModel modeloProveedores=(DefaultTableModel) tblSucursales.getModel();
            Sucursal sucursal = new Sucursal();
            
            int idSucursal=Integer.parseInt(tblSucursales.getValueAt(seleccion, 0).toString());
            String nom=tblSucursales.getValueAt(seleccion, 1).toString();
            String tel=tblSucursales.getValueAt(seleccion, 2).toString();
            String dire=tblSucursales.getValueAt(seleccion, 3).toString();
            
            
            
            sucursal.setIdSucursal(Integer.parseInt(tblSucursales.getValueAt(seleccion, 0).toString()));
            sucursal.setNombre(tblSucursales.getValueAt(seleccion, 1).toString());
            sucursal.setTelefono(tblSucursales.getValueAt(seleccion, 2).toString());
            sucursal.setDireccion(tblSucursales.getValueAt(seleccion, 3).toString());
            try{
                ControladorSucursal.eliminarSucursal(sucursal);
                if (ControladorSucursal.isCambio()) {
                    mensajeNotificacion("¡Error! Sucursal con registros vigentes.", "Error");
                }else{
                    modeloProveedores.removeRow(fila);
                    txtSucursalesBuscar.setText("");
                    mensajeNotificacion("¡Sucursal eliminada exitosamente!", "Ok");
                }
                
                
            }catch(ErrorTienda ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
           
            
        }
        }else{
            mensajeNotificacion("¡Seleccione una Sucursal de la tabla!", "Adv");
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
        jpnBarraSuperior = new javax.swing.JPanel();
        lblBotonCerrar = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        home = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jpnAgregarCompra = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        btnEliminarSucursal = new javax.swing.JButton();
        btnAgregarSucursal = new javax.swing.JButton();
        btnModificarSucursal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSucursales = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtSucursalesBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();

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
        lblSucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ESucursales.png"))); // NOI18N
        lblSucursales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSucursalesMouseClicked(evt);
            }
        });
        jpnMenu.add(lblSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

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
        jpnMenu.add(lblProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 140, 50));

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
        jpnMenu.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, 50));

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

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setToolTipText("");
        jpnBarraSuperior.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 60, 60));

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
        jpnBarraSuperior.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 55));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setToolTipText("");
        jpnBarraSuperior.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 10, 60));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnAgregarCompra.setBackground(new java.awt.Color(0, 0, 0));
        jpnAgregarCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Sucursales");
        jpnAgregarCompra.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, -1, 30));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 20, 50));

        getContentPane().add(jpnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        btnEliminarSucursal.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarSucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/eliminar.png"))); // NOI18N
        btnEliminarSucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarSucursalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarSucursalMouseExited(evt);
            }
        });
        btnEliminarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSucursalActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, 110, 30));

        btnAgregarSucursal.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarSucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/agregar.png"))); // NOI18N
        btnAgregarSucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarSucursal.setFocusCycleRoot(true);
        btnAgregarSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarSucursalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarSucursalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarSucursalMouseExited(evt);
            }
        });
        btnAgregarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSucursalActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 600, 110, 30));

        btnModificarSucursal.setBackground(new java.awt.Color(0, 0, 0));
        btnModificarSucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificarSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/modificar.png"))); // NOI18N
        btnModificarSucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarSucursalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarSucursalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarSucursalMouseExited(evt);
            }
        });
        btnModificarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarSucursalActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 600, 110, 30));

        tblSucursales =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblSucursales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idSucursal", "Sucursal", "Direccion", "Telefono"
            }
        ));
        tblSucursales.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblSucursales);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 670, 260));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Listado de Sucursales actuales:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        txtSucursalesBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSucursalesBuscarKeyTyped(evt);
            }
        });
        getContentPane().add(txtSucursalesBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 670, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Sucursal a buscar:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1200, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarSucursalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSucursalMouseEntered
        btnEliminarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminarB.png")));
    }//GEN-LAST:event_btnEliminarSucursalMouseEntered

    private void btnEliminarSucursalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSucursalMouseExited
        btnEliminarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/eliminar.png")));
    }//GEN-LAST:event_btnEliminarSucursalMouseExited

    private void btnEliminarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSucursalActionPerformed
        EliminarSucursal();
    }//GEN-LAST:event_btnEliminarSucursalActionPerformed

    private void btnAgregarSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarSucursalMouseClicked

    }//GEN-LAST:event_btnAgregarSucursalMouseClicked

    private void btnAgregarSucursalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarSucursalMouseEntered
        // Cambio del botón Agregar Proveedor a negro:
        btnAgregarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/agregarB.png")));
    }//GEN-LAST:event_btnAgregarSucursalMouseEntered

    private void btnAgregarSucursalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarSucursalMouseExited
        // Cambio del botón Agregar Proveedor a blanco:
        btnAgregarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/agregar.png")));
    }//GEN-LAST:event_btnAgregarSucursalMouseExited

    private void btnAgregarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSucursalActionPerformed
        frmSucursalesAgregar su = new frmSucursalesAgregar();
        su.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAgregarSucursalActionPerformed

    private void btnModificarSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarSucursalMouseClicked

    }//GEN-LAST:event_btnModificarSucursalMouseClicked

    private void btnModificarSucursalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarSucursalMouseEntered
        // Cambio del botón Modificar Proveedor a negro:
        btnModificarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/modificarB.png")));
    }//GEN-LAST:event_btnModificarSucursalMouseEntered

    private void btnModificarSucursalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarSucursalMouseExited
        // Cambio del botón Modificar Proveedor a blanco:
        btnModificarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/modificar.png")));
    }//GEN-LAST:event_btnModificarSucursalMouseExited

    private void btnModificarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarSucursalActionPerformed
    if(tblSucursales.getSelectedRow()!=-1){
        frmSucursalesModificar sm = new frmSucursalesModificar();
        sm.setVisible(true);
        this.setVisible(false);
        ObtenerDatos();
        } else {
            mensajeNotificacion("Debe de seleccionar una Sucursal.", "Adv");
        }
    }//GEN-LAST:event_btnModificarSucursalActionPerformed

    private void txtSucursalesBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucursalesBuscarKeyTyped
        char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{
         }
        txtSucursalesBuscar.addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyReleased(final KeyEvent e){
                String cadena = (txtSucursalesBuscar.getText());
                txtSucursalesBuscar.setText(cadena);
                repaint();
                trsFiltro.setRowFilter(RowFilter.regexFilter(txtSucursalesBuscar.getText(), 1));
            }
        });
        trsFiltro = new TableRowSorter(tblSucursales.getModel());
        tblSucursales.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtSucursalesBuscarKeyTyped

    private void lblBotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblBotonCerrarMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        frmHome home = new frmHome();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_homeMouseClicked

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        if(estadoMenu==true){
            Animacion.Animacion.subir(55, -360, 1, 2, jpnMenu);
            estadoMenu=false;
            Border empty;
            empty = BorderFactory.createEmptyBorder();
            menu.setBorder(empty);
            menu.setText("Menu");
        }else{
            Animacion.Animacion.bajar(-360, 55, 1, 2, jpnMenu);
            estadoMenu=true;
            Border raisedbevel;
            raisedbevel = BorderFactory.createRaisedBevelBorder();
            menu.setBorder(raisedbevel);
            menu.setText("Cerrar");
        }
    }//GEN-LAST:event_menuMouseClicked

    private void menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseExited

    }//GEN-LAST:event_menuMouseExited

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
            Logger.getLogger(frmSucursales.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(frmSucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSucursales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarSucursal;
    private javax.swing.JButton btnEliminarSucursal;
    private javax.swing.JButton btnModificarSucursal;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnAgregarCompra;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblParametro;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblSucursales;
    private javax.swing.JLabel lblTipoPrecio;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JLabel menu;
    private javax.swing.JTable tblSucursales;
    private javax.swing.JTextField txtSucursalesBuscar;
    // End of variables declaration//GEN-END:variables
}

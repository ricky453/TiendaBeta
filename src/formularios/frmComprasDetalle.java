/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorCompra;
import clases.DetalleCompra;
import clases.ErrorTienda;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    
    public frmComprasDetalle() {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        tHeadVentas = tblCompras.getTableHeader();
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraSuperior.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
        actualizarTablaDetalleCompra();
    }

   //---------------------------Llenar tabla de Detalle Compra----------------------------------------
        public void actualizarTablaDetalleCompra(){
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
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        lblSucursales = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        lblVentas = new javax.swing.JLabel();
        lblMenuCerrar = new javax.swing.JLabel();
        lblParametro = new javax.swing.JLabel();
        lblCompras = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAtras = new javax.swing.JLabel();
        jpnAgregarCompra = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompras = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtComprasBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();

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

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png"))); // NOI18N
        lblLogo.setToolTipText("");
        jpnBarraSuperior.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 50, 50));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setToolTipText("");
        jpnBarraSuperior.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 60, 60));

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Atras.png"))); // NOI18N
        btnAtras.setToolTipText("Volver atrás");
        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 50, 40));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnAgregarCompra.setBackground(new java.awt.Color(0, 0, 0));
        jpnAgregarCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Compras");
        jpnAgregarCompra.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, -1, 30));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnAgregarCompra.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 20, 50));

        getContentPane().add(jpnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        tblCompras =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "idCompra", "Producto", "Cantidad", "Precio Unitario"
            }
        ));
        tblCompras.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCompras);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 920, 380));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Lista de las Compras:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, -1));

        txtComprasBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComprasBuscarKeyTyped(evt);
            }
        });
        getContentPane().add(txtComprasBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 670, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Compras a buscar:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1200, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseClicked
        frmCompras co = new frmCompras();
        co.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAtrasMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void txtComprasBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprasBuscarKeyTyped

    }//GEN-LAST:event_txtComprasBuscarKeyTyped

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
    private javax.swing.JLabel btnAtras;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnAgregarCompra;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JLabel lblCompras;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenuCerrar;
    private javax.swing.JLabel lblParametro;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblSucursales;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JTable tblCompras;
    private javax.swing.JTextField txtComprasBuscar;
    // End of variables declaration//GEN-END:variables
}

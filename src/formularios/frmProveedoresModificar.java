/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorProveedor;
import clases.ErrorTienda;
import clases.Proveedor;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Ricky
 */
public class frmProveedoresModificar extends javax.swing.JFrame {

    boolean encontradoProv;
    DefaultTableModel modeloProveedores= new DefaultTableModel();
    public static String nombre;
    
    public frmProveedoresModificar() {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
    }
    

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
    public void Atras(){
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);
    }
    public void buscarRepetidos(){
            modeloProveedores.setRowCount(0);
            
            ArrayList<Proveedor> listaProveedor=new ArrayList();
            Object fila[]=new Object[7];
            
        
            try {
            listaProveedor=ControladorProveedor.Obtener();
            String[] nombreProveedores = new String []{"IdProveedor","Nombre","Telefono","Direccion","NIT","Email","NRC"};
            modeloProveedores.setColumnIdentifiers(nombreProveedores);
            Iterator<Proveedor> prov=listaProveedor.iterator();
                while(prov.hasNext()){
                    fila[0]= prov.next();
                    fila[1]= prov.next();
                    fila[2]= prov.next();
                    fila[3]= prov.next();
                    fila[4]= prov.next();
                    fila[5]= prov.next();
                    fila[6]= prov.next();
                    modeloProveedores.addRow(fila);
                    tblProveedores.setModel(modeloProveedores);
                }
            }
            
         catch (ErrorTienda ex) {
             Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
            
        
         }
    }
    
    public void GuardarModificacion(){
        encontradoProv = false;
        if (!txtNuevoDireccionProveedor.getText().equals("") && !txtNuevoNombreProveedor.getText().equals("") && !txtNuevoNITProveedor.getText().equals("") && !txtNuevoTelefono.getText().equals("") && !txtNuevoEmailProveedor.getText().equals("") && !txtNuevoNRCProveedor.getText().equals("")) {
            if(txtNuevoNITProveedor.getText().length() != 14){
                mensajeNotificacion("El NIT debe de contener 14 digitos.", "Adv");
                txtNuevoNITProveedor.requestFocus();
                txtNuevoNITProveedor.selectAll();
            }
            else{
                buscarRepetidos();
            if (tblProveedores.getRowCount()>0) {
                  int i = 0;
                  while (encontradoProv==false&&i<tblProveedores.getRowCount()) {
                     encontradoProv = tblProveedores.getValueAt(i, 1).equals(txtNuevoNombreProveedor.getText());
                     i++;
                  }
            }
            if(txtNuevoNombreProveedor.getText().equals(nombre)){
                encontradoProv = false;
            }
            if(encontradoProv == false){
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(Integer.parseInt(txtIDProveedor.getText()));
            proveedor.setNombre(txtNuevoNombreProveedor.getText());
            proveedor.setTelefono(txtNuevoTelefono.getText());
            proveedor.setDireccion(txtNuevoDireccionProveedor.getText());
            proveedor.setNIT(txtNuevoNITProveedor.getText());
            proveedor.setEmail(txtNuevoEmailProveedor.getText());
            proveedor.setNRC(txtNuevoNRCProveedor.getText());
            try{
                ControladorProveedor.Modificar(proveedor);
                mensajeNotificacion("¡Datos modificados exitosamente!", "Ok");
            } catch(ErrorTienda ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
            tblProveedores.removeAll();
            Atras();
            
        }else{mensajeNotificacion("¡Error! Nombre en uso, cambiélo.", "Error");}}
                
        }else{
            mensajeNotificacion("Debe de rellenar todos los campos.", "Error");
        }   
        encontradoProv=false;
        txtNuevoNombreProveedor.requestFocus();
        txtNuevoNombreProveedor.selectAll();
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblAtras = new javax.swing.JLabel();
        jpnCompras = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtIDProveedor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNuevoDireccionProveedor = new javax.swing.JTextField();
        txtNuevoNombreProveedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtNuevoTelefono = new javax.swing.JFormattedTextField();
        txtNuevoNITProveedor = new javax.swing.JTextField();
        btnGuardarModificarProveedor = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtNuevoEmailProveedor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNuevoNRCProveedor = new javax.swing.JTextField();

        jScrollPane1.setEnabled(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setRequestFocusEnabled(false);

        tblProveedores =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idProveedor", "Nombre", "Teléfono", "Dirección", "NIT", "E-Mail", "NRC"
            }
        ));
        tblProveedores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProveedores);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        lblAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Atras.png"))); // NOI18N
        lblAtras.setToolTipText("Volver Atrás");
        lblAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 50, 40));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 55));

        jpnCompras.setBackground(new java.awt.Color(0, 0, 0));
        jpnCompras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpnCompras.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 20, 50));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Modificar un Proveedor:");
        jpnCompras.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, -1, 30));

        txtIDProveedor.setEditable(false);
        jpnCompras.add(txtIDProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 12, 90, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ID:");
        jpnCompras.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 12, -1, 30));

        getContentPane().add(jpnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        txtNuevoDireccionProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoDireccionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoDireccionProveedorActionPerformed(evt);
            }
        });
        txtNuevoDireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoDireccionProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 410, 30));

        txtNuevoNombreProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoNombreProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNuevoNombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoNombreProveedorActionPerformed(evt);
            }
        });
        txtNuevoNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNombreProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 410, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nombre:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Teléfono:");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Dirección:");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, 20));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("NIT:");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, 20));

        txtNuevoTelefono.setForeground(new java.awt.Color(102, 0, 0));
        try {
            txtNuevoTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNuevoTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txtNuevoTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 230, 30));

        txtNuevoNITProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoNITProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoNITProveedorActionPerformed(evt);
            }
        });
        txtNuevoNITProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNITProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoNITProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 200, 30));

        btnGuardarModificarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png"))); // NOI18N
        btnGuardarModificarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarModificarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarModificarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarModificarProveedorMouseExited(evt);
            }
        });
        btnGuardarModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarModificarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, 110, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("E-Mail:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, -1, 20));

        txtNuevoEmailProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoEmailProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoEmailProveedorActionPerformed(evt);
            }
        });
        txtNuevoEmailProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoEmailProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoEmailProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 230, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("NRC:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 560, -1, 20));

        txtNuevoNRCProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoNRCProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNRCProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoNRCProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 560, 230, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
       Atras();
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void txtNuevoDireccionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoDireccionProveedorActionPerformed
        txtNuevoNITProveedor.requestFocus();
        txtNuevoNITProveedor.selectAll();

    }//GEN-LAST:event_txtNuevoDireccionProveedorActionPerformed

    private void txtNuevoDireccionProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoDireccionProveedorKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtNuevoDireccionProveedorKeyTyped

    private void txtNuevoNombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoNombreProveedorActionPerformed
        txtNuevoTelefono.requestFocus();
        txtNuevoTelefono.selectAll();
    }//GEN-LAST:event_txtNuevoNombreProveedorActionPerformed

    private void txtNuevoNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNombreProveedorKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtNuevoNombreProveedorKeyTyped

    private void txtNuevoTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoTelefonoActionPerformed
        txtNuevoDireccionProveedor.requestFocus();
        txtNuevoDireccionProveedor.selectAll();

    }//GEN-LAST:event_txtNuevoTelefonoActionPerformed

    private void txtNuevoNITProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNITProveedorKeyTyped
        char c = evt.getKeyChar();
        if(txtNuevoNITProveedor.getText().length()>=14){
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
    }//GEN-LAST:event_txtNuevoNITProveedorKeyTyped

    private void btnGuardarModificarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedorMouseEntered
        btnGuardarModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarModificarProveedorMouseEntered

    private void btnGuardarModificarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedorMouseExited
        btnGuardarModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarModificarProveedorMouseExited

    private void btnGuardarModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedorActionPerformed
        GuardarModificacion();
    }//GEN-LAST:event_btnGuardarModificarProveedorActionPerformed

    private void txtNuevoEmailProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoEmailProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoEmailProveedorKeyTyped

    private void txtNuevoNRCProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNRCProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoNRCProveedorKeyTyped

    private void txtNuevoNITProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoNITProveedorActionPerformed
        txtNuevoEmailProveedor.requestFocus();
        txtNuevoEmailProveedor.selectAll();
    }//GEN-LAST:event_txtNuevoNITProveedorActionPerformed

    private void txtNuevoEmailProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoEmailProveedorActionPerformed
        txtNuevoNRCProveedor.requestFocus();
        txtNuevoNRCProveedor.selectAll();
    }//GEN-LAST:event_txtNuevoEmailProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(frmProveedoresModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProveedoresModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarModificarProveedor;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCompras;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTable tblProveedores;
    public static javax.swing.JTextField txtIDProveedor;
    public static javax.swing.JTextField txtNuevoDireccionProveedor;
    public static javax.swing.JTextField txtNuevoEmailProveedor;
    public static javax.swing.JTextField txtNuevoNITProveedor;
    public static javax.swing.JTextField txtNuevoNRCProveedor;
    public static javax.swing.JTextField txtNuevoNombreProveedor;
    public static javax.swing.JFormattedTextField txtNuevoTelefono;
    // End of variables declaration//GEN-END:variables
}

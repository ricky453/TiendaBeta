/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorProveedor;
import clases.ErrorTienda;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import clases.ErrorTienda;
import clases.Proveedor;
import clases.ControladorProveedor;
import formularios.frmProveedores;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricky
 */
public class frmProveedoresAgregar extends javax.swing.JFrame {

    boolean encontradoProv;
    DefaultTableModel modeloProveedores= new DefaultTableModel();
    
    public frmProveedoresAgregar() {
        initComponents();
        limpiandoTxtProveedor();
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
    
    public void limpiandoTxtProveedor(){
        txtIDProveedor.setText("");
        txtNombreProveedor.setText("");
        txtNITProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtEmailProveedor.setText("");
        txtNRCProveedor.setText("");
        txtNombreProveedor.requestFocus();
        int idProv;
        try {
            idProv = ControladorProveedor.ObtenerIdProveedor();
            idProv = idProv+1;
            txtIDProveedor.setText(""+idProv);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void guardarDatos(){
        Proveedor agregado=new Proveedor();
        int idProv;
        encontradoProv = false;
        if (txtDireccionProveedor.getText().equals("") || txtNombreProveedor.getText().equals("") || txtNITProveedor.getText().equals("") || txtTelefonoProveedor.getText().equals("") || txtEmailProveedor.getText().equals("") || txtNRCProveedor.getText().equals("")) {
            mensajeNotificacion("Debe de rellenar todos los campos.", "Error");
        }
        else{
            if(txtNITProveedor.getText().length() != 14){
                mensajeNotificacion("El NIT debe de contener 14 digitos.", "Adv");
                txtNITProveedor.requestFocus();
                txtNITProveedor.selectAll();
            }
            else{
            try {
                idProv = ControladorProveedor.ObtenerIdProveedor();
                agregado.setIdProveedor(idProv+1);
            } catch (ErrorTienda ex) {
                Logger.getLogger(frmProveedoresAgregar.class.getName()).log(Level.SEVERE, null, ex);
              }
              buscarRepetidos();
              if (tblProveedores.getRowCount()>0) {
                  int i = 0;
                     while (encontradoProv==false&&i<tblProveedores.getRowCount()) {
                     encontradoProv = tblProveedores.getValueAt(i, 1).equals(txtNombreProveedor.getText());
                     i++;
                  }
              }
              if(encontradoProv == false){
                  agregado.setNombre(txtNombreProveedor.getText());
                  agregado.setTelefono(txtTelefonoProveedor.getText());
                  agregado.setNIT(txtNITProveedor.getText());
                  agregado.setDireccion(txtDireccionProveedor.getText());
                  agregado.setEmail(txtEmailProveedor.getText());
                  agregado.setNRC(txtNRCProveedor.getText());
              try {
              ControladorProveedor.Agregar(agregado);
              mensajeNotificacion("¡Proveedor agregado exitosamente!", "Ok");
              limpiandoTxtProveedor();
              //tblProveedores.removeAll();
             //actualizarTablaProveedor();
              
              } catch (ErrorTienda e) {      
           }
        }else{mensajeNotificacion("¡Error! Nombre en uso, cambiélo.", "Error");}
              encontradoProv=false;
              txtNombreProveedor.requestFocus();
              txtNombreProveedor.selectAll();
                
        }
        }
        //LlenarCompra();
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
        jLabel8 = new javax.swing.JLabel();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JFormattedTextField();
        txtNITProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtEmailProveedor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNRCProveedor = new javax.swing.JTextField();

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
        setPreferredSize(new java.awt.Dimension(1200, 700));
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
        jLabel34.setText("Agregar un Proveedor:");
        jpnCompras.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, -1, 30));

        txtIDProveedor.setEditable(false);
        txtIDProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDProveedorKeyTyped(evt);
            }
        });
        jpnCompras.add(txtIDProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID:");
        jpnCompras.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, 30));

        getContentPane().add(jpnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        txtDireccionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionProveedorActionPerformed(evt);
            }
        });
        txtDireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 410, 30));

        txtNombreProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProveedorActionPerformed(evt);
            }
        });
        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 410, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombre:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Teléfono:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Dirección:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, -1, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("NIT:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, 20));

        try {
            txtTelefonoProveedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefonoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, 230, 30));

        txtNITProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNITProveedorActionPerformed(evt);
            }
        });
        txtNITProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNITProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNITProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 230, 30));

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png"))); // NOI18N
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarProveedorMouseExited(evt);
            }
        });
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 620, 110, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("E-Mail:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, -1, 20));

        txtEmailProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailProveedorActionPerformed(evt);
            }
        });
        txtEmailProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtEmailProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 230, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("NRC:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 560, -1, 20));

        txtNRCProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNRCProveedorActionPerformed(evt);
            }
        });
        txtNRCProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNRCProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNRCProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, 230, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        frmProveedores pv = new frmProveedores();
        pv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void txtDireccionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionProveedorActionPerformed
        txtNITProveedor.requestFocus();
    }//GEN-LAST:event_txtDireccionProveedorActionPerformed

    private void txtDireccionProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionProveedorKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtDireccionProveedorKeyTyped

    private void txtNombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProveedorActionPerformed
        txtTelefonoProveedor.requestFocus();
    }//GEN-LAST:event_txtNombreProveedorActionPerformed

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtTelefonoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorActionPerformed
        txtDireccionProveedor.requestFocus();
    }//GEN-LAST:event_txtTelefonoProveedorActionPerformed

    private void txtNITProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNITProveedorKeyTyped
        char c = evt.getKeyChar();
        if(txtNITProveedor.getText().length()>=14){
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
    }//GEN-LAST:event_txtNITProveedorKeyTyped

    private void btnGuardarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarProveedorMouseEntered
        btnGuardarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarProveedorMouseEntered

    private void btnGuardarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarProveedorMouseExited
        btnGuardarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarProveedorMouseExited

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void txtIDProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDProveedorKeyTyped

    }//GEN-LAST:event_txtIDProveedorKeyTyped

    private void txtEmailProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailProveedorKeyTyped

    private void txtNRCProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNRCProveedorKeyTyped
 char c = evt.getKeyChar();
        if(txtNRCProveedor.getText().length()>=7){
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

        // TODO add your handling code here:
    }//GEN-LAST:event_txtNRCProveedorKeyTyped

    private void txtNITProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNITProveedorActionPerformed
        txtEmailProveedor.requestFocus();
    }//GEN-LAST:event_txtNITProveedorActionPerformed

    private void txtEmailProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailProveedorActionPerformed
        txtNRCProveedor.requestFocus();
    }//GEN-LAST:event_txtEmailProveedorActionPerformed

    private void txtNRCProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNRCProveedorActionPerformed
        int Agregar = JOptionPane.showConfirmDialog(null, "¿Desea agregar el Proveedor?", "Agregar Proveedor",JOptionPane.YES_NO_OPTION);
        if(Agregar == 0){
            guardarDatos();
        }
    }//GEN-LAST:event_txtNRCProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(frmProveedoresAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProveedoresAgregar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCompras;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtEmailProveedor;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtNITProveedor;
    private javax.swing.JTextField txtNRCProveedor;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JFormattedTextField txtTelefonoProveedor;
    // End of variables declaration//GEN-END:variables
}

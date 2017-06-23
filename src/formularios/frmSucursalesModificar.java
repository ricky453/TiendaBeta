/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorSucursal;
import clases.ErrorTienda;
import clases.Sucursal;
import static formularios.frmProveedoresModificar.txtNuevoNITProveedor;
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
public class frmSucursalesModificar extends javax.swing.JFrame {

    public static String nombre;
    boolean encontradoSuc;
    DefaultTableModel modeloSucursales= new DefaultTableModel();
    
    public frmSucursalesModificar() {
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
    }

    public void Atras(){
        frmSucursales su = new frmSucursales();
        su.setVisible(true);
        this.setVisible(false);
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
              //---------------------------Busca datos repetidos----------------------------------------
    public void buscarRepetidos(){
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
    
    public void GuardarModificacion(){
        encontradoSuc = false;
        if (!txtNuevoDireccionSucursal.getText().equals("") && !txtNuevoNombreSucursal.getText().equals("") && !txtNuevoTelefonoSucursal.getText().equals("")) {
                buscarRepetidos();
            if (tblSucursales.getRowCount()>0) {
                  int i = 0;
                  while (encontradoSuc==false&&i<tblSucursales.getRowCount()) {
                     encontradoSuc = tblSucursales.getValueAt(i, 1).equals(txtNuevoNombreSucursal.getText());
                     i++;
                  }
            }
            if(txtNuevoNombreSucursal.getText().equals(nombre)){
                encontradoSuc = false;
            }
            if(encontradoSuc == false){
            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal(Integer.parseInt(txtIDSucursal.getText()));
            sucursal.setNombre(txtNuevoNombreSucursal.getText());
            sucursal.setTelefono(txtNuevoTelefonoSucursal.getText());
            sucursal.setDireccion(txtNuevoDireccionSucursal.getText());
            try{
                ControladorSucursal.modificarSucursal(sucursal);
                mensajeNotificacion("¡Datos modificados exitosamente!", "Ok");
            } catch(ErrorTienda ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
            tblSucursales.removeAll();
            Atras();
            
        }else{mensajeNotificacion("¡Error! Nombre en uso, cambiélo.", "Error");}
                
        }else{
            mensajeNotificacion("Debe de rellenar todos los campos.", "Error");
        }   
        encontradoSuc=false;
        txtNuevoNombreSucursal.requestFocus();
        txtNuevoNombreSucursal.selectAll();
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSucursales = new javax.swing.JTable();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblAtras = new javax.swing.JLabel();
        jpnCompras = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtIDSucursal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNuevoDireccionSucursal = new javax.swing.JTextField();
        txtNuevoNombreSucursal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNuevoTelefonoSucursal = new javax.swing.JFormattedTextField();
        btnGuardarModificarSucursal = new javax.swing.JButton();

        tblSucursales =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblSucursales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idSucursal", "Sucursal", "Teléfono", "Dirección"
            }
        ));
        tblSucursales.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblSucursales);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 700));
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
        jLabel34.setText("Modificar una Sucursal:");
        jpnCompras.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, -1, 30));

        txtIDSucursal.setEditable(false);
        txtIDSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDSucursalKeyTyped(evt);
            }
        });
        jpnCompras.add(txtIDSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID:");
        jpnCompras.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, 30));

        getContentPane().add(jpnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        txtNuevoDireccionSucursal.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoDireccionSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoDireccionSucursalActionPerformed(evt);
            }
        });
        txtNuevoDireccionSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoDireccionSucursalKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoDireccionSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, 410, 30));

        txtNuevoNombreSucursal.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoNombreSucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNuevoNombreSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoNombreSucursalActionPerformed(evt);
            }
        });
        txtNuevoNombreSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNombreSucursalKeyTyped(evt);
            }
        });
        getContentPane().add(txtNuevoNombreSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 410, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombre:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Teléfono:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, -1, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Dirección:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, 20));

        txtNuevoTelefonoSucursal.setForeground(new java.awt.Color(102, 0, 0));
        try {
            txtNuevoTelefonoSucursal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNuevoTelefonoSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoTelefonoSucursalActionPerformed(evt);
            }
        });
        getContentPane().add(txtNuevoTelefonoSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 230, 30));

        btnGuardarModificarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png"))); // NOI18N
        btnGuardarModificarSucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarModificarSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarModificarSucursalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarModificarSucursalMouseExited(evt);
            }
        });
        btnGuardarModificarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarModificarSucursalActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarModificarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, 110, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        Atras();
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void txtIDSucursalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDSucursalKeyTyped

    }//GEN-LAST:event_txtIDSucursalKeyTyped

    private void txtNuevoDireccionSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoDireccionSucursalActionPerformed
        txtNuevoTelefonoSucursal.requestFocus();
        txtNuevoTelefonoSucursal.selectAll();
    }//GEN-LAST:event_txtNuevoDireccionSucursalActionPerformed

    private void txtNuevoDireccionSucursalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoDireccionSucursalKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtNuevoDireccionSucursalKeyTyped

    private void txtNuevoNombreSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoNombreSucursalActionPerformed
        txtNuevoDireccionSucursal.requestFocus();
        txtNuevoDireccionSucursal.selectAll();
    }//GEN-LAST:event_txtNuevoNombreSucursalActionPerformed

    private void txtNuevoNombreSucursalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNombreSucursalKeyTyped
        char mayu=evt.getKeyChar();
        if (Character.isLowerCase(mayu)) {
            String cadena=(""+mayu).toUpperCase();
            mayu=cadena.charAt(0);
            evt.setKeyChar(mayu);
        }
        else{

        }
    }//GEN-LAST:event_txtNuevoNombreSucursalKeyTyped

    private void txtNuevoTelefonoSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoTelefonoSucursalActionPerformed

    }//GEN-LAST:event_txtNuevoTelefonoSucursalActionPerformed

    private void btnGuardarModificarSucursalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarSucursalMouseEntered
        btnGuardarModificarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarModificarSucursalMouseEntered

    private void btnGuardarModificarSucursalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarSucursalMouseExited
        btnGuardarModificarSucursal.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarModificarSucursalMouseExited

    private void btnGuardarModificarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarModificarSucursalActionPerformed
        GuardarModificacion();
    }//GEN-LAST:event_btnGuardarModificarSucursalActionPerformed

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
            java.util.logging.Logger.getLogger(frmSucursalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSucursalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSucursalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSucursalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSucursalesModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarModificarSucursal;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCompras;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTable tblSucursales;
    public static javax.swing.JTextField txtIDSucursal;
    public static javax.swing.JTextField txtNuevoDireccionSucursal;
    public static javax.swing.JTextField txtNuevoNombreSucursal;
    public static javax.swing.JFormattedTextField txtNuevoTelefonoSucursal;
    // End of variables declaration//GEN-END:variables
}

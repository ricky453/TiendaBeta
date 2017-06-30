/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorParametro;
import clases.ErrorTienda;
import clases.Parametro;
import formularios.frmCompras;
import formularios.frmHome;
import formularios.frmProductos;
import formularios.frmProveedores;
import formularios.frmSucursales;
import formularios.frmVentas;
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

/**
 *
 * @author Ricky
 */
public class frmParametroModificar extends javax.swing.JFrame {

    boolean estadoMenu;
    boolean encontradopara;
        DefaultTableModel modelopara= new DefaultTableModel();

    
    public frmParametroModificar() {
        initComponents();
        this.setSize(1200, 700);
    }

     public void buscarRepetidos(){
            modelopara.setRowCount(0);
            
            ArrayList<Parametro> listaparametro=new ArrayList();
            Object fila[]=new Object[4];
            
        
            try {
            listaparametro=ControladorParametro.Obtener();
            String[] nombreSucursal = new String []{"IdParametro","Nombre","Valor",};
            modelopara.setColumnIdentifiers(nombreSucursal);
            Iterator<Parametro> par=listaparametro.iterator();
                while(par.hasNext()){
                    fila[0]= par.next();
                    fila[1]= par.next();
                    fila[2]= par.next();
                    fila[3]= par.next();
                    modelopara.addRow(fila);
                    tblParametro.setModel(modelopara);
                }
            }
            
         catch (ErrorTienda ex) {
             Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
            
        
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

        jScrollPane3 = new javax.swing.JScrollPane();
        tblParametro = new javax.swing.JTable();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblAtras = new javax.swing.JLabel();
        jpnCompras = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtIDParametro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnGuardarModificarParametro = new javax.swing.JButton();
        txtValorParametro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();

        tblParametro =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblParametro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdParametro", "Nombre", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblParametro.getTableHeader().setReorderingAllowed(false);
        tblParametro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParametroMouseClicked(evt);
            }
        });
        tblParametro.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblParametroInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tblParametro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblParametroKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblParametro);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1200, 700));
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
        jLabel34.setText("Modificar el Parámetro:");
        jpnCompras.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, -1, 30));

        txtIDParametro.setEditable(false);
        txtIDParametro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDParametroKeyTyped(evt);
            }
        });
        jpnCompras.add(txtIDParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 12, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID:");
        jpnCompras.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, 30));

        getContentPane().add(jpnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        txtNombre.setForeground(new java.awt.Color(102, 0, 0));
        txtNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 410, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nombre:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, 20));

        btnGuardarModificarParametro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png"))); // NOI18N
        btnGuardarModificarParametro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarModificarParametro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarModificarParametroMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarModificarParametroMouseEntered(evt);
            }
        });
        btnGuardarModificarParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarModificarParametroActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarModificarParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, 110, 30));

        txtValorParametro.setForeground(new java.awt.Color(102, 0, 0));
        txtValorParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorParametroActionPerformed(evt);
            }
        });
        txtValorParametro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorParametroKeyTyped(evt);
            }
        });
        getContentPane().add(txtValorParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 410, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Valor:");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        txtValorParametro.requestFocus();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
   int c=(int) evt.getKeyChar();
        char mayu=evt.getKeyChar();

        if ((c>=65 && c<=90) || (c>=97 && c<=122)  || (c==32) || (c==8)  || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
            if (Character.isLowerCase(mayu)) {
                String cadena=(""+mayu).toUpperCase();
                mayu=cadena.charAt(0);
                evt.setKeyChar(mayu);
            }
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    
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
    private void btnGuardarModificarParametroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarParametroMouseEntered
        btnGuardarModificarParametro.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarModificarParametroMouseEntered

    private void btnGuardarModificarParametroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarParametroMouseExited
        btnGuardarModificarParametro.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarModificarParametroMouseExited

    private void btnGuardarModificarParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarModificarParametroActionPerformed
         Parametro pa= new Parametro();
        try {
            pa.setIdParametro(Integer.parseInt(txtIDParametro.getText()));
            pa.setNombre(txtNombre.getText());
            pa.setValor(txtValorParametro.getText());
            ControladorParametro.Modificar(pa);
            mensajeNotificacion("Parametro modificado con exito","Ok");
            txtIDParametro.setText("");
            txtNombre.setText("");
            txtValorParametro.setText("");
            
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmParametroModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
    }//GEN-LAST:event_btnGuardarModificarParametroActionPerformed

    private void txtValorParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorParametroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorParametroActionPerformed

    private void txtValorParametroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorParametroKeyTyped
        // TODO add your handling code here:
        
           int c=(int) evt.getKeyChar();

        if ((c >=48 && c<=57) || (c==8) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
            //No pasa nada
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }

        
    }//GEN-LAST:event_txtValorParametroKeyTyped

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        frmParametro pa = new frmParametro();
        pa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void txtIDParametroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDParametroKeyTyped

    }//GEN-LAST:event_txtIDParametroKeyTyped

    private void tblParametroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParametroMouseClicked

    }//GEN-LAST:event_tblParametroMouseClicked

    private void tblParametroInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblParametroInputMethodTextChanged

    }//GEN-LAST:event_tblParametroInputMethodTextChanged

    private void tblParametroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblParametroKeyTyped

    }//GEN-LAST:event_tblParametroKeyTyped

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
            java.util.logging.Logger.getLogger(frmParametroModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmParametroModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmParametroModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmParametroModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frmParametroModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarModificarParametro;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCompras;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblLogo;
    public javax.swing.JTable tblParametro;
    public static javax.swing.JTextField txtIDParametro;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtValorParametro;
    // End of variables declaration//GEN-END:variables
}

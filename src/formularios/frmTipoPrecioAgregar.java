/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.ControladorParametro;
import clases.ControladorTipoPrecio;
import clases.ErrorTienda;
import clases.TipoPrecio;
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
 * @author Oscar
 */
public class frmTipoPrecioAgregar extends javax.swing.JFrame {

    boolean estadoMenu;
    boolean encontrado;
    public DefaultTableModel modelotipoprecio= new DefaultTableModel();

    
    public frmTipoPrecioAgregar() {
        initComponents();
        this.setSize(1200, 700);
        limpiando();
    }

     public void limpiando(){
         
         txtUtilidadTipoPrecio.setText("");
         txtNombreTipoPrecio.setText("");
         txtIDTipoPrecio.setText("");
        
        txtNombreTipoPrecio.requestFocus();
        int idtp;
        try {
            idtp = ControladorTipoPrecio.ObtenerIdMax();
            idtp = idtp+1;
            txtIDTipoPrecio.setText(""+idtp);
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     
      public void buscarRepetidos(){
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
     
     
     public void guardarDatos() throws ErrorTienda{
        TipoPrecio agregado=new TipoPrecio();
        int idTipoPrecio;
        encontrado=false;
        if (txtNombreTipoPrecio.getText().equals("") || txtUtilidadTipoPrecio.getText().equals("") ) {
            mensajeNotificacion("Debe de rellenar todos los campos.", "Error");
        }
        else{
              buscarRepetidos();
              if (tblTipoPrecio.getRowCount()>0) {
                  int i = 0;
                     while (encontrado==false&&i<tblTipoPrecio.getRowCount()) {
                     encontrado = tblTipoPrecio.getValueAt(i, 1).equals(txtNombreTipoPrecio.getText());
                     System.out.println(tblTipoPrecio.getValueAt(i, 1).toString()+" - "+txtNombreTipoPrecio.getText());
                     i++;
                  }
              }
              if(encontrado == false){
            //PARA VALIDAR QUE EL PORCENTAJE DE UTILIDAD NO SEA MENOR A 0 NI MAYOR A 1
            if (Integer.parseInt(txtUtilidadTipoPrecio.getText())>100 || Integer.parseInt(txtUtilidadTipoPrecio.getText())<0) {
                mensajeNotificacion("El porcentaje de utilidad es incorrecto", "Adv");
            } else {
                idTipoPrecio = ControladorTipoPrecio.ObtenerIdMax();
                agregado.setIdTipoPrecio(idTipoPrecio+1);
            agregado.setNombre(txtNombreTipoPrecio.getText());
            agregado.setUtilidad(Double.parseDouble(txtUtilidadTipoPrecio.getText()));
            ControladorTipoPrecio.AgregarTipoPrecio(agregado);
            mensajeNotificacion("Registro guardado con exito", "Ok");
            limpiando();
            frmTipoPrecio tp=new frmTipoPrecio();
            tp.setVisible(true);
            this.setVisible(false);
            }          
        }else{mensajeNotificacion("¡Error! Nombre en uso, cambiélo.", "Error");}
            encontrado=false;
            txtNombreTipoPrecio.requestFocus();
            txtNombreTipoPrecio.selectAll();
        }         
        //LlenarCompra();
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
        tblTipoPrecio = new javax.swing.JTable();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblAtras = new javax.swing.JLabel();
        jpnCompras = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtIDTipoPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombreTipoPrecio = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnGuardarTipoPrecio = new javax.swing.JButton();
        txtUtilidadTipoPrecio = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();

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
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblTipoPrecioInputMethodTextChanged(evt);
            }
        });
        tblTipoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblTipoPrecioKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblTipoPrecio);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/home/lanzador.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1200, 700));
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
        jLabel34.setText("Agregar Tipo de Precio:");
        jpnCompras.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, -1, 30));

        txtIDTipoPrecio.setEditable(false);
        txtIDTipoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDTipoPrecioKeyTyped(evt);
            }
        });
        jpnCompras.add(txtIDTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 12, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID:");
        jpnCompras.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, 30));

        getContentPane().add(jpnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1200, 50));

        txtNombreTipoPrecio.setForeground(new java.awt.Color(102, 0, 0));
        txtNombreTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNombreTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreTipoPrecioActionPerformed(evt);
            }
        });
        txtNombreTipoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreTipoPrecioKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 410, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nombre:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, 20));

        btnGuardarTipoPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png"))); // NOI18N
        btnGuardarTipoPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarTipoPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarTipoPrecioMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarTipoPrecioMouseEntered(evt);
            }
        });
        btnGuardarTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, 110, 30));

        txtUtilidadTipoPrecio.setForeground(new java.awt.Color(102, 0, 0));
        txtUtilidadTipoPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUtilidadTipoPrecioActionPerformed(evt);
            }
        });
        txtUtilidadTipoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUtilidadTipoPrecioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUtilidadTipoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 410, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Utilidad:");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreTipoPrecioActionPerformed
        txtUtilidadTipoPrecio.requestFocus();
        txtUtilidadTipoPrecio.selectAll();
    }//GEN-LAST:event_txtNombreTipoPrecioActionPerformed

    private void txtNombreTipoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreTipoPrecioKeyTyped
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
    }//GEN-LAST:event_txtNombreTipoPrecioKeyTyped

    private void btnGuardarTipoPrecioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarTipoPrecioMouseEntered
        btnGuardarTipoPrecio.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarTipoPrecioMouseEntered

    private void btnGuardarTipoPrecioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarTipoPrecioMouseExited
        btnGuardarTipoPrecio.setIcon(new ImageIcon(getClass().getResource("/iconos/botones/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarTipoPrecioMouseExited

    private void btnGuardarTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoPrecioActionPerformed
        try {
            guardarDatos();
        } catch (ErrorTienda ex) {
            Logger.getLogger(frmTipoPrecioAgregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarTipoPrecioActionPerformed

    private void txtUtilidadTipoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUtilidadTipoPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadTipoPrecioActionPerformed

    private void txtUtilidadTipoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadTipoPrecioKeyTyped
        int c=(int) evt.getKeyChar();

        if ((c >=48 && c<=57) || (c==8) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
            //No pasa nada
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtUtilidadTipoPrecioKeyTyped

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        frmTipoPrecio tp = new frmTipoPrecio();
        tp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged

    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed

    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void txtIDTipoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDTipoPrecioKeyTyped

    }//GEN-LAST:event_txtIDTipoPrecioKeyTyped

    private void tblTipoPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoPrecioMouseClicked

    }//GEN-LAST:event_tblTipoPrecioMouseClicked

    private void tblTipoPrecioInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblTipoPrecioInputMethodTextChanged

    }//GEN-LAST:event_tblTipoPrecioInputMethodTextChanged

    private void tblTipoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTipoPrecioKeyTyped

    }//GEN-LAST:event_tblTipoPrecioKeyTyped

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
            java.util.logging.Logger.getLogger(frmTipoPrecioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTipoPrecioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTipoPrecioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTipoPrecioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTipoPrecioAgregar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarTipoPrecio;
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
    public javax.swing.JTable tblTipoPrecio;
    public static javax.swing.JTextField txtIDTipoPrecio;
    private javax.swing.JTextField txtNombreTipoPrecio;
    private javax.swing.JTextField txtUtilidadTipoPrecio;
    // End of variables declaration//GEN-END:variables
}

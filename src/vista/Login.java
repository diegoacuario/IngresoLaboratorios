package vista;

import controlador.Funciones;
import controlador.FuncionesUsuario;
import controlador.Hilo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.swing.JButton;
import modelo.Bloquea;
import javax.swing.JOptionPane;
import modelo.Usuarios;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class Login extends javax.swing.JFrame {

    public final Properties fileConfig;
    private final String ip;
    private Usuarios u;
    private String thisIp = null;
    private String rest = null;
    private String restIp = null;
    private Hilo hilo = null;

    boolean isConected = false;

    /**
     * Creates new form jFrameBlocked
     */
    public Login() {
        fileConfig = Funciones.getFileProperties("classes/confi.properties");
        this.ip = fileConfig.getProperty("ip_servidor");
        this.rest = fileConfig.getProperty("servicio_web");

        this.setUndecorated(true);//quita bordes a jframe

        try {
            thisIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {

        }
        initComponents();
        conectar();

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//evita cerra jframe con ALT+C
        this.setExtendedState(MAXIMIZED_BOTH);//maximizado
        this.setAlwaysOnTop(true);//siempre al frente       
        //nueva instancia de Bloquea pasando como parametros e este JFrame
        new Bloquea(this).block();

    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(JButton btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public void conectar() {
        int port;
        try {
            port = Integer.parseInt(rest.split("/")[2].split(":")[1]);
        } catch (Exception e) {
            port = 80;
        }
        
            restIp = rest.split("/")[2].split(":")[0];
       // if (!ip.equals(thisIp)) {
            hilo = new Hilo(thisIp, this, null, restIp, port);
            hilo.start();
             if (!ip.equals(thisIp)) {
            btnRegistrar.setVisible(false);
//        } else {
//
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Cédula: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(13, 15, 10, 3);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jLabel2.setText("Contraseña: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(13, 15, 10, 3);
        jPanel2.add(jLabel2, gridBagConstraints);

        user.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        user.setPreferredSize(new java.awt.Dimension(200, 32));
        user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 10, 22);
        jPanel2.add(user, gridBagConstraints);

        btnSalir.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 8);
        jPanel2.add(btnSalir, gridBagConstraints);

        btnEntrar.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 8);
        jPanel2.add(btnEntrar, gridBagConstraints);

        btnRegistrar.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 8);
        jPanel2.add(btnRegistrar, gridBagConstraints);

        pass.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 10, 22);
        jPanel2.add(pass, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        new ClaveUsuario(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        Funciones f = new Funciones();
        FuncionesUsuario fu = new FuncionesUsuario();
        String url = fileConfig.getProperty("servicio_web") + "webresources/modelo.usuarios/cedula=" + user.getText() + ",clave=" + pass.getText();
        u = fu.obtieneDatosUsuario(f.obtieneJson(url));
        if (u != null) {
            if (u.getRolUsuario() == 1) {
                dispose();
                MenuAdministrador m = new MenuAdministrador(this, rootPaneCheckingEnabled, u);
                m.setVisible(true);
            } else {
                if (ip.equals(thisIp)) {
                    SeleccioneLaboratorio d = new SeleccioneLaboratorio(u);
                    d.setVisible(true);
                    this.dispose();
                } else {
                    Menu m = new Menu(null, u, null);
                    m.setVisible(true);
                    this.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña erróneos");
        }


    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        new RegistraUsuario(this, rootPaneCheckingEnabled, null, u).setVisible(true);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void passKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyTyped
        int c = evt.getKeyChar();
        if (user.getText().length() == 10 && c == 10) {
            Funciones f = new Funciones();
            FuncionesUsuario fu = new FuncionesUsuario();
            String url = fileConfig.getProperty("servicio_web") + "webresources/modelo.usuarios/cedula=" + user.getText() + ",clave=" + pass.getText();
            u = fu.obtieneDatosUsuario(f.obtieneJson(url));
            if (u != null) {
                if (u.getRolUsuario() == 1) {
                    dispose();
                    MenuAdministrador m = new MenuAdministrador(this, rootPaneCheckingEnabled, u);
                    m.setVisible(true);
                } else {
                    if (ip.equals(thisIp)) {
                        SeleccioneLaboratorio d = new SeleccioneLaboratorio(u);
                        d.setVisible(true);
                        this.dispose();
                    } else {
                        Menu m = new Menu(null, u, null);
                        m.setVisible(true);
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña erróneos");
            }
        }
    }//GEN-LAST:event_passKeyTyped

    private void userKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKeyReleased
        char c = evt.getKeyChar();

        if (user.getText().length() == 10 &&  c == 10) {
            Funciones f = new Funciones();
            FuncionesUsuario fu = new FuncionesUsuario();
            String url = fileConfig.getProperty("servicio_web") + "webresources/modelo.usuarios/cedula=" + user.getText() + ",clave=" + pass.getText();
            u = fu.obtieneDatosUsuario(f.obtieneJson(url));
            if (u != null) {
                if (u.getRolUsuario() == 1) {
                    dispose();
                    MenuAdministrador m = new MenuAdministrador(this, rootPaneCheckingEnabled, u);
                    m.setVisible(true);
                } else {
                    if (ip.equals(thisIp)) {
                        SeleccioneLaboratorio d = new SeleccioneLaboratorio(u);
                        d.setVisible(true);
                        this.dispose();
                    } else {
                        Menu m = new Menu(null, u, null);
                        m.setVisible(true);
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña erróneos");
            }
        }
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_userKeyReleased

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}

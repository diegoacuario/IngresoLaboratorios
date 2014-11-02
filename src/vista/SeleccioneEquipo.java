package vista;

import controlador.Funciones;
import controlador.FuncionesEquipo;
import controlador.FuncionesSesiones;
import controlador.HiloPing;
import java.awt.BorderLayout;
import modelo.Bloquea;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.Equipos;
import modelo.Laboratorios;
import modelo.Usuarios;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class SeleccioneEquipo extends javax.swing.JFrame {

    private final Usuarios u;
    private final Funciones f;
    private final FuncionesEquipo fl;
    private final FuncionesSesiones fs;
    private Equipos equipos[];

    public Equipos[] getEquipos() {
        return equipos;
    }

    public void setEquipos(Equipos[] equipos) {
        this.equipos = equipos;
    }
    private JButton botones[][];
    HiloPing h;

    public JButton[][] getBotones() {
        return botones;
    }

    public void setBotones(JButton[][] botones) {
        this.botones = botones;
    }

    /**
     * Creates new form SeleccioneEquipo
     *
     * @param l
     * @param u
     */
    public SeleccioneEquipo(Laboratorios l, Usuarios u) {
        this.u = u;
        this.setUndecorated(true);//quita bordes a jframe
        
        initComponents();
        f = new Funciones();
        fl = new FuncionesEquipo();
        fs = new FuncionesSesiones();
        lblNombre.setText(lblNombre.getText() + l.getNombre());
        lblNombre.setToolTipText(l.getDescripcion());

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//evita cerra jframe con ALT+C
        this.setExtendedState(MAXIMIZED_BOTH);//maximizado
        this.setAlwaysOnTop(true);//siempre al frente       
        //nueva instancia de Bloquea pasando como parametros e este JFrame
        new Bloquea(this).block();
        int idLaboratorio = l.getIdLaboratorio();
        equipos = fl.arrayToMatriz(fl.obtieneDatosEquipos(f.obtieneJsonGet(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/idLaboratorio=" + idLaboratorio)));
        int filas = 4;
        int colExtra = 1;
        if (equipos.length % 4 == 0) {
            colExtra = 0;
        }
        int col = (int) (equipos.length / 4.0f) + colExtra;
        int res = 4 - (filas * col) + equipos.length;
        int can = 0;
        botones = new JButton[filas][col];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < col; j++) {
                if (j < col - 1 || (j == col - 1 && i < res)) {
                    botones[i][j] = new JButton();
                    JLabel jlAux = new JLabel("<html><font size=6 color='blue'><b> " + equipos[can].getIp() + " </b></font></html>");
                    jlAux.setHorizontalAlignment(0);
                    botones[i][j].setLayout(new BorderLayout());
                    botones[i][j].add(jlAux, java.awt.BorderLayout.SOUTH);
                    botones[i][j].setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
                    botones[i][j].setText("Eqp-" + equipos[can].getNumero());
                    botones[i][j].setSize(200, 300);
                    botones[i][j].setBackground(Color.green);
                    botones[i][j].setToolTipText("Equipo disponible, clic para iniciar sesión");
                    if (equipos[can].getEstado() == 2) {
                        botones[i][j].setBackground(Color.yellow);
                        botones[i][j].setToolTipText("Equipo no disponible");
                    }
                    if (equipos[can].getEstado() == 1) {
                        botones[i][j].setBackground(Color.red);
                        botones[i][j].setToolTipText("Equipo ocupado");
                    }
                    can++;
                    jPanel2.add(botones[i][j]);
                    botones[i][j].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            ejecutarAlPresionarBoton(evt);
                        }
                    });
                }
            }
            jPanel2.setLayout(new java.awt.GridLayout(4, 5, 10, 10));
        }
        h = new HiloPing(this);
        h.start();
    }

    private void ejecutarAlPresionarBoton(java.awt.event.ActionEvent evt) {
        JButton boton = (JButton) evt.getSource();
        if (boton.getBackground().equals(Color.green)) {
            boton.setBackground(Color.red);
            boton.setToolTipText("Equipo ocupado");
            int x = JOptionPane.showConfirmDialog(rootPane, "Ingrese al laboratorio y ocupe el equipo número " + boton.getText().split(": ")[0].split("-")[1]
                    + ",\n desea cerrar sesión en este equipo por seguridad.");

            if (x == 2) {
                boton.setBackground(Color.green);
                boton.setToolTipText("Equipo disponible, clic para iniciar sesión");
            } else {
                String res1;
                Equipos eqp = null;
                try {
                    int numEqp = Integer.parseInt(boton.getText().split(": ")[0].split("-")[1]);
                    for (Equipos equipo : equipos) {
                        if (numEqp == equipo.getIdEquipo()) {
                            eqp = equipo;
                        }
                    }
                    res1 = fs.registrarSesion(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.sesiones/registro/",
                            eqp.getIdEquipo(), u.getIdUsuario());
                } catch (Exception ex) {
                    res1 = "false";
                }
                if (res1.equals("false")) {
                    dispose();
                    new Login().setVisible(true);
                } else {
                    String res = "false";
                    try {
                        //poner el equipo como ocupado
                        res = fl.editarEquipo(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/editar/",
                                eqp.getIdEquipo(), 1);
                    } catch (Exception ex) {

                    }
                }
                if (x == 0) {
                    new Login().setVisible(true);
                    dispose();
                } else {
                    Component c[] = jPanel2.getComponents();
                    for (int i = 0; i < c.length; i++) {
                        c[i].setEnabled(false);
                    }
                    jButton2.setEnabled(false);
                }

            }

        } else if (boton.getBackground().equals(Color.yellow)) {
            JOptionPane.showMessageDialog(rootPane, "El equipo no esta disponible");
        } else if (boton.getBackground().equals(Color.red)) {
            JOptionPane.showMessageDialog(rootPane, "El equipo esta ocupado");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acceso correcto");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 600));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1299, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.3;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecione el equipo que desea utilizar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 100);
        jPanel1.add(jLabel1, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton1.setText("Cerrar sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton2.setText("Otros laboratorios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jButton2, gridBagConstraints);

        lblNombre.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Laboratorio: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 100);
        jPanel1.add(lblNombre, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SeleccioneLaboratorio d = new SeleccioneLaboratorio(u);
        d.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}

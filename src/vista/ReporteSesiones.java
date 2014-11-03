/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.lavantech.gui.comp.DateTimePicker;
import controlador.Funciones;
import controlador.FuncionesSesiones;
import controlador.FuncionesUsuario;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.Sesiones;
import modelo.Usuarios;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public final class ReporteSesiones extends JFrame implements Printable {

    private final DateTimePicker jdFechaIni, jdFechaFin;
    private final MenuAdministrador m;
    private final FuncionesUsuario fu;
    private final FuncionesSesiones fs;
    private final Funciones f;
    private List<Usuarios> est;
    private String s[];

    /**
     * Creates new form ReporteSesiones
     *
     * @param parent
     * @param modal
     * @param m
     */
    public ReporteSesiones(Frame parent, boolean modal, MenuAdministrador m) {
        this.m = m;
        fu = new FuncionesUsuario();
        f = new Funciones();
        fs = new FuncionesSesiones();
        jdFechaIni = new DateTimePicker(new Date(), "yyyy-MM-dd HH:mm:ss");
        jdFechaFin = new DateTimePicker(new Date(), "yyyy-MM-dd HH:mm:ss");
        initComponents();
        agregarBotonesFecha();
        recargarTabla();
    }

    public void agregarBotonesFecha() {
        jdFechaIni.setFont(new Font("Calibri Light", 0, 18));
        Date d = new Date();
        jdFechaIni.setDate(new Date(d.getYear(), d.getMonth(), d.getDate()));
        jdFechaFin.setFont(new Font("Calibri Light", 0, 18));
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jdFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jdFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jdFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jdFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbxEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                        .addGap(11, 11, 11))
        );

    }

    public void recargarEstudiantes() {
        est = fu.obtieneDatosUsuarios(f.obtieneJsonGet(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.usuarios/"));
        s = new String[est.size() + 1];
        s[0] = "Todos";
        for (int i = 0; i < est.size(); i++) {
            s[i + 1] = est.get(i).getNombres() + " " + est.get(i).getApellidos();
        }
        cbxEstudiantes.setModel(new DefaultComboBoxModel(s));
    }

    public void recargarTabla() {
        String fechaIni = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jdFechaIni.getDate());
        String fechaFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jdFechaFin.getDate());
        int sel = cbxEstudiantes.getSelectedIndex();
        if (sel == 0) {
            String json = f.obtieneJsonPost(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.sesiones/sesionesEntreFechas/",
                    new Object[][]{{"fechaIni", fechaIni}, {"fechaFin", fechaFin}});
            List<Sesiones> sesiones = fs.obtieneDatosSesiones(json);
            String cabezeras[] = {"Nombres y apellidos", "Ip", "Fecha de inicio", "Fecha de fin"};
            String datos[][] = new String[sesiones.size()][cabezeras.length];
            for (int i = 0; i < sesiones.size(); i++) {
//                datos[i][0] = sesiones.get(i).getIdUsuario().getCedula();
                datos[i][0] = sesiones.get(i).getIdUsuario().getNombres() + " " + sesiones.get(i).getIdUsuario().getApellidos();
                datos[i][1] = sesiones.get(i).getIdEquipo().getIp();
//                datos[i][2] = sesiones.get(i).getIdEquipo().getIdLaboratorio().getNombre();
                datos[i][2] = sesiones.get(i).getFechaHoraInicio();
                datos[i][3] = sesiones.get(i).getFechaHoraFin();
            }
            

            jtbDatos.setModel(new DefaultTableModel(datos, cabezeras) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            if (jtbDatos.getColumnModel().getColumnCount() > 0) {
                jtbDatos.getColumnModel().getColumn(0).setPreferredWidth(140);
                jtbDatos.getColumnModel().getColumn(1).setPreferredWidth(18);
            }
        } else {
            int idUsuario = est.get(sel - 1).getIdUsuario();
            String json = f.obtieneJsonPost(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.sesiones/sesionesEntreFechasUsuario/",
                    new Object[][]{{"fechaIni", fechaIni}, {"fechaFin", fechaFin}, {"idUsuario", idUsuario}});
            List<Sesiones> sesiones = fs.obtieneDatosSesiones(json);
            String datos[][] = new String[sesiones.size()][4];
            for (int i = 0; i < sesiones.size(); i++) {
                datos[i][0] = sesiones.get(i).getIdEquipo().getIp();
                datos[i][1] = sesiones.get(i).getIdEquipo().getIdLaboratorio().getNombre();
                datos[i][2] = sesiones.get(i).getFechaHoraInicio();
                datos[i][3] = sesiones.get(i).getFechaHoraFin();
            }
            String cabezeras[] = {"Ip", "Laboratorio", "Fecha de inicio", "Fecha de fin"};

            jtbDatos.setModel(new DefaultTableModel(datos, cabezeras) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
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
        jtbDatos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxEstudiantes = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Obtener reporte de sesiones de usuario con rol estudiante");
        setResizable(false);

        jtbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbDatos);
        if (jtbDatos.getColumnModel().getColumnCount() > 0) {
            jtbDatos.getColumnModel().getColumn(0).setPreferredWidth(60);
            jtbDatos.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtbDatos.getColumnModel().getColumn(2).setPreferredWidth(40);
            jtbDatos.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Desde: ");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Hasta: ");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Estudiante");

        cbxEstudiantes.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        cbxEstudiantes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));
        cbxEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstudiantesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11))
        );

        jButton2.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jButton1.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jButton1.setText("Obtener");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton3.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(438, 438, 438))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(684, 669));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        recargarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            job.printDialog();
            job.print();
        } catch (PrinterException ex) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstudiantesActionPerformed
        recargarTabla();
    }//GEN-LAST:event_cbxEstudiantesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        m.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxEstudiantes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbDatos;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) graphics;
        //Punto donde empezará a imprimir dentro la pagina (100, 50)
        g2d.translate(pageFormat.getImageableX() + 10, pageFormat.getImageableY() + 50);
        g2d.scale(0.9, 0.9); //Reducción de la impresión al 50%
        jScrollPane1.printAll(graphics);
        return PAGE_EXISTS;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import vista.SeleccioneEquipo;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class HiloPing extends Thread {

    private SeleccioneEquipo s;
    private final FuncionesEquipo fEqp;
    private Funciones f;

    public HiloPing(SeleccioneEquipo s) {
        //s.getBotones()[0][0].setBackground(Color.blue);
        this.s = s;
        fEqp = new FuncionesEquipo();
        f = new Funciones();
    }

    @Override
    public void run() {
        while (true) {
            int c = 0;
            for (JButton[] filaBotones : s.getBotones()) {
                for (JButton cadaBtn : filaBotones) {
                    try {
                        String texto = ((JLabel) cadaBtn.getComponent(0)).getText();
                        String ip = texto.split(" ")[3];
                        int estado = s.getEquipos()[c].getEstado();
                        if (estado == 0) {
                            if (!f.ping(ip)) {
                                String res;
                                try {
                                    res = fEqp.editarEquipoEstado(
                                            Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/editarEquipoEstado/",
                                            s.getEquipos()[c].getIdEquipo(), 2);
                                } catch (Exception ex) {
                                    res = "false";
                                }
                                if (res.equals("true")) {
                                    cadaBtn.setBackground(Color.YELLOW);
                                    cadaBtn.setToolTipText("Equipo no disponible");
                                }
                            }
                        } else if (estado == 1) {
                            cadaBtn.setBackground(Color.red);
                        } else if (estado == 2) {
                            cadaBtn.setBackground(Color.YELLOW);
                        }
                        c++;
                    } catch (NullPointerException e) {

                    }
                }
            }
            c = 0;

        }
    }

}

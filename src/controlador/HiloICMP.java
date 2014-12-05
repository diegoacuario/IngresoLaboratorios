package controlador;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import modelo.Equipos;
import vista.SeleccioneEquipo;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class HiloICMP extends Thread {

    private final SeleccioneEquipo seleccioneEquipo;
    private final FuncionesEquipo fEqp;
    private final Funciones f;

    public HiloICMP(SeleccioneEquipo s) {
        //s.getBotones()[0][0].setBackground(Color.blue);
        this.seleccioneEquipo = s;
        fEqp = new FuncionesEquipo();
        f = new Funciones();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int c = 0;
                for (JButton[] filaBotones : seleccioneEquipo.getBotones()) {
                    for (JButton cadaBoton : filaBotones) {
                        try {
                            String texto = ((JLabel) cadaBoton.getComponent(0)).getText();
                            String ip = texto.split(" ")[3];
                            Equipos equipo = fEqp.obtieneDatosEquipo(f.obtieneJsonGet(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/ip=" + ip));
                            int estado = equipo.getEstado();
                            if (estado == 0) {
                                cadaBoton.setBackground(Color.GREEN);
                                if (!f.ping(ip)) {
                                    String res;
                                    try {
                                        res = fEqp.editarEquipoEstado(
                                                Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/editar/",
                                                seleccioneEquipo.getEquipos()[c].getIdEquipo(), 2);
                                    } catch (Exception ex) {
                                        res = "false";
                                    }
                                    if (res.equals("true")) {
                                        cadaBoton.setBackground(Color.YELLOW);
                                        cadaBoton.setToolTipText("Equipo no disponible");
                                    }
                                }
                            } else if (estado == 1) {
                                cadaBoton.setBackground(Color.RED);
                            } else if (estado == 2) {
                                cadaBoton.setBackground(Color.YELLOW);
                                if (f.ping(ip)) {
                                    String res;
                                    try {
                                        res = fEqp.editarEquipoEstado(
                                                Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/editar/",
                                                seleccioneEquipo.getEquipos()[c].getIdEquipo(), 0);
                                    } catch (Exception ex) {
                                        res = "false";
                                    }
                                    if (res.equals("true")) {
                                        cadaBoton.setBackground(Color.GREEN);
                                        cadaBoton.setToolTipText("Equipo disponible");
                                    }
                                }
                            }
                            c++;
                        } catch (NullPointerException e) {

                        }
                    }
                }
                sleep(5000);
            } catch (InterruptedException ex) {

            }
        }
    }

}

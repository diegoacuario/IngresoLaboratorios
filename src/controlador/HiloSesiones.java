package controlador;

import java.io.IOException;
import java.net.Socket;
import modelo.Equipos;
import modelo.Sesiones;
import vista.Login;
import vista.Menu;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class HiloSesiones extends Thread {

    private final String miIp, ipSerWeb;
    private final int port;
    private final FuncionesSesiones fs;
    private final Login login;
    private boolean isConected;
    private final Equipos eqp;
    private Socket socket;

    public HiloSesiones(String miIp, Login login, Sesiones s, String ipSerWeb, int port, Equipos eqp) {
        this.miIp = miIp;
        this.port = port;
        this.ipSerWeb = ipSerWeb;
        this.login = login;
        this.eqp = eqp;
        isConected = false;
        fs = new FuncionesSesiones();
    }

    @Override
    public void run() {
        Sesiones s = null;
        while (s == null && eqp != null) {
            try {
                try {
                    if (!isConected) {
                        this.socket = new Socket(ipSerWeb, port);
                        this.socket = new Socket(ipSerWeb, 3306);
                        isConected = true;
                    }
                    if (isConected) {
                        String json = fs.buscarSesionesIniciadas(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.sesiones/buscarSesionesIniciadas/", miIp);
                        s = fs.obtieneDatosSesion(json);
                    }
                } catch (Exception ex) {
                    isConected = false;
                }
                if (s != null) {
                    login.dispose();
                    Menu m = new Menu(null, s.getIdUsuario(), s, eqp);
                    m.setVisible(true);
                    stop();
                }
                login.getBtnEntrar().setEnabled(isConected);
                login.getBtnRegistrar().setEnabled(isConected);
                sleep(3000);
            } catch (InterruptedException ex) {
                new Login(0).setVisible(true);
            }
        }
    }

    public void closeConexion() {
        try {
            try {
                socket.close();
                isConected = false;
            } catch (NullPointerException ex) {
            }
        } catch (IOException ex) {
        }
    }
}

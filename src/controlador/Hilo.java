/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import modelo.Sesiones;
import vista.Login;
import vista.Menu;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class Hilo extends Thread {

    private String ip;
    private String ips;
    private int port;
    private FuncionesSesiones fs;
    private Funciones f;
    private Login l;
    private boolean isConected;
    Socket s;

    public Hilo(String ip, Login l, Sesiones s, String ips, int port) {
        this.ip = ip;
        this.port = port;
        this.ips = ips;
        this.l = l;
        isConected = false;
        fs = new FuncionesSesiones();
        f = new Funciones();
    }

    public void run() {
        Sesiones s = null;
        while (s == null) {
            try {
                try {

                    if (!isConected) {
                        this.s = new Socket(ips, port);
                        this.s = new Socket(ips, 3306);
                        isConected = true;
                    }
                    if (isConected) {
                        String json = fs.buscarSesionesIniciadas(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.sesiones/buscarSesionesIniciadas/", InetAddress.getLocalHost().getHostAddress());
                        s = fs.obtieneDatosSesion(json);
                    }

                } catch (Exception ex) {
                }
                if (s != null) {
                    l.dispose();
                    Menu m = new Menu(null, s.getIdUsuario(), s);
                    m.setVisible(true);
                }
                l.getBtnEntrar().setEnabled(isConected);
                l.getBtnRegistrar().setEnabled(isConected);
                sleep(1000);
            } catch (InterruptedException ex) {
                new Login().setVisible(true);
            }
        }
    }

    public void closeConexion() {
        try {
            try {
                s.close();
                isConected = false;
                //System.err.println("Canal Cerrado con el Servidor");
            } catch (NullPointerException ex) {
            }
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
        }
    }
}

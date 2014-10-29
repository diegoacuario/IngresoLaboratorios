/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Sesiones;
import vista.Login;
import vista.Menu;
import vista.MenuEstudiante;

/**
 *
 * @author AYLEEN ROMERO PATIÑO
 */
public class Hilo extends Thread {

    private String ip;
    private FuncionesSesiones fs;
    private Funciones f;
    private Login l;

    public Hilo(String ip, Login l, Sesiones s) {
        this.ip = ip;
        this.l = l;
        fs = new FuncionesSesiones();
        f = new Funciones();
    }

    public void run() {
        Sesiones s = null;
        while (s == null) {
            try {
                try {
                    String json = fs.buscarSesionesIniciadas(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.sesiones/buscarSesionesIniciadas/", InetAddress.getLocalHost().getHostAddress());
                  
                    s = fs.obtieneDatosSesion(json);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                if (s != null) {
                    l.dispose();
                    Menu m = new Menu(null, s.getIdUsuario(),s);
                    m.setVisible(true);
                }
                sleep(3000);
            } catch (InterruptedException ex) {
                new Login().setVisible(true);
            }
        }
    }
}

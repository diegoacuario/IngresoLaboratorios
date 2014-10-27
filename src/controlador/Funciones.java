/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author AYLEEN ROMERO PATIÑO
 */
public class Funciones {
      public static Properties getFileProperties(String url) {
        Properties prop = null;
        try {
            CodeSource codeSource = Funciones.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            File jarDir = jarFile.getParentFile();
            if (jarDir != null && jarDir.isDirectory()) {
                File propFile = new File(jarDir, url);
                prop = new Properties();
                prop.load(new BufferedReader(new FileReader(propFile.getAbsoluteFile())));
            }
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo de configuracion del sistema");
            System.exit(0);
        }
        return prop;
    }
}

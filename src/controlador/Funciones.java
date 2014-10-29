/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import modelo.Sesiones;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
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

    public ArrayList<Sesiones> obtieneSesiones(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<List<Sesiones>>() {
        }.getType();
        ArrayList<Sesiones> sesiones = gson.fromJson(formatoJSON, tipoObjeto);
        return sesiones;
    }

    public String obtieneJson(String url) {
        String json = "", lin;
        try {
            URL pagina = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(pagina.openStream()));
            while ((lin = in.readLine()) != null) {
                json += lin + "\n";
            }

        } catch (IOException ex) {
        }
        return json;
    }

    public String obtieneTexto(String url) {
        String json = "", lin;
        try {
            URL pagina = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(pagina.openStream()));
            while ((lin = in.readLine()) != null) {
                json += lin;
            }

        } catch (IOException ex) {
        }
        return json;
    }

}

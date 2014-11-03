/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public String obtieneJsonGet(String url) {
        String json = "", linea;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            while ((linea = in.readLine()) != null) {
                json += linea;
            }
        } catch (Exception ex) {
            json = ex + "";
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

    public boolean ping(String ip) {
        boolean ping = false;
        String pingCmd = "ping " + ip + " -t";
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    if (inputLine.contains("Host de destino inaccesible")
                            || inputLine.contains("Tiempo de espera agotado")
                            || inputLine.contains("Request timed out")
                            || inputLine.contains("Red de destino inaccesible")
                            || inputLine.contains("Destination host unreachable")
                            || inputLine.contains("Error general")
                            || inputLine.contains("General failure")) {
                        return false;
                    }
                    if (inputLine.contains("tiempo=")
                            || inputLine.contains("time=")
                            || inputLine.contains("tiempo<1m")
                            || inputLine.contains("time<1m")) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            ping = false;
        }
        return ping;
    }

    public boolean pingOtro(String ip, Integer tiempo) {
        boolean ping = false;
        try {
            InetAddress inet = InetAddress.getByName(ip);
            try {
                if (inet.isReachable(tiempo)) {
                    ping = true;
                } else {
                    ping = false;
                }
            } catch (IOException ex) {
                ping = false;
            }
        } catch (UnknownHostException ex) {
            ping = false;
        }
        return ping;
    }

    public String completaDecimoDig(String cedula) {

        String res = "0";

        int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;
        int digito = 0;
        for (int i = 0; i < cedula.length(); i++) {
            digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
            suma += ((digito % 10) + (digito / 10));
        }
        if ((suma % 10) != 0) {
            res = (10 - (suma % 10)) + "";
        }

        return cedula + res;
    }

    public boolean validateEmail(String email) {
        try {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            // Compiles the given regular expression into a pattern.
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            // Match the given input against this pattern
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     public String obtieneJsonPost(String url, Object parametros[][]) {
        String json = "", linea;
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            String parametrosEnvia = parametros[0][0] + "=" + parametros[0][1];
            for (Object[] parametro : parametros) {
                parametrosEnvia += "&" + parametro[0] + "=" + parametro[1];
            }
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(parametrosEnvia);
                wr.flush();
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                while ((linea = in.readLine()) != null) {
                    json += linea;
                }
            }
        } catch (Exception ex) {
            json = ex + "";
        }
        return json;
    }

}

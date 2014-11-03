package controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import modelo.Equipos;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class FuncionesEquipo {

    public String registrarEquipo(String url, String ip, String mac, Integer numero, Integer idLab, Integer estado) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "ip=" + ip
                + "&mac=" + mac
                + "&numero=" + numero
                + "&idLab=" + idLab
                + "&estado=" + estado;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String editarEquipo(String url, Integer idEquipo, int estado) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "idEquipo=" + idEquipo
                + "&estado=" + estado;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public ArrayList<Equipos> obtieneDatosEquipos(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<ArrayList<Equipos>>() {
        }.getType();
        ArrayList<Equipos> lab = gson.fromJson(formatoJSON, tipoObjeto);
        return lab;
    }

    public Equipos obtieneDatosEquipo(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<Equipos>() {
        }.getType();
        Equipos lab = gson.fromJson(formatoJSON, tipoObjeto);
        return lab;
    }

    public Equipos[] arrayToArreglo(ArrayList<Equipos> eqps) {
        Equipos eqp[] = new Equipos[eqps.size()];
        for (int i = 0; i < eqp.length; i++) {
            eqp[i] = eqps.get(i);
        }
        return eqp;
    }

    public String editarEquipoDatos(String url, Integer idEquipo, Integer estado,
            String mac, String ip, Integer idLaboratorio, Integer numero) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "idEquipo=" + idEquipo
                + "&estado=" + estado
                + "&mac=" + mac
                + "&ip=" + ip
                + "&idLaboratorio=" + idLaboratorio
                + "&numero=" + numero;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String editarEquipoEstado(String url, Integer idEquipo, Integer estado) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "idEquipo=" + idEquipo
                + "&estado=" + estado;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

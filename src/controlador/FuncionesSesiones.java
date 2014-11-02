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
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import modelo.Sesiones;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class FuncionesSesiones {

    public String registrarSesion(String url, Integer idEquipo, Integer idUsuario) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "idEquipo=" + idEquipo + "&idUsuario=" + idUsuario;
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

    public Sesiones obtieneDatosSesion(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<Sesiones>() {
        }.getType();
        Sesiones sesion = gson.fromJson(formatoJSON, tipoObjeto);
        return sesion;
    }

    public List<Sesiones> obtieneDatosSesiones(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<List<Sesiones>>() {
        }.getType();
        List<Sesiones> sesiones = gson.fromJson(formatoJSON, tipoObjeto);
        for (int i = 0; i < sesiones.size(); i++) {
            Sesiones s = sesiones.get(i);
            s.setFechaHoraInicio(s.getFechaHoraInicio().substring(0, 10) + " " + s.getFechaHoraInicio().substring(11, 19));
            s.setFechaHoraFin(s.getFechaHoraFin().substring(0, 10) + " " + s.getFechaHoraFin().substring(11, 19));
            sesiones.set(i, s);
        }
        return sesiones;
    }

    public String finSesion(String url, Integer idSesion) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "idSesion=" + idSesion;
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

    public String buscarSesionesIniciadas(String url, String ip) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "ip=" + ip;
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

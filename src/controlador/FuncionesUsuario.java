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
import modelo.Usuarios;

/**
 *
 * @author AYLEEN ROMERO PATIÑO
 */
public class FuncionesUsuario {

    public String registrarUsuario(String url, String cedula, String clave, String nombres, String apellidos,
            String correo, String celular, int rolUsuario) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "cedula=" + cedula
                + "&clave=" + clave
                + "&nombres=" + nombres
                + "&apellidos=" + apellidos
                + "&correo=" + correo
                + "&celular=" + celular
                + "&rolUsuario=" + rolUsuario;
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

    public String editarUsuario(String url, int idUsuario, String cedula, String clave, String nombres, String apellidos,
            String correo, String celular, int rolUsuario) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "cedula=" + cedula + "&idUsuario=" + idUsuario
                + "&clave=" + clave
                + "&nombres=" + nombres
                + "&apellidos=" + apellidos
                + "&correo=" + correo
                + "&celular=" + celular
                + "&rolUsuario=" + rolUsuario;
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

    public Usuarios obtieneDatosUsuario(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<Usuarios>() {
        }.getType();
        Usuarios usuario = gson.fromJson(formatoJSON, tipoObjeto);
        return usuario;
    }
}

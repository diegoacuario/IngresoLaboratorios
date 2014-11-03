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
import modelo.Laboratorios;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class FuncionesLaboratorio {

    public String registrarLaboratorio(String url, String cod, String nom, String des) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "cod=" + cod
                + "&nom=" + nom
                + "&des=" + des;
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

    public ArrayList<Laboratorios> obtieneDatosLaboratorios(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<ArrayList<Laboratorios>>() {
        }.getType();
        ArrayList<Laboratorios> lab = gson.fromJson(formatoJSON, tipoObjeto);
        return lab;
    }

    public Laboratorios obtieneDatosLaboratorio(String formatoJSON) {
        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<Laboratorios>() {
        }.getType();
        Laboratorios lab = gson.fromJson(formatoJSON, tipoObjeto);
        return lab;
    }

    public Laboratorios[] arrayToMatriz(ArrayList<Laboratorios> labs) {
        Laboratorios lab[] = new Laboratorios[labs.size()];
        for (int i = 0; i < lab.length; i++) {
            lab[i] = labs.get(i);
        }
        return lab;
    }

    public String editarLaboratorio(String url, Integer idLab, String codigo, String nombre, String desc) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "idLab=" + idLab
                + "&codigo=" + codigo
                + "&nombre=" + nombre
                + "&desc=" + desc;
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

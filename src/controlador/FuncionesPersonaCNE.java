package controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.cert.CertificateException;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class FuncionesPersonaCNE {

    /**
     * @param cedula
     * @return
     */
    public String datos(String cedula) {
        String json = "", linea;
        try {
            KeyStore ksCertificadosConfianza;
            InputStream fileCertificadosConfianza = new FileInputStream(new File(".keystore"));
            ksCertificadosConfianza = KeyStore.getInstance(KeyStore.getDefaultType());
            ksCertificadosConfianza.load(fileCertificadosConfianza, "123456".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksCertificadosConfianza);
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
            SSLSocketFactory sslSocketFactory = context.getSocketFactory();
            URL url = new URL("https://siae.cne.gob.ec/electoral-roll/api/voters/" + cedula + "/electoral-roll");
            URLConnection conexion = url.openConnection();
            ((HttpsURLConnection) conexion).setSSLSocketFactory(sslSocketFactory);
            conexion.connect();
            InputStream is = conexion.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            while ((linea = in.readLine()) != null) {
                json += linea;
            }
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | KeyManagementException e) {
            return null;
        }
        return json;
    }

    public VoterCNE obtienePersona(String formatoJSON) {
        if ((formatoJSON + "").equals("null")) {
            return null;
        }

        Gson gson = new Gson();
        Type tipoObjeto = new TypeToken<VoterCNE>() {
        }.getType();
        VoterCNE voter = gson.fromJson(formatoJSON, tipoObjeto);
        return voter;
    }

}

package com.iramconsulting.turnosnow.DConexionHTTP;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by RAMOS on 17/2/2017.
 */

public class clsConexcionHttpPost {
    private String urlIn="http://192.168.0.3:8080/validate?email=martin@mail.com&string=prueba123";
    private String param;
    private JSONObject Body;
    public String respString;

    public clsConexcionHttpPost(String urlIn, String param) {
        this.urlIn = urlIn;
        this.param = param;

    }

    public String clsConsultaPost() {
        Log.i("S_clsConsultaPost", "Incio");
        HttpURLConnection connection = null;

        try {
            Log.i("S_clsConsultaPost", "TRY_INICIO");

            URL url = new URL("http://192.168.0.3:8080/validate?email=martin@mail.com&string=prueba123");

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                // if response code = 200 ok
                InputStream in = new BufferedInputStream(connection.getInputStream());

                // Read the BufferedInputStream
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                respString = sb.toString();
                // End reading...............

                Log.i("S_clsConsultaPost", respString.toString());

                // Disconnect the HttpURLConnection
                connection.disconnect();
            }
            else
            {

                // Do something
                Log.v("S_clsConsultaPost", "Error en conexcion" );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        Log.i("S_clsConsultaPost", "FIn");


        return respString;
    }

}
package com.iramconsulting.turnosnow.DConexionHTTP;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by RAMOS on 17/2/2017.
 */

public class clsConexcionHttpGet {
    private String urlIn;
    private String param;
    public String respString;

    public clsConexcionHttpGet(String urlIn, String param) {
        this.urlIn = urlIn;
        this.param = param;

    }

    public String clsConsultaGet() {
        Log.i("S_clsConsultaGet", "Inicio");

        HttpClient httpClient = new DefaultHttpClient();
        Log.i("S_clsConsultaGet", "URL" + urlIn+param);

        HttpGet get = new HttpGet(urlIn+param);
        get.setHeader("Content-type", "application/json");

        try {
            Log.i("S_clsConsultaGet", "TRY BACK");

            HttpResponse respuesta_get = httpClient.execute(get);
            respString = EntityUtils.toString(respuesta_get.getEntity());
            Log.i("S_clsConsultaGet", respString.toString());
            Log.i("S_clsConsultaGet", "ANTES DER SERVER");

        } catch (Exception ex) {
            Log.e("S_clsConsultaGet", "ERROR_clsConexionHttp");
            ex.printStackTrace();
        }

        Log.i("S_clsConsultaGet", "FIn");


        return respString;
    }

}
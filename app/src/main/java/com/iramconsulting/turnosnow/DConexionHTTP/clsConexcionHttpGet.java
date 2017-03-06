package com.iramconsulting.turnosnow.DConexionHTTP;

import android.util.Log;

import java.io.InputStream;
import java.net.URL;

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


        try {
            Log.i("S_clsConsultaGet", "TRY BACK");
            URL url = new URL(urlIn);
            InputStream respString = url.openStream();

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
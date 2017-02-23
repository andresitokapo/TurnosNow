package com.iramconsulting.turnosnow.DInicio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iramconsulting.turnosnow.DConexionHTTP.ServerResponse;
import com.iramconsulting.turnosnow.DConexionHTTP.clsConexcionHttpGet;
import com.iramconsulting.turnosnow.DLogin.Login;
import com.iramconsulting.turnosnow.DPrincipal.Principal;
import com.iramconsulting.turnosnow.R;
import com.iramconsulting.turnosnow.cls.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Inicio extends AppCompatActivity {
    public String user,contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Guardar Preferencias
        SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putString("usuario","");
        editor.putString("contrasena","");
        editor.commit();

        mispreferencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
        user = mispreferencias.getString("usuario","");
        contraseña= mispreferencias.getString("contrasena","");
        Log.i("S_Inicio", "Pref:"+ user.toString()+contraseña.toString());

        if (user.equals("") || contraseña.equals("") ){
            Log.i("S_Inicio", "No tiene Usuario o Pass");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Inicio.this,Login.class);
                    startActivity(intent);
                    finish();

                }
            }, 3000);return;

        }else {
            //Ejecutar consulta WS para logien de usuario y pass
            Log.i("S_Inicio", "Tiene Usuario y pass");
            Log.i("S_Inicio", "Inicio consulta WS");
            ConsultaWS LoginUsuario = new ConsultaWS();
            LoginUsuario.execute("1");
        }



        Log.i("S_Inicio", "FINNNNNNNNNNN");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        supportFinishAfterTransition();
    }


    //----------------------------------------------------------------------------------------------
    public class ConsultaWS extends AsyncTask<String, Void, Void> {
        private String url = "http://192.168.0.3:8080/find?email=martin@mail.com";
        private String param = "";
        private String resphttp;
        private ServerResponse<User> user;

        @Override
        protected Void doInBackground(String... params) {
            Log.i("S_Inicio_doinback", "INICIO");
            clsConexcionHttpGet clsConexcionHttpGet = new clsConexcionHttpGet(url,param);
            resphttp = clsConexcionHttpGet.clsConsultaGet();
            Log.i("S_Inicio_doinback", "FIN");
            Log.i("S_Inicio_doinback", resphttp);
            Log.i("S_Inicio_doinback", "INICIO");
            ServerResponse<User> serverResponseGet = hacerGet(resphttp);
            user = serverResponseGet;

            Log.i("S_Inicio_doinback", "FIN");
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            Log.i("S_Inicio_onPost", "Dato Metadata: " + user.getMetaData().getHttpStatus().toString());
            Log.i("S_Inicio_onPost", "Dato User Telefono: " + user.getData().getTelefono().toString());

            if (user.getMetaData().getHttpStatus().equals("OK")){
                Log.i("S_Inicio_onPost", "Salio IF por OK ");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("S_Inicio_onPost", "Handler");
                        Intent intent = new Intent(Inicio.this,Principal.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);

            } else {
                Log.i("S_Inicio_onPost", "Salio IF por NOK ");
                Intent intent = new Intent(Inicio.this,Login.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        protected void onPreExecute() {
        }
    }

    public static ServerResponse<User> hacerGet(String respString) {
        Log.i("S_Inicio_ServerResponse", "INICIO SERVERRESPONSE");
        ObjectMapper mapper = new ObjectMapper();
        ServerResponse<User> serverResponse = null;

        try {
            Log.i("S_Inicio_ServerResponse", "TRY MAPPER");
            serverResponse = mapper.readValue(respString,new TypeReference<ServerResponse<User>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("S_Inicio_ServerResponse", "FIN SERVERRESPONSE");

        return serverResponse;
    }



}

package com.iramconsulting.turnosnow.DLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iramconsulting.turnosnow.DConexionHTTP.ServerResponse;
import com.iramconsulting.turnosnow.DConexionHTTP.clsConexcionHttpPost;
import com.iramconsulting.turnosnow.DLogin.cls.clsLogin;
import com.iramconsulting.turnosnow.DPrincipal.MainActivity_Principal;
import com.iramconsulting.turnosnow.R;
import com.iramconsulting.turnosnow.cls.Access;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Login extends AppCompatActivity {

    Button btnentrar;
    Button btnregistrarme;
    Button btnrefresh;
    EditText usuario;
    EditText pass;
    String res,res1;
    public clsLogin datosLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btnentrar = (Button)findViewById(R.id.button_entrar);
        final EditText usuario = (EditText)findViewById(R.id.usuario);
        final EditText pass = (EditText)findViewById(R.id.pass);
        final Button btnregistrarme = (Button)findViewById(R.id.button_registrarme);
        final Button btnrefresh = (Button)findViewById(R.id.button_Refresh);



        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("S_Login_BotonEntrar","INICIO" + usuario.getText().toString()+pass.getText().toString());

                datosLogin = new clsLogin(usuario.getText().toString(),pass.getText().toString());
                Log.i("S_Login_BotonEntrar","INICIO" + datosLogin.getUsuario().toString()+datosLogin.getPass());

                //Validar que los campos esten completos
                if (datosLogin.getUsuario().equals("") || datosLogin.getPass().equals("")) {
                    Log.i("S_Login_BotonEntrar","IF no tiene pass o user");
                    Toast.makeText(getApplicationContext(),"usuario o pass incorrecto",Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    Log.i("S_Login_BotonEntrar","IF Tiene");
                }

                //Consultar WS Validate
                Log.i("S_Login_BotonEntrar","ConsultaWSValidate");
                ConsultaWS tarea = new ConsultaWS();
                tarea.execute("1");


            }
        });

        btnregistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("S_Login_BotonRegistro","INICIO");
                Intent intent = new Intent(Login.this,RegistroUsuario.class);
                startActivity(intent);
                finish();
            }
        });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("S_Login_BotonRefresh","INICIO" );
                Log.i("S_Login_BotonRefresh","Consumo WS Refresh Constrasena" );
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        supportFinishAfterTransition();
    }

    //----------------------------------------------------------------------------------------------
    public class ConsultaWS extends AsyncTask<String, Void, Void> {
        public String url = "http://192.168.0.3:8080/validate?email=martin@mail.com&string=prueba123";
        public String param = "";
        public String resultado;
        private ServerResponse<Access> access;


        @Override
        protected Void doInBackground(String... params) {
            //Consultar el WS Validate
            clsConexcionHttpPost conexcionHttpPost = new clsConexcionHttpPost(url,param);
            resultado = conexcionHttpPost.clsConsultaPost();
            Log.i("S_Login", resultado);
            ServerResponse<Access> serverResponsePost = hacerPost(resultado);
            access = serverResponsePost;

            if (access.getMetaData().getHttpStatus().equals("OK")) {
                Log.i("S_Login_onPost", "Salio IF por OK ");

                //Guardar Preferencias
                SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mispreferencias.edit();
                editor.putString("usuario",datosLogin.getUsuario().toString());
                editor.putString("contraseña",datosLogin.getPass().toString());
                editor.commit();

                Intent intent = new Intent(Login.this, MainActivity_Principal.class);
                startActivity(intent);
                finish();

            } else {
                Log.i("S_Login_onPost", "Salio IF por NOK ");
                Toast.makeText(Login.this,"Error en usuario o pass", Toast.LENGTH_SHORT).show();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            Log.i("S_Login_onPost", "Dato Metadata: " + access.getMetaData().getHttpStatus().toString());
            Log.i("S_Login_onPost", "Dato User Telefono: " + access.getData().isAccepted());
            Log.i("S_Login_onPost", "Dato User Telefono: " + access.getData().getHash().toString());

        }

        @Override
        protected void onPreExecute() {

        }
    }



    public static ServerResponse<Access> hacerPost(String respString) {
        Log.i("S_clsConexionHttp", "INICIO SERVERRESPONSE");
        ObjectMapper mapper = new ObjectMapper();
        ServerResponse<Access> serverResponse = null;

        try {
            Log.i("S_clsConexionHttp", "TRY MAPPER");
            serverResponse = mapper.readValue(respString, new TypeReference<ServerResponse<Access>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("S_clsConexionHttp", "FIN SERVERRESPONSE");

        Log.i("S_clsConexionHttp", "FIINNN con dato: " + serverResponse.getMetaData().getHttpStatus().toString()+ serverResponse.getMetaData().getInfo().toString()+serverResponse.getMetaData().getMessage().toString());

        Log.i("S_clsConexionHttp", serverResponse.getData().getHash().toString());
        return serverResponse;
    }



}
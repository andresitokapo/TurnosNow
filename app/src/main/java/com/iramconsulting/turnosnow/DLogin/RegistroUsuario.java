package com.iramconsulting.turnosnow.DLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iramconsulting.turnosnow.DPrincipal.Principal;
import com.iramconsulting.turnosnow.R;

public class RegistroUsuario extends AppCompatActivity {

    private Button btnregistrarme;
    private EditText txtnombre, txtapellido,txtmail,txtcontrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        btnregistrarme = (Button) findViewById(R.id.button_registrarme);
        txtnombre = (EditText) findViewById(R.id.editText_nombre);
        txtapellido = (EditText) findViewById(R.id.editText_apellido);
        txtmail = (EditText) findViewById(R.id.editText_mail);
        txtcontrasena = (EditText) findViewById(R.id.editText_contrasena);


        btnregistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Validaciones
                //Si el mail ya existe
                //Integridad de los datos
                //Caracteres especiales
                //llamar WS de altausuario
                //validar la respuesta

                //Seteo de datos de usuario
                GuardarPreferencias();
                
                Toast.makeText(RegistroUsuario.this,"Felicitaciones", Toast.LENGTH_SHORT).show();

                //Link principal
                Intent intent = new Intent(RegistroUsuario.this,Principal.class);
                startActivity(intent);
            }
        });

    }

    public void GuardarPreferencias(){

        SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putString("nombre",txtnombre.getText().toString());
        editor.putString("apellido",txtapellido.getText().toString());
        editor.putString("usuario",txtmail.getText().toString());
        editor.putString("contrasena",txtcontrasena.getText().toString());
        editor.commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        supportFinishAfterTransition();
    }
}

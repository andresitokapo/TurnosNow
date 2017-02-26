package com.iramconsulting.turnosnow.DAltaTurno;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iramconsulting.turnosnow.DPrincipal.MainActivity_Principal;
import com.iramconsulting.turnosnow.R;
import com.iramconsulting.turnosnow.cls.clsTurno;

import java.util.ArrayList;


public class AltaTurno extends AppCompatActivity {

    Spinner combosucursales;
    Button cancel, consulta;
    EditText dato;
    TextView statusInfo, respuesta;
    ListView listView;
    public clsTurno[] obj;
    //String url = "http://nolaborables.com.ar/API/v1/actual";
    String url = "http://nolaborables.com.ar/API/v1/actual";
    ArrayList<String> arrayList;
    String[] listasucursales ={"Seleccione Sucursal","VillaUrquiza","Carapachai","LPM"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_turno);

        combosucursales = (Spinner) findViewById(R.id.combo_sucursal);
        cancel = (Button) findViewById(R.id.button_cancelar);
        consulta = (Button) findViewById(R.id.btn_consultar);
        statusInfo = (TextView) findViewById(R.id.txt_estado);
        dato = (EditText) findViewById(R.id.etxt_dato);
        respuesta = (TextView) findViewById(R.id.txt_respuesta);
        listView = (ListView) findViewById(R.id.listview);

        //Adaptador para completar el combo desde un array de string
        ArrayAdapter<String> adaptadorsucursales =  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listasucursales);
        combosucursales.setAdapter(adaptadorsucursales);

        combosucursales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Serv_AltaTurno", listasucursales[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Accion boton cancelar
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent siguiente = new Intent(AltaTurno.this, MainActivity_Principal.class);
                startActivity(siguiente);
                finish();
            }
        });

        //Accion boton consultar
        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConsultaWS tarea = new ConsultaWS();
                tarea.execute(dato.getText().toString());

            }
        });

    }


    //---------------------------------------------
    private class ConsultaWS extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            arrayList = new ArrayList<String>();
            //clsConexionHttp conexionHttp = new clsConexionHttp(url, "params");
            //obj = conexionHttp.consultahttp();
            Log.i("Serv_AltaTurno", "Inicio doInBackground");

            for (int i = 0; i < 5; i++) {
                Log.i("Serv_AltaTurno", obj[i].getFecha());
                arrayList.add("Numero: "+i);

            }

            Log.i("Serv_AltaTurno", "Fin doInBackground");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i("Serv_AltaTurno", "onPostExecute");
            //Enviar respuesta a las variables de layout
            respuesta.setText("Resultado Rest: OK");

            //Actualiza estado en texto estatus proceso
            statusInfo.setText("Finalizado Servicios WSREST");

            //Inicio Adapter para LISTVIEW
            //ArrayAdapter<clsTurno> arrayAdapter = new ArrayAdapter<clsTurno>(AltaTurno.this, android.R.layout.simple_list_item_1, android.R.id.text1, obj);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AltaTurno.this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "posicion" + position, Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        protected void onPreExecute() {
            Log.i("Serv_AltaTurno", "onPreExecute");
            //Actualiza estado en texto estatus proceso
            statusInfo.setText("Procesando Servicios WSREST ");
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        supportFinishAfterTransition();
    }



}

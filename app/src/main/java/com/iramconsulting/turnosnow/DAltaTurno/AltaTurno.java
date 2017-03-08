package com.iramconsulting.turnosnow.DAltaTurno;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iramconsulting.turnosnow.DConexionHTTP.ServerResponse;
import com.iramconsulting.turnosnow.R;
import com.iramconsulting.turnosnow.cls.ProviderSchedule;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class AltaTurno extends AppCompatActivity {
    ListView listView;
    public ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_turno);

        listView = (ListView) findViewById(R.id.listview);

        AltaTurno.ConsultaWS ConsultaTurnos = new AltaTurno.ConsultaWS();
        ConsultaTurnos.execute("1");


    }


    //---------------------------------------------
    private class ConsultaWS extends AsyncTask<String, Void, Void> {
        public ServerResponse<HashMap<String, ProviderSchedule>> serverResponseGet;

        @Override
        protected Void doInBackground(String... params) {
            Log.i("S_AltaTurno", "INICIO");

            serverResponseGet = hacerGet("http://192.168.0.3:8080/api/3/appointments?month=12");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            arrayList = new ArrayList<String>();

            Log.i("S_AltaTurno", "onPostExecute");

            Log.i("S_AltaTurno",serverResponseGet.getData().get("1").getName().toString());

            Log.i("S_AltaTurno",serverResponseGet.getData().get("1").getAppointments().get("170315").get(0).getAppointmentTime().toString());

            for (int i = 0; i < serverResponseGet.getData().get("1").getAppointments().size(); i++) {
                Log.i("S_","INICIO FOR" + i);
                Log.i("S_AltaTurno", serverResponseGet.getData().get("1").getAppointments().get("170315").get(i).getAppointmentTime().toString());

                arrayList.add(serverResponseGet.getData().get("1").getAppointments().get("170315").get(i).getAppointmentTime().toString());
                Log.i("Resultado ARRAY",arrayList.get(i));
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AltaTurno.this, android.R.layout.simple_list_item_1, android.R.id.text1,arrayList );
            listView.setAdapter(arrayAdapter);
        }

        @Override
        protected void onPreExecute() {
            Log.i("S_AltaTurno", "onPreExecute");

        }
    }


    public static ServerResponse<HashMap<String, ProviderSchedule>> hacerGet(String uri) {
        Log.i("S_Inicio_ServerResponse", "INICIO SERVERRESPONSE");
        ObjectMapper mapper = new ObjectMapper();
        ServerResponse<HashMap<String, ProviderSchedule>> serverResponse = null;

        try {
            URL url = new URL(uri);
            Log.i("S_Inicio_ServerResponse", "TRY MAPPER");
            InputStream is = url.openStream();
            serverResponse = mapper.readValue(is, new TypeReference<ServerResponse<HashMap<String, ProviderSchedule>>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.i("S_Inicio_ServerResponse", "FIN SERVERRESPONSE");


        return serverResponse;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        supportFinishAfterTransition();
    }



}

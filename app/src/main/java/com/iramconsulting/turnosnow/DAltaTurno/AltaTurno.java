package com.iramconsulting.turnosnow.DAltaTurno;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

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
    public ServerResponse<HashMap<String, ProviderSchedule>> serverResponseGet;
    CalendarView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_turno);

        listView = (ListView) findViewById(R.id.listview);
        calendar = (CalendarView) findViewById(R.id.calendarView);



        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

             @Override
             public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String sMonth,sDay,sYear, fecha;

                Toast.makeText(getApplicationContext(),year +"/"+ month  +"/"+ dayOfMonth ,Toast.LENGTH_SHORT).show();
                 sYear = String.valueOf(year);
                 sMonth = String.valueOf((month + 1));
                 sDay = String.valueOf(dayOfMonth);

                 sYear = sYear.substring(2,4);
                 Log.i("FECHA SELECCIONADA" ,sYear + sMonth + sDay);
                 if (sDay.length() == 1)
                     sDay = "0" + sDay;

                 if (sMonth.length() == 1)
                     sMonth = "0" + sMonth;

                 fecha = sYear +sMonth + sDay;

                 Log.i("FECHA SELECCIONADA: " ,fecha);

                 MostrarListaTurnos("1",fecha);
             }
        });

        AltaTurno.ConsultaWS ConsultaTurnos = new AltaTurno.ConsultaWS();
        ConsultaTurnos.execute("1");



    }




    //---------------------------------------------
    private class ConsultaWS extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... params) {
            Log.i("S_AltaTurno", "INICIO");

            serverResponseGet = hacerGet("http://192.168.0.3:8080/api/3/appointments?month=12");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            /*arrayList = new ArrayList<String>();
            String texto;


            for (int i = 0; i < 3; i++) {
                //Log.i("S_","INICIO FOR" + i);
                //Log.i("S_AltaTurno", serverResponseGet.getData().get("1").getAppointments().get("170315").get(i).getAppointmentTime().toString());
                texto = serverResponseGet.getData().get("1").getAppointments().get("170321").get(i).getAppointmentTime().toString();
                texto =texto.substring(0, 2) + ":" + texto.substring(2, 4) + " hs";
                arrayList.add(texto);
                //Log.i("Resultado ARRAY",arrayList.get(i));
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AltaTurno.this, android.R.layout.simple_list_item_1, android.R.id.text1,arrayList );
            listView.setAdapter(arrayAdapter);*/
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


    public void  MostrarListaTurnos(String proveedor, String dia){
        arrayList = new ArrayList<String>();
        String texto;
        int numero;

        Log.i("S_ININIOOOO","");
        if (serverResponseGet.getData().get(proveedor).getAppointments().get(dia) == null) {
            Log.i("IFFFFF Siiiii",dia);
            Toast.makeText(getApplicationContext(),"No hay turno" ,Toast.LENGTH_SHORT).show();
            return;
        }else {
            Log.i("S_NOOOOO",dia);
        }

        arrayList.add(dia);
        Log.i("S_Proveedor", proveedor);
        Log.i("S_","Fecha Seleccionada: " + dia);
        for (int i = 0; i < serverResponseGet.getData().get(proveedor).getAppointments().get(dia).size(); i++) {
            Log.i("S_INICIO FOR:", proveedor +" "+ dia);
            Log.i("S_AltaTurno", serverResponseGet.getData().get(proveedor).getAppointments().get(dia).get(i).getAppointmentTime().toString());

            texto = serverResponseGet.getData().get("1").getAppointments().get(dia).get(1).getAppointmentTime().toString();
            texto =texto.substring(0, 2) + ":" + texto.substring(2, 4) + " hs";

            arrayList.add(texto);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AltaTurno.this, android.R.layout.simple_list_item_1, android.R.id.text1,arrayList );
        listView.setAdapter(arrayAdapter);


    }

}

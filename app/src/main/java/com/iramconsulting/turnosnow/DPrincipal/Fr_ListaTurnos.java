package com.iramconsulting.turnosnow.DPrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iramconsulting.turnosnow.R;
import com.iramconsulting.turnosnow.cls.clsTurno;

import java.util.ArrayList;

public class Fr_ListaTurnos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //
    ListView listView;
    public clsTurno[] obj;
    String url = "http://nolaborables.com.ar/API/v1/actual";
    RecyclerView reciclador;
    ArrayList<clsTurno> arrayList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fr_ListaTurnos() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fr_ListaTurnos newInstance(String param1, String param2) {
        Fr_ListaTurnos fragment = new Fr_ListaTurnos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fr_lista_turnos,container, false);
        reciclador = (RecyclerView)view.findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);

        arrayList = new ArrayList<clsTurno>();
        ConsultaWS tarea = new ConsultaWS();
        tarea.execute();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    //-------------------------------------------------------------------------------------
    private class ConsultaWS extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            //LLamar al WS para consultar los turnos que tiene el cliente
            Log.i("Serv_FR_ListaTurnos", "Back");
            //Meter todos los objetos de tipo Turno, en un arraylist de tipo Turnos

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i("Serv_FR_ListaTurnos", "onPostExecute");

            //Iniciar el adaptador
            //final clsListaTurnoAdaptador adaptador = new clsListaTurnoAdaptador(arrayList);


            //Esto es el setonclik de cada cardview
            /*adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("com.iramconsulting.naturalbronze.Fr_ListaTurnos");
                    Log.i("Serv_FR_ListaTurnos", "Pulsado el elemento " + reciclador.getChildPosition(v));
                    intent.putExtra("Turno",obj[reciclador.getChildPosition(v)]);
                    startActivityForResult(intent,1);

                }
            });

            reciclador.setAdapter(adaptador);
            reciclador.setLayoutManager (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
*/
        }

        @Override
        protected void onPreExecute() {
            Log.i("S_FR_ListaTurnos", "onPreExecute");

        }
    }



}

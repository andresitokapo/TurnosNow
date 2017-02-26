package com.iramconsulting.turnosnow.DPrincipal;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iramconsulting.turnosnow.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by RAMOS on 24/12/2016.
 */

public class clsListaTurnoAdaptador extends RecyclerView.Adapter<clsListaTurnoAdaptador.ListaViewHolder> implements View.OnClickListener {
    private ArrayList<String> item;
    private View.OnClickListener listener;
    String posicion;

    public clsListaTurnoAdaptador(ArrayList<String> item) {
        this.item = item;
    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist_lista_turnos, parent, false);

        itemview.setOnClickListener(this);

        ListaViewHolder turno = new ListaViewHolder(itemview);


        return turno;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        holder.fecha.setText("FECHA");
        holder.sucursal.setText("Sucursal: " + "VARIALE");

    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        Log.v(TAG, "Serv_clsListaTurnoAdaptador" + position);

        return 0;
    }


    public class ListaViewHolder extends RecyclerView.ViewHolder {
        TextView sucursal, fecha;


        public ListaViewHolder(View itemView) {
            super(itemView);

            sucursal = (TextView) itemView.findViewById(R.id.txt_sucursal);
            fecha = (TextView) itemView.findViewById(R.id.txt_fecha);


        }
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }



}

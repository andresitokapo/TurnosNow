package com.iramconsulting.turnosnow.DPrincipal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iramconsulting.turnosnow.DAltaFavoritosQR.AltaFavoritoQR;
import com.iramconsulting.turnosnow.DAltaTurno.AltaTurno;
import com.iramconsulting.turnosnow.DPerfilProveedor.PerfilProveedor;
import com.iramconsulting.turnosnow.R;

public class MainActivity_Principal extends AppCompatActivity implements View.OnClickListener, Fr_ListaTurnos.OnFragmentInteractionListener, Fr_Informacion.OnFragmentInteractionListener {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        //Agregar logo al actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.logo_naturalbronze);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main_principal);


        btn1 = (Button)findViewById(R.id.btn_Inbox);
        btn2 = (Button) findViewById(R.id.btn_Info);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        //Iniciar en Fragment inbox
        Fr_ListaTurnos fr_listaTurnos = new Fr_ListaTurnos();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, fr_listaTurnos);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 1
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.buscarqr) {
            Intent siguiente = new Intent(this,AltaFavoritoQR.class);
            startActivity(siguiente);

        } else if (id == R.id.lupabuscar){
            Intent siguiente = new Intent(this,PerfilProveedor.class);
            startActivity(siguiente);

        } else if (id == R.id.altaturno){
            Intent siguiente = new Intent(this,AltaTurno.class);
            startActivity(siguiente);

        }


        return false;
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_Inbox:

                Fr_ListaTurnos fr_listaTurnos = new Fr_ListaTurnos();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, fr_listaTurnos);
                transaction.commit();
                break;

            case R.id.btn_Info:

                Fr_Informacion fr_informacion = new Fr_Informacion();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedor, fr_informacion);
                transaction1.commit();

                break;


        }
        }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_Principal.this);

            builder.setTitle("QUEReS SALIR??");
            builder.setMessage("ESTAS POR SALIR");
            builder.setPositiveButton("SEGURO", new DialogInterface.OnClickListener() {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
    Toast.makeText(MainActivity_Principal.this, "Hasta pronto", Toast.LENGTH_SHORT).show();
        //finish(); Solo termina con la actividad
        //System.exit(0); cierra el app sin matar el proceso, por lo que
        // el app termina reiniciando
        //Aquí tampoco se termina el app y no sé porqué
        //int Joe = android.os.Process.myPid();
        //android.os.Process.killProcess(Joe);
                    finish();
                }
            });

            builder.setNegativeButton(android.R.string.cancel, null);
            Dialog dialog = builder.create();
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }

}

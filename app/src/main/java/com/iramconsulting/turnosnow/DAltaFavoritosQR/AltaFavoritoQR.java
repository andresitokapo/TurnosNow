package com.iramconsulting.turnosnow.DAltaFavoritosQR;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;
import com.iramconsulting.turnosnow.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class AltaFavoritoQR extends Activity implements ZXingScannerView.ResultHandler {
    private static final int SOLICITUD_PERMISOS_CAMARA = 1;
    private Intent intentCamara;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_favorito_qr);
        Log.i("S_AltaFavoritoQR","INICIO");
    }

    public void onClick(View v){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},SOLICITUD_PERMISOS_CAMARA);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.w("Serv",result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //mScannerView.resumeCameraPreview(this);

    }


}
package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SolicitudDeCambioConsultarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtIdSolicitudCambio;
    private TextView txtIdExamen,txtIdRazon,txtNuevaNota;
    private CheckBox cbEstadoAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_cambio_consultar);
        helper = new ControlBDGpo16(this);
        edtIdSolicitudCambio=(EditText)findViewById(R.id.edtIdSolicitudCambio);
        txtIdExamen=(TextView)findViewById(R.id.txtIdExamen);
        txtIdRazon=(TextView)findViewById(R.id.txtIdRazon);
        txtNuevaNota=(TextView)findViewById(R.id.txtNuevaNota);
        cbEstadoAP=(CheckBox)findViewById(R.id.cbEstadoAP);}

    public void consultarSolicitudDeCambio(View v) {
        helper.abrir();
        SolicitudDeCambio solicitudDeCambio = helper.consultarSolicitudDeCambio(Integer.parseInt(edtIdSolicitudCambio.getText().toString()));
        helper.cerrar();
        if(solicitudDeCambio == null)Toast.makeText(this, "El número de solicitud de cambio " +edtIdSolicitudCambio.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
        else{
            txtIdExamen.setText(txtIdExamen.getText()+": "+solicitudDeCambio.getIdExamen());
            txtIdRazon.setText(txtIdRazon.getText()+": "+solicitudDeCambio.getIdRazon());
            txtNuevaNota.setText(txtNuevaNota.getText()+": "+solicitudDeCambio.getNuevaNota());
            cbEstadoAP.setChecked(solicitudDeCambio.isEstadoAprobado());}}}
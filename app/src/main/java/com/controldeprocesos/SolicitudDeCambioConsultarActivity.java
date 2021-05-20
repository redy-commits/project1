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
    private String examen,razon,nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_cambio_consultar);
        helper = new ControlBDGpo16(this);
        edtIdSolicitudCambio=(EditText)findViewById(R.id.edtIdSolicitudCambio);
        txtIdExamen=(TextView)findViewById(R.id.txtIdExamen);
        txtIdRazon=(TextView)findViewById(R.id.txtIdRazon);
        txtNuevaNota=(TextView)findViewById(R.id.txtNuevaNota);
        cbEstadoAP=(CheckBox)findViewById(R.id.cbEstadoAP);
        examen=txtIdExamen.getText().toString();
        razon=txtIdRazon.getText().toString();
        nota=txtNuevaNota.getText().toString();}

    public void consultarSolicitudDeCambio(View v) {
        helper.abrir();
        SolicitudDeCambio solicitudDeCambio = helper.consultarSolicitudDeCambio(Integer.parseInt(edtIdSolicitudCambio.getText().toString()));
        helper.cerrar();
        if(solicitudDeCambio == null){
            Toast.makeText(this, "El número de solicitud de cambio " +edtIdSolicitudCambio.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
            txtIdExamen.setText(examen+": ");
            txtIdRazon.setText(razon+": ");
            txtNuevaNota.setText(nota+": ");
            cbEstadoAP.setChecked(false);}
        else{
            txtIdExamen.setText(examen+": "+solicitudDeCambio.getIdExamen());
            txtIdRazon.setText(razon+": "+solicitudDeCambio.getIdRazon());
            txtNuevaNota.setText(nota+": "+solicitudDeCambio.getNuevaNota());
            cbEstadoAP.setChecked(solicitudDeCambio.isEstadoAprobado());}}}
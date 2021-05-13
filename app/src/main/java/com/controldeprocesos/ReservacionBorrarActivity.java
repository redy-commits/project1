package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReservacionBorrarActivity extends Activity {

    EditText edtIdReservacion;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_borrar);
        helper=new ControlBDGpo16 (this);
        edtIdReservacion=(EditText)findViewById(R.id.edtIdReservacion);}

    public void eliminarReservacion(View v){
        String regEliminadas;
        Reservacion reservacion=new Reservacion();
        reservacion.setIdReservacion(Integer.parseInt(edtIdReservacion.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(reservacion);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}
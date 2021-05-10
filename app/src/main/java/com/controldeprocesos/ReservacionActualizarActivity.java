package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class ReservacionActualizarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdReservacion,edtIdLocal,edtIdDocente,edtHoraInicio,edtHoraFin,edtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_actualizar);
        helper = new ControlBDGpo16(this);
        edtIdReservacion=(EditText)findViewById(R.id.edtIdReservacion);
        edtIdLocal=(EditText)findViewById(R.id.edtIdLocal);
        edtIdDocente=(EditText)findViewById(R.id.edtIdDocente);
        edtHoraInicio=(EditText)findViewById(R.id.edtHoraInicio);
        edtHoraFin=(EditText)findViewById(R.id.edtHoraFin);
        edtFecha=(EditText)findViewById(R.id.edtFecha);}

    public void actualizarReservacion(View v) {
        Reservacion reservacion = new Reservacion();
        reservacion.setIdReservacion(Integer.parseInt(edtIdReservacion.getText().toString()));
        reservacion.setIdLocal(Integer.parseInt(edtIdLocal.getText().toString()));
        reservacion.setIdDocente(Integer.parseInt(edtIdDocente.getText().toString()));
        reservacion.setHoraInicio(edtHoraInicio.getText().toString());
        reservacion.setFecha(Date.valueOf(edtFecha.getText().toString()));
        reservacion.setHoraFin(edtHoraFin.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(reservacion);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}
}
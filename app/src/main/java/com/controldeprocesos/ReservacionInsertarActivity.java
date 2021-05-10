package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class ReservacionInsertarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdReservacion,edtIdLocal,edtIdDocente,edtHoraInicio,edtHoraFin,edtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_insertar);
        helper = new ControlBDGpo16(this);
        edtIdReservacion=(EditText)findViewById(R.id.edtIdReservacion);
        edtIdLocal=(EditText)findViewById(R.id.edtIdLocal);
        edtIdDocente=(EditText)findViewById(R.id.edtIdDocente);
        edtHoraInicio=(EditText)findViewById(R.id.edtHoraInicio);
        edtHoraFin=(EditText)findViewById(R.id.edtHoraFin);
        edtFecha=(EditText)findViewById(R.id.edtFecha);}

    public void insertarReservacion(View v) {
        int idReservacion=Integer.parseInt(edtIdReservacion.getText().toString());
        int idLocal=Integer.parseInt(edtIdLocal.getText().toString());
        int idDocente=Integer.parseInt(edtIdDocente.getText().toString());
        String horaInicio=edtHoraInicio.getText().toString();
        String horaFin=edtHoraFin.getText().toString();
        Date fecha=Date.valueOf(edtFecha.getText().toString());
        String regInsertados;
        Reservacion reservacion=new Reservacion();
        reservacion.setIdReservacion(idReservacion);
        reservacion.setIdLocal(idLocal);
        reservacion.setIdDocente(idDocente);
        reservacion.setHoraInicio(horaInicio);
        reservacion.setHoraFin(horaFin);
        reservacion.setFecha(fecha);
        helper.abrir();
        regInsertados=helper.insertar(reservacion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();}
}
package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReservacionConsultarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdReservacion;
    private TextView txtFecha,txtHoraInicio,txtHoraFin,txtIdLocal,txtIdDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_consultar);
        helper = new ControlBDGpo16(this);
        edtIdReservacion=(EditText)findViewById(R.id.edtIdReservacion);
        txtIdLocal=(TextView)findViewById(R.id.txtIdLocal);
        txtIdDocente=(TextView)findViewById(R.id.txtIdDocente);
        txtHoraInicio=(TextView)findViewById(R.id.txtHoraInicio);
        txtHoraFin=(TextView)findViewById(R.id.txtHoraFin);
        txtFecha=(TextView)findViewById(R.id.txtFecha);
        txtHoraFin.setText("");
        txtIdLocal.setText("");
        txtIdDocente.setText("");
        txtHoraInicio.setText("");
        txtFecha.setText("");}

    public void consultarReservacion(View v) {
        helper.abrir();
        Reservacion reservacion = helper.consultarReservacion(Integer.parseInt(edtIdReservacion.getText().toString()));
        helper.cerrar();
        if(reservacion == null)
            Toast.makeText(this, "La reservación " +edtIdReservacion.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
        else{
            txtIdDocente.setText("Docente: "+reservacion.getIdDocente());
            txtIdLocal.setText("Local: "+reservacion.getIdLocal());
            txtHoraFin.setText("Hora de fin: "+reservacion.getHoraFin());
            txtHoraInicio.setText("Hora de inicio: "+reservacion.getHoraInicio());
            txtFecha.setText("Fecha: "+reservacion.getFecha());}}}
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
    private String docente,local,horaInicio,horaFin,fecha;

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
        docente=txtIdDocente.getText().toString();
        local=txtIdLocal.getText().toString();
        horaFin=txtHoraFin.getText().toString();
        horaInicio=txtHoraInicio.getText().toString();
        fecha=txtFecha.getText().toString();}

    public void consultarReservacion(View v) {
        helper.abrir();
        Reservacion reservacion = helper.consultarReservacion(Integer.parseInt(edtIdReservacion.getText().toString()));
        helper.cerrar();
        if(reservacion == null){
            Toast.makeText(this, "La reservación " +edtIdReservacion.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
        txtIdDocente.setText(docente+": ");
        txtIdLocal.setText(local+": ");
        txtHoraFin.setText(horaFin+": ");
        txtHoraInicio.setText(horaInicio+": ");
        txtFecha.setText(fecha+": ");}
        else{
            txtIdDocente.setText(docente+": "+reservacion.getIdDocente());
            txtIdLocal.setText(local+": "+reservacion.getIdLocal());
            txtHoraFin.setText(horaFin+": "+reservacion.getHoraFin());
            txtHoraInicio.setText(horaInicio+": "+reservacion.getHoraInicio());
            txtFecha.setText(fecha+": "+reservacion.getFecha());}}}
package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CicloConsultarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdCiclo;
    private TextView txtAnio,txtNumCiclo;
    private String anio,ciclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_consultar);
        helper = new ControlBDGpo16(this);
        edtIdCiclo=(EditText)findViewById(R.id.edtIdCiclo);
        txtAnio=(TextView)findViewById(R.id.txtAnio);
        txtNumCiclo=(TextView)findViewById(R.id.txtNumCiclo);
        anio=txtAnio.getText().toString();
        ciclo=txtNumCiclo.getText().toString();}

    public void consultarCiclo(View v) {
            helper.abrir();
            Ciclo ciclo = helper.consultarCiclo(Integer.parseInt(edtIdCiclo.getText().toString()));
            helper.cerrar();
            if(ciclo == null){
                txtAnio.setText(anio+": "+ciclo.getAnio());
                txtNumCiclo.setText(ciclo+": "+ciclo.getNumCiclo());
                Toast.makeText(this, "El ciclo "+edtIdCiclo.getText().toString()+ " no ha sido encontrado", Toast.LENGTH_LONG).show();}
            else{
                txtAnio.setText(anio+": "+ciclo.getAnio());
                txtNumCiclo.setText(ciclo+": "+ciclo.getNumCiclo());}}}
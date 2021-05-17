package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LocalConsultarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdLocal;
    private TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);
        helper = new ControlBDGpo16(this);
        edtIdLocal=(EditText)findViewById(R.id.edtIdLocal);
        txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtNombre.setText("");}

    public void consultarLocal(View v) {
        helper.abrir();
        Local local = helper.consultarLocal(Integer.parseInt(edtIdLocal.getText().toString()));
        helper.cerrar();
        if(local == null)
            Toast.makeText(this, "El local "+edtIdLocal.getText().toString()+ " no ha sido encontrado", Toast.LENGTH_LONG).show();
        else{
            txtNombre.setText("Nombre de local: "+local.getNombre());}}}

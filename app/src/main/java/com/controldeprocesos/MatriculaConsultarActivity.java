package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MatriculaConsultarActivity extends AppCompatActivity {
    private ControlBDGpo16 helper;
    private EditText edtIdMatricula;
    private TextView txtCarnet,txtCodMateria,txtIdCiclo,txtNumMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula_consultar);
        helper = new ControlBDGpo16(this);
        edtIdMatricula=(EditText)findViewById(R.id.edtIdMatricula);
        txtNumMatricula=(TextView)findViewById(R.id.txtNumMatricula);
        txtCodMateria=(TextView)findViewById(R.id.txtCodMateria);
        txtIdCiclo=(TextView)findViewById(R.id.txtIdCiclo);
        txtCarnet=(TextView)findViewById(R.id.txtCarnet);
        txtIdCiclo.setText("");
        txtNumMatricula.setText("");
        txtCodMateria.setText("");
        txtCarnet.setText("");}

    public void consultarMatricula(View v) {
        helper.abrir();
        Matricula matricula = helper.consultarMatricula(edtIdMatricula.getText().toString());
        helper.cerrar();
        if(matricula == null)
            Toast.makeText(this, "La matricula " +edtIdMatricula.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
        else{
            txtNumMatricula.setText("Numero de Matricula: "+matricula.getNumMatricula());
            txtIdCiclo.setText("Ciclo: "+matricula.getIdCiclo());
            txtCodMateria.setText("Codigo de Materia: "+matricula.getCodMateria());
            txtCarnet.setText("Carnet: "+matricula.getCarnet());}}
}

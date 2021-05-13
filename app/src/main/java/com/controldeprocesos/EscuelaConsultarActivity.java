package com.controldeprocesos;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class EscuelaConsultarActivity extends Activity {
    ControlBDGpo16 helper;
    EditText editcodEscuela;
    EditText editNombre;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_consultar);
        helper = new ControlBDGpo16(this);
        editcodEscuela = (EditText) findViewById(R.id.editcodEscuela);
        editNombre = (EditText) findViewById(R.id.editNombre);

    }
    public void consultarEscuela(View v) {
        helper.abrir();
       Escuela escuela =   helper.consultarEscuela(editcodEscuela.getText().toString());
        helper.cerrar();
        if(escuela == null)
            Toast.makeText(this, "Escuela con codEscuela " + editcodEscuela.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editNombre.setText(escuela.getNombre());
        }
        }

    public void limpiarTexto(View view) {
        editcodEscuela.setText("");
        editNombre.setText("");
    }
}
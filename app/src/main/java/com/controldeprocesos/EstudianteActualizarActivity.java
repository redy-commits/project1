package com.controldeprocesos;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class EstudianteActualizarActivity extends Activity {
    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNombre;
    EditText editCorreo;
    EditText editTipo;
    EditText editContrasena;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_actualizar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editTipo = (EditText) findViewById(R.id.editTipo);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
    }
    public void actualizarEstudiante(View v) {
        Estudiante estudiante = new Estudiante();
        Usuario usuario= new Usuario();
        estudiante.setCarnet(editCarnet.getText().toString());
        usuario.setNombre(editNombre.getText().toString());
        usuario.setContrasena(editContrasena.getText().toString());
        usuario.setTipo(editTipo.getText().toString());
        usuario.setCorreo(editCorreo.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(estudiante,usuario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCarnet.setText("");
        editNombre.setText("");
        editCorreo.setText("");
        editTipo.setText("");
        editContrasena.setText("");
    }
}

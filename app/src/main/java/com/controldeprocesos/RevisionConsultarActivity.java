package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RevisionConsultarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText edtNumRev1, edtIdExamen, edtNuevaNota, edtObserv, edtAsistio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_consultar);
        helper = new ControlBDGpo16(this);
        edtNumRev1 = (EditText) findViewById(R.id.edtNumRev1);
        edtIdExamen = (EditText) findViewById(R.id.edtIdExamen);
        edtNuevaNota = (EditText) findViewById(R.id.edtNuevaNota);
        edtObserv = (EditText) findViewById(R.id.edtObserv);
        edtAsistio = (EditText) findViewById(R.id.edtAsistio);

    }

    public void consultarRevision(View v) {
        helper.abrir();
        Revision revision = helper.consultarRevision(Integer.parseInt(edtNumRev1.getText().toString()));
        helper.cerrar();
        if(revision == null)
            Toast.makeText(this, "Revision #" + edtNumRev1.getText().toString() + " no encontrada", Toast.LENGTH_LONG).show();
        else {
            edtIdExamen.setText("El ID del examen: "+revision.getIdExamen());
            edtNuevaNota.setText("La nueva nota: "+revision.getNuevaNota());
            edtObserv.setText("Observacion: "+revision.getObserv());
            edtAsistio.setText("Asistio: "+revision.isAsistio());
        }
    }
}
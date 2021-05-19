package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RevisionActualizarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText edtNumRev1;
    EditText edtIdExamen;
    EditText edtNuevaNota;
    EditText edtObserv;
    EditText edtAsistio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_actualizar);
        helper = new ControlBDGpo16(this);
        edtNumRev1 = (EditText) findViewById(R.id.edtNumRev1);
        edtIdExamen = (EditText) findViewById(R.id.edtIdExamen);
        edtNuevaNota = (EditText) findViewById(R.id.edtNuevaNota);
        edtObserv = (EditText) findViewById(R.id.edtObserv);
        edtAsistio = (EditText) findViewById(R.id.edtAsistio);
    }

    public void actualizarRevision(View v) {
        Revision revision = new Revision();
        revision.setNumRev1(Integer.parseInt(edtNumRev1.getText().toString()));
        revision.setIdExamen(Integer.parseInt(edtIdExamen.getText().toString()));
        revision.setNuevaNota(Float.parseFloat(edtNuevaNota.getText().toString()));
        revision.setObserv(edtObserv.getText().toString());
        revision.setAsistio(Boolean.parseBoolean(edtAsistio.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(revision);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        edtNumRev1.setText("");
        edtIdExamen.setText("");
        edtNuevaNota.setText("");
        edtObserv.setText("");
        edtAsistio.setText("");
    }

}
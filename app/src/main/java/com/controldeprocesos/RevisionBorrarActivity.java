package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RevisionBorrarActivity extends AppCompatActivity {

    ControlBDGpo16 controlhelper;
    EditText edtNumRev1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_borrar);
        controlhelper=new ControlBDGpo16 (this);
        edtNumRev1 =(EditText) findViewById(R.id.edtNumRev1);
    }

    public void eliminarRevision(View v){
        String regEliminadas;
        Revision revision =new Revision();
        String numRev1 = edtNumRev1.getText().toString();
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(numRev1);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
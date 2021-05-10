package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ReservacionMenuActivity extends ListActivity{

    String[] menu={"Insertar registro","Eliminar registro","Consultar registro","Actualizar registro"};
    String[] activities={"ReservacionInsertarActivity","ReservacionEliminarActivity","ReservacionConsultarActivity","ReservacionActualizarActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(0, 0, 255));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));

        try{
            Class<?> clase=Class.forName("com.controldeprocesos."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);}
        catch(ClassNotFoundException e){
            e.printStackTrace();}}}
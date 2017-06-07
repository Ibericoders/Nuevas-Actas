package aplicaciones_android.a15_pedidos_corr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import javabeans.Acta;
import modelo.GestionDatos;

public class ListadoActivity extends AppCompatActivity{
    private Context ctx;
    private ArrayList<Acta> datos;
    private LayoutInflater lf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        GestionDatos gdatos=new GestionDatos(this);
        ListView lista=(ListView)this.findViewById(R.id.lvActas);
        //creamos adaptador a partir del arraylist
        ListAdapter adapter=new ListadoAdapter(this,gdatos.recuperarActa());
        lista.setAdapter(adapter);
    }
    public void salir(View v){
        this.finish();
    }
}

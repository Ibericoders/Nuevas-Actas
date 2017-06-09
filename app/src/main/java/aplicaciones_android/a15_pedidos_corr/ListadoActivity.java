package aplicaciones_android.a15_pedidos_corr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import java.util.ArrayList;

import javabeans.Acta;
import modelo.GestionDatos;


public class ListadoActivity extends AppCompatActivity {
    private Context ctx;
    private ArrayList<Acta> actas;
    private LayoutInflater lf;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listado);
            GestionDatos gdatos=new GestionDatos(this);
            ListView lista=(ListView)this.findViewById(R.id.lvActas);
            //creamos adaptador del arraylist
            ArrayAdapter<Acta> adapter=new ArrayAdapter<Acta>(this,
                    android.R.layout.simple_list_item_1,
                    gdatos.recuperarActa());
            lista.setAdapter(adapter);
        /*final Intent intent = this.getIntent();
        GestionDatos gdatos = new GestionDatos(this);
        actas = (ArrayList<Acta>) intent.getSerializableExtra("actas");
        ListView lista = (ListView) this.findViewById(R.id.lvActas);
        ArrayAdapter<Acta> adapter = new ArrayAdapter<Acta>(this, android.R.layout.simple_list_item_1, actas);
        //creamos adaptador a partir del arraylist
        //ListAdapter adapter=new ListadoAdapter(this,gdatos.recuperarActa());
        lista.setAdapter(adapter);*/

//----------------------------Metodo clicklistener--------------------------------------------------
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Acta ac = actas.get(position);
                //abre una nueva ventana recuperando el acta en cuestión
                //Intent intent =new Intent(this,MainActivity.class);
                //this.startActivity(intent);

            }
        };
        lista.setOnItemClickListener(listener);
    }



    //--------------------------------Boton Salir-------------------------------------------------------
    public void salir(View v) {
        this.finish();
    }
//--------------------------------------------------------------------------------------------------
}
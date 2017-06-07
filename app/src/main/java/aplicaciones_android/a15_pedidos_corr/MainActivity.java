package aplicaciones_android.a15_pedidos_corr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import javabeans.Acta;
import modelo.GestionDatos;

public class MainActivity extends AppCompatActivity {
    TextView tvFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFecha=(TextView)this.findViewById(R.id.tvFecha);
    }
    public void fecha(View v){
        Calendar cal=Calendar.getInstance();

        //generar cuadro de dialogo de fecha
        DatePickerDialog dgFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //cada vez que se seleccione una fecha se genera una cadena con los datos de la fecha
                //seleccionada
                String fecha = view.getDayOfMonth() + "/" +(view.getMonth()+1)+"/"+ view.getYear();
                tvFecha.setText(fecha);
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dgFecha.show();
        //volcamos la cadena de fecha

    }
    public void guardar(View v){
        EditText edtTitulo=(EditText)this.findViewById(R.id.edtTitulo);
        TextView tvFecha=(TextView)this.findViewById(R.id.tvFecha);
        EditText edtHora=(EditText)this.findViewById(R.id.edtHora);
        EditText edtAsistentes=(EditText)this.findViewById(R.id.edtAsistentes);
        EditText edtRelevo=(EditText)this.findViewById(R.id.edtRelevo);
        EditText edtMemoria=(EditText)this.findViewById(R.id.edtMemoria);
        EditText edtPuntosF=(EditText)this.findViewById(R.id.edtPuntosF);

        EditText edtConclusion=(EditText)this.findViewById(R.id.edtConclusion);
        EditText edtSiguiente=(EditText)this.findViewById(R.id.edtSiguiente);
        EditText edtCompromiso=(EditText)this.findViewById(R.id.edtCompromiso);
        EditText edtPropuesta=(EditText)this.findViewById(R.id.edtPropuesta);
        EditText edtEvaluacion=(EditText)this.findViewById(R.id.edtEvaluacion);
        EditText edtProxima=(EditText)this.findViewById(R.id.edtProxima);


        Acta ac=new Acta(edtTitulo.getText().toString(), tvFecha.getText().toString(),
                         edtHora.getText().toString(),edtAsistentes.getText().toString(),
                        edtRelevo.getText().toString(),edtMemoria.getText().toString(),
                        edtPuntosF.getText().toString(),edtConclusion.getText().toString(),
                        edtSiguiente.getText().toString(),edtCompromiso.getText().toString(),
                        edtPropuesta.getText().toString(),edtEvaluacion.getText().toString(),
                edtProxima.getText().toString());
        //creamos objeto del modelo
        GestionDatos gdatos=new GestionDatos(this);
        gdatos.guardarActa(ac);
    }
    public void listado(View v){
        //mostramos la actividad del listado
        Intent intent =new Intent(this,ListadoActivity.class);
        this.startActivity(intent);
    }
}




package aplicaciones_android.a15_pedidos_corr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;

import javabeans.Acta;
import modelo.GestionDatos;

import static android.R.attr.button;
import static android.R.attr.id;
import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {
    TextView tvFecha;
    TextView tvHora;
    TextView tvProxima;
    Button btFecha , btProxima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFecha=(TextView)this.findViewById(R.id.tvFecha);
        tvHora=(TextView)this.findViewById(R.id.tvHora);
        tvProxima=(TextView)this.findViewById(R.id.tvProxima);
        btFecha=(Button)this.findViewById(R.id.btFecha);
        btProxima=(Button)this.findViewById(R.id.btProxima);
    }

//-------------------------Metodo para mostrar el calendario----------------------------------------
    public void fecha(View v) {
        Calendar cal = Calendar.getInstance();
        //generar cuadro de dialogo de fecha
        DatePickerDialog dgFecha;

        v.getId();

        if (btFecha.equals (v)){
            dgFecha= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
            @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //cada vez que se seleccione una fecha se genera una cadena con los datos de la fecha
                    //seleccionada
                    String fecha = view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear();
                    tvFecha.setText(fecha);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            dgFecha.show();
        }
        if (btProxima.equals (v)){

            //generar cuadro de dialogo de fecha
            dgFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //cada vez que se seleccione una fecha se genera una cadena con los datos de la fecha
                    //seleccionada
                    String fecha = view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear();
                    tvProxima.setText(fecha);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            dgFecha.show();

        }

    }
//--------------------------------------------------------------------------------------------------

//-----------------------------Metodo para la Hora--------------------------------------------------
public void hora(View v){
    Calendar cal= Calendar.getInstance();

    TimePickerDialog tgHora=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hora=hourOfDay+":"+minute;
            tvHora.setText(hora);
        }
    },cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),true);
    tgHora.show();

}


//--------------------------------------------------------------------------------------------------

//------------------------------Metodo que llama a la funcion de guardar----------------------------
    public void guardar(View v){
        //Definimos los objetos que va a necesitar el método
        EditText edtTitulo=(EditText)this.findViewById(R.id.edtTitulo);
        TextView tvFecha=(TextView)this.findViewById(R.id.tvFecha);
        TextView tvHora=(TextView) this.findViewById(R.id.tvHora);
        EditText edtAsistentes=(EditText)this.findViewById(R.id.edtAsistentes);
        EditText edtRelevo=(EditText)this.findViewById(R.id.edtRelevo);
        EditText edtMemoria=(EditText)this.findViewById(R.id.edtMemoria);
        EditText edtPuntosF=(EditText)this.findViewById(R.id.edtPuntosF);
        EditText edtConclusion=(EditText)this.findViewById(R.id.edtConclusion);
        EditText edtSiguiente=(EditText)this.findViewById(R.id.edtSiguiente);
        EditText edtCompromiso=(EditText)this.findViewById(R.id.edtCompromiso);
        EditText edtPropuesta=(EditText)this.findViewById(R.id.edtPropuesta);
        EditText edtEvaluacion=(EditText)this.findViewById(R.id.edtEvaluacion);
        TextView tvProxima=(EditText)this.findViewById(R.id.tvProxima);
        //creamos objeto donde está guardado el método para guardar
        GestionDatos gdatos=new GestionDatos(this);
        //y definimos lo que debe tener
        if(edtTitulo.getText().length()>0 && tvFecha.getText().length()>0 && tvHora.getText().length()>0
           && edtAsistentes.getText().length()>0 && edtRelevo.getText().length()>0 && edtMemoria.getText().length()>0
           && edtPuntosF.getText().length()>0 && edtConclusion.getText().length()>0 && edtSiguiente.getText().length()>0
           && edtCompromiso.getText().length()>0 && edtPropuesta.getText().length()>0 && edtEvaluacion.getText().length()>0
           && tvProxima.getText().length()>0 ){
                Acta ac=new Acta(edtTitulo.getText().toString(), tvFecha.getText().toString(),
                        tvHora.getText().toString(),edtAsistentes.getText().toString(),
                        edtRelevo.getText().toString(),edtMemoria.getText().toString(),
                        edtPuntosF.getText().toString(),edtConclusion.getText().toString(),
                        edtSiguiente.getText().toString(),edtCompromiso.getText().toString(),
                        edtPropuesta.getText().toString(),edtEvaluacion.getText().toString(),
                        tvProxima.getText().toString());
                        this.finish ();
                //comprueba si está repetida el acta
               if(!gdatos.comprobarDatos(ac.getTitulo())){
                    gdatos.guardarActa(ac);
                    Toast.makeText(this, "Acta guardada correctamente", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(this, "Acta ya introducida", Toast.LENGTH_LONG).show();
                }
        }else{
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
//--------------------------------------------------------------------------------------------------

//---------------------------------Metodo que llama a la actividad listado--------------------------
    public void listado(View v){
        //mostramos la actividad del listado
        Intent intent =new Intent(this,ListadoActivity.class);
        this.startActivity(intent);
    }
//--------------------------------------------------------------------------------------------------

//------------------------------Boton Salir---------------------------------------------------------

    public void salir(View v){
        this.finish ();
    }

}




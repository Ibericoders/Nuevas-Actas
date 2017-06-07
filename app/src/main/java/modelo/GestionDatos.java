package modelo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import javabeans.Acta;

/**
 * Created by USUARIO on 31/03/2017.
 */
public class GestionDatos {
    String ruta = "actas.txt";
    Context ctx;

    public GestionDatos(Context ctx) {
        this.ctx = ctx;
    }


    //---------------------------------Metodo guardar---------------------------------------------------
    public void guardarActa(Acta ac) {
        try {
            FileOutputStream fos = ctx.openFileOutput(ruta, Context.MODE_APPEND);
            PrintStream salida = new PrintStream(fos);
            salida.println(ac.getTitulo() + "|" + ac.getFecha() + "|" + ac.getHora() + "|" +
                    ac.getAsistentes() + "|" + ac.getRelevos() + "|" + ac.getMemoria() + "|" + ac.getPuntos()
                    + "|" + ac.getConclusion() + "|" + ac.getSiguiente() + "|" + ac.getCompromisos() + "|" +
                    "|" + ac.getPropuestas() + "|" + ac.getEvaluacion() + "|" + ac.getProxima());


            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//--------------------------------------------------------------------------------------------------

    //-------------------------------Metodo recuperar acta----------------------------------------------
    public ArrayList<Acta> recuperarActa() {
        ArrayList<Acta> actas = new ArrayList<>();
        String s;
        try {
            FileInputStream fis = ctx.openFileInput(ruta);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
            while ((s = bf.readLine()) != null) {
                String[] datos = s.split("[|]");
                Acta ac = new Acta(datos[0], datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6], datos[7], datos[8],
                        datos[9], datos[10], datos[11], datos[12]);
                actas.add(ac);
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return actas;
    }
//--------------------------------------------------------------------------------------------------

//--------------------------Método enviar por correo------------------------------------------------
    public void onClick(View v) {
        // introducimos un array con el correo prefijado ya que solo es para ese
        String[] to = {"ibericoders@gmail.ccm"};
        String[] cc = {""};
        enviar(to, cc, "Hola",
                "Esto es un email enviado desde app de Android");
    }
//--------------------------------------------------------------------------------------------------

//--------------------------Enviar texto en correo--------------------------------------------------
    private void enviar(String[] to, String[] cc,
                        String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        //String[] to = direccionesEmail;
        //String[] cc = copias;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
       // startActivity(Intent.createChooser(emailIntent, "Email "));
    }
//--------------------------------------------------------------------------------------------------
}


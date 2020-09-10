package com.magove.mensaje_de_texto_y_llamadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
RadioButton rb_mensaje,rb_llamada;
EditText txt_numero,txt_mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Llamadas y mensajes");

        rb_llamada=this.findViewById(R.id.id_rbLlamada);
        rb_mensaje=this.findViewById(R.id.id_rbMensaje);
        txt_numero=this.findViewById(R.id.id_txtnumero);
        txt_mensaje=this.findViewById(R.id.id_txtMensaje);


    }


    public void onClick(View view){
        // validamos que radio buton se selecciona de acuerdo a su id
        switch (view.getId()){
            case R.id.id_rbMensaje:

                txt_mensaje.setEnabled(true);
                txt_numero.setEnabled(true);
                    
                // al hacer clic en el radioButton de mensaje pedira permiso para poder enviar los mensajes
                int permiso= ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS);
               // si la variable permiso es diferente de aceptado entonces mostrara un mensaje
                if (permiso!=PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
                    Toast.makeText(this,"Permiso no concedido",Toast.LENGTH_LONG).show();

                }

                break;

            case R.id.id_rbLlamada:
                txt_numero.setEnabled(true);

                break;

        }
    }


    public void realizar (View view){
       String numero,mensaje;
       numero=txt_numero.getText().toString();
       mensaje=txt_mensaje.getText().toString();

        if (rb_mensaje.isChecked()){

           if (!mensaje.isEmpty() && !numero.isEmpty()){

               // creamos instancia para hacer uso de la aplicacion por defecto para mandar mensajes.
                // le pasamos por parametro el numero y el mensaje obtenido de las cajas de texto.

               SmsManager men= SmsManager.getDefault();
               men.sendTextMessage(numero,null,mensaje,null,null);

           }

           }else if (rb_llamada.isChecked()){

            if (!numero.isEmpty()){

                Toast.makeText(this,"llamada",Toast.LENGTH_SHORT).show();
            }
       }
    }

}
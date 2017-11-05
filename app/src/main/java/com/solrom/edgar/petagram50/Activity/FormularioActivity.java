package com.solrom.edgar.petagram50.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.pojo.EnviarCorreo;

import javax.mail.Session;

/**
 * Created by edgarsr on 26/10/17.
 */

public class FormularioActivity extends AppCompatActivity{

    Session session = null;
    ProgressDialog pDialog = null;
    TextView tvNombre;
    TextView tvEmail;
    TextView tvMensaje;
    String nombreContacto;
    String email;
    String mensaje;
    Button btnEnviarComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarComponentes();
    }


    private void inicializarComponentes()
    {
        tvNombre = (TextView)findViewById(R.id.txtNombreContacto);
        tvEmail = (TextView)findViewById(R.id.txtEmailContacto);
        tvMensaje = (TextView)findViewById(R.id.txtMensajeContacto);
        btnEnviarComentario = (Button)findViewById(R.id.btnEnviarComentario);
    }



    public void enviarComentario(View v) {
        nombreContacto = tvNombre.getText().toString();
        email = tvEmail.getText().toString();
        mensaje = tvMensaje.getText().toString();

        validaCampos();

        EnviarCorreo ec = new EnviarCorreo(this , email , nombreContacto , mensaje);
        ec.execute();
    }



    private void limpiaCampos()
    {
        tvNombre.setText("");
        tvEmail.setText("");
        tvMensaje.setText("");
    }





    private void validaCampos()
    {
        if(nombreContacto.equals(""))
        {
            Toast.makeText(this, getResources().getString(R.string.falta_nombre_correo), Toast.LENGTH_SHORT).show();
        }

        if(email.equals(""))
        {
            Toast.makeText(this, getResources().getString(R.string.falta_email_correo), Toast.LENGTH_SHORT).show();
        }
    }
}

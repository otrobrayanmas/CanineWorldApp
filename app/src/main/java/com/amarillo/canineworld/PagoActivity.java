package com.amarillo.canineworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PagoActivity extends AppCompatActivity {
    Button btnWhatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        btnWhatsapp=findViewById(R.id.wasa);

        ImageButton back = (ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Button btn1 = (Button)findViewById(R.id.agregartarjeta);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PagoActivity.this, AgregarTarjetaActivity.class));
            }
        });

        Button btn2 = (Button)findViewById(R.id.continuar);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PagoActivity.this, PedidoActivity.class));
            }
        });

        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String uri= "whatsapp://send?phone="+"+573214942093";
                sendIntent.setData(Uri.parse(uri));
                startActivity(sendIntent);
            }
        });


    }
}
package com.amarillo.canineworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView btn1 = (TextView) findViewById(R.id.TextInicio);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, HomeActivity.class));
            }
        });

        TextView btn2 = (TextView) findViewById(R.id.TextPerfil);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PerfilActivity.class));
            }
        });

        TextView btn3 = (TextView) findViewById(R.id.TextCarrito);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CarritoActivity.class));
            }
        });

        TextView btn4 = (TextView) findViewById(R.id.TextFavoritos);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CategoriaActivity.class));
            }
        });

        TextView btn5 = (TextView) findViewById(R.id.TextPedidos);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MisPedidosActivity.class));
            }
        });

        TextView btn6 = (TextView) findViewById(R.id.TextIdioma);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, IdiomaActivity.class));
            }
        });

        TextView btn7 = (TextView) findViewById(R.id.TextCerrarSesión);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, prueba.class));
            }
        });

        TextView btn8 = (TextView) findViewById(R.id.TextCerrarSesión1);

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuActivity.this, MainActivity.class ));
            }
        });

    }
}
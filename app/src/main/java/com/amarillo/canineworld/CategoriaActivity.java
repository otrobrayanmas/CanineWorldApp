package com.amarillo.canineworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        ImageButton back = (ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageButton btn2 = (ImageButton)findViewById(R.id.notifications);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriaActivity.this, NotificacionesActivity.class));
            }
        });

        ImageButton art = (ImageButton)findViewById(R.id.art1);

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriaActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton art2 = (ImageButton)findViewById(R.id.art2);

        art2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriaActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton art3 = (ImageButton)findViewById(R.id.art3);

        art3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriaActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton art4 = (ImageButton)findViewById(R.id.art4);

        art4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriaActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton btm = (ImageButton)findViewById(R.id.filters);

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriaActivity.this, FiltrosActivity.class));
            }
        });



    }
}
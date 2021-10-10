package com.amarillo.canineworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class IdiomaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);

        ImageButton back = (ImageButton)findViewById(R.id.cerrar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView art = (TextView)findViewById(R.id.i1);

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdiomaActivity.this, MenuActivity.class));
            }
        });

        TextView art2 = (TextView)findViewById(R.id.i2);

        art2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdiomaActivity.this, MenuActivity.class));
            }
        });

        TextView art3 = (TextView)findViewById(R.id.i3);

        art3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdiomaActivity.this, MenuActivity.class));
            }
        });

        TextView art4 = (TextView)findViewById(R.id.i4);

        art4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdiomaActivity.this, MenuActivity.class));
            }
        });
    }
}